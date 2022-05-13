package kr.or.blackpink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.blackpink.domain.EmailDTO;
import kr.or.blackpink.service.EmailService;

@Controller // 컨트롤러 어노테이션 선언
@RequestMapping("/email") // 공통적인 매핑 주소
public class EmailController {

	@Autowired
	EmailService emailService;

	@GetMapping("/write")
	public String write() {
		return "/email/write";
	}

	@PostMapping("/send")
	// @ModelAttribute를 붙여주면 자동으로 model에 담김
	public String send(@ModelAttribute EmailDTO dto, Model model) {
		try {
			emailService.sendMail(dto);
			model.addAttribute("message", "이메일이 발송되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "이메일 발송 실패...");
		}
		return "/email/write";
	}
}
