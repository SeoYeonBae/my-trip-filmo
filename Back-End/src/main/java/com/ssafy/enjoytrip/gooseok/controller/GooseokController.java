package com.ssafy.enjoytrip.gooseok.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.enjoytrip.do17.controller.DoController;

@RestController
@RequestMapping("/gooseok")
public class GooseokController extends HttpServlet {
	
	private Logger logger = LoggerFactory.getLogger(DoController.class);
	
	private static final long serialVersionUID = 1L;
	
	@GetMapping("/{search}")
	public ModelAndView sun(@PathVariable("search") String search) {
		logger.debug("serch item : " + search);
		ModelAndView mav = new ModelAndView();
		switch (search) {
		case "sooncheon":
			mav.addObject("search", "순천시 맛집");
			break;
		case "seogwipo":
			mav.addObject("search", "서귀포시 맛집");
			break;
		case "gangreung":
			mav.addObject("search", "강릉시 맛집");
			break;
		case "haewoondae":
			mav.addObject("search", "해운대구 맛집");
			break;
		case "ganghwa":
			mav.addObject("search", "강화군 맛집");
			break;
		}
		mav.setViewName("/gooseokgooseok/gooseok");
		return mav;
	}
}
