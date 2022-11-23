package com.ssafy.vue.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.board.model.FileInfoDto;
import com.ssafy.vue.board.model.service.BoardService;
//import com.ssafy.vue.util.PageNavigation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private BoardService boardService;


	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@GetMapping("/preview")
	public ResponseEntity<?> preview() {
		try {
			List<BoardDto> list = boardService.preview(3);
			logger.debug("preview board : {} ", list);
			if (list != null) {
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}


	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("modifyArticle - 호출 {}", boardDto);
		boardService.modifyArticle(boardDto);
		if (boardDto != null) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<?> totalList() {
		logger.info("listArticle - 호출");
		try {
			Map<String, String> map = new HashMap<>();
			map.put("pgno", "1");
			map.put("key", "");
			map.put("word", "");
			
			Map<String, Object> param = new HashMap<String, Object>();

			List<BoardDto> list = boardService.listArticle(map);	// JSON Array
//			PageNavigation pageNavigation = boardService.makePageNavigation(map);
//			PageNavigation pageNavigation = new PageNavigation();
			
			logger.debug("total board : {} ", list);
//			logger.debug("pageNavigation : " + pageNavigation);
//			logger.debug("pageNavigation : " + "안됨");
			
			param.put("list", list);
//			param.put("pageNavigation", pageNavigation);
//			logger.debug("pageNavigation : " + "안됨");
			
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(param, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@GetMapping("/list/{id}")
	public ResponseEntity<?> myList(@PathVariable("id") String id) {
		logger.info("myListArticle - 호출");
		try {
			Map<String, String> map = new HashMap<>();
			map.put("pgno", "1");
			map.put("key", "");
			map.put("word", "");
			map.put("id", id);
			Map<String, Object> param = new HashMap<String, Object>();
			
			List<BoardDto> list = boardService.myListArticle(map);	// JSON Array
//			PageNavigation pageNavigation = boardService.makePageNavigation(map);
//			PageNavigation pageNavigation = new PageNavigation();
			
			logger.debug("total board : {} ", list);
//			logger.debug("pageNavigation : " + pageNavigation);
//			logger.debug("pageNavigation : " + "안됨");
			
			param.put("list", list);
//			param.put("pageNavigation", pageNavigation);
//			logger.debug("pageNavigation : " + "안됨");
			
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(param, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> boardRegister(@Value("${file.path.upload-files}") String filePath, @RequestPart("boardDto") BoardDto boardDto, @RequestPart("fileInfos") MultipartFile[] files) throws Exception {
		logger.info("boardRegister boardDto : {}", boardDto);
		logger.info("MultipartFile : {}", files.length);
		if (!files[0].isEmpty()) {
//			String realPath = servletContext.getRealPath("/upload");
//			String realPath = servletContext.getRealPath("/resources/img");
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = filePath + File.separator + today;
			logger.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();
			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
			for (MultipartFile mfile : files) {
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = mfile.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = System.nanoTime()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					fileInfoDto.setSaveFolder(today);
					fileInfoDto.setOriginalFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					logger.info("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfoDto);
			}
			boardDto.setFileInfos(fileInfos);
		}
		try {
			boardService.writeArticle(boardDto);
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("pgno", "1");
//			map.put("key", "");
//			map.put("word", "");
//			List<BoardDto> list = boardService.listArticle(map);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> view(@PathVariable("id") int articleNo) {
		logger.info("view articleNo : {}", articleNo);
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			boardService.updateHit(articleNo);
			logger.info("view boardDto : {}", boardDto);
			if (boardDto != null)
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}	
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@Value("${file.path.upload-files}") String filePath, @PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleNo) throws Exception {
		logger.info("deleteArticle - 호출");
		boardService.deleteArticle(articleNo, filePath);		
		if (articleNo != 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}