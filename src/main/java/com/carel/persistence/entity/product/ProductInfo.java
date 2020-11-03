
package com.carel.persistence.entity.product;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import com.carel.persistence.jpa.postgres.arrays.PostgresStringArray;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Description:
 * @author Matthew Xu
 * @date Jul 14, 2020
 */
@Entity
@TypeDef(name="stringArray", typeClass=PostgresStringArray.class)
public class ProductInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	private String productCode;
	
	@OneToMany(mappedBy = "productInfo")
	@JsonIgnore
	private List<ReplaceablePart> replaceableParts;
	
	@OneToMany(mappedBy = "productInfo")
	@JsonIgnore
	private List<HumidifierAlarm> humidifierAlarms;
	
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
	
	public ProductInfo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<HumidifierAlarm> getHumidifierAlarms() {
		return humidifierAlarms;
	}

	public void setHumidifierAlarms(List<HumidifierAlarm> humidifierAlarms) {
		this.humidifierAlarms = humidifierAlarms;
	}

	public List<ReplaceablePart> getReplaceableParts() {
		return replaceableParts;
	}

	public void setReplaceableParts(List<ReplaceablePart> replaceableParts) {
		this.replaceableParts = replaceableParts;
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
