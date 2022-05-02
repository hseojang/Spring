package kr.or.basic.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.basic.domain.BlackPink;
import kr.or.basic.domain.JungSun;
import kr.or.basic.domain.JustClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
@PropertySource("classpath:config/properties/dbsetting.properties")
public class HomeController {
	
	// private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	// slf4j로 대체함
	
	JustClass justTest = new JustClass();
	
	//@Autowired
	//private JungSun jungsun;
	
	@Value("${db.driverClassName}")
	private String driverName;
	
	@Autowired
	@Qualifier("jungsun") // 여기서 생성되는건 xml에서 정의된 bean
	public BlackPink blackpink;
	// public BlackPink jungsun; 으로 쓰면 에러 안남
	// 스프링이 변수명과 같은 bean id(=객체이름)에 해당하는 객체로 맞추어준다
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		log.debug("이것은 디버깅");
		log.info("이것은 인포");
		log.warn("이것은 warn");
		log.error("이것은 에러");
		
		
		log.info("Welcome home! The client locale is {}.", locale);
		// slf4j에서 만든 logger 객체 log
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "ckUploadTest";
	}
	
}
