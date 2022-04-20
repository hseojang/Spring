package kr.or.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AjaxController {
	
	@ResponseBody	// jsp로 찾아가지 않고 그대로 hello를 브라우저로 보낸다!! 엄청 중요
	@RequestMapping("/ajax") // ajax를 요청하면
	public List<Map<String, String>> rawResponse() {
		Map<String, String> myMap = new HashMap<String, String>(); 
		List<Map<String, String>> myList = new ArrayList<Map<String, String>> ();
		
		myMap.put("star1", "roze");
		myMap.put("star2", "jenni");
		myMap.put("star3", "lisa");
		myMap.put("star4", "jisu");
		
		myList.add(myMap);
		return myList;
		
		
	}

}
