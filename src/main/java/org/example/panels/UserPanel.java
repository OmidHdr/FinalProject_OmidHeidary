package org.example.panels;

import static java.lang.System.out;

public class UserPanel {
    public static void panel() {
        out.println("""
                __________________________________\s
                1. Change Password
                2. Delete User \s
                3. Delete Expert \s
                4. Show All Request \s
                5. Add Service registration \s
                6. Add Sub Service Registration \s
                7. Show All Service registration \s
                8. Show All Sub Service registration \s
                9. Logout""");
        out.print("Enter Your Number : ");
    }
}
