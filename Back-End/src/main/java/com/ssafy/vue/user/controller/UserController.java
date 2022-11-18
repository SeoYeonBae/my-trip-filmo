package com.ssafy.vue.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.do17.controller.DoController;
import com.ssafy.vue.user.model.UserDto;
import com.ssafy.vue.user.model.service.JwtServiceImpl;
import com.ssafy.vue.user.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends HttpServlet {

	private Logger logger = LoggerFactory.getLogger(DoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private static final long serialVersionUID = 1L;

	@Autowired
	private JwtServiceImpl jwtService;
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.loginUser(userDto);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());// key, data
				userService.saveRefreshToken(userDto.getId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//		try {
//			UserDto user = userService.loginUser(map); // JSON Array
//			logger.debug("login dtd : {} " + user);
//			if (user != null) {
//				return new ResponseEntity<UserDto>(user, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return exceptionHandling(e);
//		}
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
