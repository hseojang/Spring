package kr.or.yourboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.yourboard.domain.BoardVO;
import kr.or.yourboard.domain.PageCondDTO;

@Mapper
public interface BoardMapper {
	
	public int insertBoard(BoardVO vo);
	public int updateBoard(BoardVO vo);
	public int deleteBoard(int boardNo);
	public BoardVO selectBoard(int boardNo);
	public List<BoardVO> selectBoardPage(PageCondDTO pageCondDTO);
	public List<BoardVO> selectBoardList();
	public int selectBoardCount();
}
