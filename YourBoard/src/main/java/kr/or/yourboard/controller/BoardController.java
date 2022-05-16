package kr.or.yourboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.yourboard.domain.BoardVO;
import kr.or.yourboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/write")
	public String getWrite(Model model) {
		return "write";
	}
	
	@PostMapping("/write")
	public String postWrite(Model model, BoardVO boardVO) {
		log.info("check : " + boardVO.toString());
		// model.addAttribute("vo", boardVO); 값 넘겨줄때
		int result = boardService.insertBoard(boardVO);
		
		if (result != 1) {
			model.addAttribute("error", "작성에 실패했습니다");
		}
		// return "list"; // 포워딩 방식
		return "redirect:list"; // 리다이렉트 방식
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<BoardVO> boardList = boardService.selectBoardList();
		model.addAttribute("boardList", boardList);
		return "list";
	}
	
	@GetMapping("/content")
	public String getList(Model model, int boardNo) {
		BoardVO boardVO = boardService.selectBoard(boardNo);
		model.addAttribute("vo", boardVO);
		return "content";
	}
	
	@GetMapping("/edit")
	public String getEdit(Model model, int boardNo) {
		BoardVO boardVO = boardService.selectBoard(boardNo);
		model.addAttribute("vo", boardVO);
		return "edit";
	}
	
	@PostMapping("/edit")
	public String postEdit(Model model, BoardVO boardVO) {
		int result = boardService.updateBoard(boardVO);
		
		if (result != 1) {
			model.addAttribute("error", "작성에 실패했습니다");
		}
		return "redirect:list"; // 리다이렉트 방식
	}
	
	@GetMapping("/delete")
	public String getDelete(Model model, int boardNo) {
		int result = boardService.deleteBoard(boardNo);
		if (result != 1) {
			model.addAttribute("error", "작성에 실패했습니다");
		}
		return "redirect:list"; // 리다이렉트 방식
	}
	
}
