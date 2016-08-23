package com.infy.jcart.entites;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="JCART_BROWSINGHISTORY")
public class BrowsingHistoryEntity {
	
	@EmbeddedId
	private BrowsingHistoryPK browsingHistoryPK;
	private Integer productId;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public BrowsingHistoryPK getBrowsingHistoryPK() {
		return browsingHistoryPK;
	}
	public void setBrowsingHistoryPK(BrowsingHistoryPK browsingHistoryPK) {
		this.browsingHistoryPK = browsingHistoryPK;
	}
	
	
	
}