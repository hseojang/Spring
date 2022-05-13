package kr.or.blackpink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.blackpink.domain.BoardVO;
import kr.or.blackpink.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public int write(BoardVO boardVO) {
		int result = boardMapper.writeArticle(boardVO);

		return result;
	}

	@Override
	public int delete() {

		return 0;
	}

	@Override
	public int update() {

		return 0;
	}

	@Override
	public List<BoardVO> getList() {

		return null;
	}

	@Override
	public BoardVO getArticle() {

		return null;
	}

}
