package com.example.demo.chainRespon;

public class RubbishEmailHandler implements Handler {

	private Handler nextHandler;


	@Override
	public void nextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	@Override
	public void handleEmail(Email email) {
		if (email != null) {
			if (email.getType() == EmailType.RUBBISH_EMAIL) {
				ReStation.INSTANCE.recycleEmail(email);
			}
			else this.nextHandler.handleEmail(email);
		}
	}
}
