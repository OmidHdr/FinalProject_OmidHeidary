package org.example.panels;

import org.example.Main;

public class StartPanel {

    //section MainPanel
    public static void panel() {
        System.out.println("_______________ \n" +
                "1. Login \n" +
                "2. Register \n" +
                "3. Quit");
        System.out.print("Enter Your Number : ");
    }

    //section select
    public static void select() {
        switch (Main.scanner.nextLine()) {
            case "1":
                break;
            case "2":
                break;
        }
    }
    //section login
    public static int login() {
        return 0;
    }

}

