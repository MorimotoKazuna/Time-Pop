package test;

import java.util.List;

import com.google.gson.Gson;

import dao.UserDAO;
import model.User;

public class Test {
	public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAO();
            List<User> userList = userDAO.findAllUser();

            Gson gson = new Gson();
            String userJson = gson.toJson(userList);

            System.out.println(userJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
