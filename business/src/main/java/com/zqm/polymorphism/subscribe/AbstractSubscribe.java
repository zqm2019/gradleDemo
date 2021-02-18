/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.polymorphism.subscribe;

import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Date;

/**
 * 消息订阅抽象类
 * Date: 2019-11-28
 *
 * @author zqm
 */
public abstract class AbstractSubscribe {

    /**
     * 订阅消息类型
     *
     * @return
     */
    abstract byte getSubscribeType();

    /**
     * 组装订阅消息内容
     *
     * @param subscribeContent
     */
    abstract Pair<Object, Date> buildSubscribeMessage(String subscribeContent);

    public static void main(String[] args) {
        Pair<Boolean, String> pair = testPair();
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
        System.out.println(testMutablePair().getLeft());

    }

    public static Pair<Boolean,String> testPair(){
        Pair<Boolean,String> pair = new Pair<Boolean,String>(false,"ll");
        return pair;
    }

    /**
     * 不可改变
     * @return
     */
    public static ImmutablePair<Boolean,String> testImmutablePair(){
        ImmutablePair<Boolean,String> pair = new ImmutablePair<Boolean,String>(false,"ll");
        return pair;
    }

    /**
     * 可改变的pair
     * @return
     */
    public static MutablePair<Boolean,String> testMutablePair(){
        MutablePair<Boolean,String> pair = new MutablePair<Boolean,String>(false,"ll");
        pair.setLeft(true);
        return pair;
    }

}
