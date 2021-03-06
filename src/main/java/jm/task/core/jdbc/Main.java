package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Steve", "Jhon", (byte) 52);
        userService.saveUser("Osman", "Su", (byte) 22);
        userService.saveUser("Bella", "Peter", (byte) 62);
        userService.saveUser("Jhon", "Steve", (byte) 15);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.connectionClose();
    }
}
