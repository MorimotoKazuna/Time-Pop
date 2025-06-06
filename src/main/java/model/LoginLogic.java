package model;

import dao.UserDAO;

public class LoginLogic {
	public User execute(User user) {
		
		// DAOを使って、DBに登録されているユーザーと照合
		 UserDAO dao = new UserDAO();
	     User dbUser = dao.findUserByEmailAndPass(user.getEmail(), user.getPassword());
		    
//		    System.out.println(dbUser);
		    if (dbUser != null) {
	            System.out.println("ログイン成功: " + dbUser.getName());
	            return dbUser; // 成功 → DBの完全なUserオブジェクトを返す
	        } else {
	            System.out.println("ログイン失敗");
	            return null; // 失敗
	        }
	    }
	}