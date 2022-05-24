package kr.or.yourboard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.yourboard.domain.BoardVO;
import kr.or.yourboard.domain.PageCondDTO;
import kr.or.yourboard.domain.PageDTO;
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
		
		// @ModelAttribute : 원래 command 객체였음
		// 스프링 폼 태그를 사용한 jsp를 불러올 때 컨트롤러에 적어주지 않으면 
		// Neither BindingResult nor plain target object for bean name 'command' available as request attribute.
		// 익셉션 발생
		
		// 현재 코드에서는 처음 get으로 접근할 때 일반 페이지(write.jsp)를 불러오고 post로 전송할 때 validation을 거쳐
		// 에러가 발생하면 메시지를 띄울 수 있도록 write2.jsp를 호출하도록 되어있음
		
		return "board/write"; // spring form 태그를 사용하지 않은 페이지
	}
	
	@PostMapping("/write")
	public String postWrite(@ModelAttribute("vo") @Valid BoardVO boardVO, Errors errors, Model model) { 
		//@Valid : required는 클라이언트단에서의 검증, Valid 어노테이션은 Validator를 이용한 서버단 검증
		// 유효성을 검증할 변수의 클래스에 무엇을 검증할 것인지 명시해야한다
		
		// Validator가 Errors 타입 객체에 발생한 오류를 자동으로 담아줌
		// 단! 매개변수 순서로 검증할 VO/DTO 다음에 작성해야 함
		// Validator를 쓴다고 클라이언트 단에서의 검증이 필요 없는 것은 아님
		// 서버에서의 Validator는 부수적인 것
		
		log.info("check : " + boardVO.toString());
		
		if(errors.hasErrors()) { // Validator 검증 결과 에러가 발생했다면
			return "board/write2"; // spring form 태그를 넣은 쓰기 뷰 페이지로
		}
		
		// model.addAttribute("vo", boardVO); // Model로 값 넘기기
		int result = boardService.insertBoard(boardVO);
		
		if (result != 1) {
			model.addAttribute("error", "작성에 실패했습니다");
		}
		// return "list"; // 포워딩 방식
		return "redirect:list"; // 리다이렉트 방식은 Mapping된 URL을 기준으로 함
	}
	
	@GetMapping("/list")
	public String getList(PageCondDTO pageCondDTO, Model model) { // Dependency Injection에 의해 빈 객체가 생성되고 받은 요청에 맞게 설정되어 주입
		log.info("ck : " + pageCondDTO.getPageNum() + "번 페이지 / size : " + pageCondDTO.getPageSize());
		int total = boardService.selectBoardCount();
		PageDTO pageDTO = new PageDTO(pageCondDTO, total);
		List<BoardVO> boardList = boardService.selectBoardPage(pageCondDTO);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageDTO", pageDTO);
		return "board/list";
	}
	
	@GetMapping("/content")
	public String getContent(Model model, int boardNo) {
		BoardVO boardVO = boardService.selectBoard(boardNo);
		model.addAttribute("vo", boardVO);
		return "board/content";
	}
	
	@GetMapping("/edit")
	public String getEdit(Model model, int boardNo) {
		BoardVO boardVO = boardService.selectBoard(boardNo);
		model.addAttribute("vo", boardVO);
		return "board/edit";
	}
	
	@PostMapping("/edit")
	public String postEdit(Model model, BoardVO boardVO) {
		int result = boardService.updateBoard(boardVO);
		
		if (result != 1) {
			model.addAttribute("error", "작성에 실패했습니다");
		}
		return "redirect:list"; // 리다이렉트 방식
	}
	
	
	@PostMapping("/delete")
	public String postDelete(Model model, int boardNo) {
		int result = boardService.deleteBoard(boardNo);
		if (result != 1) {
			model.addAttribute("error", "작성에 실패했습니다");
		}
		return "redirect:list"; // 리다이렉트 방식
	}
	
	
	@GetMapping("/index")
	public String goIndex() {
		// /web-inf/views/index.jsp로 forwarding
		return "board/index";
	}
	
	@GetMapping("/buttons")
	public String goButtons() {
		return "board/buttons";
	}
	
	@GetMapping("/cards")
	public String goCards() {
		return "board/cards";
	}
	
}
