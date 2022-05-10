package kr.or.blackpink.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.slf4j.Slf4j;

/* 보통 AccessDeniedHandler를 상속받아 직접 핸들러를 구현해서도 많이 쓴다.*/
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response,
         AccessDeniedException ade) throws IOException, ServletException {
      log.info("커스텀 엑세스 디나이드 핸들러에 도착!"); // 이런 표시가 디버깅시 중요하다.
      log.info("error: " + ade.getMessage());
      
      // 홈 화면으로 돌아가게 함
      response.sendRedirect(request.getContextPath()+"/");
      
   }
   
}