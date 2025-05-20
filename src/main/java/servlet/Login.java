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
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();

		// 入力された情報からUserインスタンスの作成
		User inputUser = new User(0, null, email, password, null);

		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		User dbUser = loginLogic.execute(inputUser);

		if (dbUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", dbUser); // dbUserに変更してもOK
		}

		// 結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}
}
//<%= loginUser.getName() %>