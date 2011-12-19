package com.mosso.deimos.common.web.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @author liubin
 *
 */
public class PlatformMappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 记录错误日志
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if(ex != null) {
			logger.error(ex.getMessage(), ex);
		}
		return super.doResolveException(request, response, handler, ex);
	}

	
	
}
