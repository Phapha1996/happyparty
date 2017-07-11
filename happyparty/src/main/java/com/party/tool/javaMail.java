package com.party.tool;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class javaMail {

	//发送邮件函数
	public static void sendMessage(String smtpHost, String from, String to,
			String subject, String messageText) throws MessagingException,
			java.io.UnsupportedEncodingException {

		// Step 1: Configure the mail session
		//System.out.println("Configuring mail session for: " + smtpHost);
		java.util.Properties props = new java.util.Properties();
		props.setProperty("mail.smtp.auth", "true");// 指定是否需要SMTP验证
		props.setProperty("mail.smtp.host", smtpHost);// 指定SMTP服务器
		props.put("mail.transport.protocol", "smtp");
		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);// 是否在控制台显示debug信息

		// Step 2: Construct the message
		// System.out.println("Constructing message -  from=" + from + "  to=" +
		// to);
		InternetAddress fromAddress = new InternetAddress(from);
		InternetAddress toAddress = new InternetAddress(to);

		MimeMessage testMessage = new MimeMessage(mailSession);
		testMessage.setFrom(fromAddress);
		testMessage
				.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
		testMessage.setSentDate(new java.util.Date());
		testMessage.setSubject(MimeUtility.encodeText(subject, "gb2312", "B"));

		testMessage.setContent(messageText, "text/html;charset=gb2312");
		System.out.println("Message constructed");

		// Step 3: Now send the message
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(smtpHost, "wuxinmain@163.com",
				"main4366265");
		transport.sendMessage(testMessage, testMessage.getAllRecipients());
		transport.close();

		System.out.println("Message sent!");
	}
	
	//注册激活
	public static String  activation(String userName) {
		String smtpHost = "smtp.163.com";  
        String from = "wuxinmain@163.com";
        String em="http://127.0.0.1:8080/user/activate?userName="+userName;
        String subject="聚会时代注册激活";
        
        StringBuffer theMessage = new StringBuffer();
        theMessage.append("&nbsp &nbsp &nbsp 亲爱的新用户，您好:");
        theMessage.append("<hr>");
        theMessage.append("&nbsp &nbsp &nbsp 欢迎您使用聚会时代");
        theMessage.append("<hr>");
        theMessage.append("<center>");
        theMessage.append("<center><h4><font color=black>点击下面链接激活聚会时代账户</font></h4></center>");  
        theMessage.append("<hr>");         
        theMessage.append("<center><a href="+em+">点击此处激活</a></center>");  
        theMessage.append("<hr>");
        theMessage.append("***************************************************");
        theMessage.append("<hr>");
        theMessage.append("</center>");
        theMessage.append("&nbsp &nbsp &nbsp 本邮件由聚会时代系统自动发出，请勿直接回复。");
        theMessage.append("&nbsp &nbsp &nbsp 聚会时代官方网站：720China.com 微信公众号：聚会时代 QQ1群：00000000 QQ2群：11111111");
        theMessage.append("<hr>");
        try {  
       	 javaMail.sendMessage(smtpHost, from, userName, subject, theMessage.toString()); 
        	return "success";
        }  
        catch (javax.mail.MessagingException exc) {  
            exc.printStackTrace();  
            return "error";
        }  
        catch (java.io.UnsupportedEncodingException exc) {  
            exc.printStackTrace(); 
            return "error";
        }  
        
	}
	
	//修改密码
		public static String changePWD(String userName,String Password) {
			String smtpHost = "smtp.163.com";  
	        String from = "wuxinmain@163.com";
	        String em="http://127.0.0.1:8080/user/changePassword?userName="+userName+"&Password="+Password;
	        String subject="聚会时代修改密码";
	        
	        StringBuffer theMessage = new StringBuffer();
	        theMessage.append("&nbsp &nbsp &nbsp 亲爱的新用户，您好:");
	        theMessage.append("<hr>");
	        theMessage.append("&nbsp &nbsp &nbsp 欢迎您使用聚会时代");
	        theMessage.append("<hr>");
	        theMessage.append("<center>");
	        theMessage.append("<center><h4><font color=black>点击下面链接修改聚会时代账户密码</font></h4></center>");  
	        theMessage.append("<hr>");         
	        theMessage.append("<center><a href="+em+">点击此处修改密码</a></center>");  
	        theMessage.append("<hr>");
	        theMessage.append("***************************************************");
	        theMessage.append("<hr>");
	        theMessage.append("</center>");
	        theMessage.append("&nbsp &nbsp &nbsp 本邮件由聚会时代系统自动发出，请勿直接回复。");
	        theMessage.append("&nbsp &nbsp &nbsp 聚会时代官方网站：720China.com 微信公众号：聚会时代 QQ1群：00000000 QQ2群：11111111");
	        theMessage.append("<hr>");
	        try {  
	       	 javaMail.sendMessage(smtpHost, from, userName, subject, theMessage.toString()); 
	        	return "success";
	        }  
	        catch (javax.mail.MessagingException exc) {  
	            exc.printStackTrace();  
	            return "error";
	        }  
	        catch (java.io.UnsupportedEncodingException exc) {  
	            exc.printStackTrace(); 
	            return "error";
	        }  
	        
		}
}