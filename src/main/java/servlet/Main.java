package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.RecordDAO;
import model.GetUserListLogic;
import model.User;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // RecordDAOなどで今日の出勤状態を調べる処理を入れる
	    RecordDAO recordDAO = new RecordDAO();
	    
	    // 今日の日付を取得
	    LocalDate today = LocalDate.now();
	    
	    // RecordDAOから出退勤時間をMapで取得し、requestで呼び出してから使えるようにする
	    Map<Integer, String[]> timeMap = recordDAO.getTodayTimes(today);
		request.setAttribute("timeMap", timeMap);
		
	    // role=userのユーザーリストを取得して、リクエストスコープに保存
	    GetUserListLogic getUserListLogic = new GetUserListLogic();
	    List<User> userList = getUserListLogic.execute();
	    request.setAttribute("userList", userList);




	    // todayStatusという名前のMapを新たに作る。（目的：今日出勤しているかをtrueかfalseで情報を保持するため）
	    // キー=Integer(userid) 値=boolean(true/false)
	    Map<Integer, Boolean> todayStatus = new HashMap<>();
    	// MapをJSPに渡す
    	request.setAttribute("todayStatus", todayStatus);

	    for (User user : userList) {
	        boolean clockedIn = recordDAO.hasClockInForDate(user.getId(), today);  // 例えばこのメソッドを用意
	        todayStatus.put(user.getId(), clockedIn);
	    }
	    request.setAttribute("todayStatus", todayStatus);


	    // ログインしているか確認するため、セッションスコープからユーザー情報を取得
	    
	    HttpSession session = request.getSession();
	    User loginUser = (User)session.getAttribute("loginUser");

	    if (loginUser == null) {
	        // ログインしていない場合はログインページへリダイレクト
	        response.sendRedirect("index.jsp");
	    } else {
	        // ログイン済みなら画面表示
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
	        dispatcher.forward(request, response);

		}
	}
}