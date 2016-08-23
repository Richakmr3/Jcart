package com.infy.jcart.entites;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="JCART_USERWISHLIST")
public class UserWishListEntity {
	
	@EmbeddedId
	private UserWishListPK listPK;

	public UserWishListPK getListPK() {
		return listPK;
	}

	public void setListPK(UserWishListPK listPK) {
		this.listPK = listPK;
	}
	
	
	
	
}