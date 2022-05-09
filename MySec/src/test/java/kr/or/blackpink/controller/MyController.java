package kr.or.blackpink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/fbi")
@Slf4j
public class MyController {

	@GetMapping("/all") 
	public String getAll(Model model) {
		log.info("all_access");
		model.addAttribute("accessUser", "All");
		return "allaccess";
	}
	
	
	@GetMapping("member") 
	public String getMember(Model model) {
		log.info("member_access");
		model.addAttribute("accessUser", "Member");
		return "memberaccess";
	}
	
	
	@GetMapping("/admin") 
	public String getAdmin(Model model) {
		log.info("admin_access");
		model.addAttribute("accessUser", "Admin");
		return "adminaccess";
	}
	
	
	@GetMapping("/roze") 
	public String getRoze(Model model) {		
		log.info("roze_access");
		model.addAttribute("accessUser", "Roze");
		return "rozeaccess";
	}
}
