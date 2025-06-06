package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDAO;
import model.User;


/**
 * Servlet implementation class AdminPagesServlet
 */
@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// DAOインスタンス作成
        UserDAO userDAO = new UserDAO();
        // role=admin/user state=active/disableを含む全てのユーザーの取得
        List<User> userList = userDAO.findAllUser();
        
        
        // Gsonを使ってJSON文字列に変換
        Gson gson = new Gson();
        String userJson = gson.toJson(userList);

        // JSPに渡す
        request.setAttribute("userJson", userJson);


		
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin.jsp");
        dispatcher.forward(request, response);
	}

}
