package com.ssafy.vue.reply.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.board.controller.BoardController;
import com.ssafy.vue.reply.model.ReplyDto;
import com.ssafy.vue.reply.service.ReplyService;



@RestController
@RequestMapping("/reply")
public class ReplyController {

	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private ReplyService replyService;

	@Autowired
	public ReplyController(ReplyService replyService) {
		super();
		this.replyService = replyService;
	}
	

	// 댓글 등록
	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody ReplyDto replyDto) {
		logger.debug("replyRegister replyDto : {}", replyDto);
		try {
			replyService.regist(replyDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	

	// 클릭한 글에 맞는 댓글 불러오기
	@GetMapping(value = "/{no}")
	public ResponseEntity<?> replyList(@PathVariable("no") int article_no){
		logger.debug("view articleNo : {}", article_no);
		try {
			List<ReplyDto> total_list = replyService.list(article_no);
			if(total_list != null)
				return new ResponseEntity<List<ReplyDto>>(total_list, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 내가 쓴 댓글 불러오기
	@GetMapping("/my/{id}")
	public ResponseEntity<?> replyMyList(@PathVariable("id") String id){
		logger.debug("view myList : {}", id);
		try {
			List<ReplyDto> totalList = replyService.myList(id);
			if(totalList != null)
				return new ResponseEntity<List<ReplyDto>>(totalList, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 댓글 삭제
	@DeleteMapping("/{idx}")
	public ResponseEntity<String> deleteArticle(@PathVariable("idx") int idx) throws Exception {
		logger.info("deleteReply - 호출");
		replyService.delete(idx);
		if (idx != 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
