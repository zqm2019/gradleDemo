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
//import com.tuniu.mob.dist.common.constant.PebbleShopInfoMaintainExceptionEnum;
//import com.tuniu.mob.dist.common.constant.UserTypeEnum;
//import com.tuniu.mob.dist.common.constant.ValidStateEnum;
//import com.tuniu.mob.dist.dao.entity.THistoryPValue;
//import com.tuniu.mob.dist.dao.entity.THistoryPValueExample;
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
//@Service
//public class ShopInfoMaintainTypeTwoServiceImpl implements ShopInfoMaintainBuildService<ShopInfoMaintainRequest, ShopInfoMaintainResponse> {
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
//        return 2;
//    }
//
//    @Override
//    public BuildDecorator<ShopInfoMaintainRequest, ShopInfoMaintainResponse> checkRequest() {
//        return (request, shopInfoMaintainResponse) -> {
//            if (StringUtils.isEmpty(request.getTel())) {
//                shopInfoMaintainResponse.setErrorCode(PebbleShopInfoMaintainExceptionEnum.ERROR_TEL.getErrorCode());
//                shopInfoMaintainResponse.setMessage(PebbleShopInfoMaintainExceptionEnum.ERROR_TEL.getErrorMsg());
//                return;
//            }
//            List<THistoryPValue> list = gettHistoryPValues(request);
//            if (CollectionUtils.isEmpty(list)) {
//                // p1不存在存在
//                shopInfoMaintainResponse.setErrorCode(PebbleShopInfoMaintainExceptionEnum.NOT_EXiST_P_VALUE.getErrorCode());
//                shopInfoMaintainResponse.setMessage(PebbleShopInfoMaintainExceptionEnum.NOT_EXiST_P_VALUE.getErrorMsg());
//
//            } else {
//                THistoryPValue historyPValueP1 = list.get(0);
//                if (UserTypeEnum.DISTRIBUTION.getUserType() != historyPValueP1.getpType()) {
//                    shopInfoMaintainResponse.setErrorCode(PebbleShopInfoMaintainExceptionEnum.NOT_SHOP_P_VALUE.getErrorCode());
//                    shopInfoMaintainResponse.setMessage(PebbleShopInfoMaintainExceptionEnum.NOT_SHOP_P_VALUE.getErrorMsg());
//                }
//            }
//        };
//    }
//
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void handelEmpty(ShopInfoMaintainRequest shopInfoMaintainRequest, int tuniuId) {
//        // 新增或修改用户信息
//        userService.updateUserInfoByTuniuId(buildUserInfo(shopInfoMaintainRequest, tuniuId));
//        // 会员id关联到p1
//        List<THistoryPValue> historyPValueList = gettHistoryPValues(shopInfoMaintainRequest);
//        THistoryPValue historyPValueP1 = historyPValueList.get(0);
//        historyPValueP1.setTuniuId(tuniuId);
//        historyPValueMapper.updateByPrimaryKey(historyPValueP1);
//        // shop表也变 p值换tuniuId
//        updateShopTuniuId(shopInfoMaintainRequest, tuniuId);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void handelNotEmpty(ShopInfoMaintainRequest request, List<THistoryPValue> historyPValueList, int tuniuId) {
//        // 新增或修改用户信息
//        userService.updateUserInfoByTuniuId(buildUserInfo(request, tuniuId));
//        List<THistoryPValue> historyPValues = gettHistoryPValues(request);
//        THistoryPValue historyPValueP1 = historyPValues.get(0);
//        // 会员也有店铺p2
//        THistoryPValue historyPValueP2 = historyPValueList.get(0);
//        // 如果p1!=p2
//        if (!historyPValueP2.getpValue().equals(request.getPValue())) {
//            // p1 != p2
//            // 删除p2
//            TShopInfoExample example = new TShopInfoExample();
//            example.createCriteria().andTuniuIdEqualTo(tuniuId);
//            shopInfoMapper.deleteByExample(example);
//            // p值表置位无效
//            TShopInfo temp = new TShopInfo();
//            temp.setTuniuId(historyPValueP2.getTuniuId());
//            temp.setOwnerPValue(historyPValueP2.getpValue());
//            pValueService.operateHistoryByShopInfo(temp, 2, (byte) 2);
//            //  p值表换tuniuId
//            historyPValueP1.setTuniuId(tuniuId);
//            historyPValueMapper.updateByPrimaryKey(historyPValueP1);
//            // 店铺表
//            updateShopTuniuId(request, tuniuId);
//        }
//    }
//
//    private List<THistoryPValue> gettHistoryPValues(ShopInfoMaintainRequest request) {
//        THistoryPValueExample example = new THistoryPValueExample();
//        example.createCriteria().andPValueEqualTo(request.getPValue()).andValidStateEqualTo(ValidStateEnum.VALID.getState());
//        return historyPValueMapper.selectByExample(example);
//    }
//
//    private void updateShopTuniuId(ShopInfoMaintainRequest request, int tuniuId) {
//        TShopInfo record = new TShopInfo();
//        record.setTuniuId(tuniuId);
//        TShopInfoExample example = new TShopInfoExample();
//        example.createCriteria().andOwnerPValueEqualTo(request.getPValue());
//        shopInfoMapper.updateByExampleSelective(record, example);
//    }
//}
