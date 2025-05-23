package model;

import dao.MuttersDAO;

public class PostMutterLogic {
	public void execute(Mutter mutter) {		// 引数でつぶやきリストを受け取らない
		MuttersDAO dao = new MuttersDAO();		// DAOを利用してつぶやきを投稿
		dao.create(mutter);
	}
}
