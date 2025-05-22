package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
	private int id;			// 従業員番号等、任意の数列
	private String name;	// 利用者・管理者氏名
	private String email;	// メールアドレス
	private String password;	// パスワード
	private String role;
<<<<<<< HEAD
	private String nameFurigana;
=======
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
	
	public User() {}
	
	public User(int id, String name, String email, String password, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
<<<<<<< HEAD
	// user登録用 not admin
	public User(int id, String name, String nameFurigana) {
		this.id = id;
		this.name = name;
		this.nameFurigana = nameFurigana;
	}
	
	// ログイン用コンストラクタ A
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	// メイン画面 role = user 出力用コンストラクタ B
	public User(int id, String name) {
		this.email = email;
	}
	
=======
	// ログイン用コンストラクタ A
//	public User(String email, String password) {
//		this.email = email;
//		this.password = password;
//	}
	// ログイン用コンストラクタ B
	public User(String email) {
		this.email = email;
	}
	
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
	
	public int getId() {return id;}
	public String getName() {return name;}
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getRole() {return role;}
<<<<<<< HEAD
	public String getNameFurigana() {return nameFurigana;}
=======
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
	
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