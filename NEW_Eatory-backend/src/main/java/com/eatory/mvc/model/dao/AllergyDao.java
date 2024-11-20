package com.eatory.mvc.model.dao;

import java.util.List;

import com.eatory.mvc.model.dto.Allergy;

public interface AllergyDao {
	
		
	//사용자 알러지 추가 
	void insertUserAllergy(Long userId, Long allergyId);
	
	//사용자 알러지 삭제
	void deleteUserAllergy(Long userId, Long allergyId);

	//사용자 ID로 알러지 리스트 조회
	List<String> getAllergiesByUserId(Long userId);
	
	//eatory(주)가 관리하는 알러지 리스트 가져오기 
	List<Allergy> selectAllergies();

}
