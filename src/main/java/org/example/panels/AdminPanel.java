package org.example.panels;

import org.example.Main;

public class AdminPanel {
    public static void panel() {
        System.out.println("""
                _______________\s
                1. Confirm Expert
                1. Reject Expert \s
                3. Show All Request \s
                4. Service registration \s
                5. Register under the service \s
                6. Logout""");
        System.out.print("Enter Your Number : ");
    }

    //section select
    public static void select() throws Exception {
        switch (Main.scanner.nextLine()) {
            case "1":
                break;
            case "2":
                break;


        }
    }
}
