
package com.carel.persistence.entity.community;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.alibaba.fastjson.annotation.JSONField;
import com.carel.persistence.constant.CustomerCategory;
import com.carel.persistence.entity.product.Order;
import com.carel.persistence.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 14, 2020
 */
@Entity
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @ManyToMany(mappedBy = "customerList", fetch = FetchType.EAGER)
    @JSONField(serialize = false)
	private List<Sales> salesList = new ArrayList<>();
    
//	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
//	@Fetch(FetchMode.SUBSELECT)
//	@JsonIgnore
//	private List<Order> orders;
    
    @OneToMany(mappedBy = "ownerCustomer")
    @JsonIgnore
	private List<Product> ownedProductList;
    
	private String code;
	
	private Integer deptId;
	
	private String partyName;
	
	private String loginCode;
	
	@Enumerated(EnumType.STRING)
	private CustomerCategory customerCategory;
	
	private boolean isOwnerCustomer;
	
	private boolean isShownPolicy;
	
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
	
	public Customer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Sales> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<Sales> salesList) {
		this.salesList = salesList;
	}

	public CustomerCategory getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(CustomerCategory customerCategory) {
		this.customerCategory = customerCategory;
	}

//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
	
	public String getCode() {
		return code;
	}

	public List<Product> getOwnedProductList() {
		return ownedProductList;
	}

	public void setOwnedProductList(List<Product> ownedProductList) {
		this.ownedProductList = ownedProductList;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public boolean getIsOwnerCustomer() {
		return isOwnerCustomer;
	}

	public void setIsOwnerCustomer(boolean isOwnerCustomer) {
		this.isOwnerCustomer = isOwnerCustomer;
	}

	public boolean getIsShownPolicy() {
		return isShownPolicy;
	}

	public void setIsShownPolicy(boolean isShownPolicy) {
		this.isShownPolicy = isShownPolicy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
