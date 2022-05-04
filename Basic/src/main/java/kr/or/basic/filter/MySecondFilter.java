package kr.or.basic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
// @WebFilter(urlPatterns = "/*")
public class MySecondFilter implements Filter{

	// 필터의 기본 메서드 init, doFilter, destroy
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("나는야 두번째 필터 탄생");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("시작2");
		request.setAttribute("merong2", "2응");
		chain.doFilter(request, response); // Filter Chain의 핵심(필터가 여러개일 때)
		log.info("종료2"); // response 끝
	}
	
	@Override
	public void destroy() {
		log.info("나의 죽음을 알리지 마라222");
	}

}
