package com.eatory.mvc.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatory.mvc.jwt.JwtUtil;
import com.eatory.mvc.model.dto.Allergy;
import com.eatory.mvc.model.dto.User;
import com.eatory.mvc.model.service.AllergyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-allergy")
@CrossOrigin("*")
@Tag(name="Allergy API", description="Allergy CRUD API")

public class AllergyController {
	private final AllergyService allergyService;
	private final JwtUtil jwtUtil;
	
	@Autowired
	public AllergyController(AllergyService allergyService, JwtUtil jwtUtil) {
		this.allergyService = allergyService;
		this.jwtUtil = jwtUtil;
	}

	
	// 알러지 정보 조회 
	@GetMapping
    @Operation(summary = "알러지 목록 조회", description = "알러지 목록을 조회합니다.")
	public ResponseEntity<List<Allergy>> getAllAllergies(){
		List<Allergy> allergies = allergyService.getAllAllergies();
		if(allergies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Allergy>>(allergies, HttpStatus.OK);
	}
	
	
	//사용자 알러지 추가 
	@PostMapping("/user/{userId}")
	public ResponseEntity<String> addUserAllergy(@PathVariable Long userId, @RequestBody Allergy allergy) {
		boolean success = allergyService.addUserAllergy(userId, allergy.getAllergyId());
		if(success) {
			return ResponseEntity.status(HttpStatus.CREATED).body("사용자 알러지 추가 성공!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 알러지 추가 실패!");
	} 
	
	//사용자 알러지 삭제
	@DeleteMapping("/user/{userId}/{allergyId}")
	public ResponseEntity<String> deleteUserAllergy(@PathVariable Long userId, @PathVariable Long allergyId) {
		boolean success = allergyService.deleteUserAllergy(userId, allergyId);
		if(success) {
			return ResponseEntity.status(HttpStatus.CREATED).body("사용자 알러지 추가 성공!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 알러지 추가 실패!");
	} 
	
	
	

}
