package com.carel.persistence.constant;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 15, 2020
 */
public enum ProcessStatus {

	PENDING("PENDING"),
	IN_PROGRESS("IN_PROGRESS"),
	FINISHED("FINISHED"),
	EVALUATED("EVALUATED");
	
	private String description;

	private ProcessStatus(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
