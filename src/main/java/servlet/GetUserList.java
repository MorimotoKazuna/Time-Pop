package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class GetUserList
 */
@WebServlet("/GetUserList")
public class GetUserList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UserDAO dao = new UserDAO();
            List<User> userList = dao.getUsersByRole("user");
            
            Gson gson = new Gson();
            String json = gson.toJson(userList);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            // エラー時は500エラーとメッセージを返す
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("{\"error\":\"ユーザー一覧の取得に失敗しました\"}");
        }
    }
}
