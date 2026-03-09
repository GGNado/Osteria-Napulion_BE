package com.giggi.osterianapulion_be.aop.exception;

public class RateLimiterException extends RuntimeException {
    public RateLimiterException(String message) {
        super(message);
    }
}
