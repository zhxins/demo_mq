package com.example.demo.chainRespon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmailListTestDrive {

	private Handler handler1;

	private List<Email> emailList;

	public EmailListTestDrive(List<Email> emailList) {
		this.emailList = emailList;
		handler1 = new FanEmailHandler();
		Handler handler2 = new ComplainEmailHandler();
		Handler handler3 = new AdviceEmailHandler();
		Handler handler4 = new RubbishEmailHandler();
		Handler nonHandler = new NonHandler();
		handler1.nextHandler(handler2);
		handler2.nextHandler(handler3);
		handler3.nextHandler(handler4);
		handler4.nextHandler(nonHandler);
	}

	public void handleEmailList() {
		for (Email email : emailList) {
			handler1.handleEmail(email);
		}
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Email> emailList = new ArrayList<>();
		Email email1 = new Email("yanxun", EmailType.FANS_EMAIL,
				"哇，这是我第一次使用自己的责任链模式",  LocalDate.now());
		Email email2 = new Email("wanzilin", EmailType.COMPLAIN_EMAIL,
				"哇，我王者荣耀10颗星", LocalDate.of(2019, 3, 8));
		Email email3 = new Email("kobe", EmailType.ADVICE_EMAIL,
				"Ok,your gumball is too sweet", LocalDate.of(2018, 8, 24));
		Email email4 = new Email("ルフィ", EmailType.RUBBISH_EMAIL,
				"私はワンピースになりたい人です。", LocalDate.of(2017, 5, 1));

		emailList.add(email4);
		emailList.add(email3);
		emailList.add(email2);
		emailList.add(email1);
		EmailListTestDrive testDrive = new EmailListTestDrive(emailList);
		testDrive.handleEmailList();
	}

}
