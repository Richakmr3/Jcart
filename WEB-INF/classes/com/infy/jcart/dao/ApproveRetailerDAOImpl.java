package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.List;








import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.entites.LoginEntity;
import com.infy.jcart.entites.RegistrationEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;


public class ApproveRetailerDAOImpl implements ApproveRetailerDAO{
	
	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	 /**
     * @description this method finds the registered user with the status passed
     * populates a list of Registration and returns it

     * @param Character
     * @return List<Registration>
     * @throws DAO.TECHNICAL_ERROR
     * @catch Exception
     */
	@SuppressWarnings("unchecked")
	public List<Registration> getRetailersByStatus(Character status)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Registration> list = new ArrayList<Registration>();

		try {
		
			Query q = session
					.createQuery("select r from RegistrationEntity r "
							+ "where r.userId in (select l.userId from LoginEntity l "
							+ "where l.status=?)");
			q.setParameter(0, status);

			List<RegistrationEntity> l = q.list();
			for (int i = 0; i < l.size(); i++) {
				RegistrationEntity entity = l.get(i);
				Registration to = new Registration();
				to.setDob(entity.getDob());
				to.setFirstName(entity.getFirstName());
				to.setLastName(entity.getLastName());
				to.setMessageService(entity.getMessageService());
				to.setPermanentAddress(entity.getPermanentAddress());
				to.setPhoneNumber(entity.getPhoneNumber());
				to.setRegistrationId(entity.getRegistrationId());
				to.setSecurityAnswer(entity.getSecurityAnswer());
				to.setSecurityQuestion(entity.getSecurityQuestion());
				to.setShipmentAddress(entity.getShipmentAddress());
				to.setUserId(entity.getUserId());

				list.add(to);
			}
			session.getTransaction().commit();
			return list;
		}

		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getRetailersByStatus", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();
			 
		}
	}

	  /**
     * @description this method finds the selected retailer and
     * set its status as required
     * @param  List<Registration>, Character
     * @return void
     * @throws DAO.TECHNICAL_ERROR
     * @catch Exception
     */
	public void updateRetailerStatus(List<Registration> selectedRetailers,
			Character status) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
		
		
			for (int i = 0; i < selectedRetailers.size(); i++) 
			{
				Registration to = selectedRetailers.get(i);
				LoginEntity entity = (LoginEntity)session.get(LoginEntity.class, to
						.getUserId());
				entity.setStatus(status);
			}
			session.getTransaction().commit();
		}

		catch (Exception exception) 
		{
			
			JCartLogger.logError(this.getClass().getName(),
					"updateRetailerStatus", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally {
			session.close();
			 
		}

	}
}
