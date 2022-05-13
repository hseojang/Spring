package kr.or.blackpink.service;

import java.util.List;

import kr.or.blackpink.domain.BoardVO;

public interface BoardService {
	
	public int write(BoardVO boardVO);
	
	public int delete();
	
	public int update();
	
	public List<BoardVO> getList();
	
	public BoardVO getArticle();
}
