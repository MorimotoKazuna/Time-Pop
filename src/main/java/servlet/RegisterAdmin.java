package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UserDAO;


/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterAdmin")
public class RegisterAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			
			// js 管理者画面　利用者登録ボタン押下時　の処理内参照
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String nameFurigana = request.getParameter("nameFurigana");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String createdAtStr = request.getParameter("createdAt");
			
			// 文字列 → Timestampに変換
			// "2025-05-23T14:30:00Z" → "2025-05-23 14:30:00" へ変換
			Timestamp createdAt = Timestamp.valueOf(createdAtStr.replace("T", " ").replace("Z", ""));
		
			UserDAO dao = new UserDAO();
			boolean result = dao.registerAdmin(id, name, createdAt, nameFurigana, email, password);
			
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(result ? "登録成功" : "登録失敗");
		
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無効なリクエスト");
	    }
	}
}