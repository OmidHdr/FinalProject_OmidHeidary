package org.example.panels;

import org.example.Enum.JobStatus;
import org.example.Main;
import org.example.entity.Order;
import org.example.entity.Services;
import org.example.entity.SubServices;
import org.example.entity.User;
import org.example.repository.OrderRepository;
import org.example.repository.ServicesRepository;
import org.example.repository.UserRepository;
import org.example.services.OrderService;
import org.example.services.ServicesService;
import org.example.services.UserService;
import org.example.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.System.out;

public class UserPanel {

    public static final Logger logger = LoggerFactory.getLogger(UserPanel.class);

    //section panel
    public static void panel() {
        out.println("""
                __________________________________\s
                1. Change Password \s
                2. Putting money in the wallet \s
                3. Show Inventory \s
                4. Need an expert \s
                5. Logout""");
        out.print("Enter Your Number : ");
    }

    //section select
    public static void select(User user) throws Exception {
        switch (Main.scanner.nextLine()) {
            case "1":
                changePassword(user);
                panel();
                select(user);
                break;
            case "2":
                chargeWallet(user);
                panel();
                select(user);
                break;
            case "3":
                showInventory(user);
                panel();
                select(user);
                break;
            case "4":
                needAnExpert(user);
                panel();
                select(user);
                break;
            case "5":
                StartPanel.panel();
                StartPanel.select();
                break;
            default:
                panel();
                select(user);
                break;

        }

    }

    private static boolean needAnExpert(User user) {
        final int size = AdminPanel.showAllServices();
        if (size < 1) {
            out.println("There is no Service Registrtion in list\n" +
                    "First Add it and come back later !!! ");
            return false;
        }
        out.print("Enter your Number : ");
        final String n = Main.scanner.nextLine();
        final String number = Validation.betweenShow(n, size);
        ServicesService service = new ServicesService(new ServicesRepository());
        final Services services = service.findById(Long.parseLong(number), Services.class);
        final SubServices subServices = StartPanel.selectSubService(services.getId());
        out.print("Enter Description of job : ");
        String desc = Main.scanner.nextLine();
        desc = Validation.validString(desc);
        out.print("Enter your proposed price : ");
        String price = Main.scanner.nextLine();
        final long proposedPrice = Validation.validNumber(price);
        // taking date from user
        final String date = Validation.validDate();
        out.print("Enter your address : ");
        final String addresss = Main.scanner.nextLine();
        // initialize order
        Order order = new Order(user,desc,proposedPrice,date,addresss,JobStatus.waitingForExpert,services,subServices);
        final OrderService orderService = new OrderService(new OrderRepository());
        orderService.create(order);
        logger.info("user {} request a {} order ",user,order);
        return true;
    }


    //section change password
    private static void changePassword(User user) {
        UserService userService = new UserService(new UserRepository());
        out.print("Enter your new password : ");
        final String newPassword = Validation.validPassword(Main.scanner.nextLine());
        user.setPassword(newPassword);
        userService.update(user);
        out.println("password changed successfully ");
        logger.info("user {} Changed his password successfully ",user.getUsername());
    }

    //section charge wallet
    public static void chargeWallet(User user){
        UserService userService = new UserService(new UserRepository());
        out.print("Enter your Inventory you want charge it : ");
        final String addInventory = Main.scanner.nextLine();
        Long newInventory = Validation.validNumber(addInventory);
        Long oldInventory = user.getInventory();
        user.setInventory(newInventory+oldInventory);
        userService.update(user);
        out.println("Inventory changed successfully ");
        logger.info("user {} charged his inventory '{}' price successfully ",user.getUsername(),newInventory);
    }

    //section show inventory
    public static void showInventory(User user){
        UserService userService = new UserService(new UserRepository());
        final long id = user.getId();
        final User byId = userService.findById(id,User.class);
        out.println("You have : "+byId.getInventory()+" Money");
    }


}