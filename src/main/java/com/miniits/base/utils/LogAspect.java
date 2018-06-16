package com.miniits.base.utils;

import com.google.common.base.Joiner;
import com.miniits.base.dao.LogRepository;
import com.miniits.base.model.entity.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yjj on 2017/3/2.
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogRepository logRepository;

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.miniits.base.controller..*(..))")
    public void anyOperation(JoinPoint point) {
        Log log = new Log();
        log.setMethod(RequestUtil.getRequest().getMethod());
        log.setMethodName(point.getSignature().getName());
        log.setIp(RequestUtil.getRequest().getRemoteAddr());
        log.setUri(RequestUtil.getRequest().getRequestURI());
        String params = Joiner.on(",").withKeyValueSeparator("=").join(RequestUtil.getRequestParams());
        log.setParams(params);
        logRepository.save(log);
        logger.info("{}", log);
    }
}
