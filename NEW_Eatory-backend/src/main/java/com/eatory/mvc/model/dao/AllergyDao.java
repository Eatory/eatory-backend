package com.eatory.mvc.model.dao;

import java.util.List;

import com.eatory.mvc.model.dto.Allergy;

public interface AllergyDao {

	List<Allergy> selectAllergies();

	void insertUserAllergy(Long userId, Long allergyId);

	void deleteUserAllergy(Long userId, Long allergyId);

}
