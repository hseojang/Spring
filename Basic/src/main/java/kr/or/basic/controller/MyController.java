package kr.or.basic.controller;

public class MyController implements Controller {

	@Override
	public String handleRequest() {

		/*
		 * 뭔가 처리하는 부분
		 */
		return "aaa"; // view 이름을 리턴
	}
	
}
