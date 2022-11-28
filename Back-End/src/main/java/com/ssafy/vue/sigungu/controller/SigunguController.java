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

	@GetMapping("/{areaCode}")
	public ResponseEntity<?> sigunguGet(@PathVariable("areaCode") int areaCode) {
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

}
