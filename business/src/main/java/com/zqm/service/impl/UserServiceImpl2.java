/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.service.impl;

import org.springframework.stereotype.Service;

import com.zqm.dao.entity.TUserInfo;
import com.zqm.service.UserService;

/**
 * TODO: description
 * Date: 2019-11-30
 *
 * @author zhaqianming
 */
@Service
public class UserServiceImpl2 implements UserService {

    @Override
    public byte getSubscribeType() {
        return (byte) 2;
    }

    @Override
    public String updateUserInfo(TUserInfo tUserInfo) {
        return null;
    }

    @Override
    public String insertUserInfo(TUserInfo tUserInfo) {
        return null;
    }
}
