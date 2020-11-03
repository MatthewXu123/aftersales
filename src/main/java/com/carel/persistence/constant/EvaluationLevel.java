package com.carel.persistence.constant;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 15, 2020
 */
public enum EvaluationLevel {

	SATISFIED("SATISFIED"),
	COMMON("COMMON"),
	UNSATISFIED("UNSATISFIED");
	
	private String description;
	
	private EvaluationLevel(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
