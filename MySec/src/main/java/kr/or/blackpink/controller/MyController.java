package kr.or.blackpink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.blackpink.domain.MemberVO;
import kr.or.blackpink.security.CustomUser;
import kr.or.blackpink.security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/fbi")
@Slf4j
public class MyController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
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
		
		// jsp에서 sec:~~ 태그 라이브러리 사용해 security 정보를 읽어온 것 처럼
		// java 서버에서 사용할 때는 이렇게
		SecurityContext secContext = SecurityContextHolder.getContext();
		
		log.info("secContext : " + secContext.getAuthentication().getDetails());
		log.info("secContext : " + secContext.getAuthentication().getName());
		log.info("secContext : " + secContext.getAuthentication().getAuthorities());
		log.info("secContext : " + secContext.getAuthentication().getPrincipal());
		
		// member Detail 가져오는 방법
		CustomUser myuser = 
				(CustomUser) customUserDetailsService.loadUserByUsername(secContext.getAuthentication().getName()); 
		//CustomUser로 캐스팅해 받지 않으면 사용자 정보를 불러오기 위해 임의로 만든 getMember()를 사용할 수 없음
		MemberVO memberVO = myuser.getMember();
		
		log.info("memberDetail : " + memberVO.getUserid());
		log.info("memberDetail : " + memberVO.getUserName());
		log.info("memberDetail : " + memberVO.getUserpw());
		log.info("memberDetail : " + memberVO.getAuthList().toString());
		
		model.addAttribute("accessUser", "Admin");
		return "adminaccess";
	}
	
	
	@GetMapping("/roze") 
	public String getRoze(Model model) {		
		log.info("roze_access");
		model.addAttribute("accessUser", "Roze");
		return "rozeaccess";
	}
	
	@GetMapping("/admin2") 
	public String getAdmin2(Model model) {
		log.info("admin_access");
		model.addAttribute("accessUser", "Admin");
		return "userDetailsCK";
	}
}
