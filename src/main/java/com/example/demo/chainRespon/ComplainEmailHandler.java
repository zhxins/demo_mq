package com.example.demo.chainRespon;

public class ComplainEmailHandler implements Handler {

	private Handler nextHandler;
	private LawDepartment lawDepartment;

	public ComplainEmailHandler() {
		lawDepartment = new LawDepartment();
	}

	@Override
	public void nextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}


	@Override
	public void handleEmail(Email email) {
		if (email != null) {
			if (email.getType() == EmailType.COMPLAIN_EMAIL) {
				lawDepartment.readEmail(email);
			}
			else this.nextHandler.handleEmail(email);
		}
	}
}


