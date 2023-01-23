package org.example.panels;

import org.example.Main;
import org.example.entity.Services;
import org.example.repository.ServicesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminPanel {

    public static final Logger logger = LoggerFactory.getLogger(AdminPanel.class);

    //section panel
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
            case "4":
                ServiceRegistration();
                break;
            case "5":
                //todo add this
//                RegisterUnderService();
                break;
        }
    }

    private static void RegisterUnderService(Services services) {
        //todo add this code
    }


    // section registration service
    public static void ServiceRegistration() throws Exception {
        System.out.print("Enter your Service Registration name : ");
        String registrationName = Main.scanner.nextLine();
        Services services = new Services(registrationName);
        ServicesRepository servicesRepository = new ServicesRepository();
        servicesRepository.create(services);
        logger.info("services {} added into database ",services);
        panel();
        select();
    }

}
