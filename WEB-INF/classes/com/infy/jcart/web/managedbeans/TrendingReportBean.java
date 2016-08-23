package com.infy.jcart.web.managedbeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped

public class TrendingReportBean {
	private String message;
	private List<Entry<String, Double>> reportData;
	private String choice;
	private List<SelectItem> years = new ArrayList<SelectItem>();
	private List<SelectItem> months = new ArrayList<SelectItem>();
	private String from;
	private String to;

	/****
	 * @description This method is used to populate year and months for selection
	 * 
	 * @exception Exception
	 */
	public TrendingReportBean() {
		try {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			for (int i = 0; i < 5; i++) {
				years.add(new SelectItem(year + ""));
				year--;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("MMM,yyyy");

			for (int i = 0; i < 12; i++) {
				String textDate = sdf.format(c.getTime());
				months.add(new SelectItem(textDate));
				c.add(Calendar.MONTH, -1);
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "TrendingReportMB",
					e.getMessage());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Entry<String, Double>> getReportData() {
		return reportData;
	}

	public void setReportData(List<Entry<String, Double>> reportData) {
		this.reportData = reportData;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public List<SelectItem> getYears() {
		return years;
	}

	public void setYears(List<SelectItem> years) {
		this.years = years;
	}

	public List<SelectItem> getMonths() {
		return months;
	}

	public void setMonths(List<SelectItem> months) {
		this.months = months;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
