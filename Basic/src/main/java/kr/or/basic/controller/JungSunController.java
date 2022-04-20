package kr.or.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.basic.domain.JungSun;

@Controller
public class JungSunController {

	@Autowired /* 내가 만들어두라고 한거 연결시키시옹 */
	public JungSun jungSun; /* D.I Dependency Injection, IOC */
//	@RequestMapping(value = "/jungsun",method = RequestMethod.GET)
	@GetMapping("/jungsun")
	public void getJungSun(Model model) {
		jungSun.setName("나정선");
		model.addAttribute("jungsun", jungSun.getName()+"만세");
		System.out.println(jungSun.getName());
	}
}
