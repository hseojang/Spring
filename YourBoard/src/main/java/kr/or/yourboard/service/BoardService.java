package kr.or.yourboard.service;

import java.util.List;

import kr.or.yourboard.domain.BoardVO;

public interface BoardService {
	
	public int insertBoard(BoardVO vo);
	public int updateBoard(BoardVO vo);
	public int deleteBoard(int boardNo);
	public BoardVO selectBoard(int boardNo);
	public List<BoardVO> selectBoardList();
}
