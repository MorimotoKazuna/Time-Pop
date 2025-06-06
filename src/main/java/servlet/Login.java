package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// ブラウザからログイン情報の取得
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		// ログイン画面で入力された値の取得
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();


		    User user = new User(email, password);
		    LoginLogic loginLogic = new LoginLogic();
		    User dbUser = loginLogic.execute(user);

		    if (dbUser != null) {
		        HttpSession session = request.getSession();
		        session.setAttribute("loginUser", dbUser);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
				dispatcher.forward(request,response);
		    } else {
				// ログイン結果画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
				dispatcher.forward(request,response);
		}
		
	}	

}