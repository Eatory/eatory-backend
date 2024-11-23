package com.eatory.mvc.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Allergy {
	@JsonProperty("allergyId")
	private Long allergyId;
	@JsonProperty("allergyName")
	private String allergyName;
	
	public Allergy() {}
	
	public Long getAllergyId() {
		return allergyId;
	}
	public void setAllergyId(Long allergyId) {
		this.allergyId = allergyId;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	@Override
	public String toString() {
		return "Allergy [allergyId=" + allergyId + ", allergyName=" + allergyName + "]";
	}
	
	
	

}
