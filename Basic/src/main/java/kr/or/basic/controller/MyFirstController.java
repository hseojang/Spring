package kr.or.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blackpink")
public class MyFirstController {
	
	@RequestMapping("/roze")
	public String getAAA(@RequestParam("name") String myStar,
			@RequestParam("age") int starAge) {
		System.out.println("여기 오나요?");
		System.out.println("name: "+ myStar + " age: "+ starAge);
		return "roze";
	}
	
	// post 방식에만 대응하려는 경우
	@RequestMapping(value="/jenni", method = RequestMethod.POST) // url만 쓸 경유 'value=' 를 생략해도 된다
	public String getJenni() {
		System.out.println("여긴 post");
		return "roze";
	}

}
