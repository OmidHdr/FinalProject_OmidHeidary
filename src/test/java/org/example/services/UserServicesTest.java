package org.example.services;

import org.example.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServicesTest {

    @Test
    void login() throws Exception {
        User user = new User("root","root");
        AdminService adminService = new AdminService();
        assertTrue(AdminService.login(user));
    }
}