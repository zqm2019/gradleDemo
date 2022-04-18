package com.zqm.event;

import com.google.common.eventbus.EventBus;
import com.zqm.vo.People;

/**
 * @describe:
 * @author:zqm
 */
public class EventBusTest {

    public void addEvent() {
        EventBus eventBus = new EventBus();
        //注册监听
        eventBus.register(new EventListener());
        //发布事件
        eventBus.post(1);
        eventBus.post("1");
    }

    public void addEvent1() {
        EventBus eventBus = new EventBus();
        //注册监听
        eventBus.register(new EventListenerTwo());
        eventBus.register(new EventListener());

        //发布事件
        eventBus.post(1);
        eventBus.post("1");
        People people = new People();
        people.setName("zqm");
        eventBus.post(people);
    }

    public static void main(String[] args) {
        EventBusTest eventBusTest = new EventBusTest();
        eventBusTest.addEvent();
        eventBusTest.addEvent1();
    }
}
