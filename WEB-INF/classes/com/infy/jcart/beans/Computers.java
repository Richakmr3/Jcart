package com.infy.jcart.beans;


public class Computers {
	
	private Integer productId;
	private String company;
	private String productName;
	private String processorName;
	private Double processorSpeed;
	private Integer ram;
	private Integer hardDiskSize;
	private String graphicCard;
	private Integer screenSize;
	private Integer batteryBackUp;
	private Integer batteryCells;
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
	public String getProcessorName() {
		return processorName;
	}
	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}
	public Double getProcessorSpeed() {
		return processorSpeed;
	}
	public void setProcessorSpeed(Double processorSpeed) {
		this.processorSpeed = processorSpeed;
	}
	public Integer getRam() {
		return ram;
	}
	public void setRam(Integer ram) {
		this.ram = ram;
	}
	public Integer getHardDiskSize() {
		return hardDiskSize;
	}
	public void setHardDiskSize(Integer hardDiskSize) {
		this.hardDiskSize = hardDiskSize;
	}
	public String getGraphicCard() {
		return graphicCard;
	}
	public void setGraphicCard(String graphicCard) {
		this.graphicCard = graphicCard;
	}
	public Integer getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(Integer screenSize) {
		this.screenSize = screenSize;
	}
	public Integer getBatteryBackUp() {
		return batteryBackUp;
	}
	public void setBatteryBackUp(Integer batteryBackUp) {
		this.batteryBackUp = batteryBackUp;
	}
	public Integer getBatteryCells() {
		return batteryCells;
	}
	public void setBatteryCells(Integer batteryCells) {
		this.batteryCells = batteryCells;
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