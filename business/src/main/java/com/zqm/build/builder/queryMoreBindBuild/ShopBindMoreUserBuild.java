///**
// * Copyright (C) 2006-2019 Tuniu All rights reserved
// */
//package com.zqm.build.builder.queryMoreBindBuild;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.google.common.collect.Lists;
//import com.tuniu.mob.dist.common.constant.CommonConstant;
//import com.tuniu.mob.dist.common.constant.NtkShopOwnerTypeEnum;
//import com.tuniu.mob.dist.common.utils.DateUtil;
//import com.tuniu.mob.dist.dao.entity.TNtkShop;
//import com.tuniu.mob.dist.dao.entity.TShopInfo;
//import com.tuniu.mob.dist.dao.entity.TUserInfo;
//import com.tuniu.mob.dist.service.NtkShopService;
//import com.tuniu.mob.dist.service.ShopService;
//import com.tuniu.mob.dist.service.UserService;
//import com.tuniu.mob.dist.service.user.NewUserService;
//import com.tuniu.mob.dist.vo.shop.QueryMoreBindRequestVO;
//import com.tuniu.mob.dist.vo.shop.QueryMoreBindResponseVO;
//
///**
// * description: 根据p值查店铺  一p多人
// * Date: 2019-10-30
// *
// * @author zhengpeng
// */
//@Component
//public class ShopBindMoreUserBuild implements IQueryMoreBindDecorator {
//
//    @Autowired
//    private NtkShopService ntkShopService;
//
//    @Autowired
//    private NewUserService newUserService;
//
//    @Autowired
//    private ShopService shopService;
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void build(QueryMoreBindRequestVO queryMoreBindRequestVO, QueryMoreBindResponseVO responseVO) {
//        List<QueryMoreBindResponseVO.ShopBindMoreUser> shopBindMoreUsers = Lists.newArrayList();
//        responseVO.setShopBindMoreUser(shopBindMoreUsers);
//        Integer tuniuId = queryMoreBindRequestVO.getTuniuId();
//        String pValue = queryMoreBindRequestVO.getPValue();
//        List<TShopInfo> tShopInfosByP = shopService.queryShopInfo(pValue, null);
//        List<TNtkShop> tNtkShopsByP = ntkShopService.queryNtkInfo(pValue, null);
//        // 根据会员id查绑定关系
//        if ((tShopInfosByP.size() + tNtkShopsByP.size()) > 1) {
//            responseVO.setShopBindMoreUserFlag(true);
//            shopBindMoreUsers.addAll(tShopInfosByP.stream().map(tShopInfo -> {
//                QueryMoreBindResponseVO.ShopBindMoreUser shopBindMoreUser = new QueryMoreBindResponseVO.ShopBindMoreUser();
//                shopBindMoreUser.setLoginFalg(tuniuId.equals(tShopInfo.getTuniuId()));
//                shopBindMoreUser.setOpenTime(DateUtil.formatDateTime(tShopInfo.getEffectiveTime()));
//                shopBindMoreUser.setShopTypeName(CommonConstant.SHOP_TYPE_NAME);
//                shopBindMoreUser.setTuniuId(String.valueOf(tShopInfo.getTuniuId()));
//                shopBindMoreUser.setTuniuPhone(newUserService.getUserTel(tShopInfo.getTuniuId(), false));
//                shopBindMoreUser.setName(Optional.ofNullable(userService.findByUserId(tShopInfo.getTuniuId())).map(TUserInfo::getOwnerName).orElse(null));
//                return shopBindMoreUser;
//            }).collect(Collectors.toList()));
//
//            shopBindMoreUsers.addAll(tNtkShopsByP.stream().map(tNtkShop -> {
//                QueryMoreBindResponseVO.ShopBindMoreUser shopBindMoreUser = new QueryMoreBindResponseVO.ShopBindMoreUser();
//                shopBindMoreUser.setLoginFalg(tuniuId.equals(tNtkShop.getTuniuId()));
//                shopBindMoreUser.setOpenTime(DateUtil.formatDateTime(tNtkShop.getOpenTime()));
//                shopBindMoreUser.setShopTypeName(tNtkShop.getOwnerType() == NtkShopOwnerTypeEnum.NO_SALES_MAN.getType() ? CommonConstant.NOT_STAFF_TYPE_NAME : CommonConstant.STAFF_SHOP_TYPE_NAME);
//                shopBindMoreUser.setTuniuId(String.valueOf(tNtkShop.getTuniuId()));
//                shopBindMoreUser.setTuniuPhone(newUserService.getUserTel(tNtkShop.getTuniuId(), false));
//                shopBindMoreUser.setName(Optional.ofNullable(userService.findByUserId(tNtkShop.getTuniuId())).map(TUserInfo::getOwnerName).orElse(null));
//                return shopBindMoreUser;
//            }).collect(Collectors.toList()));
//        }
//    }
//}
