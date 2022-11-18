package com.ssafy.vue.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.do17.controller.DoController;
import com.ssafy.vue.user.model.UserDto;
import com.ssafy.vue.user.model.service.UserService;
import com.ssafy.vue.user.model.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController extends HttpServlet {

	private Logger logger = LoggerFactory.getLogger(DoController.class);

	private static final long serialVersionUID = 1L;

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/{userid}")
	// 중복 맵핑 은 안되ㅑㄹ라나
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}" + userId);
		int cnt = userService.checkID(userId);
		return cnt + "";
	}

	@PostMapping("/join")
	public String join(@RequestBody UserDto userDto, Model model) {
		logger.debug("userDto info : {}", userDto);
		try {
			int cnt = userService.joinUser(userDto);
			return cnt + "";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return "error/error";
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> bookList(@RequestBody Map<String, Object> map) {
		try {
			UserDto user = userService.loginUser(map); // JSON Array
			logger.debug("login dtd : {} " + user);
			if (user != null) {
				return new ResponseEntity<UserDto>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PutMapping("/modify")
	public ResponseEntity<?> userModify(@RequestBody UserDto userDto) {
		logger.debug("userModify userDto : {}", userDto);
		try {
			userService.modifyUser(userDto);
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/findPass")
	public ResponseEntity<?> findPass(@RequestBody Map<String, Object> map) {
		logger.debug("find pass info : {}" + map);
		try {
			String pass = userService.findPass(map);
			if (pass != null && pass != "")
				return new ResponseEntity<String>(pass, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

//	@DeleteMapping("/{userid}")
//	public ResponseEntity<?> deleteUser(@PathVariable("userid") String userId) {
//		try {
//			String cnt = userService.deleteUser(userId) + "";
//			if("1".equals(cnt))
//				return new ResponseEntity<String>(cnt, HttpStatus.OK);
//			else
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
