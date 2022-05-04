package kr.or.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class InterceptorController {
	
	
	@GetMapping("/form")
	public String ckForm() {
		return "formCk";
	}
	
	
	@RequestMapping("/login")
	public String ckInterceptor() {
		log.info("컨트롤러의 ckInterceptor 호출 확인");
		return "home";
	}
}
