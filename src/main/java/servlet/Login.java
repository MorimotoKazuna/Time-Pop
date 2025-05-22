package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDAO;
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

		// Userインスタンス（ユーザー情報）の生成
<<<<<<< HEAD
=======
		UserDAO dao = new UserDAO();
		
>>>>>>> edd41d76151352b9c6dfbb90dd76c66565fc9476
		User user = new User(email, password);
		
		// ログイン処理
		LoginLogic loginlogic = new LoginLogic();
		boolean isLogic = loginlogic.execute(user);		// loginlogicはLoginLogicを基にインスタンス化し、LoginLogicのexecuteメソッドを用いてboolean判定し、isLogicへTrueかFalseで代入する
		
		// ログイン成功時の処理 isLogic = True
		if (isLogic) {
			// ユーザー情報をセッションスコープに保存
			
			HttpSession session = request.getSession();		// スコープの設置
			session.setAttribute("loginUser", user);		// スコープにuserインスタンス(emailとpassを持ってる)を保存
		}
		
		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request,response);
	}	

}