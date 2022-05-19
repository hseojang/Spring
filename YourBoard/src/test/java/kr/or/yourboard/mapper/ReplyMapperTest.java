package kr.or.yourboard.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.or.yourboard.domain.BoardVO;
import kr.or.yourboard.domain.ReplyVO;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:config/spring/root-context.xml")
@Slf4j
public class ReplyMapperTest {

	@Autowired
	ReplyMapper replyMapper;
	
	@Test
	@Disabled
	public void insertTest() {
		
		ReplyVO replyVO = new ReplyVO();
		
		replyVO.setBoardNo(17);
		replyVO.setReplyTitle("답변입니다");
		replyVO.setReplyWriter("나 작가");
		replyVO.setReplyContent("내용이요");

		Assertions.assertEquals(1, replyMapper.insertReply(replyVO));
		
	}
	
	@Test
	@Disabled
	public void updateTest() {
		
		ReplyVO replyVO = new ReplyVO();
		
		replyVO.setReplyNo(5);
		replyVO.setReplyTitle("수정답변");
		replyVO.setReplyWriter("작가도 바뀌었어요");
		replyVO.setReplyContent("내용은 안바꾸나??");

		Assertions.assertEquals(1, replyMapper.updateReply(replyVO));
		
	}
	
	@Test
	@Disabled
	public void deleteTest() {
		Assertions.assertEquals(1, replyMapper.deleteReply(5));
	}
	
	@Test
	@Disabled
	public void selectTest() {
		ReplyVO replyVO = replyMapper.selectReply(4);
		Assertions.assertEquals(4, replyVO.getBoardNo());
	}
	
	@Test
	//@Disabled
	public void selectListTest() {
		List<ReplyVO> replyList = new ArrayList<ReplyVO>();
		
		replyList = replyMapper.selectBoardReply(17);
		// 확인용
		replyList.stream().forEach(vo -> System.out.println(vo.toString())); 
		
		Assertions.assertEquals(2, replyList.size());
		
	}
	
}
