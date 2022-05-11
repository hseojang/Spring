package kr.or.blackpink.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.or.blackpink.domain.MemberVO;
import lombok.Getter;

@Getter
public class CustomUser extends User {
	private static final long serialVersionUID = 1L;

	private MemberVO member;

	// 상속받은 부모 생성자 호출
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	// 이 생성자가 VO를 UserDetails로 만들어주는 생성자
	// UserDetails는 기본적으로 id, pw, 권한들을 가지고 있는데
	// 거기에다 객체 내부에 memberVO를 갖게 함(이게 중요)
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		
	
		this.member = vo;
	}
}