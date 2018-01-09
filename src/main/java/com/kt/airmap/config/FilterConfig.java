package com.kt.airmap.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kt.airmap.base.filter.LoggerFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean getFilterRegistrationBean()
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LoggerFilter());
		return registrationBean;
	}
}