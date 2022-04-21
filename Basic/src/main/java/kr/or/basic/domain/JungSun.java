package kr.or.basic.domain;

public class JungSun {
	private String name;
	private int age;
	
	private Jamba jamba;
	
	public JungSun() {
		System.out.println("기본생성자 나 가죽잠바 멋지징?");
	}
	
	public JungSun(Jamba jamba) {
		System.out.println("잠바 가져왔나봐");
		this.jamba = jamba;
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

	public Jamba getJamba() {
		return jamba;
	}

	public void setJamba(Jamba jamba) {
		System.out.println("set잠바 정말 불리나??");
		this.jamba = jamba;
	}
	
	
}
