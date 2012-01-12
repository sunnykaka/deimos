package com.mosso.deimos.common.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;

import com.mosso.deimos.user.model.User;

/**
 * 
 * @author liubin
 *
 */
public class LoginFilter implements Filter {
	
	protected FilterConfig filterConfig;
	
	protected String urlStr ;
	
	//匹配不带*通配符的url
	protected Set<String> normalUrls = new HashSet<String>();
	
	//匹配带*通配符的url
	protected Set<String> patternUrls = new HashSet<String>();
	
	private AntPathMatcher matcher = new AntPathMatcher();
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest r = ((HttpServletRequest)request);
		HttpSession session = r.getSession();
		String base = r.getContextPath();
		String url = r.getRequestURI();
		boolean exclude = false;
		if(normalUrls.contains(url)) {
			exclude = true;
        } else {
        	for(String patternUrl : patternUrls) {
        		if(StringUtils.isBlank(patternUrl)) continue;
        		if(matcher.match(patternUrl, url)) {
        			exclude = true;
        			break;
        		}
        	}
        }
        if(exclude) {
        	chain.doFilter(request, response);
        }else{
        	User user = (User)session.getAttribute(User.SESSION_SCOPE_ACCOUNT_KEY);
        	if(user == null) {
    			if(url.startsWith(r.getContextPath() + "/ajax")) {
    				((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    			} else {
    				((HttpServletResponse)response).sendRedirect(base + "/login.action");
    			}
    		}else{
    			chain.doFilter(request, response);
    		} 
        }
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		urlStr = filterConfig.getInitParameter("URL_EXCEPT");
		String[] urlExcept = urlStr.split(",");
	    String base = filterConfig.getServletContext().getContextPath();
	    for(int i = 0; i < urlExcept.length; i++){
	    	if(!urlExcept[i].contains("*")){
	    		normalUrls.add(base + urlExcept[i]);
	    	}else{
	    		patternUrls.add(base + urlExcept[i]);
	    	}
            
        }
	}
	

}
