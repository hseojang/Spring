package kr.or.blackpink.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect // Aspect임을 마킹
@Component // Bean 생성
@Slf4j
public class MyLog { // 이런 클래스를 advice라고 한다
	
	// () 안에 쓴 것을 pointcut이라고 함, 기술법을 pointcut 표현식이라 부름
//	@Before("execution(* kr.or.blackpink.service.SimpleService*.*(..))")
//	public void testLog() {
//		log.info("난 advice의 메소드 before으로 설정돼있죠");
//	}
	
	// Before, After, AfterThrowing, AfterReturning, Around가 있음
	@Around("execution(* kr.or.blackpink.service.SimpleService*.*(..))")
	public Integer aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		log.info("실행 전 하고픈 일을 여기에"); // 언제 메서드가 실행될지를 조정할 수 있음
		
		//log.info("p1 : " + pjp.getClass().getName());
		//log.info("p2 : " + pjp.getTarget().getClass().getName());
		//log.info("kgb : "+Arrays.toString(pjp.getArgs()));
		
		Integer retObj = (Integer) pjp.proceed(pjp.getArgs());
		//pjp.proceed(pjp.getArgs()); // 매개변수로 타겟 메서드를 실행
		
		log.info("실행 후 할일은 여기에");
		return retObj;
	}
}
