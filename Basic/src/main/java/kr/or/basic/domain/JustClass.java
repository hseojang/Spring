package kr.or.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@NoArgsConstructor


// @Data:데이터관련 메서드 전부 제공 원하지 않는 생성자 등이 전부 만들어져 디버깅이 어려워짐
// @AllArgsConstructor
public class JustClass {
	
	private String aaa;
	private String bbb;
	private String ccc;
	private String ddd;
	
}
