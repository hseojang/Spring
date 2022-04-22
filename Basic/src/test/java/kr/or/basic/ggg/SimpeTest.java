package kr.or.basic.ggg;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class) //junit5 사용설정
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}) // 자바 배열이므로 여러 파일 지정가능
@Slf4j
public class SimpeTest {

	@Autowired
	DataSource dataSource; //hikari의 dataSource 인터페이스임
	
	@Test // 이 모양이 기본 테스트 설정
	public void myTest() throws Exception {
		log.info(""+dataSource.getConnection());
	}
}
