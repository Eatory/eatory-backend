package com.eatory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.model.dto.User;
import com.eatory.model.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api-user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
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
		boolean isRegistered = userService.registerUser(user);
		if(isRegistered) {
			return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
		} 
		
		return new ResponseEntity<>("User registered failed", HttpStatus.BAD_REQUEST);
	}
	
	//사용자 로그인
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User loginRequest, HttpSession session){
		User user = userService.login(loginRequest.getUserId(), loginRequest.getPassword());
		if(user != null) {
			session.setAttribute("user", user); //세션에 사용자 정보 저장
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
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
