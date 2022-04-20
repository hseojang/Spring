package kr.or.basic.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/remind", produces = "text/plain; charset=UTF-8")
// 스프링 3.2버전부터 지원하는 방식 : produces = "text/plain; charset=UTF-8"
public class RemindController {

	// 통상 get방식 요청에 입력화면을 보여주고
	// post 요청에 처리를 해주는 방식으로 많이함

	// @RequestMapping("/remind/minhuk")
	// return 타입이 void인 경우? 접근하는 url 경로에 해당하는 .jsp를 찾는다
	@GetMapping("/minhuk")
	public String getMinhuk(Model model) {
		// 보통 입력 화면을 보여주는 페이지
		return "remindForm";
	}

	@GetMapping("/huk")
	public String ajaxForm() {
		return "hukForm";
	}

	@PostMapping("/huk")
	@ResponseBody // AJAX 전용, jsp찾지 말고 바로 브라우저에 responseBody로 전달하라는 뜻인듯...
					// AJAX 쓰기 전 디버깅 방식
	public String ajaxMinhuk(String nm_first, String nm_second, String nm_sel) {
		// RequestParam 어노테이션이 없으면 매개변수 값이 없다고 예외가 발생하지는 않는다
		// RequestParam 자체로 검증하는 절차가 포함됨
		System.out.println("ck1: " + nm_first);
		System.out.println("ck2: " + nm_second);
		System.out.println("sel: " + nm_sel);
		String result = "";
		try {
			int firNum = Integer.parseInt(nm_first);
			int secNum = Integer.parseInt(nm_second);
			
			if (nm_sel.equals(" ")) {
				// value = "+"인 경우 공백문자로 처리됨
				result = Integer.toString(firNum+secNum);
			}

			if (nm_sel.equals("-")) {
				result = Integer.toString(firNum-secNum);
			}

			if (nm_sel.equals("*")) {
				result = Integer.toString(firNum*secNum);
			}

			if (nm_sel.equals("/")) {
				// DecimalFormat df = new DecimalFormat("#.######");
				// result = df.format((double)firNum/(double)secNum);
				result = String.format("몫 %1$d, 나머지 %2$d", (firNum/secNum), (firNum%secNum));
				// String 문자열에 한글을 리턴할 경우 별도의 인코딩 처리가 필요한 것 같다... 위 참조
			}

		} catch (Exception e) {
			return "잘못된 입력입니다.";
		}
		System.out.println(result);
		return result;
		// return "Mrs."+aaa+" "+bbb; // 브라우저로 바로 전송됨, (return값).jsp를 찾지않음
	}

	// 변수명이 form의 input name과 같으면 자동으로 받아진당
	// 변수명을 다르게 하고 싶으면 RequestParam으로 어떤 값을 어떤 변수로 받을지 등의 설정 가능
	@PostMapping("/minhuk")
	public String postMinhuk(@RequestParam(value = "nm_name", required = false, defaultValue = "Merong") String p_name,
			@RequestParam("nm_song") String p_song, Model model) {
		// 서블릿에서 JSP로 데이타 보낼 때 Model 사용
		List<String> habit = new ArrayList<String>();
		habit.add("see soccer");
		habit.add("see tv");
		habit.add("finally sleep");

		model.addAttribute("name", p_name);
		model.addAttribute("song", p_song);

		// 요걸 즐겨쓰는 사람들도 있음
		/*
		 * ModelAndView mav = new ModelAndView(); mav.addObject("name", p_name);
		 * mav.addObject("song", p_song); mav.setViewName("remind");
		 * 
		 * return mav;
		 */
// list,map은 가장 많이 쓰는 대표 데이터타입이니 맘이 찔리는 사람은 미리 공부해 둡니다
		return "remind";
		/* /WEB-INF/views/remind.jsp */
		/* .java를 고치면 서버 리스타트, jsp는 괜찮음 */
	}

}
