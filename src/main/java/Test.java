import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
  public static void main(String[] args) {
	  
	  import com.google.gson.Gson;

	  public class Test {
	      public static void main(String[] args) {
	          Gson gson = new Gson();
	          System.out.println("Gsonは使えます！");
	      }
	  }
//    // JDBCドライバを読み込む
//    try {
//        Class.forName("org.h2.Driver");
//    } catch (ClassNotFoundException e) {
//        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
//    }	  
//    // データベースに接続
//    try (Connection conn = DriverManager.getConnection("jdbc:h2:~/timepop", "sa", "1234")) {
//
//      // SELECT文を準備
//      String sql = "SELECT * FROM USERS";
//      PreparedStatement pStmt = conn.prepareStatement(sql);
//
//      // SELECT文を実行し、結果表（ResultSet）を取得
//      ResultSet rs = pStmt.executeQuery();
//
//      // 結果表に格納されたレコードの内容を表示
//      while (rs.next()) {
//        int id = rs.getInt("ID");
//        String name = rs.getString("NAME");
//
//        // 取得したデータを出力
//        System.out.println("ID:" + id);
//        System.out.println("名前:" + name);
//      }
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
  }
}