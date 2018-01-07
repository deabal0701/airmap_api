package com.kt.airmap;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kt.airmap.base.filter.LoggerFilter;

@Configuration
@EnableWebMvc  
@ComponentScan(basePackages = {Const.PACKAGE})
@EnableScheduling
@SpringBootApplication
@EnableBatchProcessing
@PropertySources({
	@PropertySource(value="file:${properties.airmap.path}/application.properties")
})

public class AirMapApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	public static void main(String[] args) {
		
		 SpringApplication.run(AirMapApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AirMapApplication.class);
    }

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		Dynamic servlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");
		servlet.setMultipartConfig(new MultipartConfigElement("", 1024*1024*50, 1024*1024*100, 1024*1024*10));
		servlet.setAsyncSupported(true);

		// CharacterSet Encoding
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic charEncRegi = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        charEncRegi.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        charEncRegi.setAsyncSupported(true);

        FilterRegistration.Dynamic loggerFilter = servletContext.addFilter("loggerFilter", new LoggerFilter());
        loggerFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        loggerFilter.setAsyncSupported(true);
        
    //    FilterRegistration.Dynamic dbLoggingFilter = servletContext.addFilter("dbLoggingFilter", new DBLoggingFilter());
    //    dbLoggingFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    //    dbLoggingFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/system/*");
    //    dbLoggingFilter.setAsyncSupported(true);
        
  
		super.onStartup(servletContext);
	}
}