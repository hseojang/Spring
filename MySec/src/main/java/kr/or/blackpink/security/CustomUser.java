package kr.or.blackpink.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.or.blackpink.domain.AuthVO;
import kr.or.blackpink.domain.MemberVO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
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
		
		List<SimpleGrantedAuthority> ckList =  vo.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList());
		
		// 위에서 만든 ckList와 같은 값, stream 연산을 반복문으로 처리시 아래와 같음
		// 중간 오퍼레이션 함수와 종료 오퍼레이션 함수의 구분 필요
		List<AuthVO> myList = vo.getAuthList();
		List<SimpleGrantedAuthority> ck2List = new ArrayList<SimpleGrantedAuthority>(); // 옮겨담을 것
		
		for (AuthVO authVO : myList) {
			ck2List.add(new SimpleGrantedAuthority(authVO.getAuth()));
		}
		
		log.info("stream result : " + ckList);
		log.info("check : " + ck2List); // 결과 확인
		
		this.member = vo;
		
		// 따라서 회원 정보(VO)의 아이디, 비밀번호, 소유 권한들(SimpleGrantedAuthority의 컬렉션으로 변환)을 매개변수로 받고 멤버VO를 갖게하는 생성자가 됨
	}
}