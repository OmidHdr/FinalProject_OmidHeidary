package org.example.panels;

import org.example.Main;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.services.UserService;
import org.example.validation.Validation;

import static java.lang.System.out;

public class UserPanel {
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
        }

    }

    private static boolean changePassword(User user) {
        UserService userService = new UserService(new UserRepository());
        out.print("Enter your new password : ");
        final String newPassword = Validation.validPassword(Main.scanner.nextLine());
        user.setPassword(newPassword);
        userService.update(user);
        return true;
    }

}