<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDAO;
import model.LoginLogic;
import model.User;


public class Main {
    public static void main(String[] args) {
    	try {
    		Class.forName("org.h2.Driver");
    	} catch (ClassNotFoundException e) {
    		throw new IllegalStateException (
    				"JDBCドライバを読み込めませんでした");
    	}
    	// データベースへ接続　try/catch
    	try (Connection conn = DriverManager.getConnection("jdbc:h2:~/timepop", "sa", "1234")) {
    		
    		// 【処理内容】
    		String sql = "SELECT * FROM USERS";
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		
    		ResultSet rs = pStmt.executeQuery();
    		
    		while (rs.next()) {
    			int id = rs.getInt("ID");
    			String name = rs.getString("NAME");
    			
    		System.out.println("ID:" + id);
    		System.out.println("NAME:" + name);
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
//    		return null;
    	}
=======
import dao.UserDAO;

public class Main {
    public static void main(String[] args) {
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
//        UserDAO userDAO = new UserDAO();

//        // コンストラクタ
//        String email = "k.morimoto.forwork@gmail.com";
////        String password = "0828";
//        
////        User dbUser = userDAO.findUserByEmailAndPass(email ,password);
//        User dbUser = userDAO.findUserByEmailAndPass(email);
//
//        if (dbUser != null) {
//            System.out.println("取得成功！");
//            System.out.println("Email: " + dbUser.getEmail());
//            System.out.println("Password: " + dbUser.getPassword());
//        } else {
//            System.out.println("ユーザーが見つかりませんでした。");
//        }
    	UserDAO dao = new UserDAO();
        dao.findAllUser();
<<<<<<< HEAD
//        String inputEmail = "admin@example";
//        String inputPassword = "0000";
        User user = new User("admin@example", "0000");
        System.out.println(user);
		LoginLogic loginlogic = new LoginLogic();
		boolean isLogic = loginlogic.execute(user);	
        System.out.println(isLogic);
=======
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
    }
}