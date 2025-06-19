package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.dao.StudentDAO.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[AOP] Before method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.dao.StudentDAO.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[AOP] After method: " + joinPoint.getSignature().getName());
    }
}