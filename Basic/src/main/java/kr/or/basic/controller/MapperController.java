package kr.or.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.basic.mapper.TestMapper;
import kr.or.basic.service.SimpleService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;

@Controller
@Slf4j
public class MapperController {

	@Autowired
	private SimpleService simpleService;
	
	// public TestMapper testMapper; // 컨트롤러에선 직접 Mapper(DAO)를 부르지 않으므로...
	// 객체를 생성하지 않아도 되는 건 스프링이 bean으로 관리하기 때문
	
	
	// @GetMapping(value="/mybatis", produces = "application/json") 을 잭슨래퍼가 상수처럼 처리해줌
	@GetMapping(value="/mybatis", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String test(String buyer_id) {
		return simpleService.select(buyer_id);
	}
	
	@GetMapping(value="/mybatis/insert", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int insert(String sm_id, String sm_name) {
		return simpleService.insert(sm_id, sm_name);
	}
	
	@GetMapping(value="/mybatis/update", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int update(String sm_id, String sm_name) {
		return simpleService.update(sm_id, sm_name);
	}
	
	@GetMapping(value="/mybatis/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int delete(String sm_id) {
		System.out.println("체크!!!");
		return simpleService.delete(sm_id);
	}
	
}
