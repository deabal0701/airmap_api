package com.kt.airmap.external.comm.log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;

@Component
@org.aspectj.lang.annotation.Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
public class LoggingBatchHistoryAspect {
	 
	@Autowired
	KMAMapperDao kmaMapperDao;
	
	@Before("execution(* com.kt.airmap.external.kma.service.forecast.*.*(..))")
	public void doSomethingBefore(JoinPoint joinPoint) { 
		//TODO : API Call History
		
	}

	@After("execution(* com.kt.airmap.api.sif.master.service..*.*(..))") 
	public void doSomethingAfter(JoinPoint joinPoint) { 
		//TODO : API Call History
	}

}
