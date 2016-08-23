package com.infy.jcart.business.service;

import java.util.List;

import java.util.Map;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;


public class IssueServiceImpl implements IssueService
{
	

	/***
	 * @description invokes getIssuesForRetailer() method of IssueDAOImpl class
	 * and returns the list obtained
	 * @param String
	 * @return List<IssueDetails>
	 * @throws IssueService.NO_ISSUES_FOUND
	 * @catch Exception
	 */
	public List<IssueDetails> getIssuesForRetailer(String retailerId) throws Exception {
		try {
			
			List<IssueDetails> list=Factory.createIssueDAO().getIssuesForRetailer(retailerId);
			if (list.isEmpty()) {
				throw new Exception("IssueService.NO_ISSUES_FOUND");
			}
			return list;
		}  catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getIssuesForRetailer", e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes getIssuesSummary() method of IssueDAOImpl class
	 * and returns the map obtained
	 * @return Map<String, Integer>
	 * @throws IssueService.NO_ISSUES_FOUND
	 * @catch Exception
	 */
	public Map<String, Integer> getIssuesSummary()throws Exception {
		try {
		
			Map<String, Integer> list=Factory.createIssueDAO().getIssuesSummary();
			if (list.isEmpty()) {
				throw new Exception("IssueService.NO_ISSUES_FOUND");
			}
			return list;
		}  catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getIssuesSummary",
					e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes deactivateRetailer() method of IssueDAOImpl class
	 * @param String
	 * @return void
	 * @throws Exception
	 * @catch Exception
	 */
	public void deactivateRetailer(String retailerId) throws Exception {
		try {
			
			Factory.createIssueDAO().deactivateRetailer(retailerId);

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"deactivateRetailer", e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes acknowledgeIssues() method of IssueDAOImpl class
	 * @param List<IssueDetailsTO> 
	 * @return void
	 * @throws IssueService.SELECT_ATLEAST_ONE_ISSUE
	 * @catch Exception
	 */
	public void acknowledgeIssues(List<IssueDetails> selectedIssues) throws Exception {
		try {
			if (selectedIssues.isEmpty()) {
				throw new Exception("IssueService.SELECT_ATLEAST_ONE_ISSUE");
			}
			Factory.createIssueDAO().acknowledgeIssues(selectedIssues);

		 
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"acknowledgeIssues", e.getMessage());
			throw e;
		}
	}

}
