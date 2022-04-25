package kr.or.basic.controller;

import java.util.HashMap;
import java.util.Map;

// path -> controller를 연결 시킬 클래스
public class HandlersMapping {

	// String을 Controller로 매핑할 것이므로 Map 생성
	Map<String, Controller> mappings;
	// 제네릭에 인터페이스를 쓰면 해당 인터페이스를 구현하는 모든 클래스를 포함할 수 있다 like 변수 다형성
	
	public HandlersMapping() {
		this.mappings = new HashMap<String, Controller>(); // Map 인터페이스의 HashMap 클래스 객체 생성
	}

	public Map<String, Controller> getMappings() {
		return mappings;
	}
	
}
