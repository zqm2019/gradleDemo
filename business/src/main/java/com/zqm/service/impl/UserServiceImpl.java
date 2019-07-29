
package com.zqm.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqm.dao.entity.TUserInfo;
import com.zqm.dao.entity.TUserInfoExample;
import com.zqm.dao.mapper.TUserInfoMapper;
import com.zqm.service.UserService;

/**
 * TODO: description
 * Date: 2019-07-20
 *
 * @author zhaqianming
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TUserInfoMapper userInfoMapper;

    @Override
    public String insertUserInfo(TUserInfo tUserInfo) {
        userInfoMapper.insertSelective(tUserInfo);
        return "ok";
    }

    @Override
    public String updateUserInfo(TUserInfo tUserInfo) {
        TUserInfoExample tUserInfoExample = new TUserInfoExample();
        tUserInfoExample.createCriteria().andTelEqualTo(tUserInfo.getTel());

        userInfoMapper.updateByExampleSelective(tUserInfo, tUserInfoExample);
        return "ok";
    }
}
