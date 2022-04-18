package com.zqm.event;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.Subscribe;
import com.zqm.vo.People;
import lombok.extern.slf4j.Slf4j;

/**
 * @describe:
 * @author:zqm
 */
@Slf4j
public class EventListenerTwo {

    /**

     * 监听 Integer 类型的消息

     */

    @Subscribe
    public void listenInteger(Integer param) {

        log.info("listener ->" + param);

    }



    /**

     * 监听 String 类型的消息

     */

    @Subscribe
    public void listenString(People param) {
        log.info("EventListener#people ->" + JSON.toJSONString(param));

    }

}
