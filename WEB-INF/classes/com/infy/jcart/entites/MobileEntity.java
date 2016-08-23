package com.infy.jcart.entites;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JCART_MOBILE")
public class MobileEntity {
	
	@Id
	private Integer productId;
	private String company;
	private String productName;
	private Character sim;
	private Double camera;
	private Integer screenSize;
	private String resolution;
	private Double batteryType;
	private String description;
	private String generation;
	private String memory;
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getGeneration() {
		return generation;
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
	public void setGeneration(String generation) {
		this.generation = generation;
	}
	public Character getSim() {
		return sim;
	}
	public void setSim(Character sim) {
		this.sim = sim;
	}
	public Double getCamera() {
		return camera;
	}
	public void setCamera(Double camera) {
		this.camera = camera;
	}
	public Integer getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(Integer screenSize) {
		this.screenSize = screenSize;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public Double getBatteryType() {
		return batteryType;
	}
	public void setBatteryType(Double batteryType) {
		this.batteryType = batteryType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	
}