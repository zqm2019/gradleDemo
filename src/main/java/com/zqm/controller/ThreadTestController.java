package com.zqm.controller;

import com.zqm.executor.AsyncExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * 线程相关测试
 * Date: 2019-08-20
 *
 * @author zhaqianming
 */
@RestController
@RequestMapping("thread")
public class ThreadTestController {

    @Autowired
    private AsyncExecutorService asyncExecutorService;

    @RequestMapping("test")
    public String testThread() {
        asyncExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("dd");
            }
        });
        String ss = "";
        Future<String> future = asyncExecutorService.submit(new MyCallable());
        try {
            ss = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ss;
    }

    public static void main(String[] args) throws Exception {
        createNewThread();
        createNewCallable();
    }

    public static void createNewThread() {
        //匿名函数方式
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("2+3="+5);
//            }
//        });
        //推荐使用这样的方式
        Thread thread = new Thread(() -> System.out.println("2+3=" + 5));
        /**
         * 由线程池执行线程
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         * 执行
         */
        executorService.execute(thread);
        /**
         * 关闭
         */
        executorService.shutdown();
        /**
         * 启动线程
         */
        thread.start();
    }


    public static void createNewCallable() throws ExecutionException, InterruptedException, TimeoutException {
        MyCallable myCallable = new MyCallable();
        try {
            System.out.println(myCallable.call());
        } catch (Exception e) {
            e.printStackTrace();
        }


        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(myCallable);
        System.out.println("Future 获得执行结果:" + future.get());


        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        //使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程
        //没这句，下句代码获取不到结果，会一直等待执行结果
        new Thread(futureTask, "线程1").start();//靠他来启动线程
        String taskGet = futureTask.get();
        System.out.println("FutureTask 获取执行结果:" + taskGet);

        //设置超时时间
        String ta = futureTask.get(1, TimeUnit.MICROSECONDS);

        System.out.println(ta);

    }


    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "my name is callable";
            //事务之后异步操作
//            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter(){
//                @Override
//                public void afterCommit() {
//                    asyncExecutorService.execute();
//                }
//            });
        }
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void insert(Object techBook) {
//        insert(techBook);
        //事务

//        send after tx commit but is async
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        executorService.submit(new Runnable() {
                            @Override
                            public void run() {
                                //do you what you want
                            }
                        });
                    }
                }
        );
    }

}
