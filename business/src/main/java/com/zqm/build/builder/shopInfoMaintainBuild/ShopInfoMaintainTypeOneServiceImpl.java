///**
// * Copyright (C) 2006-2019 Tuniu All rights reserved
// */
//package com.zqm.build.builder.shopInfoMaintainBuild;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import com.tuniu.mob.dist.common.builder.BuildDecorator;
//import com.tuniu.mob.dist.common.constant.OperationChannelEnum;
//import com.tuniu.mob.dist.common.constant.PebbleShopInfoMaintainExceptionEnum;
//import com.tuniu.mob.dist.common.constant.UserTypeEnum;
//import com.tuniu.mob.dist.common.constant.ValidStateEnum;
//import com.tuniu.mob.dist.dao.entity.THistoryPValue;
//import com.tuniu.mob.dist.dao.entity.THistoryPValueExample;
//import com.tuniu.mob.dist.dao.entity.TNtkShop;
//import com.tuniu.mob.dist.dao.entity.TShopInfo;
//import com.tuniu.mob.dist.dao.entity.TShopInfoExample;
//import com.tuniu.mob.dist.dao.mapper.THistoryPValueMapper;
//import com.tuniu.mob.dist.dao.mapper.TNtkShopMapper;
//import com.tuniu.mob.dist.dao.mapper.TShopInfoMapper;
//import com.tuniu.mob.dist.dao.mapper.TUserInfoMapper;
//import com.tuniu.mob.dist.service.ShopService;
//import com.tuniu.mob.dist.service.UserService;
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
//public class ShopInfoMaintainTypeOneServiceImpl implements ShopInfoMaintainBuildService<ShopInfoMaintainRequest, ShopInfoMaintainResponse> {
//
//    @Autowired
//    private ShopService shopService;
//
//    @Autowired
//    private PValueService pValueService;
//
//    @Autowired
//    private TShopInfoMapper shopInfoMapper;
//
//    @Autowired
//    private TUserInfoMapper tUserInfoMapper;
//
//    @Autowired
//    private TNtkShopMapper ntkShopMapper;
//
//    @Autowired
//    private THistoryPValueMapper historyPValueMapper;
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public int getType() {
//        return 1;
//    }
//
//    @Override
//    public BuildDecorator<ShopInfoMaintainRequest, ShopInfoMaintainResponse> checkRequest() {
//        return (request, shopInfoMaintainResponse) -> {
//            if (StringUtils.isEmpty(request.getTel())) {
//                shopInfoMaintainResponse.setErrorCode(PebbleShopInfoMaintainExceptionEnum.ERROR_TEL.getErrorCode());
//                shopInfoMaintainResponse.setMessage(PebbleShopInfoMaintainExceptionEnum.ERROR_TEL.getErrorMsg());
//            } else if (StringUtils.isEmpty(request.getParentPValue())) {
//                shopInfoMaintainResponse.setErrorCode(PebbleShopInfoMaintainExceptionEnum.ERROR_PARENT_P_VALUE.getErrorCode());
//                shopInfoMaintainResponse.setMessage(PebbleShopInfoMaintainExceptionEnum.ERROR_PARENT_P_VALUE.getErrorMsg());
//            } else if (StringUtils.isEmpty(request.getName())) {
//                shopInfoMaintainResponse.setErrorCode(PebbleShopInfoMaintainExceptionEnum.ERROR_NAME.getErrorCode());
//                shopInfoMaintainResponse.setMessage(PebbleShopInfoMaintainExceptionEnum.ERROR_NAME.getErrorMsg());
//            } else {
//                THistoryPValueExample example = new THistoryPValueExample();
//                example.createCriteria().andPValueEqualTo(request.getPValue()).andValidStateEqualTo(ValidStateEnum.VALID.getState());
//                List<THistoryPValue> list = historyPValueMapper.selectByExample(example);
//                if (!CollectionUtils.isEmpty(list)) {
//                    // p1存在
//                    shopInfoMaintainResponse.setErrorCode(PebbleShopInfoMaintainExceptionEnum.EXiST_P_VALUE.getErrorCode());
//                    shopInfoMaintainResponse.setMessage(PebbleShopInfoMaintainExceptionEnum.EXiST_P_VALUE.getErrorMsg());
//                }
//            }
//        };
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void handelEmpty(ShopInfoMaintainRequest request, int tuniuId) {
//        // 新增或修改用户信息
//        userService.updateUserInfoByTuniuId(buildUserInfo(request, tuniuId));
//        TShopInfo tShopInfo = new TShopInfo();
//        // 新增店铺
//        shopService.addShopInfo(request.getPValue(), request.getParentPValue(), tuniuId, tShopInfo);
//        // 插入p值表
//        pValueService.operateHistoryByShopInfo(tShopInfo, 1, (byte) 1);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void handelNotEmpty(ShopInfoMaintainRequest request, List<THistoryPValue> historyPValueList, int tuniuId) {
//        // tuniuId对应的店铺存在 可能是多个
//        // 修改店铺p值 修改p值表置为无效p p值表插入
//        // 是否存在外部店铺标志
//        // 新增或修改用户信息
//        userService.updateUserInfoByTuniuId(buildUserInfo(request, tuniuId));
//        TShopInfo tShopInfo = new TShopInfo();
//        boolean hasShop = historyPValueList.stream().anyMatch(historyPValue -> UserTypeEnum.DISTRIBUTION.getUserType() == historyPValue.getpType());
//        historyPValueList.forEach(historyPValue -> {
//            if (UserTypeEnum.DISTRIBUTION.getUserType() == historyPValue.getpType()) {
//                // 外部店铺
//                TShopInfo record = new TShopInfo();
//                record.setOwnerPValue(request.getPValue());
//                record.setParentPValue(request.getParentPValue());
//                record.setOperationChannel(OperationChannelEnum.COOPERATION_SHOP.getChannel());
//                TShopInfoExample example = new TShopInfoExample();
//                example.createCriteria().andTuniuIdEqualTo(tuniuId);
//                shopInfoMapper.updateByExampleSelective(record, example);
//                // p值表插入
//                tShopInfo.setOwnerPValue(request.getPValue());
//                tShopInfo.setTuniuId(tuniuId);
//                tShopInfo.setOperationChannel(OperationChannelEnum.COOPERATION_SHOP.getChannel());
//                pValueService.operateHistoryByShopInfo(tShopInfo, 1, (byte) 1);
//                // 修改p值表置为无效p
//                TShopInfo temp = new TShopInfo();
//                temp.setTuniuId(historyPValue.getTuniuId());
//                temp.setOwnerPValue(historyPValue.getpValue());
//                pValueService.operateHistoryByShopInfo(temp, 2, (byte) 2);
//            } else {
//                // 内部店铺
//                // 1.新增外部店铺 , 如果他有外部店铺就不需要新增,只需要修改就完事了
//                if (!hasShop) {
//                    shopService.addShopInfo(request.getPValue(), request.getParentPValue(), tuniuId, tShopInfo);
//                }
//                // 2.删除内部店铺
////                TNtkShopExample ntkShopExample = new TNtkShopExample();
////                ntkShopExample.createCriteria().andTuniuIdEqualTo(historyPValue.getTuniuId()).andPValueEqualTo(historyPValue.getpValue());
////                ntkShopMapper.deleteByExample(ntkShopExample);
//                // 3.p值表插入
//                TShopInfo shopInfo = new TShopInfo();
//                shopInfo.setOwnerPValue(request.getPValue());
//                shopInfo.setTuniuId(tuniuId);
//                shopInfo.setOperationChannel(OperationChannelEnum.COOPERATION_SHOP.getChannel());
//                pValueService.operateHistoryByShopInfo(shopInfo, 1, (byte) 1);
//                // 4.修改p值表置为无效p
//                TNtkShop temp = new TNtkShop();
//                temp.setTuniuId(historyPValue.getTuniuId());
//                temp.setpValue(historyPValue.getpValue());
//                pValueService.operateHistoryByNtkshop(temp, 2, (byte) 2);
//            }
//        });
//    }
//}
