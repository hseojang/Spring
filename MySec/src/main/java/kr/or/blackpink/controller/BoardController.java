package kr.or.blackpink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.blackpink.domain.BoardVO;
import kr.or.blackpink.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/list")
	public String boardList() {
		return "/board/list";
	}
	
	@GetMapping("/write")
	public String boardWriteForm() {
		return "/board/writeForm";
	}
	
	@PostMapping("/write")
	public String boardWrite(BoardVO boardVO, Model model) {
		int result = boardService.write(boardVO);
		
		if (result>0) {
			model.addAttribute("message", "작성에 성공했습니다.");
		} else {
			model.addAttribute("message", "작성에 실패했습니다.");
			return "";
		}
		return "/board/list";
	}
	
	@GetMapping("/edit")
	public String boardEditForm() {
		return "/board/writeForm";
	}
	
	@PostMapping("/edit")
	public String boardEdit() {
		return "/board/list";
	}
	
	@PostMapping("/delete")
	public String boardDelete() {
		return "/board/list";
	}
	
}
