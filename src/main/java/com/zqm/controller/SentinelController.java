package com.zqm.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.collect.Lists;
import com.zqm.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe: 哨兵
 * @function Sentinel 是面向分布式服务架构的轻量级流量控制组件，
 * 主要以流量为切入点，从限流、流量整形、熔断降级、系统负载保护等多个维度来帮助您保障微服务的稳定性。
 * @author:zqm
 */
@RestController
@RequestMapping("test")
@Slf4j
public class SentinelController {

    /**
     * 启动 到jar文件夹目录执行 java -jar sentinel-dashboard-1.7.2.jar 用户名和密码都是sentinel
     * @return
     */


    @RequestMapping("hello")
    public String testSentinel(){
        oo();
        return "hello sentinel";
    }

    @RequestMapping("hello2")
    public String test2(){
        return doSomeThing("dfd");
    }

    @Autowired
    private SentinelService sentinelService;
    @GetMapping("hello3")
    public void test3(){
        sentinelService.doSometing();
    }

    public static void main(String[] args) {
        // 配置规则.
        initFlowRules();
        while (true) {
//            getNumber();


            // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
          oo();
        }
    }
private static final String hh= "HelloWorld";
    private static void  oo(){
        try (Entry entry = SphU.entry(hh)) {
            // 被保护的逻辑
            System.out.println("hello world");
        } catch (BlockException ex) {
            // 处理被流控的逻辑
            System.out.println("blocked!");
        }
    }

    /**
     * //     一条FlowRule有以下几个重要的属性组成：
     * //
     * //resource: 规则的资源名
     * //grade: 限流阈值类型，qps 或线程数
     * //count: 限流的阈值
     * //limitApp: 被限制的应用，授权时候为逗号分隔的应用集合，限流时为单个应用
     * //strategy: 基于调用关系的流量控制
     * //controlBehavior：流控策略
     */
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(hh);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    private static void initNumberResource() {
        List<FlowRule> rules = Lists.newArrayListWithCapacity(1);
        FlowRule rule = new FlowRule();
        rule.setRefResource(number_resource);
        //qps
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    private static final String number_resource = "number";

    private static int getNumber() {

        try {
            Entry entry = SphU.entry(number_resource);
            System.out.println(String.valueOf(RandomUtils.nextInt()).substring(0, 1));
        } catch (BlockException e) {
            System.out.println("限流了");
        } finally {
//            if (entry != null) {
//                entry.exit();
//            }
        }

        return 0;
    }

    /**
     * 防止业务代码入侵，使用注解SentinelResource，blockHandler对应限流策略即具体函数
     *
     * @param str
     */

    @SentinelResource(value = "doSomeThing", blockHandler = "exceptionHandler")
    public static String doSomeThing(String str) {
        log.info(str);
        return str;
    }

    /**
     * 限流与阻塞处理
     * 实现处理函数，该函数的传参必须与资源点的传参一样，并且最后加上BlockException异常参数；同时，返回类型也必须一样
     *
     * @param str
     * @param ex
     */
    public String exceptionHandler(String str, BlockException ex) {
        log.error("blockHandler：" + str, ex);
        return str;
    }

//    public static void main(String[] args) {
//        while (true) {
//            System.out.println(doSomeThing("hello"));
//        }
//    }
}
