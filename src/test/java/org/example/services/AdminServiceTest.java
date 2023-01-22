package org.example.services;

import org.example.entity.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    @Test
    void login() throws Exception {
        Admin admin = new Admin("root","root");
        AdminService adminService = new AdminService();
        assertTrue(AdminService.login(admin));
    }
}