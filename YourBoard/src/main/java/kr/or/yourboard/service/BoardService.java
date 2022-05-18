package kr.or.yourboard.service;

import java.util.List;

import kr.or.yourboard.domain.BoardVO;
import kr.or.yourboard.domain.PageCondDTO;

public interface BoardService {
	
	public int insertBoard(BoardVO vo);
	public int updateBoard(BoardVO vo);
	public int deleteBoard(int boardNo);
	public BoardVO selectBoard(int boardNo);
	public List<BoardVO> selectBoardPage(PageCondDTO pageCondDTO);
	public List<BoardVO> selectBoardList();
	public int selectBoardCount();
}
