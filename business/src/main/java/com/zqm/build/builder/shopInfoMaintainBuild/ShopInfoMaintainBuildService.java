///**
// * Copyright (C) 2006-2019 Tuniu All rights reserved
// */
//package com.zqm.build.builder.shopInfoMaintainBuild;
//
//import java.util.List;
//
//import org.springframework.util.Base64Utils;
//import org.springframework.util.StringUtils;
//
//import com.tuniu.mob.boot.http.exception.InvalidParamException;
//import com.tuniu.mob.dist.common.builder.BuildDecorator;
//import com.tuniu.mob.dist.common.constant.OperationChannelEnum;
//import com.tuniu.mob.dist.common.constant.UserTypeEnum;
//import com.tuniu.mob.dist.common.utils.ShadowUtils;
//import com.tuniu.mob.dist.dao.entity.THistoryPValue;
//import com.tuniu.mob.dist.dao.entity.TUserInfo;
//
///**
// * TODO: description
// * Date: 2019-08-06
// *
// * @author zhengpeng
// */
//public interface ShopInfoMaintainBuildService<ShopInfoMaintainRequest, ShopInfoMaintainResponse> {
//
//    int getType();
//
//    BuildDecorator<ShopInfoMaintainRequest, ShopInfoMaintainResponse> checkRequest();
//
//    void handelEmpty(ShopInfoMaintainRequest request, int tuniuId);
//
//    void handelNotEmpty(ShopInfoMaintainRequest request, List<THistoryPValue> historyPValueList, int tuniuId);
//
//    default TUserInfo buildUserInfo(com.tuniu.mob.dist.thrift.model.ShopInfoMaintainRequest request, int tuniuId) {
//        TUserInfo userInfo = new TUserInfo();
//        userInfo.setTuniuId(tuniuId);
//        userInfo.setpValue(request.getPValue());
//        if (!StringUtils.isEmpty(request.getName())) {
//            userInfo.setNickName(Base64Utils.encodeToString(request.getName().getBytes()));
//        }
//        userInfo.setOwnerName(request.getName());
//        userInfo.setUserType(UserTypeEnum.DISTRIBUTION.getUserType());
//        //对手机号码进行AES加密
//        String tel = request.getTel();
//        try {
//            if (!StringUtils.isEmpty(tel)) {
//                userInfo.setOwnerPhone(ShadowUtils.encrypt2Hex(tel));
//            }
//        } catch (InvalidParamException e) {
//        }
//        userInfo.setChannel(OperationChannelEnum.COOPERATION_SHOP.getChannel());
//        return userInfo;
//    }
//}
