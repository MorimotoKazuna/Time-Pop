package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
<<<<<<< HEAD
import java.sql.Timestamp;
import java.time.LocalDateTime;

import model.User;




	public class UserDAO {
		// DB保存先・ユーザー名・パスワードを定数に格納
		private static final String JDBC_URL = "jdbc:h2:~/timepop";
=======



	public class UserDAO {
		// DB保存先・ユーザー名・パスワードを定数に格納
		private static final String JDBC_URL = "jdbc:h2:./time-pop";
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
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
	    
<<<<<<< HEAD
	    //【全てのユーザーの取得】
	    public void findAllUser() {
=======
	    
	    public static void findAllUser() {
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
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
<<<<<<< HEAD
			            	int dbid = rs.getInt("ID");
			            	String dbname = rs.getString("NAME");
			            	String dbemail = rs.getString("EMAIL");
			                String dbpassword = rs.getString("PASSWORD");
=======
			            	int dbid = rs.getInt("id");
			            	String dbname = rs.getString("name");
			            	String dbemail = rs.getString("email");
			                String dbpassword = rs.getString("password");
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
			                // Dbuserとしてインスタンス化
//			                return new User(dbid, dbname, dbemail, dbpassword);
			                System.out.println("ID：" + dbid + "EMAIL：" + dbemail + "PASSWORD：" + dbpassword);
//			                System.out.println("テーブル：" + tableName);            
			            }
			            
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
<<<<<<< HEAD
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
	   
	   // Usersテーブルへrole = userの登録
	   public boolean registerUser(int id, String name, Timestamp time, String nameFurigana) {
		   // H2データベースへ繋ぐ
	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        	// SQLのSELECT文の設定
	            String sql = "INSERT INTO USERS (ID, NAME, ROLE, CREATED_AT, STATE, NAME_FURIGANA) VALUES (?, ?, 'user', ?, 'active', ?)";
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            Timestamp time = Timestamp.valueOf(LocalDateTime.now());
	            // 1つ目の？
	            pStmt.setInt(1, id);
	            // 2つ目の？
	            pStmt.setString(2, name);
	            // 3つ目の？
	            pStmt.setTimestamp(3, time);
	            // 4つ目の？
	            pStmt.setString(4, nameFurigana);
	            
	            pStmt.executeUpdate(); // INSERTなので executeUpdate() を使う
	            
                System.out.println("利用者登録完了");
                return true;
	                
            } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("利用者登録失敗");
	            return false;
	            
	        }
	  
	        return null;
	}
//		   // メイン画面に利用者(role = user)の情報を取得するためのSQL文
=======
		            System.out.println("内側失敗");
//			        return null;
		        }
		    
	    
//		   public User findUserByEmailAndPass(String email) {
//			   // H2データベースへ繋ぐ
//		        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
//		        	// SQLのSELECT文の設定
//		            String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
//		            PreparedStatement pStmt = conn.prepareStatement(sql);
//		            // 1つ目の？
//		            pStmt.setString(1, email);
//		            // 2つ目の？
////		            pStmt.setString(2, password);
//		            ResultSet rs = pStmt.executeQuery();
//		            
//		            // next()は結果を一行ずつ取り出すためのメソッド
//		            if (rs.next()) {
//		            	String dbemail = rs.getString("email");
////		                String dbpassword = rs.getString("password");
//		                // Dbuserとしてインスタンス化
//		                return new User(dbemail);
//		            } else {
//		            	return null;
//		            }
//
//		        } catch (SQLException e) {
//		            e.printStackTrace();
//			        return null;
//		        }
//		    }

		   // メイン画面に利用者(role = user)の情報を取得するためのSQL文
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
//		   public User findUserByRole() {
//			   // H2データベースへ繋ぐ
//		        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
//		        	// SQLのSELECT文の設定
//		            String sql = "SELECT ID, NAME FROM USERS WHERE ROLE = ?";
//		            PreparedStatement pStmt = conn.prepareStatement(sql);
//		            // 1つ目の？
//		            pStmt.setString(1, "user");
//		            ResultSet rs = pStmt.executeQuery();
//		            
//		            // next()は結果を一行ずつ取り出すためのメソッド
//		            while (rs.next()) {
//		                int id = rs.getInt("id");
//		                String name = rs.getString("name");
//		                System.out.println("取得成功");
<<<<<<< HEAD
//		                return new User(id, name);
=======
////		                return new Users(id, name);
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
//		            }
//
//		        } catch (SQLException e) {
//		            e.printStackTrace();
//		            System.out.println("失敗");
//		        }
<<<<<<< HEAD
//		  
//		        return null;
//		    }
=======
		  
//		        return null;
        		System.out.println("外側失敗");
		    }
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
	}
