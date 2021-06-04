package com.zqm.base;

import com.zqm.dao.entity.TUserInfo;
import com.zqm.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @describe:
 * @author:zqm
 */
public class NewTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setAddress("ll");
        tUserInfo.setTel("");
        userService.updateUserInfo(tUserInfo);
    }

    @Test
    public void testSort(){
        System.out.println("a.id_own_org");
    }
}
