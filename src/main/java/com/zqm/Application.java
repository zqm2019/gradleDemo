package com.zqm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * Date: 2019-07-17
 *
 * @author zhaqianming
 */
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.zqm"})
// 启动去除数据库依赖 如果是使用阿里druid 还需去掉DruidDataSourceAutoConfiguration.class
//@SpringBootApplication(scanBasePackages = {"com.zqm"},exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

@EnableCaching
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        setRegisterErrorPageFilter(false);
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        /**
         *
         *  如果想打开图形化界面 使用下面方式 WebSocketConfig BeanController 这两个类需要注释 才能开启下面的swing窗口
         *  SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
         *  builder.headless(false).web(false).run(args);
         *  new JFram();
         */

    }
}