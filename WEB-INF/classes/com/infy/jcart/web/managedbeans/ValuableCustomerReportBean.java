package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;



@ManagedBean
@SessionScoped
public class ValuableCustomerReportBean {
	private Integer numberOfRecords;
	private List<Registration> paginationList;
	private Integer counter = 0;
	private Integer totalPages = 0;
	private List<Registration> customerList;
	private String message;
	private HtmlDataTable datatable;
	private Registration selectedTO;

	/***
	 * @description This method is used to populate the customer details based on the
	 * selected no of records
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 * 
	 */
	public void getReport(ValueChangeEvent event) {
		try {
			numberOfRecords = (Integer) event.getNewValue();
			
			customerList = Factory.createReportService().getValuableCustomers();
			
			
			counter = 0;
			
			if (!customerList.isEmpty()) {
				paginationList = new ArrayList<Registration>();
			}
			totalPages = customerList.size() / numberOfRecords;
			
			if (customerList.size() % numberOfRecords != 0) {
				totalPages = totalPages + 1;
			}
			for (int i = counter; i < counter + numberOfRecords
					&& i < customerList.size(); i++) {
				paginationList.add(customerList.get(i));
				
			}
			
			
		} catch (Exception e) {
			
			
			JCartLogger.logError(this.getClass().getName(), "getReport", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to display the next set of records available from the
	 * list of records
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String getNext() {
		String path="";
		try {
			counter = counter + numberOfRecords;
			paginationList = new ArrayList<Registration>();
			for (int i = counter; i < counter + numberOfRecords
					&& i < customerList.size(); i++) {
				paginationList.add(customerList.get(i));
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getNext", e
					.toString());
			paginationList = null;
			
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path="/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

	/***
	 * @description This method is used to display the previous set of records available from
	 * the list of records
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String getPrevious() {
		String path="";
		try {
			counter = counter - numberOfRecords;
			paginationList = new ArrayList<Registration>();
			for (int i = counter; i < counter + numberOfRecords
					&& i < customerList.size(); i++) {
				paginationList.add(customerList.get(i));
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getPrevious", e
					.toString());
			paginationList = null;
			this.message = JCartConfig.getMessageFromProperties(e.getMessage());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path="/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

	/***
	 * @description This method is used to retrieve the selected product
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String getProductsPurchased() {
		String path="";
		try {
			selectedTO = (Registration) datatable.getRowData();
			
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductsPurchased", e.getMessage());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path="/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
			
		}
		return path;
	}

	public List<Registration> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Registration> customerList) {
		this.customerList = customerList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HtmlDataTable getDatatable() {
		return datatable;
	}

	public void setDatatable(HtmlDataTable datatable) {
		this.datatable = datatable;
	}

	public Registration getSelectedTO() {
		return selectedTO;
	}

	public void setSelectedTO(Registration selectedTO) {
		this.selectedTO = selectedTO;
	}

	public Integer getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Integer numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public List<Registration> getPaginationList() {
		return paginationList;
	}

	public void setPaginationList(List<Registration> paginationList) {
		this.paginationList = paginationList;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
