package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDAO;
import model.User;


/**
 * Servlet implementation class GetUserDetail
 */
@WebServlet("/GetUserDetail")
public class GetUserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    UserDAO dao = new UserDAO();
	    User user = null;
	    try {
	        user = dao.getUserById(id);
	    } catch (SQLException e) {
	        throw new ServletException(e);
	    }

	    response.setContentType("application/json;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(new Gson().toJson(user));
	}

}
