package com.giggi.osterianapulion_be.aop.aspect;

import com.giggi.osterianapulion_be.aop.annotation.RateLimit;
import com.giggi.osterianapulion_be.aop.exception.RateLimiterException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimitAspect {

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {

        String key = buildKey(joinPoint);
        Bucket bucket = buckets.computeIfAbsent(key, k -> createBucket(rateLimit));

        if (!bucket.tryConsume(1)) {
            throw new RateLimiterException("Troppe richieste, rallenta.");
        }

        return joinPoint.proceed();
    }

    private Bucket createBucket(RateLimit rateLimit) {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                        rateLimit.requests(),
                        Refill.intervally(rateLimit.requests(), Duration.ofSeconds(rateLimit.durationSeconds()))
                ))
                .build();
    }

    private String buildKey(ProceedingJoinPoint joinPoint) {
        String ip = getClientIp();
        String method = joinPoint.getSignature().toShortString();
        return String.format("rate:%s:%s", ip, method);
    }

    private String getClientIp() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}