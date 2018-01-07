package com.kt.airmap.base.mvc.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.kt.airmap.base.message.BaseResponse;
import com.kt.airmap.base.mvc.context.BaseRequestContextHolder;
import com.kt.airmap.base.mvc.message.Message;
import com.kt.airmap.base.mvc.message.MessageAccessor;

@ControllerAdvice(annotations = RestController.class)
public class RestControllerMessageAdvice implements ResponseBodyAdvice<Object> {

	private static final Logger logger = LoggerFactory.getLogger(RestControllerMessageAdvice.class);

	@Override
	public boolean supports(MethodParameter paramMethodParameter, Class<? extends HttpMessageConverter<?>> paramClass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter paramMethodParameter, MediaType paramMediaType,
			Class<? extends HttpMessageConverter<?>> paramClass, ServerHttpRequest paramServerHttpRequest,
			ServerHttpResponse paramServerHttpResponse) {
		
		if (body instanceof BaseResponse) {
			return body;
		} else {
			BaseResponse baseResponse = new BaseResponse();
			MessageAccessor messageAccessor = BaseRequestContextHolder.get().getMessageAccessor();
			if(messageAccessor != null && messageAccessor.getMessageList().size() > 0) {
				Message message = messageAccessor.getMessageList().get(0);
				baseResponse.setResponseCode(message.getCode());
				baseResponse.setMessage(message.getMsg());
				baseResponse.setPaging(message.getPaging());
			}
			baseResponse.setData(body);
			return baseResponse;
		}
	}
	
	/**
	 * 기본 예외처리 헨들러, 다른 예외처리 헨들러에서 처리되지 않은 예외들을 처리함.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleOtherExceptions(Exception ex, HttpServletRequest request) {

		//logProcess(ex,request);
		HttpStatus httpStatus = HttpStatus.OK;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		BaseResponse baseResponse = new BaseResponse();
		ex.printStackTrace();
		return new ResponseEntity<BaseResponse>(baseResponse, headers, httpStatus);
	}
}
