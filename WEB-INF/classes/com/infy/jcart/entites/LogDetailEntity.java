package com.infy.jcart.entites;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQueries({
@NamedQuery(name="getLogForUser",query="select c from LogDetailEntity c where c.userId=?"),
@NamedQuery(name="getLastLogoutTime",query="select max(c.logoutTime) from LogDetailEntity c where c.userId=?")
})
@Table(name="JCART_LOGDETAIL")
public class LogDetailEntity 
{

	@Id
	@SequenceGenerator(name="logID",sequenceName="db_logID",allocationSize=1,initialValue=4007)
	@GeneratedValue(generator="logID",strategy=GenerationType.SEQUENCE)
	private Integer logId;
	private String userId;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar logoutTime;
	
	public void setLogoutTime(Calendar logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Calendar getLogoutTime() {
		return logoutTime;
	}
	
}
