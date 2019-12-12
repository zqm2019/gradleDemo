///**
// * Copyright (C) 2006-2019 Tuniu All rights reserved
// */
//package com.zqm.build.builder.queryMoreBindBuild;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import com.google.common.collect.Lists;
//import com.tuniu.mob.boot.http.exception.InvalidParamException;
//import com.tuniu.mob.dist.common.utils.ShadowUtils;
//import com.tuniu.mob.dist.service.help.UserInfoHelp;
//import com.tuniu.mob.dist.vo.shop.QueryMoreBindRequestVO;
//import com.tuniu.mob.dist.vo.shop.QueryMoreBindResponseVO;
//import com.tuniu.mob.dist.vo.user.OrderCustDO;
//
///**
// * description: 根据会员号查询绑定关系
// * Date: 2019-10-30
// *
// * @author zhengpeng
// */
//@Component
//public class UserBindMoreWxBuild implements IQueryMoreBindDecorator {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserBindMoreWxBuild.class);
//
//    @Autowired
//    private UserInfoHelp userInfoHelp;
//
//    @Override
//    public void build(QueryMoreBindRequestVO queryMoreBindRequestVO, QueryMoreBindResponseVO responseVO) {
//        List<QueryMoreBindResponseVO.UserBindMoreWx> userBindMoreWxes = Lists.newArrayList();
//        responseVO.setUserBindMoreWx(userBindMoreWxes);
//        Integer tuniuId = queryMoreBindRequestVO.getTuniuId();
//        // 查询头像和昵称
//        List<OrderCustDO> orderCustDOS = userInfoHelp.getWxUserNicknameAndHeadUrls(tuniuId);
//        if (!CollectionUtils.isEmpty(orderCustDOS)) {
//            responseVO.setUserBindWxFlag(true);
//            userBindMoreWxes.addAll(orderCustDOS.stream().map(orderCustDO -> {
//                QueryMoreBindResponseVO.UserBindMoreWx userBindMoreWx = new QueryMoreBindResponseVO.UserBindMoreWx();
//                BeanUtils.copyProperties(orderCustDO, userBindMoreWx);
//                if (!StringUtils.isEmpty(orderCustDO.getUnionId())) {
//                    try {
//                        userBindMoreWx.setUnionIdEnc(ShadowUtils.encrypt2Hex(orderCustDO.getUnionId()));
//                    } catch (InvalidParamException e) {
//                        LOGGER.info("加密unionId:{}失败", orderCustDO.getUnionId(), e);
//                    }
//                }
//
//                return userBindMoreWx;
//            }).collect(Collectors.toList()));
//        }
//    }
//}
