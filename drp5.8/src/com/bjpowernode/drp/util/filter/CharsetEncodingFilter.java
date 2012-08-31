package com.bjpowernode.drp.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 统一设置字符集
 * @author Administrator
 *
 */
public class CharsetEncodingFilter implements Filter {
	
	private String encoding;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		//设置字符集
		servletRequest.setCharacterEncoding(encoding);
	    //System.out.println("CharsetEncodingFilter.doFilter------begin");
		filterChain.doFilter(servletRequest, servletResponse);
		//System.out.println("CharsetEncodingFilter.doFilter------end");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//取得初始化参数
		this.encoding = filterConfig.getInitParameter("encoding");
		System.out.println("CharsetEncodingFilter.init() encoding=" + this.encoding);
	}
}
