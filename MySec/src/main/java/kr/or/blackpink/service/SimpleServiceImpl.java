package kr.or.blackpink.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SimpleServiceImpl implements SimpleService {

	@Override
	public Integer add(String str1, String str2) {
		log.info("난 ADD");
		return Integer.parseInt(str1)+Integer.parseInt(str2);
	}
	
	@Override
	public Integer minus(String str1, String str2) {
		log.info("난 MINUS");
		return Integer.parseInt(str1)-Integer.parseInt(str2);
	}

}
