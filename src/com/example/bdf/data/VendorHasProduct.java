package com.example.bdf.data;

public class VendorHasProduct {
	private int id;
	private int vendorId;
	private int productBarcode;
	private double price;
	public VendorHasProduct() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getProductBarcode() {
		return productBarcode;
	}

	public void setProductBarcode(int productBarcode) {
		this.productBarcode = productBarcode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
