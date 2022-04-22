package kr.or.basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.basic.mapper.TestMapper;


//@Service // 스프링에게 객체 생성을 의뢰하는, 이 클래스가 실제 서비스 구현체임을 명시적으로 표현
public class SimpleServiceImpl2 implements SimpleService {
	
	@Autowired
	private TestMapper testMapper;

	@Override
	public String select(String buyer_id) {
		return "메렁";
	}

	@Override
	public int insert(String sm_id, String sm_name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String sm_id, String sm_name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String sm_id) {
		// TODO Auto-generated method stub
		return 0;
	}


	

}
