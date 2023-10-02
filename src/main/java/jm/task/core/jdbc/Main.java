package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Николай", "Иванов", (byte) 26);
        userService.saveUser("Александр", "Петров", (byte) 65);
        userService.saveUser("Владимир", "Сидоров", (byte) 45);
        userService.saveUser("Алексей", "Кузнецов", (byte) 32);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}

