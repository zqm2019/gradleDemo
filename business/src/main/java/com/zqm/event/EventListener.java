package com.zqm.event;

import com.google.common.eventbus.Subscribe;
import lombok.extern.log4j.Log4j;

/**
 * @describe:
 * @author:zqm
 */
@Log4j
public class EventListener {


    /**
     * 监听 Integer 类型的消息
     */

    @Subscribe
    public void listenInteger(Integer param) {
        log.info("EventListener#listenInteger ->" + param);
    }


    /**
     * 监听 String 类型的消息
     */

    @Subscribe
    public void listenString(String param) {
        log.info("EventListener#listenString ->" + param);

    }




}
