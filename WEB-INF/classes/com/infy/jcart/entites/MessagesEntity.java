package com.infy.jcart.entites;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="JCART_MESSAGES")
public class MessagesEntity {
	
	@Id
	@SequenceGenerator(name="messageID",sequenceName="db_messageID",allocationSize=1,initialValue=50000)
	@GeneratedValue(generator="messageID",strategy=GenerationType.SEQUENCE)
	private Integer messageId;
	private String senderId;
	private String recieverId;
	private String message;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar messageDate;
	
	public void setMessageDate(Calendar messageDate) {
		this.messageDate = messageDate;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Calendar getMessageDate() {
		return messageDate;
	}
	
}