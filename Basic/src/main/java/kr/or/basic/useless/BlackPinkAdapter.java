package kr.or.basic.useless;

/* Adapter 패턴 */

// 인터페이스 - 빈 구현체(=어댑터) - 클래스
// 빈 구현체인 어답터를 상속받아 필요한 메소드를 오버라이드해 쓰는 식
// 인터페이스의 일부만을 구현하고 싶을 때에 이용, 구현 해도 되고 안해도 되는 메서드들이 많을 수록 효과적
public class BlackPinkAdapter implements BlackPink {

	@Override
	public void init() {
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void destroy2() {

	}
	
}
