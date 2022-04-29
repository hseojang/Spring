package kr.or.basic.domain;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("jungsun")
@Component /*Spring MVC에서 제공해주는 어노테이션, Bean 생성*/
public class JungSun implements BlackPink {
	private String name;
	private int age;
	
	private Jamba jamba;
	
	public JungSun() {
		System.out.println("기본생성자 나 가죽잠바 멋지징?");
	}
	
	public JungSun(Jamba jamba) {
		System.out.println("잠바 가져왔나봐 잠바 생성자");
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
	
	@PostConstruct // init-method에 해당
	public void initStart() {
		System.out.println("생성된 뒤에 할 일?");
	}
	
	@PreDestroy // destroy-method에 해당
	public void destroyMethod() {
		System.out.println("나 갈께? 언제갈진 몰랑?");
	}
	
}
