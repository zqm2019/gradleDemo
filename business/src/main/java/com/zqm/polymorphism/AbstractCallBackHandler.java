
package com.zqm.polymorphism;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * node层回调处理
 * Date: 2019-10-23
 *
 * @author zhaqianming
 */
public abstract class AbstractCallBackHandler implements ApplicationContextAware {

    //存放回调类型和处理器的映射关系
    private static final Map<Integer, Class<? extends AbstractCallBackHandler>> TYPE_HANDLER = new HashMap<>();

    private static ApplicationContext applicationContext;


    static {
        TYPE_HANDLER.put(CallBackTypeEnum.CALL_BACK_TYPE_ONE.getType(), CreateRoomCallbackVoHandler.class);
        TYPE_HANDLER.put(CallBackTypeEnum.CALL_BACK_TYPE_TWO.getType(), QrCodeCallbackVoHandler.class);
        TYPE_HANDLER.put(CallBackTypeEnum.CALL_BACK_TYPE_THREE.getType(), QrCodeCallbackVoHandler.class);
        TYPE_HANDLER.put(CallBackTypeEnum.CALL_BACK_TYPE_FOUR.getType(), QrCodeCallbackVoHandler.class);
        //todo 新的回调类型再次添加
    }

    public static AbstractCallBackHandler getCallBackHandler(Integer productType) {
        Class<? extends AbstractCallBackHandler> clazz = TYPE_HANDLER.getOrDefault(productType, null);
        return applicationContext.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public abstract void buildCall(String strContent, AbstractCallBackHandler abstractCallBackHandler);

}
