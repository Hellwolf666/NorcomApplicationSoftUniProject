package com.example.norcomapllication.init;

import com.example.norcomapllication.service.UserService;
import org.springframework.boot.CommandLineRunner;

public class DBInit  implements CommandLineRunner {
    private final UserService userService;

    public DBInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
    userService.initUserAndRoles();
    }

}