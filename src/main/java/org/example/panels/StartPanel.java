package org.example.panels;

import org.example.Main;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.services.UserService;
import org.example.services.AdminService;
import org.example.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


public class StartPanel {

    public static final Logger logger = LoggerFactory.getLogger(StartPanel.class);

    //section MainPanel
    public static void panel() {
        System.out.println("""
                _______________\s
                1. Login\s
                2. Register\s
                3. Quit""");
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
            case "1" -> {
                final User user = createUser();
                final boolean login = AdminService.login(user);
                final boolean loginUser = UserService.login(user);
                if(login){
                    logger.info("User " + user.getUsername() + " Logged in successfully ");
                    System.out.println("User Logged in successfully");
                    AdminPanel.panel();
                    AdminPanel.select();
                }else if(loginUser) {
                    System.out.println("User exist");
                }else{
                    System.out.println("wrong username or password ");
                    logger.error("wrong username or password");
                    panel();
                    select();
                }
            }
            case "2" -> register();
        }
    }

    //section register
    public static void register() throws Exception {
        System.out.print("Enter your First Name : ");
        String firstName = Main.scanner.nextLine();
        firstName = Validation.validString(firstName);
        System.out.print("Enter your Last Name : ");
        String lastName = Main.scanner.nextLine();
        lastName = Validation.validString(lastName);
        System.out.print("Enter your Email Address : ");
        String email = Main.scanner.nextLine();
        email = Validation.validString(email);
        LocalDate date = LocalDate.now();
        System.out.print("Enter Your Password : ");
        String password = Main.scanner.nextLine();
        System.out.println("""
                ________________\s
                1. customer
                2. expert\s
                """);
        System.out.print("Enter your role( 1 or 2 ) : ");
        String role = Main.scanner.nextLine();
        role = Validation.between(role);
        final String roleUser = Validation.Role(Integer.parseInt(role));

        // initialize customer
        boolean status = role.equals("customer");
        User user = new User(firstName, lastName, email, date, password, Role.getFromString(roleUser), status, 0L);
        UserService userService = new UserService();
        userService.create(user);
        logger.info("User " + user.getUsername() + " created successfully");
        panel();
        select();
    }

}

