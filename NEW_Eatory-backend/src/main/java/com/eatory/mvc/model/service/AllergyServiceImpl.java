package com.eatory.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatory.mvc.model.dao.AllergyDao;
import com.eatory.mvc.model.dto.Allergy;

@Service
public class AllergyServiceImpl implements AllergyService{
	
	private final AllergyDao allergydao;

    @Autowired
    public AllergyServiceImpl(AllergyDao allergydao) {
        this.allergydao = allergydao;
    }

    public List<Allergy> getAllAllergies() {
        return allergydao.selectAllergies();
    }

    public boolean addUserAllergy(Long userId, Long allergyId) {
        try {
            System.out.println("service: addUserAllergy 호출됨 - userId: " + userId + ", allergyId: " + allergyId);
            allergydao.insertUserAllergy(userId, allergyId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("service:알러지 추가 중 오류 발생: " + e.getMessage());
            return false;
        }
    }                

    public boolean deleteUserAllergy(Long userId, Long allergyId) {
        try {
        	allergydao.deleteUserAllergy(userId, allergyId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
