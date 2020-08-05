package com.zqm.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @describe:
 * CountDownLatch(int count) //实例化一个倒计数器，count指定计数个数
 * countDown() // 计数减一
 * await() //等待，当计数减到0时，所有线程并行执行
 * @author:zqm
 */
public class ExecutorController {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(32, 64, 60,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), new ThreadFactoryBuilder()
                .setNameFormat("zqm-pool-%d").build());
        final CountDownLatch cdOrder = new CountDownLatch(2);
        final CountDownLatch cdAnswer = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                {
                    try {
                        System.out.println("选手" + Thread.currentThread().getName() + "正在等待裁判发布口令");
                        //await()方法等到当前进程清理，清理之前是阻塞的，除非该进程被中断了
                        cdOrder.await();
                        System.out.println("选手" + Thread.currentThread().getName() + "已接受裁判口令");
                        Thread.sleep((long) (12 * 10));
                        System.out.println("选手" + Thread.currentThread().getName() + "到达终点");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //计数器为0释放所有线程 必有
                        cdAnswer.countDown();
                    }
                }

            });
        }
        try {
            //主线程main等下多线程执行语句1，不然主线程和多线程并行了
            Thread.sleep((long) (10 * 10));
            //这是主线程
            System.out.println("裁判" + Thread.currentThread().getName() + "即将发布口令");
            //关闭多线程中的第一个等待，清0
            //为什么是执行两次，是因为的初始值count值是二
            cdOrder.countDown();
            cdOrder.countDown();
            Thread.sleep((long) (5 * 10));
            //主线程
            System.out.println("裁判" + Thread.currentThread().getName() + "已发送口令，正在等待所有选手到达终点");
            //主线程
            Thread.sleep((long) (12 * 10));
            System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        executorService.shutdown();
    }
}
