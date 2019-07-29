
package com.zqm.service;

import com.zqm.dao.entity.TUserInfo;

/**
 * TODO: description
 * Date: 2019-07-26
 *
 * @author zhaqianming
 */
public interface UserService {

    String updateUserInfo(TUserInfo tUserInfo);

    String insertUserInfo(TUserInfo tUserInfo);

}
