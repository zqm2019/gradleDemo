
package com.zqm.polymorphism;

import org.springframework.stereotype.Service;

/**
 * TODO: description
 * Date: 2019-10-25
 *
 * @author zhaqianming
 */
@Service
public class PolymorphismService {

    public boolean createCallBack(int type, String callBackString) {
        AbstractCallBackHandler abstractCallBackHandler = AbstractCallBackHandler.getCallBackHandler(type);
        abstractCallBackHandler.buildCall(callBackString, abstractCallBackHandler);
        return true;
    }

}
