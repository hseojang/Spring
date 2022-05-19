package kr.or.yourboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.yourboard.domain.ReplyVO;

@Mapper
public interface ReplyMapper {


	public int insertReply(ReplyVO replyVO);
	public int updateReply(ReplyVO replyVO);
	public int deleteReply(int replyNo);
	public ReplyVO selectReply(int replyNo); // 실무에서는 long 타입을 많이씀
	public List<ReplyVO> selectReplyList();
	public List<ReplyVO> selectBoardReply(int boardNo);
	
}
