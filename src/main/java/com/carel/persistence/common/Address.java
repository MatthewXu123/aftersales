package com.carel.persistence.common;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 15, 2020
 */
public class Address{

	private String nation;
	
	private String province;
	
	private String city;
	
	private String street;

	public Address() {
		super();
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address [nation=" + nation + ", province=" + province + ", city=" + city + ", street=" + street + "]";
	}

	
}
