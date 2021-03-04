package com.zqm.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @describe:
 * 在bean中不要使用this来调用被@Async、@Transactional、@Cacheable等注解标注的方法，this下注解是不生效的。
 * @author:zqm
 */
@Component
public class TestThisComponent {

    /**
     * 调用两个方法的输出结果是
     * 1:main
     * 2:main
     * 2:SimpleAsyncTaskExecutor-2
     */
        public void async1() {
            System.out.println("1:" + Thread.currentThread().getName());
            this.async2();
        }



        @Async
        public void async2() {
            System.out.println("2:" + Thread.currentThread().getName());
        }


    /**
     * 调用两个方法的输出结果是
     *   1:main
     *  2:SimpleAsyncTaskExecutor-2
     * 2:SimpleAsyncTaskExecutor-3
     */

//    @Component
//    public class AsyncService implements ApplicationContextAware {
//
//        private ApplicationContext applicationContext;
//
//        public void async1() {
//            System.out.println("1:" + Thread.currentThread().getName());
//            // 使用AppicationContext来获得动态代理的bean
//            this.applicationContext.getBean(AsyncService.class).async2();
//        }
//
//        @Async
//        public void async2() {
//            System.out.println("2:" + Thread.currentThread().getName());
//        }
//
//        // 注入ApplicationContext
//        @Override
//        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//            this.applicationContext = applicationContext;
//        }
//    }
}
