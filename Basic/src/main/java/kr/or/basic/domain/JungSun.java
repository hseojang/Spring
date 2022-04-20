package kr.or.basic.domain;

public class JungSun {
	private String name;
	private int age;
	
	public JungSun() {
		System.out.println("나 가죽잠바 멋지징?");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
