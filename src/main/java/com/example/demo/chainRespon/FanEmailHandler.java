package com.example.demo.chainRespon;

public class FanEmailHandler implements Handler{

	private Handler nextHandler;
	private CEO ceo;

	public FanEmailHandler() {
		ceo = new CEO();
	}

	@Override
	public void nextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}

	@Override
	public void handleEmail(Email email) {
		if (email != null) {
			if (email.getType() == EmailType.FANS_EMAIL) {
				ceo.readEmail(email);
			}
			else this.nextHandler.handleEmail(email);
		}
	}
}
