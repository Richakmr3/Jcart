package com.infy.jcart.dao;


import java.util.Calendar;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.infy.jcart.entites.OrderLogEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class ShipmentDAOImpl implements ShipmentDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

		/***
	 * @description this method gets the user id from session, populates the
	 *              OrderLogEntity object and returns the order id generated
	 * @param Integer
	 *            , Integer
	 * @return Integer
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public Integer addNewShipmentDetails(Integer paymentId,
			Integer numOfProducts) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession httpSession = (HttpSession) etx.getSession(true);
			String userId = (String) httpSession.getAttribute("userId");
			Calendar today = Calendar.getInstance();

			OrderLogEntity entity = new OrderLogEntity();
			entity.setPaymentId(paymentId);
			entity.setUserId(userId);
			entity.setOrderDate(today);
			entity.setDeliveryStatus('P');
			entity.setNumOfProducts(numOfProducts);
			session.save(entity);
			
			session.getTransaction().commit();
			return entity.getOrderId();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"addNewShipmentDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

	}

	
	
}
