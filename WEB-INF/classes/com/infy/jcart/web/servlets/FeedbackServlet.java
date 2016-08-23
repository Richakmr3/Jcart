package com.infy.jcart.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infy.jcart.beans.OverAllFeedBack;
import com.infy.jcart.dao.FeedBackDAOImpl;
import com.infy.jcart.resources.JCartLogger;


public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FeedbackServlet() {

	}

	public void init(ServletConfig config) throws ServletException {

	}

	public void destroy() {

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String feedbackComment = request.getParameter("feedback");
		String rating = request.getParameter("rating");
		/* calling the service method */
		PrintWriter out = response.getWriter();

		OverAllFeedBack allFeedBack = new OverAllFeedBack();
		try {
			allFeedBack.setFeedbackComment(feedbackComment);
			allFeedBack.setRating(Integer.parseInt(rating));
			allFeedBack.setUserId(userId);
			out
					.print("Thanks for giving the feedback :--"
							+ userId
							+ "<a href='../help/overallfeedback.jsp'>Click To Go Back</a>");
			new FeedBackDAOImpl().submitFeedback(allFeedBack);
		} catch (Exception e) {
			if (null == e.getMessage()
					|| e.getMessage().equalsIgnoreCase("null")) {
			} else {
				out
						.write("<center> </h3>Please Enter a Proper Rating</h3> <a href='../help/overallfeedback.jsp'>Click To Go Back </a></center>");
				JCartLogger.logError(this.getClass().getName(), "submitFeedback",
						e.toString());
			
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
