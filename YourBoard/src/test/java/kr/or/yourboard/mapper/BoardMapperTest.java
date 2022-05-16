package kr.or.yourboard.mapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.yourboard.domain.BoardVO;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:config/spring/root-context.xml")
@Slf4j
public class BoardMapperTest {

	// database-context에서 정의한 bean
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void selectListTest() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		// Assertions.assertInstanceOf(boardList.getClass(), boardMapper.selectBoardList());
		// 클래스 타입 비교임
		
		boardList = boardMapper.selectBoardList();
		boardList.stream().forEach(vo -> System.out.println(vo.toString())); 
		// parallelStream()은 병렬스트림, 시스템이 알아서 스트림을 쪼개 병렬로 처리함 동기화 문제가 생길 수 있음
		
		Assertions.assertEquals(14, boardList.size());
		
	}
	
	@Test
	@Disabled
	public void selectTest() {
		BoardVO boardVO = boardMapper.selectBoard(10);
		Assertions.assertEquals(10, boardVO.getBoardNo());
	}
	
	@Test
	@Disabled
	public void deleteTest() {
		Assertions.assertEquals(1, boardMapper.deleteBoard(3));
	}
	
	
	@Test
	@Disabled
	public void updateTest() {
		BoardVO vo; // 테스트용 VO 객체
		for (int i = 1; i <= 15; i++) {
			vo = new BoardVO();
			vo.setBoardNo(i);
			vo.setBoardTitle("제목수정됨" + i);
			vo.setBoardWriter("진짜작가" + i);
			vo.setBoardContent("진짜최종내용");

			Assertions.assertEquals(1, boardMapper.updateBoard(vo));
		}
	}
	
	@Test
	@Disabled
	@DisplayName("mapperInsert") // 테스트 창에 보여줄 이름 지정 가능
	public void insertTest() {
		BoardVO vo; // 테스트용 VO 객체
		for (int i = 1; i <= 15; i++) {
			vo = new BoardVO();
			vo.setBoardTitle("제목" + i);
			vo.setBoardWriter("대충작가" + i);
			vo.setBoardContent("대충내용");

			Assertions.assertEquals(1, boardMapper.insertBoard(vo));
		}
	}

	@Test
	@Disabled
	public void connTest() throws SQLException { // connection 테스트
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection conn = sqlSession.getConnection();
		Assertions.assertNotNull(conn);

		/*
		 * Statement stmt = conn.createStatement(); ResultSet rs =
		 * stmt.executeQuery("select * from tb_board");
		 * 
		 * while(rs.next()) { log.info("check : " + rs.getString(2));
		 * 
		 * }
		 */
	}

}
