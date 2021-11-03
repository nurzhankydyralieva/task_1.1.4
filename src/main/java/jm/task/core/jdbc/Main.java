package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Steve", "Jhon", (byte) 52);
        userService.saveUser("Osman", "Su", (byte) 22);
        userService.saveUser("Bella", "Peter", (byte) 62);
        userService.saveUser("Jhon", "Steve", (byte) 15);
        userService.getAllUsers();
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
