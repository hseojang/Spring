package kr.or.basic.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.basic.domain.Minhuk;

@Controller
@RequestMapping("/gangkuk")
public class RequestBodyController {

	// 사용자에게 입력폼을 보여주려 할 때는 get방식으로 받는다
	@GetMapping("/inputForm")
	public String inputForm() {
		return "inputForm";
	}
	
	@PostMapping(value="/inputForm1", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String postForm(	String nm_name, 
							String nm_special, 
							@RequestParam("nm_girl") String nm_girl2) { //@RequestParam으로 변수명을 폼의 name 속성에 지정된 것 외에 다른 이름으로도 받을 수 있음
		
		return nm_name + nm_special + nm_girl2;
	}
	
	@PostMapping(value="/inputForm2", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Minhuk postForm2 (Minhuk minhuk) { //VO로 받기
		// 컨텍스트에 등록하지 않으면 딱히 자동으로 생성되지 않음
		// 해당 객체가 필요할 때 스프링이 생성하고 세팅함(servlet-context 설정에 따라 컴포넌트로 자동으로 읽어오는 듯)
		return minhuk;
	}
	
	@PostMapping(value="/inputForm3", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String postForm3 (@RequestBody String query) throws Exception { 
		// @RequestBody를 매개변수 String으로 하면 쿼리스트링 형식으로 받게 함
		return URLDecoder.decode(query, "UTF-8");
	}
	
	@PostMapping(value="/inputForm4", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map postForm4 (@RequestBody Map<String, String> map) { 
		// @RequestBody는 쿼리스트링 형식으로 받게 함
		// 이방식은 Form에서 바로 submit할 때엔 안됨
		System.out.println(map.toString()); //map 내용 확인용
		return map;
	}
	
	@PostMapping(value="/inputForm5", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map postForm5 (@RequestBody Map<String, List<String>> map) {
		// @RequestBody는 POST 방식에서만 쓸 수 있음
		// 받을 데이터 타입이 섞여있을 때에는 Object를 사용하면 되지만... 타입을 일관성있게 통일시켜 받는게 더좋음
		// ajax는 XmlHttpRequest 객체와 요청을 주고받는 개념이기에 비동기적 통신이 가능함
		List<String> mylist = map.get("nm_girl");
		for (String myval : mylist) {
			System.out.println(myval);
		}
		return map;
	}
	
	@PostMapping(value="/inputForm6", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String postForm6 (Minhuk minhuk) { 
		List<MultipartFile> files = minhuk.getFiles();
		for (MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());
		}
		return "OK"; // file 객체가 포함된 걸 리턴하면 JSON으로 변환하는 과정에서 직렬화 오류가 생긴다
	}
	
}
