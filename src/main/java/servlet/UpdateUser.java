package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.UserDAO;



/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateUserサーブレットにリクエスト到達");
		request.setCharacterEncoding("UTF-8");
	    
	    BufferedReader reader = request.getReader();
	    String json = reader.lines().collect(Collectors.joining());
	    
	    Gson gson = new Gson();
	    JsonObject obj = gson.fromJson(json, JsonObject.class);
	    
	    int id = obj.get("id").getAsInt();
	    String name = obj.get("name").getAsString();
	    String nameFurigana = obj.get("nameFurigana").getAsString();
	    String state = obj.get("state").getAsString();
	    System.out.println(id + name + nameFurigana + state);

	    // DB更新処理（UPDATE文）
	    boolean success = UserDAO.updateUser(id, name, nameFurigana, state);

	    response.setContentType("application/json");
	    response.getWriter().write("{\"success\": " + success + "}");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.getWriter().write("UpdateUser servlet is alive.");
	}

}