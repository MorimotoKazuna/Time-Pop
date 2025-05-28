package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	private int id;			// 従業員番号等、任意の数列
	private String name;	// 利用者・管理者氏名
	private String email;	// メールアドレス
	private String password;	// パスワード
	private String role;
	private String nameFurigana;
	private String state;
	
	public User() {}
	
	public User(int id, String name, String email, String password, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	// user登録用
	public User(int id, String name, String nameFurigana) {
		this.id = id;
		this.name = name;
		this.nameFurigana = nameFurigana;
	}
	
	// userメイン画面出力用
	public User(int id, String nameFurigana) {
		this.id = id;
		this.nameFurigana = nameFurigana;
	}
	
	// ログイン用
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	// getter
	public int getId() {return id;}
	public String getName() {return name;}
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getRole() {return role;}
	public String getNameFurigana() {return nameFurigana;}
	

	// setter
	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
	public void setRole(String role) { this.role = role; }
	public void setNameFurigana(String nameFurigana) { this.nameFurigana = nameFurigana; }
	public void setState(String state) { this.state = state ; }
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return email.equals(other.email) && password.equals(other.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}