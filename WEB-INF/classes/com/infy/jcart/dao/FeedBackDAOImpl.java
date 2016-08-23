package com.infy.jcart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.infy.jcart.beans.OverAllFeedBack;
import com.infy.jcart.resources.JCartLogger;


public class FeedBackDAOImpl implements FeedBackDAO{

	
	

	/***
	 * @description this method returns a Connection with oracle database
	 * @return Connection
	 * @throws Exception
	 */
	
	public  Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager
				.getConnection("CONNECTION URL","USERNAME", "PASSWORD");
		return connection;
	}

	/***
	 * @description this method inserts feedback to the database by checking the
	 * number of records
	 * @param OverAllFeedBackTO
	 * @return void
	 * @throws Exception
	 */
	public void submitFeedback(OverAllFeedBack overAllFeedBackTO)
			throws Exception {
		try {
/******************************************************************************/			
			FeedBackDAOImpl backDAOImpl=new FeedBackDAOImpl();
			Connection connection = backDAOImpl.getConnection();
/******************************************************************************/				
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select max(feedbackId) from jcart_OverallfeedBack ");
			int i = 0;
			while (resultSet.next()) {
				i = resultSet.getInt(1);
			}
			if (i == 0) {
				i = 1;
			} else {
				i++;
			}
			statement
					.executeUpdate("insert into jcart_OverallfeedBack values ("
							+ i + ",'" + overAllFeedBackTO.getFeedbackComment()
							+ "' ," + +overAllFeedBackTO.getRating() + ", '"
							+ overAllFeedBackTO.getUserId() + "'  )");
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "submitFeedback",
					e.toString());
			
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
	}
	/***
	 * @description this method finds the feedback records for a particular user and returns it
	 * @param String
	 * @return List<OverAllFeedBackTO> 
	 * @throws Exception
	 */
	public List<OverAllFeedBack> getFeedbackComment(String userId)
			throws Exception {
		try {
			FeedBackDAOImpl backDAOImpl=new FeedBackDAOImpl();
			Connection connection = backDAOImpl.getConnection();
		//	Connection connection = FeedBackDAOImpl.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from jcart_OverallfeedBack where USERID like "
							+ "'" + userId + "'");

			List<OverAllFeedBack> list = new ArrayList<OverAllFeedBack>();
			while (resultSet.next()) {
				OverAllFeedBack to = new OverAllFeedBack();
				to.setFeedbackId(resultSet.getInt(1));
				to.setFeedbackComment(resultSet.getString(2));
				to.setRating(resultSet.getInt(3));
				to.setUserId(resultSet.getString(4));
				list.add(to);
			}

			return list;

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getFeedbackComment",
					e.toString());
		
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
	}
}
