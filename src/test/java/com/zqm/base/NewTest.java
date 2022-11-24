package com.zqm.base;

import com.zqm.dao.entity.TUserInfo;
import com.zqm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @describe:
 * @author:zqm
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class NewTest {

    @Resource
    private List<UserService> userService;



    @Test
    public void test1(){
        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setAddress("ll");
        tUserInfo.setTel("");
//        userService.updateUserInfo(tUserInfo);
    }

    @Test
    public void testSort(){
        System.out.println("a.id_own_org");
    }
}
