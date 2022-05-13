package kr.or.blackpink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.blackpink.domain.BoardVO;

@Mapper
public interface BoardMapper {
	
	public BoardVO getArticle(String bdUsername);
	
	public List<BoardVO> getList();
	
	public int writeArticle(BoardVO boardVO);
	
	public int updateArticle(BoardVO boardVO);
	
	public int deleteArticle(String bdNo);

}
