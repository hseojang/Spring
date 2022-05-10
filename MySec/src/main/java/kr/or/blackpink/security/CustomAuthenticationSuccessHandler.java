package kr.or.blackpink.security;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

// 인증 성공 시 보통 어디로 보낼지를 결정
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
			Authentication auth) throws IOException, ServletException {
		Iterator<? extends GrantedAuthority> myIter = auth.getAuthorities().iterator();
		
		log.info("authName : " + auth.getName());
		log.info("authCre : " + auth.getCredentials());
		log.info("authDet : " + auth.getDetails());
		log.info("authPri : " + auth.getPrincipal());
		log.info("---");
		
		while(myIter.hasNext()) {
			String authName = myIter.next().toString(); // Authentication에 담긴 Authorities 하나씩 확인
			log.info("auth : " + authName); // 어떤 권한이 넘어오는지 확인
			if(authName.equals("ROLE_ADMIN")) {
				res.sendRedirect("/blackpink/fbi/admin");
				break;
			} else if (authName.equals("ROLE_MEMBER")) {
				res.sendRedirect("/blackpink/fbi/member");
				break;
			} else if (authName.equals("ROLE_ROZE")) {
				res.sendRedirect("/blackpink/fbi/roze");
				break;
			}
			
			//log.info("auth : " + myIter.next());
		}
		
	}

}
