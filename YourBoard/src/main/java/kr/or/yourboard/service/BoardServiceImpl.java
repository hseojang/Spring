package kr.or.yourboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.yourboard.domain.BoardVO;
import kr.or.yourboard.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public int insertBoard(BoardVO vo) {

		return boardMapper.insertBoard(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {

		return boardMapper.updateBoard(vo);
	}

	@Override
	public int deleteBoard(int boardNo) {

		return boardMapper.deleteBoard(boardNo);
	}

	@Override
	public BoardVO selectBoard(int boardNo) {

		return boardMapper.selectBoard(boardNo);
	}

	@Override
	public List<BoardVO> selectBoardList() {

		return boardMapper.selectBoardList();
	}

}
