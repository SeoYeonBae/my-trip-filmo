package com.ssafy.vue.notice.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.board.controller.BoardController;
import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.notice.model.NoticeDto;
import com.ssafy.vue.notice.model.service.NoticeService;
import com.ssafy.vue.user.model.UserDto;
import com.ssafy.vue.util.PageNavigation;
import com.ssafy.vue.util.ParameterCheck;
import com.ssafy.vue.util.SizeConstant;


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

	@GetMapping("/preview")
	public ResponseEntity<?> preview() {
		try {
			List<NoticeDto> list = noticeService.preview(3);
			logger.debug("preview notice : {} ", list);
			if (list != null) {
				return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/list")
	public ResponseEntity<?> list(@RequestBody Map<String, String> map) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			logger.debug("reguest body : {} ", map);
			PageNavigation pageNavigation = noticeService.makePageNavigation(map);
			logger.debug(pageNavigation + "");
			param.put("pageNavigation", pageNavigation);
			List<NoticeDto> list = noticeService.listArticle(map);
			logger.debug("notice : {} ", list);
			param.put("list", list);
			if (! param.isEmpty()) {
				return new ResponseEntity<Map<String, Object>>(param, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> noticeRegister(@RequestBody NoticeDto noticeDto) {
		logger.debug("noticeRegister noticeDto : {}", noticeDto);
		try {
			noticeService.writeArticle(noticeDto);
			Map<String, String> map = new HashMap<String, String>();
			map.put("pgno", "1");
			List<NoticeDto> list = noticeService.listArticle(map);
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> noticeInfo(@PathVariable("id") int articleNo) {
		logger.debug("view articleNo : {}", articleNo);
		try {
			NoticeDto noticeDto = noticeService.getArticle(articleNo);
			noticeService.updateHit(articleNo);
			logger.debug("view noticeDto : {}", noticeDto);
			if (noticeDto != null)
				return new ResponseEntity<NoticeDto>(noticeDto, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> userDelete(@PathVariable("id") int articleNo) {
		logger.debug("삭제!!!!!!!!!!!!!!!!!!!!");
		logger.debug("Delete notice id : {}", articleNo);
		try {
			noticeService.deleteArticle(articleNo);
			Map<String, String> map = new HashMap<String, String>();
			map.put("pgno", "1");
			List<NoticeDto> list = noticeService.listArticle(map);
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@PutMapping
	public ResponseEntity<?> noticeModify(@RequestBody Map<String, String> map) {
		logger.debug("Modify map : {}", map);
		try {
			noticeService.modifyArticle(map);
			map.put("pgno", "1");
			List<NoticeDto> list = noticeService.listArticle(map);
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
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
//			redirect(request, response, "/notice/write.jsp" + queryString);
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
//	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//		response.sendRedirect(request.getContextPath() + path);
//	}
//
//	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//			throws ServletException, IOException {
//		RequestDispatcher disp = request.getRequestDispatcher(path);
//		disp.forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
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
//			List<NoticeDto> list = noticeService.listArticle(map);
//			request.setAttribute("notices", list);
//
//			int totalArticle = noticeService.countArticle();
//			request.setAttribute("totalData", totalArticle);
//			request.setAttribute("dataPerPage", SizeConstant.SIZE_PER_LIST);
//			request.setAttribute("pageCount", SizeConstant.LIST_PER_PAGE);
//
//			forward(request, response, "/notice/list.jsp?" + queryString);
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글목록 얻기 중 에러발생!!!");
//			forward(request, response, "/index.jsp");
//		}
//	}
//
//	private void listcontent(HttpServletRequest request, HttpServletResponse response) {
//		int pgNo = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//		Map<String, String> map = new HashMap<>();
//		map.put("pgno", pgNo + "");
//		map.put("key", "");
//		map.put("word", "");
//		try {
//			List<NoticeDto> list = noticeService.listArticle(map);
//			request.setAttribute("list", list);
//			forward(request, response, "/notice/listcontent.jsp");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void preview(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			List<NoticeDto> list = noticeService.preview(3);
//			request.setAttribute("list", list);
//			forward(request, response, "/notice/preview.jsp");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void write(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if (userDto != null && userDto.getIsManager() != 0) {
//			NoticeDto noticeDto = new NoticeDto();
//			noticeDto.setUserId(userDto.getId());
//			noticeDto.setSubject(request.getParameter("subject"));
//			noticeDto.setContent(request.getParameter("content"));
//			try {
//				noticeService.writeArticle(noticeDto);
//				redirect(request, response, "/notice?act=list&" + queryString);
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글작성 중 에러발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "관리자만 이용 가능합니다.");
//			forward(request, response, "/notice?act=list&" + queryString);
//		}
//
//	}
//
//	private void view(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if (userDto != null) {
//			try {
//				int articleNo = Integer.parseInt(request.getParameter("articleno"));
//				NoticeDto noticeDto = noticeService.getArticle(articleNo);
//				noticeService.updateHit(articleNo);
//				request.setAttribute("notice", noticeDto);
//				forward(request, response, "/notice/view.jsp?" + queryString);
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글 얻기 중 에러발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "로그인 이후 이용 가능합니다.");
//			forward(request, response, "/notice?act=list&" + queryString);
//		}
//	}
//
//	private void delete(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if (userDto != null && userDto.getIsManager() != 0) {
//			try {
//				int articleNo = Integer.parseInt(request.getParameter("articleno"));
//				noticeService.deleteArticle(articleNo);
//				redirect(request, response, "/notice?act=list");
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글 삭제 중 에러발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "관리자만 이용 가능합니다.");
//			forward(request, response, "/notice?act=list&" + queryString);
//		}
//	}
//
//	private void mvModify(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if (userDto != null && userDto.getIsManager() != 0) {
//			int articleNo = Integer.parseInt(request.getParameter("articleno"));
//			NoticeDto noticeDto;
//			try {
//				noticeDto = noticeService.getArticle(articleNo);
//				request.setAttribute("notice", noticeDto);
//				forward(request, response, "/notice/modify.jsp?" + queryString);
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "목록 처리 중 에러 발생!!!");
//				forward(request, response, "/index.jsp");
//
//			}
//		} else {
//			request.setAttribute("msg", "관리자만 이용 가능합니다.");
//			forward(request, response, "/notice?act=list&" + queryString);
//		}
//	}
//
//	private void modify(HttpServletRequest request, HttpServletResponse response, String queryString)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserDto userDto = (UserDto) session.getAttribute("userinfo");
//		if (userDto != null && userDto.getIsManager() != 0) {
//			NoticeDto noticeDto = new NoticeDto();
//			noticeDto.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
//			noticeDto.setSubject(request.getParameter("subject"));
//			noticeDto.setContent(request.getParameter("content"));
//			try {
//				noticeService.modifyArticle(noticeDto);
//				redirect(request, response, "/notice?act=list&" + queryString);
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "수정 중 에러 발생!!!");
//				forward(request, response, "/index.jsp");
//			}
//		} else {
//			request.setAttribute("msg", "관리자만 이용 가능합니다.");
//			forward(request, response, "/notice?act=list&" + queryString);
//		}
//	}

}
