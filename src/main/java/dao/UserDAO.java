package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;


	public class UserDAO {
		// DB保存先・ユーザー名・パスワードを定数に格納
		private final String JDBC_URL = "jdbc:h2:./time-pop";
		private final String DB_USER = "sa";
		private final String DB_PASS = "1234";
		
	    // コンストラクタで1回だけドライバを読み込む（推奨）
	    static {
	        try {
	            Class.forName("org.h2.Driver");
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException("H2ドライバのロードに失敗しました", e);
	        }
	    }
	    
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
		                int id = rs.getInt("id");
		                String name = rs.getString("name");
		                String role = rs.getString("role");
		                return new User(id, name, email, password, role);
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return null;
		    }
		}