package org.example.panels;

import org.example.Main;
import org.example.Enum.Role;
import org.example.entity.Expert;
import org.example.entity.Services;
import org.example.entity.SubServices;
import org.example.entity.User;
import org.example.repository.ServicesRepository;
import org.example.repository.SubServiceRepository;
import org.example.repository.UserRepository;
import org.example.services.ServicesService;
import org.example.services.SubServicesService;
import org.example.services.UserService;
import org.example.services.AdminService;
import org.example.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.List;

import static java.lang.System.out;


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
                final User loginUser = UserService.login(user);
                if (login) {
                    logger.info("User " + user.getUsername() + " Logged in successfully ");
                    System.out.println("User Logged in successfully");
                    AdminPanel.panel();
                    AdminPanel.select();
                } else if (loginUser != null) {
                    logger.info("User " + user.getUsername() + " Logged in successfully ");
                    UserPanel.panel();
                    UserPanel.select(loginUser);
                } else {
                    System.out.println("wrong username or password ");
                    logger.error("wrong username or password");
                    panel();
                    select();
                }
            }
            case "2" -> register();
            case "3" -> {

            }
            default -> {
                panel();
                select();
            }
        }
    }

    //section register
    public static void register() throws Exception {
        out.print("Enter your First Name : ");
        String firstName = Main.scanner.nextLine();
        firstName = Validation.validString(firstName);
        out.print("Enter your Last Name : ");
        String lastName = Main.scanner.nextLine();
        lastName = Validation.validString(lastName);
        out.print("Enter your Email Address : ");
        String email = Main.scanner.nextLine();
        email = Validation.validateEmail(email);
        LocalDate date = LocalDate.now();
        out.print("Enter Your Password : ");
        String password = Main.scanner.nextLine();
        password = Validation.validPassword(password);
        out.println("""
                ________________\s
                1. customer
                2. expert\s
                """);
        out.print("Enter your role( 1 or 2 ) : ");
        String role = Main.scanner.nextLine();
        role = Validation.between(role);
        final String roleUser = Validation.Role(Integer.parseInt(role));

        if (role.equals("2")) {

            Services services = selectService();
            SubServices subServices = selectSubService(services.getId());
            // initialize expert
            Expert expert = new Expert(firstName, lastName, email, date, password, Role.getFromString(roleUser), false, 0L,services,subServices);
            UserService userService = new UserService(new UserRepository());
            userService.create(expert);
            logger.info("Expert {} created successfully",expert.getUsername());
            panel();
            select();
        } else {
            // initialize customer
            User user = new User(firstName, lastName, email, date, password, Role.getFromString(roleUser), true, 0L);
            UserService userService = new UserService(new UserRepository());
            userService.create(user);
            logger.info("User " + user.getUsername() + " created successfully");
            panel();
            select();
        }
    }


    //section select Service
    public static Services selectService() throws Exception {
        final int i = AdminPanel.showAllServices();
        System.out.print("Choice your Service : ");
        String ability = Main.scanner.nextLine();
        final String s = Validation.betweenShow(ability, i);
        ServicesService service = new ServicesService(new ServicesRepository());
        final Services byId = service.findById(Long.parseLong(s), Services.class);
        return byId;
    }

    public static SubServices selectSubService(Long id) {
        SubServicesService subServices = new SubServicesService(new SubServiceRepository());
        List<SubServices> show = subServices.selectSubService(id);
        out.println("_________________________");
        show.forEach(services1 -> {
            out.print("ID : " + services1.getId()+"\t\t");
            out.println("Name : " + services1.getName());
        });
        out.print("choice your sub service : ");
        String sub = Main.scanner.nextLine();
        String number = Validation.betweenShow(sub, show.size());
        final SubServices byId = subServices.findById(Long.parseLong(number), SubServices.class);
        return byId;
    }


}

