package com.example.demo.chainRespon;

import java.time.LocalDate;

public class Email {
	private String author;
	private EmailType type;
	private String content;
	private LocalDate date;

	public Email(String author,
				 EmailType type,
				 String content,
				 LocalDate date ) {
		this.author = author;
		this.type = type;
		this.content = content;
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public EmailType getType() {
		return type;
	}
	public void setType(EmailType type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
