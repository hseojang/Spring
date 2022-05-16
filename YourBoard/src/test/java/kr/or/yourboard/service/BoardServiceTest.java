package kr.or.yourboard.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.yourboard.domain.BoardVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:config/spring/root-context.xml")
public class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	@Test
	public void selectList() {
		List<BoardVO> boardList = boardService.selectBoardList();
		
		// ::은 메소드 참조라고 부름
		boardList.forEach(System.out::println);
	}

}
