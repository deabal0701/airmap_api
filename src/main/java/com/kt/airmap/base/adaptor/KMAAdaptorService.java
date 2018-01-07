package com.kt.airmap.base.adaptor;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kt.airmap.base.interceptor.LoggingRestTemplateMessageInterceptor;

@Service
public class KMAAdaptorService {

	private static final Logger logger = LoggerFactory.getLogger(KMAAdaptorService.class);

	@Value("#{'${kma.url}'.split(',')}")
	private List<String> urls;
	@Value("${kma.contextPath}")
	private String contextPath;
	
	private static int roundRobinCount = 1;
    
    private int getRoundRobinCount(int size) {
    	if (roundRobinCount > size) {
    		roundRobinCount = 1;
    	}
    	return roundRobinCount++;
    }
    
    private URI getUrl(String path) {
    	if (urls != null && urls.size() > 0) {
			return getUrl(urls.get(getRoundRobinCount(urls.size()) - 1) + contextPath, path);
		}
    	return null;
    }

    protected URI getUrl(String baseUrl, String path) {
        return getUrl(baseUrl, path, null);
    }
    
	private URI getUrl(String path, Map<String, String> param) {
		if ((this.urls != null) && (this.urls.size() > 0)) {
			return getUrl(urls.get(getRoundRobinCount(urls.size()) - 1) + contextPath, path, param);
		}
		return null;
	}

    protected URI getUrl(String baseUrl, String path, Map<String, String> param) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl).path(path);
        if (param != null) {
            Iterator<String> iteratortor = param.keySet().iterator();
            while (iteratortor.hasNext()) {
                String key = (String) iteratortor.next();
                builder.queryParam(key, param.get(key));
            }
        }
        return builder.build(true).toUri();
    }

    protected <T> HttpEntity<T> getRequestEntity(T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<T>(body, headers);
    }

    private RestTemplate getRestTemplate() {
    	HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    	factory.setConnectTimeout(5 * 1000);
    	factory.setReadTimeout(10 * 1000);
    	RestTemplate restTemplate = new RestTemplate(factory);
    	
    	List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
    	ris.add( new LoggingRestTemplateMessageInterceptor());
    	restTemplate.setInterceptors(ris);
    	restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    	
    	return restTemplate;
    }
   
	public <T> T get(String path, Class<T> responseType) {
		return (T) get(path, null, responseType);
	}

	public <T> T get(String path, Map<String, String> param, Class<T> responseType) {
		try {
			return (T) getRestTemplate().getForObject(getUrl(path, param), responseType);
		} catch (ResourceAccessException e) {
			logger.error(e.getMessage(), e);
			try {
				return (T) getRestTemplate().getForObject(getUrl(path, param), responseType);
			} catch (ResourceAccessException e1) {
				logger.error(e.getMessage(), e);
			}
		}
		return (T) getRestTemplate().getForObject(getUrl(path, param), responseType);
	}
  
}
