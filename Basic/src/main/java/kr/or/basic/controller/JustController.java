package kr.or.basic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JustController {
	
	@RequestMapping(value = "/just")
	public String getJust(Model model) {
		// Controller가 jsp에 값을 넘겨야 할 때, Model 사용
		// 매개 변수에 넣으면 new Model()을 하지 않아도된다
		// model은 request 객체를 이용해 만들어짐, 정보 전송을 위해 구성됨
		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put("bts1", "지민");
		myMap.put("bts2", "랩괴");
		myMap.put("bts3", "정국");
		
		model.addAttribute("bts", myMap);
		model.addAttribute("myName", "Roze");
		return "test/just";
		// "WEB-INF/views/" + "test/just" + ".jsp"
	}
}
