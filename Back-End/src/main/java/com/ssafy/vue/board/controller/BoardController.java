package com.ssafy.vue.board.controller;

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

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.board.model.service.BoardService;
import com.ssafy.vue.notice.model.NoticeDto;
import com.ssafy.vue.util.PageNavigation;

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
	
	

//	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
//	@GetMapping
//	public ResponseEntity<List<BoardDto>> listArticle(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) BoardParameterDto boardParameterDto) throws Exception {
//		logger.info("listArticle - 호출");
//		return new ResponseEntity<List<BoardDto>>(boardService.listArticle(boardParameterDto), HttpStatus.OK);
//	}
	
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
			PageNavigation pageNavigation = new PageNavigation();
			
			logger.debug("total board : {} ", list);
//			logger.debug("pageNavigation : " + pageNavigation);
			logger.debug("pageNavigation : " + "안됨");
			
			param.put("list", list);
//			param.put("pageNavigation", pageNavigation);
			logger.debug("pageNavigation : " + "안됨");
			
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
			PageNavigation pageNavigation = new PageNavigation();
			
			logger.debug("total board : {} ", list);
//			logger.debug("pageNavigation : " + pageNavigation);
			logger.debug("pageNavigation : " + "안됨");
			
			param.put("list", list);
//			param.put("pageNavigation", pageNavigation);
			logger.debug("pageNavigation : " + "안됨");
			
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
	public ResponseEntity<?> userRegister(@RequestBody BoardDto boardDto) {
		logger.debug("boardRegister boardDto : {}", boardDto);
		try {
			boardService.writeArticle(boardDto);
			Map<String, String> map = new HashMap<String, String>();
			map.put("pgno", "1");
			List<BoardDto> list = boardService.listArticle(map);
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	

	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteArticle(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteArticle - 호출");
		boardService.deleteArticle(articleno);
		if (articleno != 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> noticeInfo(@PathVariable("id") int articleNo) {
		logger.debug("view articleNo : {}", articleNo);
		try {
			BoardDto boardDto = boardService.getArticle(articleNo);
			boardService.updateHit(articleNo);
			logger.debug("view boardDto : {}", boardDto);
			if (boardDto != null)
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	private void preview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	try {
//		List<BoardDto> list = boardService.preview(3);
//		request.setAttribute("list", list);
//		forward(request, response, "/board/preview.jsp");
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
//}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String act = request.getParameter("act");
//
//		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//		String key = ParameterCheck.nullToBlank(request.getParameter("key"));
//		String word = ParameterCheck.nullToBlank(request.getParameter("word"));
//		String queryString = "pgno=" + pgNo + "&key=" + key + "&word=" + ParameterCheck.urlEncoding(word);
//
//		if ("list".equals(act)) {
//			list(request, response, queryString);
//		} else if ("listcontent".equals(act)) {
//			listcontent(request, response);
//		} else if ("preview".equals(act)) {
//			preview(request, response);
//		} else if ("mvwrite".equals(act)) {
//			redirect(request, response, "/board/write.jsp?"+queryString);
//		} else if ("write".equals(act)) {
//			write(request, response, queryString);
//		} else if ("view".equals(act)) {
//			view(request, response, queryString);
//		} else if ("mvmodify".equals(act)) {
//			mvModify(request, response, queryString);
//		} else if ("delete".equals(act)) {
//			delete(request, response, queryString);
//		} else if ("modify".equals(act)) {
//			modify(request, response, queryString);
//		} else {
//			redirect(request, response, "/index.jsp");
//		}
//	}
//
//
//
//	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
//	}
//
//	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//		response.sendRedirect(request.getContextPath() + path);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		doGet(request, response);
//	}
//
//	private void list(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//		try {
//			int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//			String key = ParameterCheck.nullToBlank(request.getParameter("key"));
//			String word = ParameterCheck.nullToBlank(request.getParameter("word"));
//			Map<String, String> map = new HashMap<>();
//			map.put("pgno", pgNo + "");
//			map.put("key", key);
//			map.put("word", word);
//			List<BoardDto> list = boardService.listArticle(map);
//			request.setAttribute("articles", list);
//			
//			int totalArticle = boardService.countArticle();
//			request.setAttribute("totalData", totalArticle);
//			request.setAttribute("dataPerPage", SizeConstant.SIZE_PER_LIST);
//			request.setAttribute("pageCount", SizeConstant.LIST_PER_PAGE);
//			
//			forward(request, response, "/board/list.jsp?" + queryString);
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글목록 얻기 중 에러발생!!!");
//			forward(request, response, "/index.jsp");
//		}
//	}
//	
//
//	private void listcontent(HttpServletRequest request, HttpServletResponse response) {
//		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//		Map<String, String> map = new HashMap<>();
//		map.put("pgno", pgNo + "");
//		map.put("key", "");
//		map.put("word", "");
//		try {
//			List<BoardDto> list = boardService.listArticle(map);
//			request.setAttribute("list", list);
//			forward(request, response, "/board/listcontent.jsp");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//
//	private void preview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			List<BoardDto> list = boardService.preview(3);
//			request.setAttribute("list", list);
//			forward(request, response, "/board/preview.jsp");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//	private void write(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if (userDto != null) {
//			BoardDto boardDto = new BoardDto();
//			boardDto.setUserId(userDto.getId());
//			boardDto.setSubject(request.getParameter("subject"));
//			boardDto.setContent(request.getParameter("content"));
//			try {
//				boardService.writeArticle(boardDto);
//				redirect(request, response, "/board?act=list&" + queryString);
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글작성 중 에러발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
//			forward(request, response, "/board?act=list&" + queryString);
//		}
//
//	}
//
//	private void view(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if(userDto != null) {
//			try {
//				int articleNo = Integer.parseInt(request.getParameter("articleno"));
//				BoardDto boardDto = boardService.getArticle(articleNo);
//				boardService.updateHit(articleNo);
//				request.setAttribute("article", boardDto);
//				forward(request, response, "/board/view.jsp?" + queryString);
//				;
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글 얻기 중 에러발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
//			forward(request, response, "/board?act=list&" + queryString);
//		}
//
//	}
//
//	private void delete(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if(userDto != null) {
//			try {
//				int articleNo = Integer.parseInt(request.getParameter("articleno"));
//				boardService.deleteArticle(articleNo);
//				redirect(request, response, "/board?act=list");
//				;
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글 삭제 중 에러발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
//			forward(request, response, "/board?act=list&" + queryString);
//		}	
//	}
//
//	private void mvModify(HttpServletRequest request, HttpServletResponse response, String queryString) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if(userDto != null) {
//			int articleNo = Integer.parseInt(request.getParameter("articleno"));
//			BoardDto boardDto;
//			try {
//				boardDto = boardService.getArticle(articleNo);
//				request.setAttribute("article", boardDto);
//				forward(request, response, "/board/modify.jsp?" + queryString);
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "목록 처리 중 에러 발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
//			forward(request, response, "/board?act=list&" + queryString);
//		}	
//
//	}
//	
//	private void modify(HttpServletRequest request, HttpServletResponse response, String queryString) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if(userDto != null) {
//			BoardDto boardDto = new BoardDto();
//			boardDto.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
//			boardDto.setSubject(request.getParameter("subject"));
//			boardDto.setContent(request.getParameter("content"));
//			try {
//				boardService.modifyArticle(boardDto);
//				redirect(request, response, "/board?act=list&" + queryString);
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "수정 중 에러 발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "로그인 후 이용 가능합니다.");
//			forward(request, response, "/board?act=list&" + queryString);
//		}	
//	}

}
