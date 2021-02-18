package com.zqm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @describe:
 * @author:zqm
 */
@Component
public class HelperApplication implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        HelperApplication.applicationContext = applicationContext;
    }

    /**
     * 一下获取class方法都是可以的，可以任选一种
     * 1通过对象的实例获取
     * 2通过类名.class获取
     * 3通过类的地址获取
     *
     * @return
     */
    public static Helper getHelper() throws IllegalAccessException, InstantiationException {
        Class clz = new Helper().getClass();
        Class<?> aClass = null;
        try {
            //注意要是全路径才生效
            aClass = Class.forName("com.zqm.utils.Helper");
        } catch (Exception e) {

        }
        Helper helper = new Helper();
        Helper helper1 =  Helper.class.newInstance();
        if (aClass == null) {
            return applicationContext.getBean(Helper.class);
        } else {
            return (Helper) applicationContext.getBean(aClass);
        }

    }
}
