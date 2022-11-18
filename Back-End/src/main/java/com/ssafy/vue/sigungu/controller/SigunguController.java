package com.ssafy.vue.sigungu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.vue.do17.controller.DoController;
import com.ssafy.vue.do17.model.DoDto;
import com.ssafy.vue.sigungu.model.SigunguDto;
import com.ssafy.vue.sigungu.model.service.SigunguService;
import com.ssafy.vue.sigungu.model.service.SigunguServiceImpl;

@RestController
@RequestMapping("/sigungu")
public class SigunguController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(SigunguController.class);
	
	private SigunguService sigugnuService;

	
	@Autowired
	public SigunguController(SigunguService sigugnuService) {
		super();
		this.sigugnuService = sigugnuService;
	}

	@GetMapping("/{areacode}")
	public ResponseEntity<?> sigunguGet(@PathVariable("areacode") int areaCode) {
		try {
			List<SigunguDto> list = sigugnuService.list(areaCode);
			logger.debug("시군구!!! " + list);
			if (list != null) {
				return new ResponseEntity<List<SigunguDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String act = request.getParameter("act");
//		
//		if("list".equals(act)) {
//			list(request, response);
//		} else {
//			redirect(request, response, "/index.jsp");
//		}
//		
//	}
//
//	private void list(HttpServletRequest request, HttpServletResponse response) {
//		int areaCode = Integer.parseInt(request.getParameter("areaCode"));
//		try {
//			List<SigunguDto> list = new ArrayList<SigunguDto>();
//			list = sigugnuService.list(areaCode);
//			ObjectMapper mapper = new ObjectMapper();
//			String jsonInString = mapper.writeValueAsString(list);
//			response.setContentType("application/json;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.print(jsonInString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//
//	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//		response.sendRedirect(request.getContextPath() + path);
//		
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		doGet(request, response);
//	}

}
