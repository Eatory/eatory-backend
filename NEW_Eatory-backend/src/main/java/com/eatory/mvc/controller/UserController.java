package com.eatory.mvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.mvc.jwt.JwtUtil;
import com.eatory.mvc.model.dto.LoginRequest;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.dto.UserProfile;
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
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//프로필 정보 조회 (회원 불러오기)
	@GetMapping("/{userId}")
	public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long userId){
		UserProfile userProfile = userService.getUserProfile(userId);
		if( userProfile == null ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userProfile, HttpStatus.OK);
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
	
	//사용자 로그인 json {"email":"222@gmail.com", "password":"pass1"}
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest){
		//Service 에서 로그인 처리 및 토큰 생성 
		Map<String, Object> response = userService.loginUser(loginRequest);
		
		if(response.containsKey("access-token")) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}
	
	//로그아웃 API
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody Map<String, String> body) {
		
		String email = body.get("email");
		if (email == null || email.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
	    }
		
		try {
			// Refresh Token 삭제
			boolean isDeleted = userService.logoutUser(email);
			
			if(isDeleted) {
				return ResponseEntity.status(HttpStatus.OK).body("Logout 성공!");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Logout 실패!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃 중 에러가 발생했습니다.");
		}
	}
	
	
	
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request){
		//Service에서 Refresh Token 검증 및 새로운 Access Token 발급 
		String email = request.get("email");
		String refreshToken = request.get("refreshToken");
		
		String newAccessToken = userService.refreshAccessToken(email, refreshToken);
		if(newAccessToken != null) {
			return ResponseEntity.ok(Map.of("access-token", newAccessToken));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("message", "유효하지 않거나 만료된 Refresh Token 입니다."));
		}
		
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
