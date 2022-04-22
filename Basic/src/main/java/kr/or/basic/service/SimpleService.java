package kr.or.basic.service;

import org.springframework.stereotype.Service;

/* 인터페이스 사용은 결합도를 낮춘다 */
/* 관례적으로 DAO와 이름을 같게 만듬. 다만 if 등의 판별식이 들어갈 수 있다는 점이 다름*/

public interface SimpleService {
	
	
	public String select(String buyer_id);
	public int insert(String sm_id, String sm_name);
	public int update(String sm_id, String sm_name);
	public int delete(String sm_id);

}
