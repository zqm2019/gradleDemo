
package com.zqm.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

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
//        executorService = new ThreadPoolExecutor(executorCoreSize, executorMaxPoolSize, keepAliveSeconds,
//                TimeUnit.SECONDS, new LinkedBlockingDeque<>(queueCapacity));
        //推荐使用自定义线程池名字，方便出错回溯
        executorService = new ThreadPoolExecutor(executorCoreSize, executorMaxPoolSize, keepAliveSeconds,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(queueCapacity),new ThreadFactoryBuilder()
                .setNameFormat("zqm-pool-%d").build());
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
