package com.databasetuning.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

	@Around("@annotation(InvocationTimeLogger) || @within(InvocationTimeLogger)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getName();

		long startTime = System.currentTimeMillis();
		try {
			log.info("Invoking method {}.{}", className, methodName);
			return joinPoint.proceed();
		} finally {
			long endTime = System.currentTimeMillis();
			long processingTime = endTime - startTime;
			MDC.put("methodName", className + '.' + methodName);
			MDC.put("methodDuration", Long.toString(processingTime));
			log.info("Invoked method {}.{} in {} ms", className, methodName, processingTime);
			MDC.remove("methodDuration");
			MDC.remove("methodName");
		}

	}
}
