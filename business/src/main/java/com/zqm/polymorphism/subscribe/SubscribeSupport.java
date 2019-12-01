/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.polymorphism.subscribe;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订阅support
 * Date: 2019-11-28
 *
 * @author zqm
 */
@Component
public class SubscribeSupport {

    @Autowired
    private List<AbstractSubscribe> subscribes;

    private static Map<Byte, AbstractSubscribe> subscribeServiceMap;


    @PostConstruct
    private void init() {
        subscribeServiceMap = subscribes.stream().collect(Collectors.toMap(AbstractSubscribe::getSubscribeType, a -> a, (k, v) -> v));
    }

    public AbstractSubscribe getAbstractSubscribe(byte subscribeType) {
        return subscribeServiceMap.get(subscribeType);
    }

}
