package com.kt.airmap.etc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kt.airmap.base.common.DateUtil;
import com.kt.airmap.external.task.ScheduledTasks;

public class TestMainClass {
	
	 private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	 private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			 
	public static void main(String[] args) {
		
		  log.info("The time is now {}", dateFormat.format(new Date()));
		   
		  String str1 =  DateUtil.getDayCom(0);
		  System.out.println(str1);
	}
}
