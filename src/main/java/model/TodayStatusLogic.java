package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TodayStatusLogic {
	
public Map<Integer, Boolean> getTodayStatus() {
    Map<Integer, Boolean> statusMap = new HashMap<>();
	String JDBC_URL = "jdbc:h2:~/timepop";
	String DB_USER = "sa";
	String DB_PASS = "1234";

    try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
        String sql = "SELECT USER_ID FROM RECORDS WHERE CAST(CREATED_AT AS DATE) = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, Date.valueOf(LocalDate.now()));

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int userId = rs.getInt("USER_ID");
            statusMap.put(userId, true); // 出勤済み
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return statusMap;
}
}

