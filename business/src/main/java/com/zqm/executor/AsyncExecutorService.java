
package com.zqm.executor;

import javax.annotation.PostConstruct;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 异步任务执行器
 * Date: 2017-02-16
 *
 * @author zqm
 */
@Component
public class AsyncExecutorService {
    @Value("${executorPool.executorMaxPoolSize}")
    private int executorMaxPoolSize;

    @Value("${executorPool.executorCoreSize}")
    private int executorCoreSize;

    @Value("${executorPool.keepAliveSeconds}")
    private int keepAliveSeconds;

    @Value("${executorPool.queueCapacity}")
    private int queueCapacity;

    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        executorService = new ThreadPoolExecutor(executorCoreSize, executorMaxPoolSize, keepAliveSeconds, TimeUnit.SECONDS, new LinkedBlockingDeque<>(queueCapacity));
    }

    /**
     * 提交异步任务(无返回值)
     */
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    /**
     * 提交异步任务(有返回值)
     */
    public <T> Future<T> submit(Callable<T> task) {
        return executorService.submit(task);
    }
}
