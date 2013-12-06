package ua.edu.lp.reliability.model.email;

import java.util.ArrayList;
import java.util.List;

public class Email {

	private String sender;

	private List<String> recipients;

	private List<String> ccRecipients;

	private List<String> bccRecipients;

	private String subject;

	private String content;

	public Email() {
		super();
		recipients = new ArrayList<>();
		ccRecipients = new ArrayList<>();
		bccRecipients = new ArrayList<>();
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public List<String> getCcRecipients() {
		return ccRecipients;
	}

	public void setCcRecipients(List<String> ccRecipients) {
		this.ccRecipients = ccRecipients;
	}

	public List<String> getBccRecipients() {
		return bccRecipients;
	}

	public void setBccRecipients(List<String> bccRecipients) {
		this.bccRecipients = bccRecipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
