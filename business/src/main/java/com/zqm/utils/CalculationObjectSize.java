package com.zqm.utils;

import com.sun.org.apache.xpath.internal.operations.String;
import com.zqm.vo.People;
import org.apache.lucene.util.RamUsageEstimator;

import java.lang.instrument.Instrumentation;

/**
 * @describe: 计算对象大小
 * java.lang.instrument.Instrumentation.getObjectSize()方法，可以很方便的计算任何一个运行时对象的大小，
 * 返回该对象本身及其间接引用的对象在内存中的大小。不过，这个类的唯一实现类InstrumentationImpl的构造方法是私有的，
 * 在创建时，需要依赖一个nativeAgent，
 * 和运行环境所支持的一些预定义类信息，我们在代码中无法直接实例化它，需要在JVM启动时，通过指定代理的方式，让JVM来实例化它。
 * @author:zqm
 */
public class CalculationObjectSize {


    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation instP) {
        instrumentation = instP;
    }

    public static long sizeOf(Object obj) {
        return instrumentation.getObjectSize(obj);
    }

//    //计算指定对象及其引用树上的所有对象的综合大小，单位字节
//    long RamUsageEstimator.sizeOf(Object obj)
//
//    //计算指定对象本身在堆空间的大小，单位字节
//    long RamUsageEstimator.shallowSizeOf(Object obj)
//
//    //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
//    String RamUsageEstimator.humanSizeOf(Object obj)

    public static void main(String[] args) {
        System.out.println(RamUsageEstimator.shallowSizeOf(new People()));
        System.out.println( RamUsageEstimator.shallowSizeOf(new People()));
    }
}
