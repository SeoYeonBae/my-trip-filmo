package com.ssafy.vue.tourlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping({ "/", "/index" })
	public String showRoot() {
		return "index";
	}
	
	@GetMapping("/sun")
	public String sun() {
		return "sun/sun";
	}

	@GetMapping("/weather")
	public String weather() {
		return "weather/weather";
	}

	@GetMapping("/map")
	public String map() {
		return "map/map";
	}

	@GetMapping("/nlist")
	public String noticeList() {
		return "notice/list";
	}
	
	@GetMapping("/blist")
	public String boardList() {
	    return "board/list";
	}
}
