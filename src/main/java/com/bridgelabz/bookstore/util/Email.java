package com.bridgelabz.bookstore.util;

import java.io.Serializable;

public class Email implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String to;
	String from;
	String subject;
	String body;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Email [to=" + to + ", from=" + from + ", subject=" + subject + ", body=" + body + "]";
	}
}
