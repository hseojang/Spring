package kr.or.basic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/*
	실제 소스를 확인하니 HandlerInterceptor 인터페이스에 default를 이용해 미리 구현돼있음. 어댑터 or 인터페이스 어느쪽을 받아와도 무방
 */

@Slf4j
public class MyFirstInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getParameter("nm_admin").equalsIgnoreCase("admin")) {
			log.info("너 관리자 맞구나");
			return true; // 가던 길 가세요
		}

		log.info("인터셉터 반환값 : false");
		response.sendRedirect("/basic/user/form");
		return false; // 이동을 막기
	} // form 페이지에서 전송한 값을 보고 일치하면 원래 보내던 경로로 보내줌(true), false면 이동을 막음. 
	//  preHandle 단계에서 false로 인터셉터가 블락하면 url주소 표시는 바뀌지만 화면엔 아무것도 포워드되지 않음.

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		log.info("언제 실행되요");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("난 또 언제 실행되어요?");
	}

}
