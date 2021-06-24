
package com.carel.persistence.entity.product;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.carel.persistence.entity.pk.SparePartPK;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 23, 2020
 */
@Entity
public class SparePart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SparePartPK pk;
	
	private String description;
	
	private int requiredNum;
	
	@Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

	public SparePart(SparePartPK pk, String description, int requiredNum, Date createTime, Date updateTime) {
		super();
		this.pk = pk;
		this.description = description;
		this.requiredNum = requiredNum;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public SparePart() {
		super();
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRequiredNum() {
		return requiredNum;
	}

	public void setRequiredNum(int requiredNum) {
		this.requiredNum = requiredNum;
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

	public SparePartPK getPk() {
		return pk;
	}

	public void setPk(SparePartPK pk) {
		this.pk = pk;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		SparePart other = (SparePart) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SparePart [pk=" + pk + ", description=" + description + ", requiredNum=" + requiredNum + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
}
