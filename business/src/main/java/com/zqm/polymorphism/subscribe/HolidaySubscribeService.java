/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.polymorphism.subscribe;

import java.util.Date;

import org.springframework.stereotype.Service;

import javafx.util.Pair;

/**
 * 节假日消息订阅
 * Date: 2019-11-28
 *
 * @author zqm
 */
@Service
public class HolidaySubscribeService extends AbstractSubscribe {

    @Override
    byte getSubscribeType() {
        return (byte) 2;
    }

    @Override
    Pair<Object, Date> buildSubscribeMessage(String subscribeContent) {
        return new Pair<>(1, new Date());
    }


}
