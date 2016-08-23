package com.infy.jcart.web.managedbeans;


import java.util.ArrayList;



import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;



import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped

public class CategorySalesGraphBean {
	private String message;
	private String styleClass;
	private Integer choice;
	private String currentFinancialYear;
	private String previousFinancialYear;
	private List<Entry<String, Double>> categoryEntries;
	private List<Entry<String, Double>> subCategoryEntries;
	private HtmlDataTable datatable;
	private Entry<String, Double> selectedCategory;
	
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) 
	{
		this.styleClass = styleClass;
	}
	Calendar fromDate = null;
	Calendar toDate = null;

	/****
	 * @description This method is used in setting financial years for generating the
	 * category sales graph
	 */
	public CategorySalesGraphBean() {
		Calendar c = Calendar.getInstance();
		if (c.get(Calendar.MONTH) > 2) {
			setCurrentFinancialYear("Apr " + c.get(Calendar.YEAR) + " - Mar "
					+ (c.get(Calendar.YEAR) + 1));
			setPreviousFinancialYear("Apr " + (c.get(Calendar.YEAR) - 1)
					+ " - Mar " + (c.get(Calendar.YEAR)));
		} else {
			setCurrentFinancialYear("Apr " + (c.get(Calendar.YEAR) - 1)
					+ " - Mar " + c.get(Calendar.YEAR));
			setPreviousFinancialYear("Apr " + (c.get(Calendar.YEAR) - 2)
					+ " - Mar " + (c.get(Calendar.YEAR) - 1));

		}
	}

	/****
	 * @description This method is used to generate category sales graph for the selected
	 * financial year
	 * 
	 * 
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 */
	public void getCategorySalesGraph(ValueChangeEvent event) {
		try {
			categoryEntries = null;
			subCategoryEntries = null;
			message = null;
			this.choice = (Integer) event.getNewValue();
			fromDate = Calendar.getInstance();
			toDate = Calendar.getInstance();
			Calendar c = Calendar.getInstance();
			if (c.get(Calendar.MONTH) > 2) {
				if (choice == 1) {
					fromDate.set(Calendar.MONTH, 3);
					fromDate.set(Calendar.DATE, 1);
					toDate.set(Calendar.MONTH, 2);
					toDate.set(Calendar.DATE, 1);
					toDate.set(Calendar.YEAR, toDate.get(Calendar.YEAR) + 1);
				} else if (choice == 2) {
					fromDate.set(Calendar.MONTH, 3);
					fromDate.set(Calendar.DATE, 1);
					fromDate
							.set(Calendar.YEAR, fromDate.get(Calendar.YEAR) - 1);
					toDate.set(Calendar.MONTH, 2);
					toDate.set(Calendar.DATE, 1);
				}
			} else {
				if (choice == 1) {
					fromDate.set(Calendar.MONTH, 3);
					fromDate.set(Calendar.DATE, 1);
					fromDate
							.set(Calendar.YEAR, fromDate.get(Calendar.YEAR) - 1);
					toDate.set(Calendar.MONTH, 2);
					toDate.set(Calendar.DATE, 1);
					toDate.set(Calendar.YEAR, toDate.get(Calendar.YEAR));
				} else if (choice == 2) {
					fromDate.set(Calendar.MONTH, 3);
					fromDate.set(Calendar.DATE, 1);
					fromDate
							.set(Calendar.YEAR, fromDate.get(Calendar.YEAR) - 2);
					toDate.set(Calendar.MONTH, 2);
					toDate.set(Calendar.DATE, 1);
					toDate.set(Calendar.YEAR, toDate.get(Calendar.YEAR) - 1);
				}
			}
			Map<String, Double> map = Factory.createReportService().getCategorySalesData(
					fromDate, toDate);
			categoryEntries = new ArrayList<Entry<String, Double>>(map
					.entrySet());
		} catch (Exception e) 
		{
			JCartLogger.logError(this.getClass().getName(),
					"getCategorySalesGraph", e.getMessage());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
			ErrorRedirect.reDirect();
			}
			else{
				this.styleClass="errorMsg";
			this.message = JCartConfig.getMessageFromProperties(e.getMessage());
			}
			}
	}

	/****
	 * @description This method is used to generate sales graph for the selected sub category
	 * 
	 * @return String
	 * @exception Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getSubCategorySalesData() {
		String path="";
		try {
			message = null;
			Entry<String, Double> entry = (Entry<String, Double>) datatable
					.getRowData();
			Map<String, Double> map = Factory.createReportService()
					.getSubCategorySalesData(entry.getKey(), fromDate, toDate);
			subCategoryEntries = new ArrayList<Entry<String, Double>>(map
					.entrySet());
			
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getSubCategorySalesData", e.getMessage());
			this.styleClass="errorMsg";
			this.message = JCartConfig.getMessageFromProperties(e.getMessage());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
			path = "/pages/errorPage";}
		}
		return path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Entry<String, Double>> getCategoryEntries() {
		return categoryEntries;
	}

	public void setCategoryEntries(List<Entry<String, Double>> categoryEntries) {
		this.categoryEntries = categoryEntries;
	}

	public List<Entry<String, Double>> getSubCategoryEntries() {
		return subCategoryEntries;
	}

	public void setSubCategoryEntries(
			List<Entry<String, Double>> subCategoryEntries) {
		this.subCategoryEntries = subCategoryEntries;
	}

	public HtmlDataTable getDatatable() {
		return datatable;
	}

	public void setDatatable(HtmlDataTable datatable) {
		this.datatable = datatable;
	}

	public Entry<String, Double> getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Entry<String, Double> selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public void setCurrentFinancialYear(String currentFinancialYear) {
		this.currentFinancialYear = currentFinancialYear;
	}

	public String getCurrentFinancialYear() {
		return currentFinancialYear;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}

	public Integer getChoice() {
		return choice;
	}

	public String getPreviousFinancialYear() {
		return previousFinancialYear;
	}

	public void setPreviousFinancialYear(String previousFinancialYear) {
		this.previousFinancialYear = previousFinancialYear;
	}

	public Calendar getFromDate() {
		return fromDate;
	}

	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}

	public Calendar getToDate() {
		return toDate;
	}

	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}

}
