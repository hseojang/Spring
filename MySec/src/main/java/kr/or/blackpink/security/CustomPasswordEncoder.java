package kr.or.blackpink.security;

import org.springframework.security.crypto.password.PasswordEncoder;

// 껍데기만 암호화하는 것 처럼 보이는 Encoder 제작
public class CustomPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {

		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		// 단순 문자열 비교
		return rawPassword.toString().equals(encodedPassword);
	}

	
}
