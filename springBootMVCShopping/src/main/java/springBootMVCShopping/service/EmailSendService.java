package springBootMVCShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSendService {
	@Autowired
	JavaMailSender mailSender;
	public void mailsend(String html, String subject, String fromEmail, String toEmail) {
		/* 메일링하는 기본 문법 */
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			msg.setHeader("content-type", "text/html; charset=UTF-8");
			msg.setContent(html, "text/html; charset=UTF-8"); // 내용
			msg.setSubject(subject); // 제목
			msg.setFrom(new InternetAddress(fromEmail)); // 보내는 사람
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail)); // 받는 사람
			mailSender.send(msg); // 이메일 전송
		} catch (MessagingException e) {
			e.printStackTrace();
		} // 모듈화 끝...
	}
}
