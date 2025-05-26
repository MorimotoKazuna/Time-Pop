package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


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
    
    
    // 【出勤処理 part1 出勤時間の入力有無確認】
    public boolean isClockInRegistered(Connection conn, int userId, LocalDate date) throws SQLException {
        String sql = "SELECT id FROM ATTENDANCE WHERE user_id = ? AND date = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();
            return rs.next(); // 存在すれば true
        }
    }
    // 【出勤処理 part2 出勤時間の入力】
    public void insertClockIn(Connection conn, int userId, LocalDate date, LocalTime clockInTime, Timestamp createdAt) throws SQLException {
        String sql = "INSERT INTO ATTENDANCE (user_id, date, clock_in, created_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(date));
            ps.setTime(3, Time.valueOf(clockInTime));
            ps.setTimestamp(4, createdAt);
            ps.executeUpdate();
        }
}
}
