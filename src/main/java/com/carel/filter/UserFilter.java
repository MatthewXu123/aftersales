
package com.carel.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 8, 2020
 */
//@Component
public class UserFilter extends OncePerRequestFilter{

private static final String[] FILTER_URL_GROUP = {"/u"};
	
	
	/* @author Matthew Xu
	 * @date Sep 16, 2019
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
//		String url = req.getRequestURI().substring(req.getContextPath().length());
//		if (requireFiltered(url)) {
//			Authentication authentication = jwtService.verifyAuthentication(req);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		} 
//		chain.doFilter(req, resp);
//		return;
	}
	
	/**
	 * 
	 * Description: If the url need to be filtered, return true. If otherwise, return false.
	 * @param url
	 * @return
	 * @author Matthew Xu
	 * @date Jan 12, 2020
	 */
	private boolean requireFiltered(String url){
		return ArrayUtils.toString(FILTER_URL_GROUP).contains(url);
	}
}
