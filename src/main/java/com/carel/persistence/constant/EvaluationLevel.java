package com.carel.persistence.constant;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 15, 2020
 */
public enum EvaluationLevel {

	SATISFIED("SATISFIED", "满意"),
	COMMON("COMMON", "一般"),
	UNSATISFIED("UNSATISFIED", "不满意");
	
	private String description;
	
	private String secDescription;
	
	private EvaluationLevel(String description,String secDescription){
		this.description = description;
		this.secDescription = secDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSecDescription() {
		return secDescription;
	}

	public void setSecDescription(String secDescription) {
		this.secDescription = secDescription;
	}
	
}
