package com.zqm.controller;

import java.util.concurrent.TimeUnit;

/**
 * @describe: volatile 可见性，保障不同线程的对变量的修改是可见的，禁止指令重排
 * jvm 在不改变程序结果会优化执行顺序，但有volatile是不会进行重排序的，
 * @author:zqm
 */
public class VolatileController {

    final static int MAX_VALUE = 5;
    static  int LOCAL_VALUE = 0;

    static void testVolatile() {
        new Thread(() -> {

            while (LOCAL_VALUE < MAX_VALUE) {
                System.out.println("read" + LOCAL_VALUE);
            }
        }, "reader").start();

        new Thread(() -> {
            int localValue = LOCAL_VALUE;
            while (localValue < MAX_VALUE) {
                System.out.println("update" + LOCAL_VALUE);
                LOCAL_VALUE = ++localValue;
                try {
                    //休眠2s，等到read输出
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "update").start();
    }

    public static void main(String[] args) {
        testVolatile1();
    }

    static void testVolatile1() {
        new Thread(() -> {
            int localValue = LOCAL_VALUE;

            while (LOCAL_VALUE < MAX_VALUE) {
                if (localValue != LOCAL_VALUE){
                    System.out.println("read" + LOCAL_VALUE);
                    localValue = LOCAL_VALUE;
                }
            }
        }, "reader").start();

        new Thread(() -> {
            int localValue = LOCAL_VALUE;
            while (localValue < MAX_VALUE) {

                System.out.println("update" + LOCAL_VALUE);
                LOCAL_VALUE=  ++localValue;
                try {
                    //休眠2s，等到read输出
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "update").start();
    }
}
