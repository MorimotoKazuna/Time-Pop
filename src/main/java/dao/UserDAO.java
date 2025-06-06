package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	      
	    

		    
	   //【index.jsp→main.jsp 管理者ユーザーログイン用】
	   public User findUserByEmailAndPass(String email, String password) {
		   User user = null;
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
	            
	            if (rs.next()) {
	            	user = new User();
	            	user.setEmail(rs.getString("EMAIL"));
	            	user.setPassword(rs.getString("PASSWORD"));
	            	user.setName(rs.getString("NAME"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
		        return null;
	        }
	        return user;
	    }
	   
//	   //【main.jsp→admin.jsp 管理者ユーザーログイン用】
//	   public User findUserByEmailAndPassAndName(String email, String password, String name) {
//		   User user = null;
//		   
//		   // H2データベースへ繋ぐ
//	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
//	        	// SQLのSELECT文の設定
//	            String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ? AND NAME = ?";
//	            PreparedStatement pStmt = conn.prepareStatement(sql);
//	            // 1つ目の？
//	            pStmt.setString(1, email);
//	            // 2つ目の？
//	            pStmt.setString(2, password);
//	            // 3つ目の？	
//	            pStmt.setString(3, name);
//	            ResultSet rs = pStmt.executeQuery();
//	            
//	            // next()は結果を一行ずつ取り出すためのメソッド
//	            if (rs.next()) {
//	            	user = new User();
//	            	user.setEmail(rs.getString("EMAIL"));
//	            	user.setPassword(rs.getString("PASSWORD"));
//	            	user.setName(rs.getString("NAME"));
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//		        return null;
//	        }
//	        return user;
//	    }
	   
	   //【Usersテーブルへrole = adminの登録】
	   public boolean registerAdmin(int id, String name, Timestamp time, String nameFurigana, String email, String password) {
		   // H2データベースへ繋ぐ
	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        	// SQLのINSERT文の設定
	            String sql = "INSERT INTO USERS (ID, NAME, ROLE, CREATED_AT, STATE, NAME_FURIGANA, EMAIL, PASSWORD) VALUES (?, ?, 'admin', ?, 'active', ?, ?, ?)";
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            
	            // 1～4つ目の？
	            pStmt.setInt(1, id);
	            pStmt.setString(2, name);
	            pStmt.setTimestamp(3, time); // サーブレットのTimestampから取ってくる createdAt
	            pStmt.setString(4, nameFurigana);
	            pStmt.setString(5, email);
	            pStmt.setString(6, password);
	            
	            pStmt.executeUpdate(); // INSERTなので executeUpdate() を使う
	            
                System.out.println("利用者登録完了");
                return true;
	                
            } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("利用者登録失敗");
	            return false;
	            
	        }

	   	}

	   
	   //【Usersテーブルへrole = userの登録】
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
	   
	   
	   //【Usersテーブルへrole = userの更新】
	   public static boolean updateUser(int id, String name, String nameFurigana, String state) {
		   // H2データベースへ繋ぐ
	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        	// SQLのINSERT文の設定
	        	 String sql = "UPDATE USERS SET NAME = ?, NAME_FURIGANA = ?, STATE = ? WHERE ID = ?";
    	         PreparedStatement stmt = conn.prepareStatement(sql);
    	         
	    		 stmt.setString(1, name);
    	         stmt.setString(2, nameFurigana);
    	         stmt.setString(3, state);
    	         stmt.setInt(4, id);
    	    		
    	         return stmt.executeUpdate() > 0;
    	    } catch (SQLException e) {
    	         e.printStackTrace();
    	         return false;
    	    }
    	}
	   
	 //【Usersテーブルへrole = adminの更新】
	   public static boolean updateAdmin(int id, String name, String nameFurigana, String email, String password, String state) {
		   // H2データベースへ繋ぐ
	        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
	        	// SQLのINSERT文の設定
	        	 String sql = "UPDATE USERS SET NAME = ?, NAME_FURIGANA = ?, EMAIL = ?, PASSWORD = ?, STATE = ? WHERE ID = ?";
    	         PreparedStatement stmt = conn.prepareStatement(sql);
    	         
	    		 stmt.setString(1, name);
    	         stmt.setString(2, nameFurigana);
    	         stmt.setString(3, email);
    	         stmt.setString(4, password);
    	         stmt.setString(5, state);
    	         stmt.setInt(6, id);
    	    		
    	         return stmt.executeUpdate() > 0;
    	    } catch (SQLException e) {
    	         e.printStackTrace();
    	         return false;
    	    }
    	}
	   
		public User getUserById(int id) throws SQLException {
		    String sql = "SELECT ID, NAME, STATE FROM USERS WHERE ID = ?";
		    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, id);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            User u = new User();
		            u.setId(rs.getInt("ID"));
		            u.setName(rs.getString("NAME"));
		            u.setState(rs.getString("STATE"));
		            return u;
		        }
		    }
		    return null;
		}

		public boolean updateState(int userId, int newState) {
		    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
		         PreparedStatement ps = conn.prepareStatement("UPDATE USERS SET STATE = ? WHERE ID = ?")) {

		        ps.setInt(1, newState);
		        ps.setInt(2, userId);
		        int rows = ps.executeUpdate();

		        return rows > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}		// ▲

	   
	   
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
		   
		   //【メイン画面への出退勤登録対象者(role = user)の出力 activeのみ】
		   public List<User> findUserByRole() {
			   
			   List<User> userList = new ArrayList<>();
			   
			   
			   try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				   String sql = "SELECT * FROM USERS WHERE ROLE = 'user' AND STATE = 'active' ORDER BY ID";
			   		PreparedStatement pStmt = conn.prepareStatement(sql);
			   		ResultSet rs = pStmt.executeQuery();
			   		
			   		while (rs.next()) {
			   			int id = rs.getInt("ID");
			   			String nameFurigana = rs.getString("NAME_FURIGANA");
			   			User user = new User(id,nameFurigana);
			   			userList.add(user);
				   }
			   } catch (SQLException e) {
		            e.printStackTrace();
			        return null;
		        }
			   return userList;
		   }
		   
		   
		   //【全てのユーザーを取得しリストに格納】
		    public List<User> findAllUser() {
		    	
		    	List<User> allUserList = new ArrayList<>();
		    	
				   // H2データベースへ繋ぐ
			        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			        	// SQLのSELECT文の設定
			            String sql = "SELECT * FROM USERS ORDER BY ID";
			            Statement pStmt = conn.createStatement();
			            ResultSet rs = pStmt.executeQuery(sql);
			            	
				            // next()は結果を一行ずつ取り出すためのメソッド
				            while (rs.next()) {
//				            	String tableName = rs.getString("TABLE_NAME");
				            	int dbid = rs.getInt("ID");
				            	String dbname = rs.getString("NAME");
				            	String dbnamefurigana = rs.getString("NAME_FURIGANA");
		            			String dbemail = rs.getString("EMAIL");
				                String dbpassword = rs.getString("PASSWORD");
				                String dbstate = rs.getString("STATE");
				                String dbrole = rs.getString("ROLE");
				                User user = new User(dbid, dbname, dbnamefurigana, dbemail, dbpassword, dbstate, dbrole);
				                allUserList.add(user);        
				            }      
					   } catch (SQLException e) {
				            e.printStackTrace();
					        return null;
					   }
					   return allUserList;
			        }
		    }
		    
		   
//		   //【メイン画面　月報出力のIDプルダウンリスト用】
//		   public boolean registerUser(int id, String name, Timestamp time, String nameFurigana) {
//			   // H2データベースへ繋ぐ
//		        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
//		        	// SQLのINSERT文の設定
//		            String sql = "INSERT INTO USERS (ID, NAME, ROLE, CREATED_AT, STATE, NAME_FURIGANA) VALUES (?, ?, 'user', ?, 'active', ?)";
//		            PreparedStatement pStmt = conn.prepareStatement(sql);
//		            
//		            // 1～4つ目の？
//		            pStmt.setInt(1, id);
//		            pStmt.setString(2, name);
//		            pStmt.setTimestamp(3, time); // サーブレットのTimestampから取ってくる createdAt
//		            pStmt.setString(4, nameFurigana);
//		            
//		            pStmt.executeUpdate(); // INSERTなので executeUpdate() を使う
//		            
//	                System.out.println("利用者登録完了");
//	                return true;
//		                
//	            } catch (SQLException e) {
//		            e.printStackTrace();
//		            System.out.println("利用者登録失敗");
//		            return false;
//		            
//		        }
//		   	}

		
	
	
		        