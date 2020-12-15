
package com.zqm.executor;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zqm.vo.People;
import javafx.util.Pair;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
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

    public static void main(String[] args) {
        List<Pair<String, String>> pairs = combineTwoElement(Lists.newArrayList("1", "2","3","4"));
        List<Pair<String, String>> pairs1 = combineTwoElement(Lists.newArrayList("1", "2","3","4","5"));
        System.out.println(pairs);
        System.out.println(pairs1);

    }

    public static List<Pair<String, String>> combineTwoElement(List<String> list) {
        List<Pair<String, String>> resultList = Lists.newArrayListWithExpectedSize(list.size()*list.size()/2);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        for (int j = 0; j < list.size(); j++) { if (list.size() >= 2) {
                resultList.addAll(ll(list.subList(j,list.size())));
        }
        }
        return resultList;
    }


    public static List<Pair<String, String>> ll (List<String> list){
        List<Pair<String, String>> resultList = Lists.newArrayListWithExpectedSize(list.size()*list.size()/2);

        for (int j = 1; j < list.size(); j++) {
            resultList.add(new Pair<>(list.get(0), list.get(j)));
        }
        return  resultList;
    }


    public static class student extends People{

    }
}
