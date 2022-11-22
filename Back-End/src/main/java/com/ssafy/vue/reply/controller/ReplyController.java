package com.ssafy.vue.reply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.board.controller.BoardController;
import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.reply.model.ReplyDto;
import com.ssafy.vue.reply.service.ReplyService;

import io.swagger.annotations.ApiParam;


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
