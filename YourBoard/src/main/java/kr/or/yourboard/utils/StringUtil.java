package kr.or.yourboard.utils;

// 가변인자를 사용하는 Util 클래스 만들기
public class StringUtil {

	public static boolean containsOr(String target, String... args) { 
		// 가변 인자는 하나만 쓸 수 있고, 매개변수 나열의 맨 끝에만 위치해야 한다
		if(target==null) {return false;}
		if(args==null) {return false;}
		
	 // for (int i=0; i<args.length; i++) {
		for (String s : args) {
			
		 // if (target.contains(args[i])) {
			if(target.contains(s)) {
				return true;
			}
		}
		return false;
	}
	
}
