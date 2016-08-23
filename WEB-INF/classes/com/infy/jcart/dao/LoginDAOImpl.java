package com.infy.jcart.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Login;
import com.infy.jcart.entites.LogDetailEntity;
import com.infy.jcart.entites.LoginEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class LoginDAOImpl implements LoginDAO {

	SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description This method finds and returns the LoginEntity object based
	 *              on the userId.
	 * @param String
	 * @return LoginEntity
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	private LoginEntity getLoginEntityDetailsByUserId(String userId)
			throws Exception {
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		LoginEntity le = null;
		try {
			le = (LoginEntity) session.get(LoginEntity.class, userId);
			
			session.getTransaction().commit();
			
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getLoginEntityDetailsByUserId", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getLoginEntityDetailsByUserId", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close();
			// 
		}

		return le;
	}

	/***
	 * @description This method finds the LoginEntity and checks whether it is
	 *              empty or not and accordingly returns a String.
	 * @param Login
	 * @return String
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	public String getLogin(String userId) throws Exception {
		LoginEntity le = null;
		String loginDetailsFound = null;
		try {
			le = getLoginEntityDetailsByUserId(userId);
			if (le == null) {
				loginDetailsFound = "Not Found";
			} else {
				loginDetailsFound = "Found";
			}
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "getLogin",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} catch (Exception exception) {
			/*
			 * if (session.getTransaction().isActive()) {
			 * session.getTransaction().rollback(); }
			 */

			/*
			 * if (exception.getMessage().equals("DAO.INVALID_USERID")) { throw
			 * exception; }
			 */
			JCartLogger.logError(this.getClass().getName(), "getLogin",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		return loginDetailsFound;
	}

	/***
	 * @description This method finds and returns the LoginEntity object based
	 *              on the userId.
	 * @param Login
	 * @return Login
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	public Login authorize(Login login) throws Exception {
		LoginEntity le = null;
		Login log = new Login();
		try {
			le = getLoginEntityDetailsByUserId(login.getUserId());
			
			if (le != null) {
				log.setUserId(le.getUserId());
				log.setPassword(le.getPassword());
				log.setRole(le.getRole());
				log.setStatus(le.getStatus());
			}
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "authorize",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "authorize",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		return log;
	}

	/***
	 * @description This method retrieves the lastLogoutTime for the given
	 *              userId.
	 * @param String
	 * @return List<Calendar>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Calendar> getLastLogoutTime(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query q = session.getNamedQuery("getLastLogoutTime");
			q.setParameter(0, userId);
			
			List<Calendar> calList = q.list();
			
			session.getTransaction().commit();
			
			return calList;
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getLastLogoutTime", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getLastLogoutTime", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
	
			session.close(); 
		}

	}

	/***
	 * @description this method finds the user and sets it status as 'B'
	 * @param String
	 * @return void
	 * @throws Exception
	 * @catch PersistenceException, Exception
	 */
	public void blockAccount(String userId) throws Exception {

		Session session = sessionFactory.openSession();

		try {
			LoginEntity le = getLoginEntityDetailsByUserId(userId);
			if (le != null) {
				session.beginTransaction();
				le.setStatus('B');
				session.getTransaction().commit();
			}
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "blockAccount",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "blockAccount",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}

	}

	/***
	 * @description this method finds the user and sets it status as 'L'
	 * @param String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	public void lockAccount(String userId) throws Exception {
		Session session = sessionFactory.openSession();

		try {
			LoginEntity le = getLoginEntityDetailsByUserId(userId);
			
			if (le != null) 
			{
				
				session.beginTransaction();
				le.setStatus('L');
				session.update(le);
				session.getTransaction().commit();
				
			}
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "lockAccount",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "lockAccount",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

	/***
	 * @description this method finds the user and sets it status as 'A'
	 * @param String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	public void unlockAccount(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			LoginEntity le = getLoginEntityDetailsByUserId(userId);
			if (le != null) {
				session.beginTransaction();
				le.setStatus('A');
				session.getTransaction().commit();
			}
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "unlockAccount",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "unlockAccount",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

	/***
	 * @description this method populates the LoginEntity and persists it
	 * @param Login
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	public void saveLoginDetails(Login login) throws Exception {

		Session session = sessionFactory.openSession();
		try {
			LoginEntity le = new LoginEntity();
			le.setUserId(login.getUserId());
			le.setPassword(login.getPassword());
			le.setStatus(login.getStatus());
			le.setRole(login.getRole());
			session.beginTransaction();
			session.save(le);
			session.getTransaction().commit();
		} catch (PersistenceException exception) {
			
			JCartLogger.logError(this.getClass().getName(), "saveLoginDetails",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			
			JCartLogger.logError(this.getClass().getName(), "saveLoginDetails",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

	/***
	 * @description this method finds the retailer and sets it status as 'A'
	 * @param String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	public void activateRetailer(String userId) throws Exception {

		Session session = sessionFactory.openSession();

		try {
			LoginEntity le = getLoginEntityDetailsByUserId(userId);
			if (le != null) {
				session.beginTransaction();
				le.setStatus('A');
				session.getTransaction().commit();
			}
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "activateRetailer",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "activateRetailer",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

	/***
	 * @description this method finds the retailer and sets it status as 'R'
	 * @param String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	public void rejectRetailer(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {

			LoginEntity le = getLoginEntityDetailsByUserId(userId);
			if (le != null) {
				session.beginTransaction();
				le.setStatus('R');
				session.getTransaction().commit();
			}
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "rejectRetailer",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "rejectRetailer",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

	/***
	 * @description this method finds the retailer and sets it status as 'D'
	 * @param String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */

	public void deactivateRetailer(String userId) throws Exception {
		Session session = sessionFactory.openSession();

		try {
			LoginEntity le = getLoginEntityDetailsByUserId(userId);
			if (le != null) {
				session.beginTransaction();
				le.setStatus('D');
				session.getTransaction().commit();
			}
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "deactivateRetailer",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"deactivateRetailer", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

	/**
	 * @description this method populates a LogDetailEntity and persists it
	 * @param String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch PersistenceException, Exception
	 */
	public void logout(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			LogDetailEntity entity = new LogDetailEntity();
			entity.setUserId(userId);
			entity.setLogoutTime(Calendar.getInstance());
			session.save(entity);
			session.getTransaction().commit();
		} catch (PersistenceException exception) {
			JCartLogger.logError(this.getClass().getName(), "logout",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "logout",
					exception.toString());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

}
