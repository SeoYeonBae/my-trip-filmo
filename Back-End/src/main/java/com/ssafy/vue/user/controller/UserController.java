package com.ssafy.vue.user.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ssafy.vue.board.model.BoardDto;
import com.ssafy.vue.board.model.service.BoardService;
import com.ssafy.vue.board.model.service.BoardServiceImpl;
import com.ssafy.vue.do17.controller.DoController;
import com.ssafy.vue.reply.service.ReplyService;
import com.ssafy.vue.user.model.UserDto;
import com.ssafy.vue.user.model.service.JwtServiceImpl;
import com.ssafy.vue.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.coobird.thumbnailator.Thumbnails;

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

	// ?????????
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
//				logger.debug("????????? accessToken ?????? : {}", accessToken);
//				logger.debug("????????? refreshToken ?????? : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("????????? ?????? : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// ?????? ??????
	@ApiOperation(value = "????????????", notes = "?????? ????????? ?????? Token??? ????????????.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "????????? ????????? ?????????.", required = true) String userid,
			HttpServletRequest request) {
		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
//			logger.info("?????? ????????? ??????!!!");
			try {
//				????????? ????????? ??????.
				UserDto userDto = userService.userInfo(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("???????????? ?????? : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("?????? ????????? ??????!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// ????????????
	@ApiOperation(value = "????????????", notes = "?????? ????????? ?????? Token??? ????????????.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("???????????? ?????? : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	// ?????? ??????
	@ApiOperation(value = "Access Token ?????????", notes = "????????? access token??? ??????????????????.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, userDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getId()))) {
				String accessToken = jwtService.createAccessToken("userid", userDto.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("??????????????? ??????????????? ?????????!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("????????????????????? ?????????!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// ????????? ?????? ??????
	@GetMapping("/{userid}")
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}" + userId);
		int cnt = userService.checkID(userId);
		return cnt + "";
	}

	// ?????? ??????
	@PostMapping("/join")
	public String join(@RequestBody UserDto userDto, Model model) {
		try {
			logger.info("userDto info : {}", userDto);
			int cnt = userService.joinUser(userDto);
			return cnt + "";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "?????? ?????? ??? ?????? ??????!!!");
			return "error/error";
		}
	}

	// ?????? ?????? ??????
	@PutMapping("/modify")
	public ResponseEntity<?> userModify(@RequestBody UserDto userDto) {
		try {
			logger.info("userModify userDto : {}", userDto);
			userService.modifyUser(userDto);
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// ?????? ?????? ?????? + ?????????
	@PutMapping("/modify/img")
	public ResponseEntity<?> userModify(@Value("${file.path.upload-profile}") String filePath,
			@RequestPart("userDto") UserDto userDto,
			@RequestPart(value = "fileInfo", required = false) MultipartFile file) {
		logger.info("userModify userDto: {}", userDto);
		logger.info("userModify userDto with img: {}", file);
		try {
			if (!file.isEmpty()) {
				String saveFolder = filePath;
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				String originalFileName = file.getOriginalFilename();
				String saveFileName = userDto.getId() + originalFileName.substring(originalFileName.lastIndexOf('.'));
				logger.info("?????? ?????? ?????? : {}, ?????? ?????? ?????? ?????? : {}", file.getOriginalFilename(), saveFileName);
				File saveFile = new File(folder, saveFileName);
				file.transferTo(saveFile);
//	            Thrumbnail Image
				File thumbnailFile = new File(saveFolder, "s_" + saveFileName);
				userDto.setSaveFile(saveFileName);

				BufferedImage bo_img = ImageIO.read(saveFile);
				double ratio = 3;
				int width = (int) (bo_img.getWidth() / ratio);
				int height = (int) (bo_img.getHeight() / ratio);

				Thumbnails.of(saveFile).size(width, height).toFile(thumbnailFile);
			}
			userService.modifyUser(userDto);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// ???????????? ??????
	@PostMapping("/findPass")
	public ResponseEntity<?> findPass(@Value("${spring.mail.manager}") String managerMail, @Value("${spring.mail.password}") String managerPass, @RequestBody Map<String, Object> map) {
		try {
			logger.info("find pass info : {}" + map);
			Map<String, String> mailInfo = userService.findPass(map);
			if (mailInfo != null) {
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

				Session session = Session.getInstance(props, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(managerMail, managerPass);
					}
				});

				String receiver = mailInfo.get("user_email"); // ?????? ?????? ??????
				String pass = mailInfo.get("user_password");

				String title = "????????? ?????? ???????????? ??????";
				String content = "<h2 class='font-weight-bold text-primary heading mt-5 mb-5'> ???????????? ???????????? ??????????????? " + pass
						+ " ?????????." + "</h2>";
				Message message = new MimeMessage(session);
				try {
					message.setFrom(new InternetAddress("megd78988@gmail.com", "????????? ??????", "utf-8"));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
					message.setSubject(title);
					message.setContent(content, "text/html; charset=utf-8");

					Transport.send(message);
				} catch (Exception e) {
					return exceptionHandling(e);
				}
				
				return new ResponseEntity<Map<String, String>>(mailInfo, HttpStatus.OK);
			} else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// ?????? ??????
	@PostMapping("/delete")
	public ResponseEntity<?> deleteUser(@Value("${file.path.upload-profile}") String filePath, @RequestBody UserDto userDto) {
		logger.debug("?????? ????????? : " + userDto);
		try {
			userService.offFk();
			userService.deleteUser(userDto, filePath);
			userService.onFk();
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
