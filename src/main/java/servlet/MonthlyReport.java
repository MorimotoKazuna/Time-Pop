package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.h2.result.Row;

@WebServlet("/MonthlyReport")
public class MonthlyReport extends HttpServlet {

    private AttendanceDAO attendanceDAO = new AttendanceDAO(); // DAOの実装は別途

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String userIdStr = req.getParameter("userId");
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");

        if (userIdStr == null || startDateStr == null || endDateStr == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "パラメータ不足");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdStr);
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);

            // DBから指定ユーザー・期間の出退勤データを取得
            List<AttendanceRecord> records = attendanceDAO.getRecords(userId, startDate, endDate);

            // Excelファイルを作成
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("月報");

            // ヘッダー行作成
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("日付");
            headerRow.createCell(1).setCellValue("出勤時間");
            headerRow.createCell(2).setCellValue("退勤時間");

            // データ行作成
            int rowIdx = 1;
            for (AttendanceRecord record : records) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(record.getDate().toString());
                row.createCell(1).setCellValue(record.getClockIn() != null ? record.getClockIn().toString() : "-");
                row.createCell(2).setCellValue(record.getClockOut() != null ? record.getClockOut().toString() : "-");
            }

            // 適当にカラム幅を自動調整
            for (int i = 0; i <= 2; i++) {
                sheet.autoSizeColumn(i);
            }

            // レスポンスのヘッダーを設定（ファイル名は適宜変更）
            resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            resp.setHeader("Content-Disposition", "attachment; filename=\"MonthlyReport.xlsx\"");

            // ファイルを書き込み、リソースを閉じる
            try (OutputStream out = resp.getOutputStream()) {
                workbook.write(out);
            }
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "サーバーエラー");
        }
    }
}
