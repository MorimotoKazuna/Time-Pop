package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.AttendanceRecord;





public class RecordDAO {
	// DB保存先・ユーザー名・パスワードを定数に格納
	private static final String JDBC_URL = "jdbc:h2:~/timepop";
	private static final String DB_USER = "sa";
	private static final String DB_PASS = "1234";
	
    // コンストラクタで1回だけドライバを読み込む（推奨）
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2ドライバのロードに失敗しました", e);
        }
    }
    
    
    // 【本日の出退勤時刻を取得】
    public Map<Integer, String[]>getTodayTimes(LocalDate date) {
    	Map<Integer, String[]> timeMap = new HashMap<>();
    	
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

        	String sql = "SELECT USER_ID, CLOCK_IN, CLOCK_OUT FROM ATTENDANCE WHERE CAST(CREATED_AT AS DATE) = ?";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setDate(1, Date.valueOf(date));
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()) {
            	 int userId = rs.getInt("USER_ID");
            	 String clockIn = rs.getString("CLOCK_IN");
            	 String clockOut = rs.getString("CLOCK_OUT");
            	 // timeMap キ=userid 値=String[]　のMap
            	 timeMap.put(userId, new String[]{clockIn, clockOut});
             }
        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
        return timeMap;

    }
    
    
    // ×【出勤処理 part1 出勤時間の入力有無確認】
    public boolean hasClockInForDate(int userId, LocalDate date) {
        String sql = "SELECT COUNT(*) FROM ATTENDANCE WHERE user_id = ? AND DATE(created_at) = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setInt(1, userId);
             stmt.setDate(2, Date.valueOf(date));
             ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
                return rs.getInt(1) > 0;
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }    
    
    
    // 【出勤処理 part2 出勤時間の入力　Inter】
    public void recordClockIn(int userId, String createdAt) throws SQLException {
	   // H2データベースへ繋ぐ
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

        	String sql = "INSERT INTO ATTENDANCE (user_id, date, clock_in, created_at) VALUES (?, CURRENT_DATE, CURRENT_TIME, ?)";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, userId);
             ps.setString(2, createdAt);
             ps.executeUpdate();
        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    
    //　【退勤処理　Enter】
    public void recordClockOut(int userId) throws SQLException {
 	   // H2データベースへ繋ぐ
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

        	String sql = "UPDATE ATTENDANCE SET clock_out = CURRENT_TIME WHERE user_id = ? AND date = CURRENT_DATE";
             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, userId);
             ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    
    public List<AttendanceRecord> getAttendanceWithUserNames(int userId, LocalDate startDate, LocalDate endDate) {
    	
        List<AttendanceRecord> records = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

		        String sql = """
		            SELECT 
						a.user_id,
						a.date,
						a.clock_in,
						a.clock_out,
						u.name
						FROM 
						ATTENDANCE a
						JOIN 
						USERS u ON a.user_id = u.id
						WHERE
						a.user_id = ?
						AND a.date BETWEEN ? AND ?
						ORDER BY 
						a.date, a.user_id;
		        """;
		        
        		PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, userId);
                stmt.setDate(2, java.sql.Date.valueOf(startDate));
                stmt.setDate(3, java.sql.Date.valueOf(endDate));

	             ResultSet rs = stmt.executeQuery();
	
	             while (rs.next()) {
	            	    int uId = rs.getInt("user_id");
	            	    LocalDate date = rs.getDate("date").toLocalDate(); // ← 修正
	            	    LocalTime clockIn = rs.getTime("clock_in") != null ? rs.getTime("clock_in").toLocalTime() : null; // ← 修正
	            	    LocalTime clockOut = rs.getTime("clock_out") != null ? rs.getTime("clock_out").toLocalTime() : null; // ← 修正
	            	    String name = rs.getString("name");

	            	    records.add(new AttendanceRecord(uId, date, clockIn, clockOut, name));
	            	}
	
	        } catch (SQLException e) {
	        	 e.printStackTrace();
	        	 System.out.println("出勤データの取得中にエラーが発生しました");
	        }

        return records;
    }
}
