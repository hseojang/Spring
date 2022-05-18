package kr.or.yourboard.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.yourboard.domain.BoardVO;
import kr.or.yourboard.domain.PageCondDTO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:config/spring/root-context.xml")
public class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	@Test
	@Disabled
	public void selectList() {
		List<BoardVO> boardList = boardService.selectBoardList();
		
		// ::은 메소드 참조라고 부름
		boardList.forEach(System.out::println);
		Assertions.assertEquals(14, boardList.size());
	}
	
	@Test
	public void selectPage() {
		PageCondDTO pageCondDTO = new PageCondDTO();
		List<BoardVO> boardList = boardService.selectBoardPage(pageCondDTO);
		

		boardList.forEach(System.out::println);
		Assertions.assertEquals(5, boardList.size());
	}

}
