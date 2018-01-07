/**
 * <PRE>
 *  Project 3MP.master-api
 *  Package com.kt.airmap.base.filter
 * </PRE>
 * @brief
 * @file LoggerFilter.java
 * @date 2016. 1. 5. 오후 7:31:08
 * @author kim.seokhun@kt.com
 *  변경이력
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        kim.seokhun@kt.com  : 2016. 1. 5.       :            : 신규 개발.
 *
 * Copyright © 2013 kt corp. all rights reserved.
 */
package com.kt.airmap.base.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * <PRE>
 *  ClassName LoggerFilter
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2016. 1. 5. 오후 7:31:08
 * @author kim.seokhun@kt.com
 */
@Component
public class LoggerFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// Nothing to do
	}

	public void destroy() {
		// Nothing to do
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest((HttpServletRequest) request);
		BufferedResponseWrapper wrappedResponse = new BufferedResponseWrapper((HttpServletResponse) response);

		StringBuilder requestLogBuilder = new StringBuilder();
		requestLogBuilder.append("\nrequest [\n");
		requestLogBuilder.append("url : " + ((HttpServletRequest)request).getRequestURL().toString() + "\n");
		requestLogBuilder.append("method : " + ((HttpServletRequest)request).getMethod() + "\n");
		Map<String, String> headers = getRequestHeaders((HttpServletRequest) request);
		Iterator<String> iterator = headers.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			requestLogBuilder.append(key.toLowerCase() + " : " + headers.get(key) + "\n");
		}
		requestLogBuilder.append("parameter : " + getRequestParameters((HttpServletRequest) request) + "\n");
		requestLogBuilder.append("body : \n" + IOUtils.toString(wrappedRequest.getReader()) + "\n]");
		logger.debug(requestLogBuilder.toString());
		wrappedRequest.resetInputStream();
	
		chain.doFilter(wrappedRequest, wrappedResponse);

		StringBuilder responseLogBuilder = new StringBuilder();
		responseLogBuilder.append("\nresponse [\n");
		responseLogBuilder.append("status : " + wrappedResponse.getStatus() + "\n");
		iterator = wrappedResponse.getHeaderNames().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			responseLogBuilder.append(key.toLowerCase() + " : " + wrappedResponse.getHeader(key) + "\n");
		}
