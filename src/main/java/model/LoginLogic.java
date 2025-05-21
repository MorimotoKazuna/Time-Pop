package model;

import dao.UserDAO;

public class LoginLogic {
	public boolean execute(User user) {
		if (user == null) {
			return false;
		}

		// DAOを使って、DBに登録されているユーザーと照合
		UserDAO dao = new UserDAO();
		User dbUser = dao.findUserByEmailAndPass(user.getEmail(), user.getPassword());

		// ユーザーが見つかればログイン成功
		return dbUser != null;
	}
}

//	return false;
//UserDAO dao = new UserDAO();
//return dao.findUserByEmailAndPass(inputUser.getEmail(), inputUser.getPassword());
// 引数で受け取るのは、ログインフォームから入力された仮のユーザー情報
// UserDAOを用いたデータベース内部の検索準備に用いる
// 見つかった場合、idや名前等未設定であった場所含み、Userオブジェクトを返す
// 見つからなければ、nullが返る
