package org.example.panels;

import org.example.Main;
import org.example.entity.Customer;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.repository.UserService;
import org.example.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


public class StartPanel {

    public static final Logger logger = LoggerFactory.getLogger(StartPanel.class);
    //section MainPanel
    public static void panel() {
        System.out.println("_______________ \n" +
                "1. Login \n" +
                "2. Register \n" +
                "3. Quit");
        System.out.print("Enter Your Number : ");
    }

    //section create user
    public static User createUser() {
        System.out.print("Enter your username : ");
        String username = Main.scanner.nextLine();
        System.out.print("Enter your password : ");
        String password = Main.scanner.nextLine();
        return new User(username, password);
    }

    //section select
    public static void select() throws Exception {
        switch (Main.scanner.nextLine()) {
            case "1":
                final User user = createUser();
                AdminService adminService = new AdminService();
                final boolean login = adminService.login(user);
                if (login) {
                    System.out.println("User "+user.getUsername()+" Logged in successfully ");
                    logger.info("User Logged in successfully");
                } else {
                    System.out.println("wrong username or password ");
                    logger.error("wrong username or password");
                    panel();
                    select();
                }
                break;
            case "2":
                register();
                break;
        }
    }

    //section register
    public static void register() throws Exception {
//        /*
        System.out.print("Enter your First Name : ");
        String firstName = Main.scanner.nextLine();
        System.out.print("Enter your Last Name : ");
        String lastName = Main.scanner.nextLine();
        System.out.print("Enter your Email Address : ");
        String email = Main.scanner.nextLine();
        LocalDate date = LocalDate.now();
        System.out.print("Enter Your Password : ");
        String password = Main.scanner.nextLine();
        System.out.print("Enter your role(customer / expert ) : ");
        String role = Main.scanner.nextLine();

        // initialize customer
        boolean status = false;
        if (role.equals("customer")){
            status = true;
        }
        User user = new User(firstName,lastName,email,date,password,Role.getFromString(role),status,0L);
        UserService userService = new UserService();
        userService.create(user);
        logger.info("User "+user.getUsername()+" created successfully");
        panel();
        select();
    }

}

