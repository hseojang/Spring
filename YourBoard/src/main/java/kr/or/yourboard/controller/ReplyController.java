package kr.or.yourboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.yourboard.domain.ReplyVO;
import kr.or.yourboard.service.ReplyService;
import lombok.extern.slf4j.Slf4j;

//@RestController = rest와 연관이 있는 것 같다... 의미는 @responsebody+@controller
@Controller
@RequestMapping("/reply")
@Slf4j
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@ResponseBody
	@GetMapping(value="/list", produces = "application/json;charset=utf-8" )
	public List<ReplyVO> getReplyList(@RequestParam("boardNo") int boardNo) {
		log.info("get 받은 boardNo : " + boardNo);

		return replyService.selectBoardReply(boardNo);
		
	}	
	
	@ResponseBody
	@PostMapping(value="/list", produces = "application/json;charset=utf-8" )
	public List<ReplyVO> postReplyList(@RequestParam("boardNo") int boardNo) {
		log.info("post 받은 boardNo : " + boardNo);

		return replyService.selectBoardReply(boardNo);
		
	}	
	
	@ResponseBody
	@PostMapping(value="/insert")
	public String postNewReply( ReplyVO replyVO) {
		log.info(replyVO.toString());

		String msg = "";
		if(replyService.insertReply(replyVO)<=0) {
			msg = "댓글 작성에 실패했습니다.";
		}
		return msg;
	}	
	
	@ResponseBody
	@PostMapping(value="/delete")
	public String deleteReply(@RequestBody ReplyVO replyVO) {
		int replyNo = replyVO.getReplyNo();
		log.info("delete 요청 받은 번호 : " + replyNo);
		String msg = "";
		if(replyService.deleteReply(replyNo)<=0) {
			msg = "댓글 삭제에 실패했습니다.";
		}
		return msg;
	}	
	
	@ResponseBody
	@PostMapping(value="/edit")
	public String updateReply(ReplyVO replyVO) {

		String msg = "";
		if(replyService.updateReply(replyVO)<=0) {
			msg = "댓글 수정에 실패했습니다.";
		}
		return msg;
		
	}	
	
	
}
