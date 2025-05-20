package model;

import dao.UserDAO;

public class LoginLogic {
	public User execute(User inputUser) {
		UserDAO dao = new UserDAO();
		return dao.findUserByEmailAndPass(inputUser.getEmail(), inputUser.getPassword());
		// 引数で受け取るのは、ログインフォームから入力された仮のユーザー情報
		// UserDAOを用いたデータベース内部の検索準備に用いる
		// 見つかった場合、idや名前等未設定であった場所含み、Userオブジェクトを返す
		// 見つからなければ、nullが返る
	}
}
