package kr.or.blackpink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/list")
	public String boardList() {
		return "/board/list";
	}
	
	@GetMapping("/write")
	public String boardWriteForm() {
		return "/board/writeForm";
	}
	
	@PostMapping("/write")
	public String boardWrite() {
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
