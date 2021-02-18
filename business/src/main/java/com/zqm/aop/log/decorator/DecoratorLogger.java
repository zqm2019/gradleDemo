
package com.zqm.aop.log.decorator;

import org.slf4j.Logger;

/**
 * description:日志具体装饰类
 * Date: 2021-01-12
 *
 * @author
 */
public class DecoratorLogger extends AbstractDecoratorLogger {

    public Logger logger;

    public DecoratorLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void warn(String msg, Object arg) {
        logger.warn(msg, arg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

}
