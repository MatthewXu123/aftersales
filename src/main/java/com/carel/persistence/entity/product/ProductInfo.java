
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

import com.alibaba.fastjson.annotation.JSONField;
import com.carel.persistence.jpa.postgres.arrays.PostgresStringArray;

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

    private String description;
    
    private String type;
    
	@OneToMany(mappedBy = "productInfo")
	@JSONField(serialize = false)
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<HumidifierAlarm> getHumidifierAlarms() {
		return humidifierAlarms;
	}

	public void setHumidifierAlarms(List<HumidifierAlarm> humidifierAlarms) {
		this.humidifierAlarms = humidifierAlarms;
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
