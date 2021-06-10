package com.example.demo.chainRespon;

public class BusinessDepartment {
	public void readEmail(Email email) {
		System.out.println("Business department read the email : " +
				email.getContent());
	}
}
