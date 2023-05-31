package com.mesgs.springlistener.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ORDER_DETAILS")
public class Messages implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	String productID;
	String productName;
	int quantity;
	
	public Messages() {
		super();
	}
	
	public Messages(String productID, String productName, int quantity) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Messages [id=" + id + ", productID=" + productID + ", productName=" + productName + ", quantity="
				+ quantity + "]";
	}
	
}
