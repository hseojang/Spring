package kr.or.yourboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest")
public class RestController {

	@PutMapping("/put")
	@ResponseBody
	public String putTest() {
		return "Put Comming Soon";
	}
	
	@DeleteMapping("/delete")
	@ResponseBody
	public String deleteTest() {
		return "Delete Comming Soon";
	}
	
}
