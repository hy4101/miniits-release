package com.miniits.base.utils;

import com.google.common.base.Joiner;
import com.miniits.base.dao.LogRepository;
import com.miniits.base.model.entity.Log;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by wq on 2017/3/2.
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogService logService;

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.miniits.base.controller..*(..))")
    public void anyOperation(JoinPoint point) {
        Log log = new Log();
        logService.search(new Pageable(1,15));
        String uri = RequestUtil.getRequest().getRequestURI();
        log.setMethod(RequestUtil.getRequest().getMethod());
        log.setMethodName(point.getSignature().getName());
        log.setIp(RequestUtil.getRequest().getRemoteAddr());
        if (uri.indexOf("admin") == -1) {
            try {
                String json = AddressUtils.getAddresses(log.getIp());
                Map<String, String> address = AddressUtils.getAddress(json);
                log.setCountry(address.get("country"));
                log.setRegion(address.get("region"));
                log.setCity(address.get("city"));
            } catch (Exception e) {
            }
        }
        log.setUri(RequestUtil.getRequest().getRequestURI());
        String params = Joiner.on(",").withKeyValueSeparator("=").join(RequestUtil.getRequestParams());
        log.setParams(params);
        try {
            logRepository.save(log);
        } catch (Exception e) {
        }
        logger.info("{}", log);
    }
}
