import dao.UserDAO;

public class Main {
    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();

//        // コンストラクタ
//        String email = "k.morimoto.forwork@gmail.com";
////        String password = "0828";
//        
////        User dbUser = userDAO.findUserByEmailAndPass(email ,password);
//        User dbUser = userDAO.findUserByEmailAndPass(email);
//
//        if (dbUser != null) {
//            System.out.println("取得成功！");
//            System.out.println("Email: " + dbUser.getEmail());
//            System.out.println("Password: " + dbUser.getPassword());
//        } else {
//            System.out.println("ユーザーが見つかりませんでした。");
//        }
    	UserDAO dao = new UserDAO();
        dao.findAllUser();
    }
}