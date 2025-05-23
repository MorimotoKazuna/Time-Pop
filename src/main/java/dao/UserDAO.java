package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import model.User;




	public class UserDAO {
		// DB保存先・ユーザー名・パスワードを定数に格納
		private static final String JDBC_URL = "jdbc:h2:~/timepop";
		private static final String DB_USER = "sa";
		private static final String DB_PASS = "1234";
		
	    // コンストラクタで1回だけドライバを読み込む（推奨）
	    static {
	        try {
	            Class.forName("org.h2.Driver");
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException("H2ドライバのロードに失敗しました", e);
	        }
	    }
	    
	    //【全てのユーザーの取得】
	    public void findAllUser() {
			   // H2データベースへ繋ぐ
		        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
		        	// SQLのSELECT文の設定
		            String sql = "SELECT * FROM USERS";
//		        	String sql = "SHOW TABLES";
		            
		            try (Statement stmt = conn.createStatement();
		            		ResultSet rs = stmt.executeQuery(sql)){
		            	
			            // next()は結果を一行ずつ取り出すためのメソッド
			            while (rs.next()) {
//			            	String tableName = rs.getString("TABLE_NAME");
			            	int dbid = rs.getInt("ID");
			            	String dbname = rs.getString("NAME");
			            	String dbemail = rs.getString("EMAIL");
			                String dbpassword = rs.getString("PASSWORD");
			                // Dbuserとしてインスタンス化
//			                return new User(dbid, dbname, dbemail, dbpassword);
			                System.out.println("ID：" + dbid + "EMAIL：" + dbemail + "PASSWORD：" + dbpassword);
//			                System.out.println("テーブル：" + tableName);            
			            }
			            
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	    }
	    
		    
	   //【管理者ユーザーログイン用】
	   public User findUserByEmailAndPass(String email, String password) {
		   // H2データベースへ繋ぐ
	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        	// SQLのSELECT文の設定
	            String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            // 1つ目の？
	            pStmt.setString(1, email);
	            // 2つ目の？
	            pStmt.setString(2, password);
	            ResultSet rs = pStmt.executeQuery();
	            
	            // next()は結果を一行ずつ取り出すためのメソッド
	            if (rs.next()) {
	            	String dbemail = rs.getString("EMAIL");
		                String dbpassword = rs.getString("PASSWORD");
	                // Dbuserとしてインスタンス化
	                return new User(dbemail, dbpassword);
	            } else {
	            	return null;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
		        return null;
	        }
	    }
	   
	   //【Usersテーブルへrole = userの登録 part1】
	   public boolean registerUser(int id, String name, Timestamp time, String nameFurigana) {
		   // H2データベースへ繋ぐ
	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        	// SQLのINSERT文の設定
	            String sql = "INSERT INTO USERS (ID, NAME, ROLE, CREATED_AT, STATE, NAME_FURIGANA) VALUES (?, ?, 'user', ?, 'active', ?)";
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            
	            // 1～4つ目の？
	            pStmt.setInt(1, id);
	            pStmt.setString(2, name);
	            pStmt.setTimestamp(3, time); // サーブレットのTimestampから取ってくる createdAt
	            pStmt.setString(4, nameFurigana);
	            
	            pStmt.executeUpdate(); // INSERTなので executeUpdate() を使う
	            
                System.out.println("利用者登録完了");
                return true;
	                
            } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("利用者登録失敗");
	            return false;
	            
	        }

	   	}
	   
	   
	   //【Usersテーブルへrole = userの登録 part2 idの重複時】
	   
		   // メイン画面に利用者(role = user)の情報を取得するためのSQL文
	   // Arraylistに格納必須
		   public User findUserByRoleAndState() {
			   // H2データベースへ繋ぐ
		        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
		        	// SQLのSELECT文の設定
		            String sql = "SELECT ID, NAME FROM USERS WHERE ROLE = 'user' AND STATE = 'active'";
		            try ( Statement stmt = conn.createStatement();
		            		ResultSet rs = stmt.executeQuery(sql)){
		            
		            // next()は結果を一行ずつ取り出すためのメソッド
		            while (rs.next()) {
		                int id = rs.getInt("ID");
		                String name = rs.getString("NAME");
		                System.out.println("アクティブ利用者取得成功");
		                System.out.println("ID："+ id + "NAME：" + name);
		                return new User(id, name);
		            }
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		            System.out.println("アクティブ利用者取得失敗");
		        } return null;
		    
		   }
		   }
		        