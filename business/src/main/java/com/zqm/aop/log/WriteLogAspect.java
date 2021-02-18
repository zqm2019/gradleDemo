package com.zqm.aop.log;

import com.zqm.aop.log.decorator.NewLoggerFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @describe:
 * @author:zqm
 */
@Aspect
@Component
public class WriteLogAspect {

    @Around(value = "execution(* com.zqm..*.*(..)) && (@annotation(annotation))")
    public Object addLog(ProceedingJoinPoint pjp, WriteLog annotation) throws Throwable {
        Logger logger = NewLoggerFactory.getLogger(WriteLogAspect.class);
        String logName = annotation.value().isEmpty() ? pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() : annotation.value();
        Object[] args = pjp.getArgs();
        Object[] logArgs = args;
        if (!StringUtils.isEmpty(annotation.removeArg())) {
            List<Object> list = new ArrayList<>();
            Collections.addAll(list, logArgs);
            //注意超出数组范围或者错误移除数据
            String[] removeArg = annotation.removeArg().split(",");
            for (int i = 0; i < removeArg.length; i++) {
                String s = removeArg[i];
                int j = NumberUtils.toInt(s);
                if (i != 0) {
                    j = j-i;
                }
                if (CollectionUtils.isNotEmpty(list) && list.size() > j) {
                    list.remove(j);
                }
            }
            logArgs = list.toArray();
        }
        Object value;
        try {
            value = pjp.proceed(args);
            if (!annotation.onlyException()) {
                logger.info(logName, logArgs, value, annotation.jsonFormat());
            }
        } catch (Throwable e) {
            logger.warn(logName + "error", logArgs, e, annotation.jsonFormat());
            if (annotation.throwException()) {
                throw e;
            }
            return null;
        }
        return value;
    }
}
