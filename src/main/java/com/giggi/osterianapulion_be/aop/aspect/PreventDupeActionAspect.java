package com.giggi.osterianapulion_be.aop.aspect;

import com.giggi.osterianapulion_be.aop.annotation.PreventDuplicateSubmission;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class PreventDupeActionAspect {

    private final ConcurrentHashMap<String, Long> lockMap = new ConcurrentHashMap<>();

    @Around("@annotation(preventDuplicateSubmission)")
    public Object preventDuplicate(ProceedingJoinPoint joinPoint,
                                   PreventDuplicateSubmission preventDuplicateSubmission) throws Throwable {

        String key = buildKey(joinPoint);
        long duration = preventDuplicateSubmission.lockDurationMs();
        long now = System.currentTimeMillis();

        Long existing = lockMap.compute(key, (k, ts) -> {
            if (ts != null && now - ts < duration) return ts;
            return now;
        });

        if (!existing.equals(now)) {
            throw new Error("Azione duplicata, riprova tra poco.");
        }

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            lockMap.remove(key);
            throw e;
        }
    }

    private String buildKey(ProceedingJoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        String ip = getClientIp();
        String argsHash = String.valueOf(Arrays.hashCode(joinPoint.getArgs()));
        return String.format("dupe:%s:%s:%s", ip, method, argsHash);
    }

    private String getClientIp() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = request.getHeader("X-Forwarded-For"); // dietro nginx/proxy
        if (ip == null || ip.isBlank()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}