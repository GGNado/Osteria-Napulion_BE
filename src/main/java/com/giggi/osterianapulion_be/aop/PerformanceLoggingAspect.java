package com.giggi.osterianapulion_be.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceLoggingAspect {

    @Around("execution(* com.giggi.osterianapulion_be.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;

            log.info("Metodo {} eseguito in {} ms",
                    joinPoint.getSignature().toShortString(),
                    duration);

            return result;
        } catch (Throwable ex) {
            long duration = System.currentTimeMillis() - start;

            log.error("Metodo {} fallito dopo {} ms - errore: {}",
                    joinPoint.getSignature().toShortString(),
                    duration,
                    ex.getMessage());

            throw ex;
        }
    }
}