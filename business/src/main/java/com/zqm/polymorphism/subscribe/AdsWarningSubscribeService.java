/**
 * Copyright (C) 2006-2019 Tuniu All rights reserved
 */
package com.zqm.polymorphism.subscribe;

import java.util.Date;

import org.springframework.stereotype.Service;

import javafx.util.Pair;

/**
 * @author zqm
 * @date 2019/11/29 9:31
 **/
@Service
public class AdsWarningSubscribeService extends AbstractSubscribe {


    @Override
    byte getSubscribeType() {
        return (byte) 1;
    }

    @Override
    Pair<Object, Date> buildSubscribeMessage(String subscribeContent) {
        return null;
    }

}
