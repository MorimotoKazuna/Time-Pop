package model;

import java.io.Serializable;

public class User implements Serializable {
	private String email;	// メールアドレス
	private String pass;	// パスワード
	
	public User() {}
	
	public User(String name, String pass) {
		this.email = email;
		this.pass = pass;
	}
	
	public String getName() { return email; }
	public String getPass() { return pass; }
	
}
