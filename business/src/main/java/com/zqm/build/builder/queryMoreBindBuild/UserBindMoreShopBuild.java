///**
// * Copyright (C) 2006-2019 Tuniu All rights reserved
// */
//package com.zqm.build.builder.queryMoreBindBuild;
//
//import java.util.List;
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
//import com.tuniu.mob.dist.service.NtkShopService;
//import com.tuniu.mob.dist.service.ShopService;
//import com.tuniu.mob.dist.vo.shop.QueryMoreBindRequestVO;
//import com.tuniu.mob.dist.vo.shop.QueryMoreBindResponseVO;
//
///**
// * description: 根据会员id查店铺  一人多p
// * Date: 2019-10-30
// *
// * @author zhengpeng
// */
//@Component
//public class UserBindMoreShopBuild implements IQueryMoreBindDecorator{
//
//
//    @Autowired
//    private NtkShopService ntkShopService;
//
//    @Autowired
//    private ShopService shopService;
//
//    @Override
//    public void build(QueryMoreBindRequestVO queryMoreBindRequestVO, QueryMoreBindResponseVO responseVO) {
//        List<QueryMoreBindResponseVO.UserBindMoreShop> userBindMoreShops = Lists.newArrayList();
//        responseVO.setUserBindMoreShop(userBindMoreShops);
//        Integer tuniuId = queryMoreBindRequestVO.getTuniuId();
//        String pValue = queryMoreBindRequestVO.getPValue();
//        List<TShopInfo> tShopInfosByTuniuId = shopService.queryShopInfo(null, tuniuId);
//        List<TNtkShop> tNtkShopsByTuniuId = ntkShopService.queryNtkInfo(null, tuniuId);
//        if ((tShopInfosByTuniuId.size() + tNtkShopsByTuniuId.size()) > 1) {
//            responseVO.setUserBindMoreShopFalg(true);
//
//            userBindMoreShops.addAll(tShopInfosByTuniuId.stream().map(tShopInfo -> {
//                QueryMoreBindResponseVO.UserBindMoreShop userBindMoreShop = new QueryMoreBindResponseVO.UserBindMoreShop();
//                userBindMoreShop.setLoginFalg(pValue.equals(tShopInfo.getOwnerPValue()));
//                userBindMoreShop.setOpenTime(DateUtil.formatDateTime(tShopInfo.getEffectiveTime()));
//                userBindMoreShop.setPValue(tShopInfo.getOwnerPValue());
//                userBindMoreShop.setShopTypeName(CommonConstant.SHOP_TYPE_NAME);
//                return userBindMoreShop;
//            }).collect(Collectors.toList()));
//
//            userBindMoreShops.addAll(tNtkShopsByTuniuId.stream().map(tNtkShop -> {
//                QueryMoreBindResponseVO.UserBindMoreShop userBindMoreShop = new QueryMoreBindResponseVO.UserBindMoreShop();
//                userBindMoreShop.setLoginFalg(pValue.equals(tNtkShop.getpValue()));
//                userBindMoreShop.setOpenTime(DateUtil.formatDateTime(tNtkShop.getOpenTime()));
//                userBindMoreShop.setPValue(tNtkShop.getpValue());
//                userBindMoreShop.setShopTypeName(tNtkShop.getOwnerType() == NtkShopOwnerTypeEnum.NO_SALES_MAN.getType() ? CommonConstant.NOT_STAFF_TYPE_NAME : CommonConstant.STAFF_SHOP_TYPE_NAME);
//                return userBindMoreShop;
//            }).collect(Collectors.toList()));
//        }
//    }
//}
