package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/LoginToAdmin")
public class LoginToAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// ブラウザからログイン情報の取得
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		// ログイン画面で入力された値の取得
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();

		// Userインスタンス（ユーザー情報）の生成
		User user = new User(email, password);
		
		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		User dbUser = loginLogic.execute(user);  // loginUserはemail, passwordのみセット済

		
		// ログイン成功時の処理 isLogic = True
		if (dbUser != null) {
		    HttpSession session = request.getSession();
		    session.setAttribute("loginUser", dbUser);  // 名前なども含まれる		
		    
		    response.getWriter().write("success");
		} else {
			response.getWriter().write("failure");
		}
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		// DAOインスタンス作成
//        UserDAO userDAO = new UserDAO();
//        // role=admin/user state=active/disableを含む全てのユーザーの取得
//        List<User> userList = userDAO.findAllUser();
//        
//        
//        // Gsonを使ってJSON文字列に変換
//        Gson gson = new Gson();
//        String userJson = gson.toJson(userList);
//
//        // JSPに渡す
//        request.setAttribute("userJson", userJson);
//
//
//		
//        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin.jsp");
//        dispatcher.forward(request, response);
//	}



}