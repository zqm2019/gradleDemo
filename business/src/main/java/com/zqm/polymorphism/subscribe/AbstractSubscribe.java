/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.polymorphism.subscribe;

import java.util.Date;

import javafx.util.Pair;

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


}
