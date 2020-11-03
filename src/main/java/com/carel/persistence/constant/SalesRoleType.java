package com.carel.persistence.constant;

public enum SalesRoleType {

	BRANCH_MANAGER("Branch Manager"),
	SALES_MANAGER("Sales Manager");
	
	private String description;
	
	private SalesRoleType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
