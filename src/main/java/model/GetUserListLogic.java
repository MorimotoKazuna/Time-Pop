package model;

import java.util.List;

import dao.UserDAO;

public class GetUserListLogic {
	public List<User> execute() {
		UserDAO dao = new UserDAO();
		// role=user state=activeのユーザーリスト作成
		List<User> userList = dao.findUserByRole();
		return userList;
	}
}
