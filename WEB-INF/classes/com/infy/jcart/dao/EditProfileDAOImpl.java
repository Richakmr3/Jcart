package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.entites.LoginEntity;
import com.infy.jcart.entites.ProductBidEntity;
import com.infy.jcart.entites.ProductEntity;
import com.infy.jcart.entites.RegistrationEntity;
import com.infy.jcart.resources.CalendarUtility;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class EditProfileDAOImpl implements EditProfileDAO {
	private SessionFactory sessionFactory = HibernateUtility
			.getSessionFactory();

	/**
	 * @description this method finds the registered user, populates a
	 *              RegistrationTO for it and returns it
	 * @param String
	 * @return RegistrationTO
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Registration getProfileDetails(String userId) throws Exception {
		Session session = sessionFactory.openSession();

		try 
		{
			Query query = session
					.createQuery("Select e from RegistrationEntity e where e.userId =?");
			query.setParameter(0, userId);
			List<RegistrationEntity> enlist = new ArrayList<RegistrationEntity>();
			RegistrationEntity en = new RegistrationEntity();
			enlist = query.list();
			Registration to = null;
			if (enlist.size() != 0) 
			{
				en = enlist.get(0);
				to = new Registration();
				to.setDob(en.getDob());
				to.setFirstName(en.getFirstName());
				to.setLastName(en.getLastName());
				to.setMessageService(en.getMessageService());
				to.setPermanentAddress(en.getPermanentAddress());
				to.setPhoneNumber(en.getPhoneNumber());
				to.setRegistrationId(en.getRegistrationId());
				to.setSecurityAnswer(en.getSecurityAnswer());
				to.setSecurityQuestion(en.getSecurityQuestion());
				to.setShipmentAddress(en.getShipmentAddress());
				to.setUserId(en.getUserId());
			}
			return to;
		} 
		catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getProfileDetails", e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {

			session.close();
			 
		}

	}

	/**
	 * @description this method finds the password for the user and returns it
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getPasswordDetails(String userId) throws Exception 
	{
		Session session = sessionFactory.openSession();
		try {

			Query query = session
					.createQuery("Select e.password from LoginEntity e where e.userId =?");
			query.setParameter(0, userId);
			List<String> list = new ArrayList<String>();

			list = query.list();
			String password = new String();
			if (list.size() != 0) 
			{
				password = list.get(0);
			}
			return password;
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getPasswordDetails", e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			session.close();
			 
		}
	}

	/**
	 * @description this method finds the particular user and updates it profile
	 *              details and returns 1 on successful updation
	 * @param RegistrationTO
	 * @return Integer
	 * @throws Exception
	 */
	public Integer updateProfile(Registration to) throws Exception {

		Session session = sessionFactory.openSession();
		try {

			RegistrationEntity en = new RegistrationEntity();
			session.beginTransaction();

			en = (RegistrationEntity) session.get(RegistrationEntity.class,
					to.getRegistrationId());

			/*
			 * Calendar c=Calendar.getInstance(); c.setTime(to.getDob());
			 */
			en.setDob(to.getDob());
			en.setFirstName(to.getFirstName());
			en.setLastName(to.getLastName());

			en.setPermanentAddress(to.getPermanentAddress());
			en.setPhoneNumber(to.getPhoneNumber());
			en.setRegistrationId(to.getRegistrationId());
			en.setShipmentAddress(to.getShipmentAddress());
			

			session.merge(en);
			session.getTransaction().commit();
			return 1;

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "updateProfile",
					e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}

	}

	/**
	 * @description this method finds the particular user and changes it
	 *              password
	 * 
	 * @param String
	 *            , String
	 * @return Integer
	 * @throws Exception
	 */
	public Integer changePassword(String userId, String newPassword)
			throws Exception {

		Session session = sessionFactory.openSession();
		try {

			LoginEntity en = new LoginEntity();
			session.beginTransaction();
			en = (LoginEntity) session.get(LoginEntity.class, userId);
			en.setPassword(newPassword);
			session.getTransaction().commit();
			return 1;

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "changePassword",
					e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}

	}

	/**
	 * @description this method toggles the message settings for the user and
	 *              accordingly returns the boolean value
	 * @param String
	 * @return boolean
	 * @throws Exception
	 */
	// returns true if the user earlier had a message service as Y and the
	// method changed it to N
	// returns false if the user had message service as N and the method changed
	// it to Y
	public Boolean editMessageSetting(String userId) throws Exception {

		Session session = sessionFactory.openSession();
		try {

			Registration regTO = new EditProfileDAOImpl()
					.getProfileDetails(userId);

			RegistrationEntity regEntity = (RegistrationEntity) session.get(
					RegistrationEntity.class, regTO.getRegistrationId());
			if (regEntity.getMessageService() == 'N') {
				regEntity.setMessageService('Y');
				session.beginTransaction();
				session.merge(regEntity);
				session.getTransaction().commit();
				return false;
			}
			regEntity.setMessageService('N');
			session.beginTransaction();
			session.merge(regEntity);
			session.getTransaction().commit();

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"editMessageSetting", e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();
			 
		}
		return true;
	}

	/**
	 * @description this method checks whether a particular user has open bids
	 *              or not if not then returns false otherwise true
	 * @param String
	 * @return boolean
	 * @throws Exception
	 */
	// returns true if any bids are open for the user id wrt the current
	// date.returns false otherwise
	@SuppressWarnings("unchecked")
	public Boolean checkOpenBids(String userId) throws Exception {

		List<ProductEntity> prodList = new ArrayList<ProductEntity>();
		Session session = sessionFactory.openSession();
		try {

			Calendar today = Calendar.getInstance();
			Query query = session
					.createQuery("Select p from ProductEntity p where p.sellerId =? and p.productType=?");
			query.setParameter(0, userId);
			query.setParameter(1, 'U');
			prodList = query.list();
			if (prodList.size() == 0) {
				return false;
			}
			for (ProductEntity prod : prodList) {
				List<ProductBidEntity> prodBidList = new ArrayList<ProductBidEntity>();
				int prodId = prod.getProductId();
				Query query2 = session
						.createQuery("Select pd from ProductBidEntity pd where pd.productId =?0");
				query2.setParameter(0, prodId);
				prodBidList = query2.list();
				for (ProductBidEntity bid : prodBidList) {
					Calendar bidStartDate = bid.getBidStartDate();

					Calendar bidEndDate = CalendarUtility.addDaystoDate(
							bidStartDate, bid.getDuration());
					if (today.after(bidStartDate) && today.before(bidEndDate)) {
						return true;
					}
					return false;
				}
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "checkOpenBids",
					e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally {
			session.close();
			 
		}
		return false;
	}

	/**
	 * @description this method finds the user and returns its role
	 * @param String
	 * @return Character
	 * @throws Exception
	 */
	public Character getRoleFromUserId(String userId) throws Exception {

		LoginEntity login;
		Session session = sessionFactory.openSession();
		try {

			session.beginTransaction();
			login = (LoginEntity) session.get(LoginEntity.class, userId);
			if (login == null) {
				return null;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getRoleFromUserId", e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}
		return login.getRole();
	}

	/**
	 * @description this method finds the role of the user by invoking
	 *              getRoleFromUserId for the user there should be no open bid,
	 *              otherwise find the user and set its status as 'D'
	 * @param String
	 * @return boolean
	 * @throws Exception
	 */
	public Boolean deactivateUser(String userId) throws Exception {

		Session session = sessionFactory.openSession();
		try {

			session.beginTransaction();
			if (this.getRoleFromUserId(userId) == 'U') {
				if (new EditProfileDAOImpl().checkOpenBids(userId)) {
					return false;
				}
			}
			LoginEntity logentity = (LoginEntity) session.get(
					LoginEntity.class, userId);
			logentity.setStatus('D');
			session.persist(logentity);
			session.getTransaction().commit();
			return true;

		} catch (Exception e) 
		{
			JCartLogger.logError(this.getClass().getName(), "deactivateUser",
					e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}
	}

	/**
	 * @description this method updates the messageService settings for a user
	 *              returns true if update is done otherwise false and set its
	 *              status as 'D'
	 * @param String
	 *            , Character
	 * @return boolean
	 * @throws Exception
	 */
	public Boolean toggleMessageSettings(String userId, Character status)
			throws Exception {

		Session session = sessionFactory.openSession();
		try {

			session.beginTransaction();
			Query query = session
					.createQuery("update RegistrationEntity r SET r.messageService=?0 WHERE r.userId =?1");
			query.setParameter(0, status);
			query.setParameter(1, userId);
			int rowCount = query.executeUpdate();
			session.getTransaction().commit();
			if (rowCount == 1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"toggleMessageSettings", e.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}
	}
}
