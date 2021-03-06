package com.ggs.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * @author Not Today
 * @date 2019年8月11日-下午4:59:13
 */
public class MailUtils {

	public static void sendMail(String email, String emailMsg) throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.smtp.host", "smtp.126.com");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 阿里云禁用25端口,这里将邮件发送端口修改为ssl的465端口
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.port", "465");

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xxxxxxxxxx", "xxxxxxxxxxx");
			}
		};

		// 使用过环境属性和授权信息,创建邮件会话
		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress("xxxxxxxxxxx@126.com", "Starbug")); // 设置发送者

			message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

			message.setSubject("用户激活");
			// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

			message.setContent(emailMsg, "text/html;charset=utf-8");

			// 3.创建 Transport用于将邮件发送

			Transport.send(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			String err = e.getMessage();
			// 在这里处理message内容， 格式是固定的
			System.out.println(err);
		}
	}
}
