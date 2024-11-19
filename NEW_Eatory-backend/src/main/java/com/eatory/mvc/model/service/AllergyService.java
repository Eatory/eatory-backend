package com.eatory.mvc.model.service;

import java.util.List;

import com.eatory.mvc.model.dto.Allergy;

public interface AllergyService {

	List<Allergy> getAllAllergies();

	boolean addUserAllergy(Long userId, Long allergyId);

	boolean deleteUserAllergy(Long userId, Long allergyId);


}
