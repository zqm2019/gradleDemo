
package com.zqm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqm.dao.entity.TUserInfo;
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

    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Override
    public String insertUserInfo(TUserInfo tUserInfo) {
        tUserInfoMapper.insertSelective(tUserInfo);
        return "ok";
    }
}
