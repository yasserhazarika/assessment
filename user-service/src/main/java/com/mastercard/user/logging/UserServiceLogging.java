package com.mastercard.user.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * This class is used to log every controller methods.
 */
@Aspect
@Component
public class UserServiceLogging {

	private static final Logger log = LogManager.getLogger(UserServiceLogging.class);

	@Around("execution(* com.mastercard.user.controller..*(..)))")
	public Object profileAllMethods(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		// Get intercepted method details
		final String className = methodSignature.getDeclaringType().getSimpleName();
		final String methodName = methodSignature.getName();

		final StopWatch stopWatch = new StopWatch();

		// Measure method execution time
		stopWatch.start();
		final Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();

		// Log method execution time
		log.info("Time taken to execute " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis()
				+ " ms");

		return result;
	}

}
