package com.cong.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

import com.cong.annotation.ReadOnlyAnnotation;
import com.cong.config.DataSourceContextHolder;


//todo

@Aspect
public class ReadOnlyAnnotationInterceptor implements Ordered{
	
	
	/*@Around("@annotation(readOnlyAnnotation)")
    public Object proceed(ProceedingJoinPoint joinPoint, ReadOnlyAnnotation readOnlyAnnotation) throws Throwable {
 
        try {
        	DataSourceContextHolder.setDB(DataSourceContextHolder.DEFAULT_SEC_DS);
            Object proceed = joinPoint.proceed();
            return proceed;
 
        } finally {
        	DataSourceContextHolder.clearDB();
        }
    }*/

	
	 @Override
	    public int getOrder() {
	        return 0;
	    }


}
