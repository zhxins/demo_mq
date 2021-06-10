package com.example.demo.chainRespon;

import java.util.ArrayList;
import java.util.List;

public enum ReStation {
	INSTANCE;  // INSTANCE is same as
//	  public static final MySingleton INSTANCE = new MySingleton();
	// 但更加简洁且无偿提供了序列化机制，即使在面对复杂的序列化或者反射攻击的时候
	// 都能防止多次实例化。

	private List<Email> emailList ;

	private ReStation() {
		emailList = new ArrayList<>();
	}

	public synchronized void recycleEmail(Email email) {
		System.out.println("Recycle station recycleing the email: "
				+ email.getContent());
		emailList.add(email);
	}

	public synchronized void deleteEmail(Email email) {
		emailList.remove(email);
	}

	public synchronized void clear() {
		emailList.clear();
	}
}
