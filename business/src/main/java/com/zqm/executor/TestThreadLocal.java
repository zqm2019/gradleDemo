package com.zqm.executor;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe:
 * @author:zqm
 */

public class TestThreadLocal {

    private static final ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    /**
     * itl 支持父线程的本地变量传递给子线程这种基本操作,线程池方式不支持
     */
    private static final ThreadLocal<Map<String, String>> threadLocal1 = new InheritableThreadLocal<>();

    /**
     * ttl 所以TTL一定是支持父线程的本地变量传递给子线程这种基本操作的，ITL也可以做到，但是前面有讲过，ITL在线程池的模式下，
     * 就没办法再正确传递了，所以TTL做出的改进就是即便是在线程池模式下，也可以很好的将父线程本地变量传递下去，
     */
    private static final ThreadLocal<Map<String, String>> threadLocal2 = new TransmittableThreadLocal<>();



    public static Map<String, String> getMap() {
        if (threadLocal.get() == null) {
            threadLocal.set(new ConcurrentHashMap<>());
        }
        return threadLocal.get();
    }


    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = getMap();

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                map.put(String.valueOf(i), String.valueOf(i));
            }
        }
        threadLocal.set(map);
        threadLocal2.set(map);
        System.out.println(Thread.currentThread().getName()+"threadLocal:" + threadLocal.get());
        System.out.println(Thread.currentThread().getName()+"threadLocal2:" + threadLocal2.get());


        Runnable r = () -> {
            Map<String, String> map1 = getMap();
            if (map != null && map1.size() == 0) {
                map1.put("1", "1");
            }
            System.out.println(Thread.currentThread().getName()+"threadLocal:" + threadLocal.get());
            System.out.println(Thread.currentThread().getName()+"threadLocal2:" + threadLocal2.get());
            threadLocal.set(map1);
            threadLocal2.set(map1);
            System.out.println(Thread.currentThread().getName()+"threadLocal:" + threadLocal.get());
            System.out.println(Thread.currentThread().getName()+"threadLocal2:" + threadLocal2.get());

        };
        Thread t = new Thread(r);
        t.start();

        Thread.sleep(900);
        System.out.println(Thread.currentThread().getName()+"threadLocal:" + threadLocal.get());
        System.out.println(Thread.currentThread().getName()+"threadLocal2:" + threadLocal2.get());
    }
}
