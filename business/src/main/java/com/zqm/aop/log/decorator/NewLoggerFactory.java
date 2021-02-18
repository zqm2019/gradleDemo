
package com.zqm.aop.log.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: 日志适配器工厂
 * Date: 2021-01-12
 *
 * @author
 */
public class NewLoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        return new NewLogger(LoggerFactory.getLogger(clazz));
    }

}
