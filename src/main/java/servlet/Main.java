package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.GetUserListLogic;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// role=userのユーザーリストを取得して、リクエストスコープに保存
		GetUserListLogic getUserListLogic = new GetUserListLogic();
		List<User> userList = getUserListLogic.execute();
		request.setAttribute("userList", userList);
		
		//ログインしているか確認するため、セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();			// セッションスコープの設置
		User loginUser = (User)session.getAttribute("loginUser");
		
		if (loginUser == null ) {		// ログインしていない状態
			// リダイレクト ※フォワードだと、URLがログインできていないのにMainに入っている表記になるため。
			response.sendRedirect("index.jsp");
		} else {						// ログイン済みの場合
			// フォワード
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
					"WEB-INF/jsp/main.jsp");
				dispatcher.forward(request,response);
		}		
	}
	

	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String text = request.getParameter("text");
			
			
//			
//			// 入力値チェック
//			if (text != null && text.length() != 0) {				
//				// セッションスコープに保存されたユーザー情報を取得
//				HttpSession session = request.getSession();
//				User loginUser = (User) session.getAttribute("loginUser");
//				
//				// つぶやきを作成してつぶやきリストに追加
//				Mutter mutter = new Mutter(loginUser.getName(), text);
//				PostMutterLogic postMutterLogic = new PostMutterLogic();
//				postMutterLogic.execute(mutter);
//			} else {
//				// エラーメッセージをリクエストスコープに保存
//				request.setAttribute("errorMsg","つぶやきが入力されていません");
//			}
//			
//			// つぶやきリストを取得して、リクエストスコープに保存
//			GetUserListLogic getMutterListLogic = new GetUserListLogic();
//			List<Mutter> mutterList = getMutterListLogic.execute();
//			request.setAttribute("mutterList", mutterList);
//			
//				// メイン画面にフォワード
//				RequestDispatcher dispatcher =
//						request.getRequestDispatcher(
//						"WEB-INF/jsp/main.jsp");
//					dispatcher.forward(request,response);
//					
//			
		}
}
