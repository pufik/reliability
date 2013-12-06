package ua.edu.lp.reliability.mail.service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import ua.edu.lp.reliability.model.email.Email;

@Service("emailService")
public class DefaultEmailService implements EmailService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultEmailService.class);

	@Resource(name = "mailSender")
	private JavaMailSender mailSender;

	@Async
	@Override
	public void send(Email email) {
		Assert.notNull(email, "Email can't be null");
		LOG.info("Send email: " + email);

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);
		try {
			messageHelper.setFrom(email.getSender());
			messageHelper.setTo(email.getRecipients().toArray(new String[]{}));
			messageHelper.setCc(email.getCcRecipients().toArray(new String[]{}));
			
			mailSender.send(message);
		} catch (MessagingException e) {
			LOG.error("Send email message exception", e);
		}

	}
}
