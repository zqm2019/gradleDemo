package com.zqm.controller;

import com.google.common.collect.Lists;
import com.zqm.utils.Helper;
import com.zqm.utils.HelperApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @describe:
 * @author:zqm
 */
@RestController
public class HelperTestController {

    @Autowired
    private Helper helper;

    @GetMapping("testA")
    public String testA() {
        return helper.run();
    }

    @GetMapping("testB")
    public String testB() {
        Helper helper1 = new Helper();
        return helper1.run();
    }


    @GetMapping("testC")
    public String testC() throws IllegalAccessException, InstantiationException {
        Helper c = Helper.class.newInstance();
        return c.run();
    }

    @GetMapping("testD")
    public String testD() {
        Helper d = HelperApplication.getHelper();
        return d.run();
    }

    @GetMapping("testE")
    public String testE() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Helper d = (Helper) Class.forName("com.zqm.utils.Helper").newInstance();
        return d.run();
    }

    static Thread t1 = null;
    static Thread t2 = null;

    //    public static void main(String[] args) throws InterruptedException {
//        List<Integer> numberList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26);
//        List<String> stringList = Lists.newArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
//        List<String> resultList = Lists.newArrayListWithExpectedSize(numberList.size()+stringList.size());
//        t1 = new Thread(() -> {
//            for (Integer integer : numberList) {
//                resultList.add(String.valueOf(integer));
//                //唤醒指定阻塞线程
//                LockSupport.unpark(t2);
//                //阻塞当前线程
//                LockSupport.park();
//            }
//        });
//
//        t2 = new Thread(() -> {
//            for (String str : stringList) {
//                //阻塞当前线程
//                LockSupport.park();
//                resultList.add(str);
//                //唤醒指定阻塞线程
//                LockSupport.unpark(t1);
//
//            }
//        });
//        t1.start();
//        t2.start();
//        //阻塞主线程一会
//        Thread.sleep((long) (10 * 10));
//        System.out.println(resultList);
//        System.out.println(resultList.stream().collect(Collectors.joining()));
//
//    }
    enum ThreadToRun {
        T1, T2
    }

    static volatile ThreadToRun threadToRun = ThreadToRun.T1;

//    public static void main(String[] args) throws InterruptedException {
//        List<Integer> numberList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26);
//        List<String> stringList = Lists.newArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
//        List<String> resultList = Lists.newArrayListWithExpectedSize(numberList.size() + stringList.size());
//
//        t1 = new Thread(() -> {
//            for (Integer integer : numberList) {
//                //如果不是T1线程，就一直循环
//                while (threadToRun != ThreadToRun.T1) {}
//                resultList.add(String.valueOf(integer));
//                threadToRun = ThreadToRun.T2;
//
//            }
//        });
//
//        t2 = new Thread(() -> {
//            for (String str : stringList) {
//                while (threadToRun != ThreadToRun.T2) {}
//                    resultList.add(String.valueOf(str));
//                    threadToRun = ThreadToRun.T1;
//
//            }
//        });
//        t1.start();
//        t2.start();
//        //阻塞主线程一会
//        Thread.sleep((long) (10 * 100));
//        System.out.println(resultList);
//        System.out.println(String.join("", resultList));
//
//    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> numberList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26);
        List<String> stringList = Lists.newArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        List<String> resultList = Lists.newArrayListWithExpectedSize(numberList.size() + stringList.size());
        Object o = new Object();
        t1 = new Thread(() -> {
            synchronized (o) {
                for (Integer integer : numberList) {
                    resultList.add(String.valueOf(integer));
                    try {
                        //唤醒队列中的一个线程
                        o.notify();
                        //进入等待队列，当前线程让出锁
                        o.wait();
                    } catch (Exception e) {
                    }
                }
                o.notify();
            }

        });

        t2 = new Thread(() -> {
            synchronized (o) {
                for (String str : stringList) {
                    resultList.add(str);
                    try {
                        //唤醒队列中的一个线程
                        o.notify();
                        //进入等待队列，当前线程让出锁
                        o.wait();
                    } catch (Exception e) {
                    }
                }
                //必须，否则程序无法停止
                o.notify();

            }
        });
        t1.start();
        t2.start();
        //阻塞主线程一会
        Thread.sleep((long) (10 * 100));
        System.out.println(resultList);
        System.out.println(String.join("", resultList));

    }
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
//   唤醒一个等待线程。该线程从等待方法返回前必须获得与Condition相关的锁。
//     condition.signal();
//    造成当前线程在接到信号或被中断之前一直处于等待状态
//     condition.wait();



}
