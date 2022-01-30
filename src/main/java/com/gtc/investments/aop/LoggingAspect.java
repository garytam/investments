package com.gtc.investments.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
//    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class.getName());

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Before("execution(* com.gtc..controller..*(..)))")
    public void logMethodExecutionTime(JoinPoint joinPoint) throws Throwable {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        StringBuffer sb2 = new StringBuffer();
        for ( int i = 0; i < codeSignature.getParameterNames().length; i++) {
            System.out.println(codeSignature.getParameterNames()[i] + ": " + joinPoint.getArgs()[i]);
            sb2.append(codeSignature.getParameterNames()[i] + ": " + joinPoint.getArgs()[i]).append(";");
        }

        StringBuffer sb = new StringBuffer();
        sb.append("before method:").append(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()).append(":")
                .append(sb2.toString());
        logger.debug(sb.toString());
    }

     @AfterReturning(
        value = "execution(* com.gtc..controller..*(..))",
        returning = "returned")
    public void logMethodAfterExecutionTime(JoinPoint joinPoint, Object returned) throws Throwable {
        StringBuffer sb = new StringBuffer();
        sb.append("after: ").append(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())
                .append(" return values -> [").append(returned.toString()).append("]");

         logger.debug(sb.toString());
         MDC.clear();
    }

    @AfterThrowing(pointcut="execution(* com.gtc..*(..))", throwing="exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        String currentMethod = className + "." + methodName;
        String exceptionThrowingMethd = exception.getStackTrace()[0].getClassName() + "." + exception.getStackTrace()[0].getMethodName();

        if (currentMethod.equals(exceptionThrowingMethd)) {

            System.out.println("=============  Exception " + joinPoint.getSignature().getName() + " =============");
            System.out.println("Exception throw by " + className + "." + methodName + "() -----");
//            System.out.println("-----" + exception.getStackTrace()[0].getClassName() + "." + exception.getStackTrace()[0].getMethodName());
            System.out.println("exception message:" + exception.getMessage());
            System.out.println(exception);
            System.out.println("=====================================");
        }
    }
}
