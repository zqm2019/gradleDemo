///**
// * Copyright (C) 2006-2019 Tuniu All rights reserved
// */
//package com.zqm.build.builder.shopInfoMaintainBuild;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import com.tuniu.mob.dist.common.builder.BuildDecorator;
//import com.tuniu.mob.dist.common.constant.PebbleShopInfoMaintainExceptionEnum;
//import com.tuniu.mob.dist.common.constant.UserTypeEnum;
//import com.tuniu.mob.dist.common.constant.ValidStateEnum;
//import com.tuniu.mob.dist.dao.entity.THistoryPValue;
//import com.tuniu.mob.dist.dao.entity.THistoryPValueExample;
//import com.tuniu.mob.dist.dao.entity.TShopInfo;
//import com.tuniu.mob.dist.dao.entity.TShopInfoExample;
//import com.tuniu.mob.dist.dao.mapper.THistoryPValueMapper;
//import com.tuniu.mob.dist.dao.mapper.TShopInfoMapper;
//import com.tuniu.mob.dist.service.pvalue.PValueService;
//import com.tuniu.mob.dist.thrift.model.ShopInfoMaintainRequest;
//import com.tuniu.mob.dist.thrift.model.ShopInfoMaintainResponse;
//
///**
// * TODO: description
// * Date: 2019-08-06
// *
// * @author zhengpeng
// */
//
//@Service
//public class ShopInfoMaintainTypeThreeServiceImpl implements ShopInfoMaintainBuildService<ShopInfoMaintainRequest, ShopInfoMaintainResponse> {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ShopInfoMaintainTypeThreeServiceImpl.class);
//
//    @Autowired
//    private PValueService pValueService;
//
//    @Autowired
//    private TShopInfoMapper shopInfoMapper;
//
//    @Autowired
//    private THistoryPValueMapper historyPValueMapper;
//
//    @Override
//    public int getType() {
//        return 3;
//    }
//
//    @Override
//    public BuildDecorator<ShopInfoMaintainRequest, ShopInfoMaintainResponse> checkRequest() {
//        return (request, shopInfoMaintainResponse) -> {
//            List<THistoryPValue> list = gettHistoryPValues(request);
//            if (CollectionUtils.isEmpty(list)) {
//                // 店铺不存在
//                shopInfoMaintainResponse.setErrorCode(PebbleShopInfoMaintainExceptionEnum.NOT_EXiST_P_VALUE.getErrorCode());
//                shopInfoMaintainResponse.setMessage(PebbleShopInfoMaintainExceptionEnum.NOT_EXiST_P_VALUE.getErrorMsg());
//            }
//        };
//    }
//
//    private List<THistoryPValue> gettHistoryPValues(ShopInfoMaintainRequest request) {
//        THistoryPValueExample example = new THistoryPValueExample();
//        example.createCriteria().andPValueEqualTo(request.getPValue()).andValidStateEqualTo(ValidStateEnum.VALID.getState())
//                .andPTypeEqualTo(UserTypeEnum.DISTRIBUTION.getUserType());
//        return historyPValueMapper.selectByExample(example);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void handelEmpty(ShopInfoMaintainRequest request, int tuniuId) {
//        List<THistoryPValue> list = gettHistoryPValues(request);
//        THistoryPValue tHistoryPValue = list.get(0);
//        // 删除店铺
//        TShopInfoExample example = new TShopInfoExample();
//        example.createCriteria().andOwnerPValueEqualTo(request.getPValue());
//        LOGGER.info("删除店铺P:{}", request.getPValue());
//        shopInfoMapper.deleteByExample(example);
//        // 更新历史p
//        pValueService.operateHistoryByShopInfo(new TShopInfo(), 2, tHistoryPValue.getTuniuId(), tHistoryPValue.getpValue(), (byte) 2);
//    }
//
//    @Override
//    public void handelNotEmpty(ShopInfoMaintainRequest request, List<THistoryPValue> historyPValueList, int tuniuId) {
//
//    }
//}
