package com.fromme.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class URICheckFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getHeader("referer") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/app/main/error/404.jsp");
			dispatcher.forward(request, response);			
		}else {			
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
