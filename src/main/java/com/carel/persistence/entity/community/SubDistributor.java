
package com.carel.persistence.entity.community;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import com.carel.persistence.entity.product.Product;
import com.carel.persistence.jpa.postgres.arrays.PostgresStringArray;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 2, 2020
 */
@Entity
@TypeDef(name="stringArray", typeClass=PostgresStringArray.class)
public class SubDistributor {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne
	@JoinColumn(name = "dis_id", referencedColumnName = "id")
	private Distributor distributor;

	@OneToMany(mappedBy = "subDistributor")
	@JsonIgnore
	private List<Product> products;
	
	private String code;
	
	private String description;
	
	private String loginCode;
	
	@Type(type = "stringArray")
	private String[] phones;
	
	@Type(type = "stringArray")
	private String[] emails;
	
	private boolean isShownPolicy;
	
	@Column(updatable = false)
	@CreationTimestamp
	private Date createTime;

	@UpdateTimestamp
	private Date updateTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String[] getPhones() {
		return phones;
	}

	public void setPhones(String[] phones) {
		this.phones = phones;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
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

}
