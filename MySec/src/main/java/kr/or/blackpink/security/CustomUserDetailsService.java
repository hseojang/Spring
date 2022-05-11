package kr.or.blackpink.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.blackpink.domain.MemberVO;
import kr.or.blackpink.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// return타입이 UserDetails라서 memberVO를 Userdetails로 바꿔야함
		// UserDetails를 구현한 스프링 내장의 User 클래스를 상속받아 만들면 됨
		
		log.info("ckk{}" + username);
		MemberVO memberVO = memberMapper.read(username); //security에서 username이라고 하는 것, 지금 테이블의 userid임
		return memberVO == null ? null : new CustomUser(memberVO);

	}

}
