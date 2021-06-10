package com.example.demo.chainRespon;

public class AdviceEmailHandler implements Handler {

	private Handler nextHandler;
	private BusinessDepartment businessDepartment;

	public AdviceEmailHandler() {
		businessDepartment  = new BusinessDepartment();
	}

	@Override
	public void nextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	@Override
	public void handleEmail(Email email) {
		if (email != null) {
			if (email.getType() == EmailType.ADVICE_EMAIL) {
				businessDepartment.readEmail(email);
			}
			else this.nextHandler.handleEmail(email);
		}
	}
}
