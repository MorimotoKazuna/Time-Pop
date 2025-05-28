package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.RecordDAO;
import model.AttendanceRecord;

/**
 * Servlet implementation class MonthlyReport
 */
@WebServlet("/MonthlyReport")
public class MonthlyReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// MonthlyReportServlet.java
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータ取得
        String userIdStr = request.getParameter("userId");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        // パース処理
        int userId = Integer.parseInt(userIdStr);
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        // DBアクセス
        RecordDAO dao = new RecordDAO();
        List<AttendanceRecord> records = dao.getAttendanceWithUserNames(userId, startDate, endDate);

        // JSPに渡す
        request.setAttribute("userId", userId);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("records", records); 

        // JSPへフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/report.jsp").forward(request, response);
      }
}
