package org.example.panels;

import org.example.Main;

public class AdminPanel {
    public static void panel() {
        System.out.println("_______________ \n" +
                "1. Add a new  \n" +
                "2. Confirm Loan\n" +
                "3. Reject Loan]n" +
                "4. Logout");
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
