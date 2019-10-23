/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.controller;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Timer 和 ScheduledExecutor 都仅能提供基于开始时间与重复间隔的任务调度
 * Timer 的优点在于简单易用，但由于所有任务都是由同一个线程来调度，
 * 因此所有任务都是串行执行的，同一时间只能有一个任务在执行，前一个任务的延迟或异常都将会影响到之后的任务。
 * <p>
 * ScheduledExecutor 每一个被调度的任务都会由线程池中一个线程去执行，因此任务是并发执行的，
 * 相互之间不会受到干扰。需要注意的是，只有当任务的执行时间到来时，
 * ScheduledExecutor 才会真正启动一个线程，其余时间 ScheduledExecutor 都是在轮询任务的状态。
 * <p>
 * <p>
 * ScheduledExecutor 和 Calendar 实现复杂任务调度
 * <p>
 * Quartz 满足更多的复杂调度需求 实现JOb接口
 * <p>
 * Date: 2019-10-22
 *
 * @author zhaqianming
 */
public class ScheduleController extends TimerTask implements Runnable , Job {

    private String jobName;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public ScheduleController(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }

    @Override
    public void run() {
        System.out.println("execute：" + jobName);

    }

    public static void main(String[] args) {
        /**
         * 同步
         */
//        Timer timer = new Timer();
//        long delay1 = 1 * 1000;
//        long period1 = 1000;
//        // 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
//        timer.schedule(new ScheduleController("job1"), delay1, period1);
//
//        long delay2 = 1 * 1000;
//        long period2 = 1000;
//        // 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
//        timer.schedule(new ScheduleController("job2"), delay2, period2);
        /**
         * 异步
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        long initdelay = 1;
        long period = 1;
        scheduledExecutorService.scheduleAtFixedRate(new ScheduleController("job3"), initdelay, period, TimeUnit.SECONDS);
    }
}
