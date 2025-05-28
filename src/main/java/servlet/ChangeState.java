package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UserDAO;



/**
 * Servlet implementation class UpdateUserState
 */
@WebServlet("/ChangeState")
public class ChangeState extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int newState = Integer.parseInt(request.getParameter("newState"));

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.updateState(userId, newState);

        if (success) {
            response.sendRedirect("admin.jsp"); // ← 成功後の遷移先
        } else {
            request.setAttribute("errorMsg", "状態変更に失敗しました。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
