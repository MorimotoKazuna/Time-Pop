package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.RecordDAO;

/**
 * Servlet implementation class ClockOut
 */
@WebServlet("/Enter")
public class ClockOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
		    String userIdStr = request.getParameter("user_id");
	        int userId = Integer.parseInt(request.getParameter("user_id"));
	        // 現在時刻はrecordClockOutのCURRENT_TIMEで送信される

	        try {
	            RecordDAO dao = new RecordDAO();
	            dao.recordClockOut(userId);
	        } catch (Exception e) {
	            e.printStackTrace(); // ログに出力（本番では適切な処理）
	        }
	        response.sendRedirect("Main");
	    }	

}
