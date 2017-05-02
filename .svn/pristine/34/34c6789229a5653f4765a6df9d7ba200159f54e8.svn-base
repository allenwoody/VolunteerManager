package com.allen.web.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class URLBrokerLauncherInterceptor extends HandlerInterceptorAdapter {
	private static String myTag = "_" + URLBrokerLauncherInterceptor.class.getName();
	private static String tagValue = "1";
	private Map<String, String> brokers;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object tag = request.getAttribute(myTag);
		if (tag == null) {
			for (Map.Entry<String, String> entry : this.brokers.entrySet()) {
				request.setAttribute((String) entry.getKey(), entry.getValue());
			}
			request.setAttribute(myTag, tagValue);
		}
		return super.preHandle(request, response, handler);
	}

	public Map<String, String> getBrokers() {
		return brokers;
	}

	public void setBrokers(Map<String, String> brokers) {
		this.brokers = brokers;
	}
	
}
