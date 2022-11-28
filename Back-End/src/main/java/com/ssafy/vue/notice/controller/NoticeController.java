package com.ssafy.vue.notice.controller;

import java.util.*;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.notice.model.NoticeDto;
import com.ssafy.vue.notice.model.service.NoticeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/notice")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	private NoticeService noticeService;

	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	// 공지사항 전체 목록
	@GetMapping("/list")
	public ResponseEntity<?> list() {
		try 
		{
			Map<String, String> map = new HashMap<>();
			Map<String, Object> param = new HashMap<String, Object>();
//			logger.debug("reguest body : {} ", map);
			
			List<NoticeDto> list = noticeService.listArticle(map);
			param.put("list", list);
			
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(param, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	// 공지사항 등록
	@PostMapping("/register")
	public ResponseEntity<?> noticeRegister(@RequestBody NoticeDto noticeDto) {
		logger.debug("noticeRegister noticeDto : {}", noticeDto);
		try {
			noticeService.writeArticle(noticeDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		}

	}
	
	// 공지사항 조회
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> noticeInfo(@PathVariable("id") int articleNo) {
//		logger.debug("view articleNo : {}", articleNo);
		try {
			NoticeDto noticeDto = noticeService.getArticle(articleNo);
			noticeService.updateHit(articleNo);
//			logger.debug("view noticeDto : {}", noticeDto);
			if (noticeDto != null)
				return new ResponseEntity<NoticeDto>(noticeDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 공지사항 수정
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) NoticeDto noticeDto) throws Exception {
//		logger.info("modifyArticle : {}", noticeDto);
		noticeService.modifyArticle(noticeDto);
		if (noticeDto != null) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
	
	// 공지사항 삭제
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
//		logger.info("deleteArticle : {} ", articleno);
		noticeService.deleteArticle(articleno);
		if (articleno != 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
