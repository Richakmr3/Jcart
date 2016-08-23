package com.infy.jcart.beans;


public class Camera {
	
	private Integer productId;
	private String company;
	private String productName;
	private Integer opticalZoom;
	private Integer digitalZoom;
	private Integer pixels;
	private Integer focalLength;
	private String memory;
	private Integer battery;
	private String shutterSpeed;
	private String description;
	private Boolean selected;
	private Double price;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getOpticalZoom() {
		return opticalZoom;
	}
	public void setOpticalZoom(Integer opticalZoom) {
		this.opticalZoom = opticalZoom;
	}
	public Integer getDigitalZoom() {
		return digitalZoom;
	}
	public void setDigitalZoom(Integer digitalZoom) {
		this.digitalZoom = digitalZoom;
	}
	public Integer getPixels() {
		return pixels;
	}
	public void setPixels(Integer pixels) {
		this.pixels = pixels;
	}
	public Integer getFocalLength() {
		return focalLength;
	}
	public void setFocalLength(Integer focalLength) {
		this.focalLength = focalLength;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public Integer getBattery() {
		return battery;
	}
	public void setBattery(Integer battery) {
		this.battery = battery;
	}
	public String getShutterSpeed() {
		return shutterSpeed;
	}
	public void setShutterSpeed(String shutterSpeed) {
		this.shutterSpeed = shutterSpeed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
}