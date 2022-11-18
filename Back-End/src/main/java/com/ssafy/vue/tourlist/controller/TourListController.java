package com.ssafy.vue.tourlist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.vue.do17.controller.DoController;
import com.ssafy.vue.tourlist.model.TourListDto;
import com.ssafy.vue.tourlist.model.service.TourListService;
import com.ssafy.vue.tourlist.model.service.TourListServiceImpl;
import com.ssafy.vue.util.ParameterCheck;

@RestController
@RequestMapping("/tourlist")
public class TourListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(DoController.class);
	
	private TourListService tourListService;


	@Autowired
	public TourListController(TourListService tourListService) {
		super();
		this.tourListService = tourListService;
	}

	@GetMapping("/today/{typeId}")
	public ModelAndView TodaysTour(@PathVariable("typeId") int typeId) {
		logger.debug("today's TourList 진입 :  타입은 " +typeId);
		
		ModelAndView mav = new ModelAndView();
		
		String todayTitle = "";
	
		switch(typeId) {
		case 12:
			mav.addObject("typeId", 12);
			todayTitle = "오늘의 관광지";
			break;
		case 15:
			mav.addObject("typeId", 15);
			todayTitle = "오늘의 행사/공연/축제";
			break;
		case 39:
			mav.addObject("typeId", 39);
			todayTitle = "오늘의 맛집";
			break;
		}

		mav.addObject("keywordRecommend", todayTitle);
		mav.setViewName("/recommend/recommend");
		return mav;
		
	}
	
	
	@GetMapping("/recommend/{typeId}")
	private ResponseEntity<?> recommend(@PathVariable("typeId") int typeId) {
		logger.debug("typeId : " + typeId);
		try {
			TourListDto tourListDto = tourListService.recommend(typeId);
			logger.debug("Dto : {}" + tourListDto);
			if(tourListDto != null) {
				return new ResponseEntity<TourListDto>(tourListDto, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	@GetMapping("/{areaCode}/{sigunguCode}/{contentTypeId}")
	public ResponseEntity<?> map(@PathVariable("areaCode") int areaCode, @PathVariable("sigunguCode") int sigunguCode, @PathVariable("contentTypeId") int contentTypeId) {
		try {
			logger.debug("contentTypeId : " + contentTypeId);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("areaCode", areaCode);
			map.put("sigunguCode", sigunguCode);
				map.put("contentTypeId", contentTypeId);
			List<TourListDto> list = tourListService.getData(map);
			logger.debug("tourList : {} ", list);
			if (list != null) {
				return new ResponseEntity<List<TourListDto>>(list, HttpStatus.OK);
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
