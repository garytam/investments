package com.gtc.investments.aop;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodToMDC {

    private final Log log = LogFactory.getLog(MethodToMDC.class);

    @Before("execution(public * *(..)) && @annotation(com.gtc.investments.aop.MethodMDC)")
    public void addMethodNameToMDC(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String methNameVal = MDC.get("MethodName");
        if(StringUtils.isEmpty(methNameVal)) {
            methNameVal = "";
        } else {
            methNameVal = methNameVal.replace("'", "");
            methNameVal += ">";
        }
        methNameVal += methodName;
        MDC.put("MethodName", "'" + methNameVal + "'");
    }

    @After("execution(public * *(..)) && @annotation(com.gtc.investments.aop.MethodMDC)")
    public void removeMethodNameToMDC(JoinPoint joinPoint) {
        String methNameVal = MDC.get("MethodName");
        int pos = -1;
        if(!StringUtils.isEmpty(methNameVal)) {
            methNameVal = methNameVal.replace("'", "");
            pos = methNameVal.lastIndexOf(">");
        }
        if(pos >= 0) {
            methNameVal = methNameVal.substring(0, pos);
        } else {
            //leave it. when the thread will be re-used then the MethodName will be overwritten
        }

        MDC.put("MethodName", "'" + methNameVal + "'");
    }

}
