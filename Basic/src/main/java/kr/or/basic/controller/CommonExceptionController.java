package kr.or.basic.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/*예외 처리 모으기*/
@ControllerAdvice
public class CommonExceptionController {

	// 예외 처리
	@ExceptionHandler(NullPointerException.class) // 널포인터익셉션 클래스인 예외를 핸들링 시키는 어노테이션
	public String nullHandler(NullPointerException ne, Model model) { // 익셉션을 매개변수로 사용할 수도 있다
		System.out.println("error msg : " + ne.getMessage());
		model.addAttribute("exception", ne.getMessage());
		/* NullPointerException의 메시지도 NULL이다...*/
		return "error/error";
	}
	
	// 예외 처리
	@ExceptionHandler(NoHandlerFoundException.class) // 핸들러가 없는 익셉션?
	public String notFoundHandler(NoHandlerFoundException nhf, Model model) {
		System.out.println("error msg : " + nhf.getMessage());
		model.addAttribute("exception", "ppp" + nhf.getMessage() + "ppp");
		return "error/error";
	}
	
	// 예외 처리
	@ExceptionHandler(Exception.class)
	public String nullHandler(Exception e, Model model) {
		System.out.println("error msg : " + e.getMessage());
		model.addAttribute("exception", e.getMessage());
		return "error/error";
	}
	
	
}
