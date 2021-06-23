package com.bridgelabz.bookstore.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailService {

	public static void send(String toEmail, String subject, String body)
	{
		final String fromEmail = "prajakta14bramhe@gmail.com"; 
		// requires valid gmail id 
		final String password = "14praju1996@"
		; // correct password for gmail id
	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	// SMTP Host 
	props.put("mail.smtp.port", "587");
	// TLS Port
	props.put("mail.smtp.auth", "true");
	// enable authentication 
	props.put("mail.smtp.starttls.enable", "true"); 
	// enable STARTTLS
	// to check email sender credentials are valid or not 
	Authenticator auth = new Authenticator() 
	{ 
	protected PasswordAuthentication getPasswordAuthentication() 
	{ 
		return new PasswordAuthentication(fromEmail, password); 
		
	}
	};
	javax.mail.Session session = Session.getInstance(props, auth);
	try { 
	MimeMessage msg = new MimeMessage(session);
	msg.addHeader("Content-type", "text/HTML; charset=UTF-8"); 
	msg.addHeader("format", "flowed");
	msg.addHeader("Content-Transfer-Encoding", "8bit");
	msg.setFrom(new InternetAddress("no_reply@gmail.com", "NoReply"));
	msg.setReplyTo(InternetAddress.parse("prajakta14bramhe@gmail.com", false));
	msg.setSubject(subject, "UTF-8");
	msg.setText(body, "UTF-8");
	msg.setSentDate(new Date());
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false)); 
	Transport.send(msg);
	System.out.println("Email Sent Successfully........."); } 
	catch (Exception e)
	{ 
		e.printStackTrace(); 
		}
	}

}
