package kr.or.basic.controller;

// 많은 개수의 컨트롤러가 존재하니 컨트롤러의 통일성을 위해 인터페이스 생성
public interface Controller {
	// 요청을 핸들하는 메소드
	public String handleRequest();

}
