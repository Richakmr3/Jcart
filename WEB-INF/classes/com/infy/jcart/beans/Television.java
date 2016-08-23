package com.infy.jcart.beans;


public class Television {
	
	private Integer productId;
	private String company;
	private String productName;
	private String pixelResolution;
	private Integer screenSize;
	private Character hdReady;
	private String description;
	private String dimension;
	private Double powerConsumption;
	private Double speakerOutput;
	private String speakerType;
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
	public String getPixelResolution() {
		return pixelResolution;
	}
	public void setPixelResolution(String pixelResolution) {
		this.pixelResolution = pixelResolution;
	}
	public Integer getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(Integer screenSize) {
		this.screenSize = screenSize;
	}
	public Character getHdReady() {
		return hdReady;
	}
	public void setHdReady(Character hdReady) {
		this.hdReady = hdReady;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public Double getPowerConsumption() {
		return powerConsumption;
	}
	public void setPowerConsumption(Double powerConsumption) {
		this.powerConsumption = powerConsumption;
	}
	public Double getSpeakerOutput() {
		return speakerOutput;
	}
	public void setSpeakerOutput(Double speakerOutput) {
		this.speakerOutput = speakerOutput;
	}
	public String getSpeakerType() {
		return speakerType;
	}
	public void setSpeakerType(String speakerType) {
		this.speakerType = speakerType;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
	
}