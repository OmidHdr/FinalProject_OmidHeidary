package org.example.panels;

import org.example.Main;
import org.example.entity.User;
import org.example.repository.UserRepository;
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
                7. Need an expert \s
                9. Logout""");
        out.print("Enter Your Number : ");
    }

    //section select
    public static void select(User user) {
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
            case "3":
                //todo
        }

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


}