//		responseLogBuilder.append("content type : " + wrappedResponse.getContentType() + "\n");
		responseLogBuilder.append("content : \n" + wrappedResponse.getContent() + "\n]");
		logger.debug(responseLogBuilder.toString());
	
	}
		
	
	private Map<String, String> getRequestHeaders(HttpServletRequest request) {
		Map<String, String> headers = new HashMap<String, String>();
		Enumeration<?> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = (String)names.nextElement();
			String value = request.getHeader(name);
			headers.put(name, value);
		}
		return headers;
	}

	private Map<String, String> getRequestParameters(HttpServletRequest request) {
		Map<String, String> parameters = new HashMap<String, String>();
		Enumeration<?> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String)names.nextElement();
			String value = request.getParameter(name);
			parameters.put(name, value);
		}
		return parameters;
	}

	private static class ResettableStreamHttpServletRequest extends HttpServletRequestWrapper {

		private byte[] rawData;
		private HttpServletRequest request;
		private ResettableServletInputStream servletStream;

		public ResettableStreamHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.servletStream = new ResettableServletInputStream();
		}

		public void resetInputStream() {
			servletStream.stream = new ByteArrayInputStream(rawData);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (rawData == null) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(this.request.getInputStream() , StandardCharsets.UTF_8));
					rawData = IOUtils.toByteArray(reader, "UTF-8");
					servletStream.stream = new ByteArrayInputStream(rawData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return servletStream;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			if (rawData == null) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(this.request.getInputStream() , StandardCharsets.UTF_8));
					rawData = IOUtils.toByteArray(reader, "UTF-8");
					servletStream.stream = new ByteArrayInputStream(rawData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return new BufferedReader(new InputStreamReader(servletStream));
		}

		private class ResettableServletInputStream extends ServletInputStream {

			private InputStream stream;

			@Override
			public int read() throws IOException {
				return stream.read();
			}

			/* (non-Javadoc)
			 * @see javax.servlet.ServletInputStream#isFinished()
			 */
			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			/* (non-Javadoc)
			 * @see javax.servlet.ServletInputStream#isReady()
			 */
			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			/* (non-Javadoc)
			 * @see javax.servlet.ServletInputStream#setReadListener(javax.servlet.ReadListener)
			 */
			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub

			}
		}
	}

	public class TeeServletOutputStream extends ServletOutputStream {

    	private final TeeOutputStream targetStream;

    	public TeeServletOutputStream( OutputStream one, OutputStream two ) {
    		targetStream = new TeeOutputStream( one, two);
    	}

		@Override
		public void write(int arg0) throws IOException {
			this.targetStream.write(arg0);
		}

		public void flush() throws IOException {
			super.flush();
			this.targetStream.flush();
		}

		public void close() throws IOException {
			super.close();
			this.targetStream.close();
		}

		/* (non-Javadoc)
		 * @see javax.servlet.ServletOutputStream#isReady()
		 */
		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see javax.servlet.ServletOutputStream#setWriteListener(javax.servlet.WriteListener)
		 */
		@Override
		public void setWriteListener(WriteListener arg0) {
			// TODO Auto-generated method stub

		}
    }

	public class BufferedResponseWrapper implements HttpServletResponse {

    	HttpServletResponse original;
    	TeeServletOutputStream tee;
    	ByteArrayOutputStream bos;

    	public BufferedResponseWrapper(HttpServletResponse response) {
    		original = response;
    	}

    	public String getContent() {
    		if (bos != null) {
    			return bos.toString();
    		}
    		return null;
		}

    	public PrintWriter getWriter() throws IOException {
    		return original.getWriter();
    	}

    	public ServletOutputStream getOutputStream() throws IOException {
    		if( tee == null ){
    			bos = new ByteArrayOutputStream();
    			tee = new TeeServletOutputStream( original.getOutputStream(), bos );
    		}
    		return tee;

    	}

		@Override
		public String getCharacterEncoding() {
			return original.getCharacterEncoding();
		}

		@Override
		public String getContentType() {
			return original.getContentType();
		}

		@Override
		public void setCharacterEncoding(String charset) {
			original.setCharacterEncoding(charset);
		}

		@Override
		public void setContentLength(int len) {
			original.setContentLength(len);
		}

		@Override
		public void setContentType(String type) {
			original.setContentType(type);
		}

		@Override
		public void setBufferSize(int size) {
			original.setBufferSize(size);
		}

		@Override
		public int getBufferSize() {
			return original.getBufferSize();
		}

		@Override
		public void flushBuffer() throws IOException {
			tee.flush();
		}

		@Override
		public void resetBuffer() {
			original.resetBuffer();
		}

		@Override
		public boolean isCommitted() {
			return original.isCommitted();
		}

		@Override
		public void reset() {
			original.reset();
		}

		@Override
		public void setLocale(Locale loc) {
			original.setLocale(loc);
		}

		@Override
		public Locale getLocale() {
			return original.getLocale();
		}

		@Override
		public void addCookie(Cookie cookie) {
			original.addCookie(cookie);
		}

		@Override
		public boolean containsHeader(String name) {
			return original.containsHeader(name);
		}

		@Override
		public String encodeURL(String url) {
			return original.encodeURL(url);
		}

		@Override
		public String encodeRedirectURL(String url) {
			return original.encodeRedirectURL(url);
		}

		@SuppressWarnings("deprecation")
		@Override
		public String encodeUrl(String url) {
			return original.encodeUrl(url);
		}

		@SuppressWarnings("deprecation")
		@Override
		public String encodeRedirectUrl(String url) {
			return original.encodeRedirectUrl(url);
		}

		@Override
		public void sendError(int sc, String msg) throws IOException {
			original.sendError(sc, msg);
		}

		@Override
		public void sendError(int sc) throws IOException {
			original.sendError(sc);
		}

		@Override
		public void sendRedirect(String location) throws IOException {
			original.sendRedirect(location);
		}

		@Override
		public void setDateHeader(String name, long date) {
			original.setDateHeader(name, date);
		}

		@Override
		public void addDateHeader(String name, long date) {
			original.addDateHeader(name, date);
		}

		@Override
		public void setHeader(String name, String value) {
			original.setHeader(name, value);
		}

		@Override
		public void addHeader(String name, String value) {
			original.addHeader(name, value);
		}

		@Override
		public void setIntHeader(String name, int value) {
			original.setIntHeader(name, value);
		}

		@Override
		public void addIntHeader(String name, int value) {
			original.addIntHeader(name, value);
		}

		@Override
		public void setStatus(int sc) {
			original.setStatus(sc);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void setStatus(int sc, String sm) {
			original.setStatus(sc, sm);
		}

		/* (non-Javadoc)
		 * @see javax.servlet.ServletResponse#setContentLengthLong(long)
		 */
		@Override
		public void setContentLengthLong(long arg0) {
			original.setContentLengthLong(arg0);
		}

		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpServletResponse#getHeader(java.lang.String)
		 */
		@Override
		public String getHeader(String arg0) {
			return original.getHeader(arg0);
		}

		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpServletResponse#getHeaderNames()
		 */
		@Override
		public Collection<String> getHeaderNames() {
			return original.getHeaderNames();
		}

		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpServletResponse#getHeaders(java.lang.String)
		 */
		@Override
		public Collection<String> getHeaders(String arg0) {
			return original.getHeaders(arg0);
		}

		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpServletResponse#getStatus()
		 */
		@Override
		public int getStatus() {
			return original.getStatus();
		}
    }
}
