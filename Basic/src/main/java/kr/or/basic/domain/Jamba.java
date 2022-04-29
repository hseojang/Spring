package kr.or.basic.domain;

import org.springframework.stereotype.Component;

@Component
public class Jamba {
	public String color;
	
	Jamba() {
		System.out.println("Jamba 저절로 실행됨");
		this.color = "black";
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		System.out.println("넘어온 값 : " + color);
		this.color = color;
	}
	
	

}
