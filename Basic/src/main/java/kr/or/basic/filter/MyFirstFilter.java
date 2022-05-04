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

/* 톰캣에서 Spring DispatcherServlet으로 넘어가는 중간에 하는게 Filter 
 * 보통 보안 관련 접근로그를 남기거나 특정 URL 패턴에 공통 적용이 필요한 기능을 구현할 때 사용
 * Spring Security가 (필터와 인터셉터를 이용해서 만들어짐)
 * 필터가 조금 넓은 영역에 적용된다면, 인터셉터는 좀더 구체적인 좁은 영역에 적용 
 * 인터셉터는 was에서 디스패처가 불리고 스프링의 영역으로 넘어간 뒤에 적용되는 것
 */
@Slf4j
// @WebFilter("/*") // 어노테이션은 필터 체인의 순서를 알 수 없음, 괄호 안에 지정되는 기본값은 urlpattern
public class MyFirstFilter implements Filter{

	// 필터의 기본 메서드 init, doFilter, destroy
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("나는야 첫번째 필터 탄생");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("시작");
		request.setAttribute("merong", "응");
		chain.doFilter(request, response); // Filter Chain의 핵심(필터가 여러개일 때)
		// 다음 필터에 요청과 응답을 넘겨주는 식 - 필터 안에서 필터 호출, 호출된 필터가 종료되어야 그 필터를 호출한 필터도 종료될 수 있는 구조
		log.info("종료"); // response 끝
	}
	
	@Override
	public void destroy() {
		log.info("나의 죽음을 알리지 마라");
	}

}
