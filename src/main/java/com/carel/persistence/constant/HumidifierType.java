package com.carel.persistence.constant;

public enum HumidifierType {
	
	UE("UE",1),
	UR("UR",2),
	HUT("HUT",3),
	HUH("HUH",4),
	OTHER("OTHER",0);
	
	private String description;
	
	private int code;
	
	private HumidifierType(String description){
		this.description = description;
	}
	
	private HumidifierType(int code){
		this.code = code;
	}
	
	private HumidifierType(String description, int code){
		this.description = description;
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
	
}
