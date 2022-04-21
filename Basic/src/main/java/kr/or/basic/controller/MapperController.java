package kr.or.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.basic.mapper.TestMapper;

@Controller
public class MapperController {

	@Autowired
	public TestMapper testMapper;
	
	// @GetMapping(value="/mybatis", produces = "application/json") 을 잭슨래퍼가 상수처럼 처리해줌
	@GetMapping(value="/mybatis", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String test() {
		return testMapper.getBuyer();
	}
	
}
