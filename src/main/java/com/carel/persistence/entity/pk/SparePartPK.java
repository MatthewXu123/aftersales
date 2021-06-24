
package com.carel.persistence.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Description:
 * @author Matthew Xu
 * @date Jun 23, 2021
 */
@Embeddable
public class SparePartPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "product_code")
	private String productCode;
	
	@Column(name = "part_code")
	private String partCode;

	public SparePartPK(String productCode, String partCode) {
		super();
		this.productCode = productCode;
		this.partCode = partCode;
	}

	public SparePartPK() {
		super();
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partCode == null) ? 0 : partCode.hashCode());
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
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
		SparePartPK other = (SparePartPK) obj;
		if (partCode == null) {
			if (other.partCode != null)
				return false;
		} else if (!partCode.equals(other.partCode))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SparePartPK [productCode=" + productCode + ", partCode=" + partCode + "]";
	}
	
}
