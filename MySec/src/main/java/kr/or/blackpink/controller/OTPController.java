package kr.or.blackpink.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.blackpink.service.GoogleOTPService;

@Controller
@RequestMapping("/otp")
public class OTPController {
	
	@Autowired
	GoogleOTPService otpService;
	
	
	@GetMapping("/verify")
	public String getAuthOTP() {
		
		return "otp/verify";
	}
	
	// 체크하는 메소드임
	@PostMapping("/verify")
	public String postAuthOTP(int inputCode, String userid) {
		otpService.auth(inputCode, userid);
		return "otp/verify";
	}
	
	@GetMapping("/generate")
	public String getGenOTP(Model model) {
		
		SecurityContext secContext = SecurityContextHolder.getContext();
		String username = secContext.getAuthentication().getName();
		String host = "blackpink";
		Map map = otpService.generate(username, host);
		model.addAttribute("otpMap", map);
		
		return "otp/generate";
	}
}
