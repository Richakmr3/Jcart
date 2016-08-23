package com.infy.jcart.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.infy.jcart.beans.LogDetail;
import com.infy.jcart.entites.LoginEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class AccountDAOImpl implements AccountDAO {

	private SessionFactory sessionFactory = HibernateUtility
			.getSessionFactory();

	/***
	 * @description this method finds the log details of user according to his
	 *              status
	 * @param Character
	 * @return List <LogDetail>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogDetail> getAccountsByStatus(Character status)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<LogDetail> detailList = new ArrayList<LogDetail>();
		try {
			Query q = session
					.createSQLQuery("select l.userid,l.role,k.logouttime from jcart_login l left join jcart_logdetail k on l.userId=k.userId where l.status= :stat");
			q.setParameter("stat", status);
			List<List<Object>> l = q.setResultTransformer(Transformers.TO_LIST).list();

			for (List<Object> objList : l) {
				String uid = (String) objList.get(0);
				Character role = (Character) objList.get(1);
				Timestamp t = (Timestamp) objList.get(2);
				LogDetail to = new LogDetail();
				to.setUserId(uid);

				if (t != null) {
					Calendar c = Calendar.getInstance();
					c.setTimeInMillis(t.getTime());
					to.setLogoutTime(c);
				}
				to.setRole(role);

				detailList.add(to);
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAccountsByStatus", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
		}
		return detailList;
	}

	/***
	 * @description this method activates a user's account by setting its status
	 *              as 'A'
	 * @param List
	 *            <LogDetail>
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void activateLockedOrBlockedAccounts(List<LogDetail> accountList)
			throws Exception {

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			for (int i = 0; i < accountList.size(); i++) {
				LoginEntity entity = (LoginEntity) session.get(
						LoginEntity.class, accountList.get(i).getUserId());
				entity.setStatus('A');
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"activateLockedOrBlockedAccounts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();
		}
	}

}
