package com.eatory.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.mvc.jwt.JwtUtil;
import com.eatory.mvc.model.dto.LoginRequest;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.service.UserService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-user")
@Tag(name = "User API", description = "User CRUD API")
@CrossOrigin("*")
public class UserController {
	
	private final UserService userService;
	private final JwtUtil jwtUtil;
	
	public UserController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	//회원 목록 불러오기
	@GetMapping("/users")
	public ResponseEntity<List<User>> userList() {
		List<User> users = userService.getUserList();
		if(users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	//사용자 회원가입
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User user) {
		boolean isRegistered = userService.signup(user);
		if(isRegistered) {
			return ResponseEntity.status(HttpStatus.CREATED).body("유저가 성공적으로 등록됨! 회원가입 완료!");
		} 
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("유저 회원가입 실패!");
	}
	
	//사용자 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest){
		HttpStatus status = null;
		Map<String, Object> result = new HashMap<>();
		User loginUser = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
		
		if(loginUser != null) {
			result.put("message", "login 성공");
			result.put("access-token", jwtUtil.createToken(loginUser.getUsername()));
//			result.put("user", loginUser);
			result.put("user", Map.of("email", loginUser.getEmail(), "name", loginUser.getUsername())); // 데이터 구조 명확히 반환
			status = HttpStatus.ACCEPTED;

		} else {
			result.put("message", "login 실패");
			status = HttpStatus.UNAUTHORIZED;
		} 
		return new ResponseEntity<>(result, status);
	}
	
	
	//사용자 로그아웃 
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session){
		session.invalidate(); //세션 무효화하여 로그아웃 처리
		return new ResponseEntity<String>("Logged out successfully",HttpStatus.OK);
	}
	
	//추가 피드백
	//DTO 사용: LoginRequestDto, SignUpRequestDto 생성하여 사용하는 것을 추천합니다.
	//민감 정보가 포함된 로그인 요청이나 회원가입의 경우 이렇게 따로 사용함 
	
	
	
}
