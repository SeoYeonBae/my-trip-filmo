package com.ssafy.vue.reply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	

	@PostMapping("/register")
	public ResponseEntity<?> userRegister(@RequestBody ReplyDto replyDto) {
		logger.debug("boardRegister boardDto : {}", replyDto);
		try {
			replyService.regist(replyDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
