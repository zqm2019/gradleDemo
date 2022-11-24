
package com.zqm.controller;

import com.zqm.service.impl.PeopleService;
import com.zqm.vo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.math.BigDecimal;

/**
 * spring 获取bean的几种方式:
 * 方法一：在初始化时保存ApplicationContext对象
 * 适用于採用Spring框架的独立应用程序，须要程序通过配置文件手工初始化Spring的情况。
 * <p>
 * 方法二：通过Spring提供的utils类获取ApplicationContext对象
 * 适合于採用Spring框架的B/S系统，通过ServletContext对象获取ApplicationContext对象
 * <p>
 * 方法三：继承自抽象类ApplicationObjectSupport
 * 抽象类ApplicationObjectSupport提供getApplicationContext()方法,能够方便的获取ApplicationContext
 * <p>
 * 方法四：继承自抽象类WebApplicationObjectSupport
 * 调用getWebApplicationContext()获取WebApplicationContext
 * <p>
 * 方法五：实现接口ApplicationContextAware(参照TestAnnotationController里bean的初始化)
 * 实现该接口的setApplicationContext(ApplicationContext context)方法，并保存ApplicationContext 对象。
 * Spring初始化时，会通过该方法将ApplicationContext对象注入。
 * <p>
 * 方法六：通过Spring提供的 ContextLoader
 * <p>
 * <p>
 * 1、如果类第一个字母大写第二个小写，那么首字母小写获取bean
 * <p>
 * 2、如果第一个和第二个字母都是大写的，那个获取bean首字母要大写
 * Date: 2019-08-26
 *
 * @author zhaqianming
 */
@RestController
@RequestMapping("bean")
public class BeanController extends WebApplicationObjectSupport {

    private PeopleService peopleService;

    @PostConstruct
    public void init() {
        // 方法2 继承WebApplicationObjectSupport 获取ServletContext 然后调用工具类
        ServletContext servletContext = getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        peopleService = (PeopleService) applicationContext.getBean("peopleService");

//        //方法3 good
//        ApplicationContext applicationContext = getApplicationContext();
//        peopleService = (PeopleService) applicationContext.getBean("peopleService");


        //todo webApplicationContext为null
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

    }

    @RequestMapping(value = "test")
    public People testCreateBean() {
        return peopleService.getPeople(1);
    }


    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal(2);
        double d = decimal.doubleValue();
        System.out.println(d);
    }

}
