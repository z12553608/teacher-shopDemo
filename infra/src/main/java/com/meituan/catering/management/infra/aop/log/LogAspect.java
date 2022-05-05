package com.meituan.catering.management.infra.aop.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.meituan.catering.management.shop..*.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doLogTheTargetProxyType(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getThis();
        if (AopUtils.isJdkDynamicProxy(target)) {
            log.info("Call JDK Proxy: {}", target);
        } else if (AopUtils.isCglibProxy(target)) {
            log.info("Call CGLIB Proxy: {}", target);
        } else {
            log.info("Call Original Object: {}", target);
        }
        return joinPoint.proceed();
    }

}
