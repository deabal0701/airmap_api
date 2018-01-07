package com.kt.airmap.base.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingRestTemplateMessageInterceptor  implements ClientHttpRequestInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoggingRestTemplateMessageInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    
    	ClientHttpResponse response = execution.execute(request, body);
        log(request,body,response);
        return response;
    }

    private void log(HttpRequest request, byte[] body, ClientHttpResponse response) throws IOException {
        //do logging
    	InputStream is = response.getBody();
		byte[] bodyData = IOUtils.toByteArray(is);
		
		//Request Log
		StringBuilder reqLog = new StringBuilder();
		reqLog.append("\n");
		reqLog.append(" >>> [REQUEST] -------------------------------------------->");
		reqLog.append("\n");
		reqLog.append(" >>> Method : " + request.getMethod());
		reqLog.append("\n");
		reqLog.append(" >>> URI : " + request.getURI());
		reqLog.append("\n");
		reqLog.append(" >>> Request Header " + request.getHeaders());
		reqLog.append("\n");
		reqLog.append(" >>> Request Body : " +  new String(body, Charset.defaultCharset()));
		reqLog.append("\n");
		
		log.info(reqLog.toString());
		//Response Log
		StringBuilder resLog = new StringBuilder();
		resLog.append("\n");
		resLog.append(" >>> [RESPONSE] <--------------------------------------------");
		resLog.append("\n");
		resLog.append(" >>> Status code : " + response.getStatusCode());
		resLog.append("\n");
		resLog.append(" >>> Response Header " + response.getHeaders());
		resLog.append("\n");
		resLog.append(" >>> Response Body : " + new String(bodyData, Charset.defaultCharset()));
		resLog.append("\n");
	
		log.info(resLog.toString());
    }
}