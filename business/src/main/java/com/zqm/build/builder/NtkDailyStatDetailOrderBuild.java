///**
// * Copyright (C) 2006-2019 Tuniu All rights reserved
// */
//package com.zqm.build.builder;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.tuniu.bi.uis.ntk.thrift.model.NtkDailyStatDetailOrder;
//import com.tuniu.mob.dist.common.utils.DateUtil;
//import com.tuniu.mob.dist.dao.entity.TTkOrder;
//import com.tuniu.mob.dist.service.tkCust.TkCustService;
//
///**
// * TODO: description
// * Date: 2019-09-17
// *
// * @author zhengpeng
// */
//
//@Component
//public class NtkDailyStatDetailOrderBuild {
//
//    // 零售orderType
//    private static List<Integer> rtlList = Arrays.asList(70, 78, 100);
//
//    private static final String DATA_SOURCE_RETAIL = "rtl";//数据来源:零售
//
//    @Autowired
//    private TkCustService tkCustService;
//
//
//    public BuildDecorator<TTkOrder, NtkDailyStatDetailOrder> orderBuild() {
//        return (tTkOrder, order) -> {
//            order.setOrderType(tTkOrder.getOrderType());
//            order.setPval(tTkOrder.getpValue());
//            order.setOrderId(tTkOrder.getOrderId());
//            if (tTkOrder.getSignPrice() != null) {
//                order.setTotalPrice(tTkOrder.getSignPrice().doubleValue());
//            }
//            order.setProductId(tTkOrder.getProductId());
//            order.setClassBrandId(tTkOrder.getProductClassId());
//            order.setChildClassBrandId(tTkOrder.getProductChildClassId());
//            order.setProductName(tTkOrder.getProductName());
//            if (rtlList.contains(tTkOrder.getOrderType())) {
//                order.setChannelSource(DATA_SOURCE_RETAIL);
//            }
//            if (tTkOrder.getAdultNum() != null) {
//                order.setAdultNum(tTkOrder.getAdultNum());
//            }
//            if (tTkOrder.getChildrenNum() != null) {
//                order.setChildrenNum(tTkOrder.getChildrenNum());
//            }
//            if (tTkOrder.getAddTime() != null) {
//                order.setAddTime(DateUtil.formatDateTime(tTkOrder.getAddTime()));
//            }
//            if (tTkOrder.getSignTime() != null) {
//                order.setSignTime(DateUtil.formatDateTime(tTkOrder.getSignTime()));
//            }
//            if (tTkOrder.getBackTime() != null) {
//                order.setBackTime(DateUtil.formatDateTime(tTkOrder.getBackTime()));
//            }
//            order.setStatDate(DateUtil.formatDate(new Date()));
//            order.setCustId(tTkOrder.getUserId());
//            // 从拉新表里查找锁粉p
//            if (tTkOrder.getUserId() != null) {
//                order.setRgPvalue(tkCustService.selectByCustId(tTkOrder.getUserId()));
//            }
//        };
//    }
//
//
//}
