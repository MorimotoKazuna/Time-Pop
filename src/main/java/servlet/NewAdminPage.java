package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/NewAdminPage")
public class NewAdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // データ処理などがあればここで実行
//        request.getRequestDispatcher("/WEB-INF/jsp/newAdmin.jsp")
//               .forward(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/newAdmin.jsp");
        dispatcher.forward(request, response);
    }
}
