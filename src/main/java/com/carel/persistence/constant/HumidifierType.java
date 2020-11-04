package com.carel.persistence.constant;

public enum HumidifierType {

	HUH("HUH"),
	HUT("HUT");
	
	private String description;
	
	private HumidifierType(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
