package com.zqm.controller;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @describe: 限流三大常见算法：
 * <p>
 * 令牌桶(Token Bucket)：
 * 令牌桶算法的原理是系统会以一个恒定的速度往桶里放入令牌，而如果请求需要被处理，则需要先从桶里获取一个令牌
 * ，当桶里没有令牌可取时，则拒绝服务。 当桶满时，新添加的令牌被丢弃或拒绝
 * <p>
 * 漏桶(leaky bucket)：
 * 它的主要目的是控制数据注入到网络的速率，平滑网络上的突发流量，数据可以以任意速度流入到漏桶中。
 * 漏桶算法提供了一种机制，通过它，突发流量可以被整形以便为网络提供一个稳定的流量。
 * 漏桶可以看作是一个带有常量服务时间的单服务器队列，如果漏桶为空，则不需要流出水滴，如果漏桶（包缓存）溢出，那么水滴会被溢出丢弃
 * <p>
 * 计数器:
 * 主要用来限制总并发数，比如数据库连接池大小、线程池大小、程序访问并发数等都是使用计数器算法
 * @author:zqm
 */
public class LimitVisitorTestController {

    public static void main(String[] args) {
        testRateLimiter();
        testCount1();
        testCount2();
        testCount1();
    }


    /**
     * 创建一个限流器，设置每秒放置的令牌数：2个。速率是每秒可以2个的消息。
     * 返回的RateLimiter对象可以保证1秒内不会给超过5个令牌，并且是固定速率的放置。达到平滑输出的效果
     */
    private static RateLimiter limiter = RateLimiter.create(10);

    public static void testRateLimiter() {
        while (true)
        {
            /**
             * acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
             * acquire(N)可以获取多个令牌。
             */
            System.out.println(limiter.acquire(2));
        }
        /**
         *  acquire()获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
         *   acquire(N)可以获取多个令牌。
         *   limiter.acquire(1) 相当于limiter.acquire()
         */
//        limiter.acquire(1);
//        try {
//            // 处理核心逻辑
//            TimeUnit.SECONDS.sleep(1);
//            System.out.println("--" + System.currentTimeMillis() / 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 使用AomicInteger来进行统计当前正在并发执行的次数，
     * 如果超过域值就简单粗暴的直接响应给用户，说明系统繁忙，请稍后再试或其它跟业务相关的信息。
     * 瞬时超高流量会直接拒绝服务
     */
    private static AtomicInteger count = new AtomicInteger(0);

    public static void testCount1() {
        //增加count
        count.incrementAndGet();

        if (count.get() >= 5) {
            System.out.println("请求用户过多，请稍后在试！" + System.currentTimeMillis() / 1000);
        } else {
            count.incrementAndGet();
            try {
                //处理核心逻辑
                TimeUnit.SECONDS.sleep(1);
                System.out.println("--" + System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //减少count
                count.decrementAndGet();
            }
        }
    }


    /**
     * Semaphore信号量来控制并发执行的次数，如果超过域值信号量，则进入阻塞队列中排队等待获取信号量进行执行。
     * 如果阻塞队列中排队的请求过多超出系统处理能力，则可以在拒绝请求
     * 如果是瞬时的高并发，可以使请求在阻塞队列中排队，而不是马上拒绝请求，从而达到一个流量削峰的目的。
     */
    private static Semaphore semphore = new Semaphore(5);

    public static void testCount2() {
        if (semphore.getQueueLength() > 100) {
            System.out.println("当前等待排队的任务数大于100，请稍候再试...");
        }
        try {
            semphore.acquire();
            // 处理核心逻辑
            TimeUnit.SECONDS.sleep(1);
            System.out.println("--" + System.currentTimeMillis() / 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semphore.release();
        }
    }

}
