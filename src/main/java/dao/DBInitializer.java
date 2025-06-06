package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DBInitializer implements ServletContextListener {

    private static final String JDBC_URL = "jdbc:h2:~/timepop";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "1234";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            // USERSテーブル作成
            String createUsersTable = "CREATE TABLE IF NOT EXISTS USERS (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "name_furigana VARCHAR(100) DEFAULT '不明' NOT NULL, " +
                    "email VARCHAR(255), " +
                    "password VARCHAR(255), " +
                    "role VARCHAR(20) NOT NULL CHECK (role IN ('admin', 'user')), " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "state VARCHAR(20) NOT NULL DEFAULT 'active' CHECK (state IN ('active', 'disable')), " +
                    "UNIQUE(id)" +
                    ")";
            stmt.executeUpdate(createUsersTable);

            // ATTENDANCEテーブル作成
            String createAttendanceTable = "CREATE TABLE IF NOT EXISTS ATTENDANCE (" +
                    "id IDENTITY PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "clock_in TIME, " +
                    "clock_out TIME, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE, " +
                    "UNIQUE(user_id, date)" +
                    ")";
            stmt.executeUpdate(createAttendanceTable);

            // 初期管理者の登録（存在しなければ）
            String checkAdminSql = "SELECT COUNT(*) FROM USERS WHERE id = 0";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkAdminSql);
                 ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    String insertAdminSql = "INSERT INTO USERS (id, name, email, password, role, created_at) " +
                            "VALUES (0, '管理者', 'admin@example.com', '0000', 'admin', '2025-05-29')";
                    stmt.executeUpdate(insertAdminSql);
                    System.out.println("[DBInitializer] 初期管理者を登録しました。");
                } else {
                    System.out.println("[DBInitializer] 初期管理者はすでに存在します。");
                }
            }

            System.out.println("[DBInitializer] テーブル作成および初期化完了。");

        } catch (SQLException e) {
            System.err.println("[DBInitializer] エラー発生:");
            e.printStackTrace();
        }
    }

//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        // 終了時の処理（必要なら）
//    }
}

