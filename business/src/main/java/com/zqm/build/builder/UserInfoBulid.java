///**
// * Copyright (C) 2006-2019 Tuniu All rights reserved
// */
//package com.zqm.build.builder;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.tuniu.mob.dist.client.OaClient;
//import com.tuniu.mob.dist.client.constant.OaQuerySalesTypeEnum;
//import com.tuniu.mob.dist.client.vo.OaQuerySalesResponse;
//import com.tuniu.mob.dist.dao.entity.TUserInfo;
//
///**
// * TODO: description
// * Date: 2019-07-25
// *
// * @author zhengpeng
// */
//@Component
//public class UserInfoBulid {
//
//    @Autowired
//    private OaClient oaClient;
//
//    /**
//     * 根据oaName补充用户信息
//     * @return
//     */
//    public BuildDecorator<TUserInfo, String> bulidByOaName() {
//        return (userInfo, s) -> {
//            OaQuerySalesResponse response = oaClient.getEmployeesInfo(OaQuerySalesTypeEnum.SPELLING.getType(), s.toLowerCase());
//            if (response != null) {
//                Object obj = response.getData().getSales().get(s.toLowerCase());
//                OaQuerySalesResponse.Employee employee = JSON.parseObject(JSON.toJSONString(obj), OaQuerySalesResponse.Employee.class);
//                if (employee != null) {
//                    userInfo.setUid(employee.getSalerId());
//                    userInfo.setOwnerName(employee.getName());
//                }
//            }
//        };
//    }
//
//}
