package org.example.panels;

import org.example.Main;
import org.example.entity.Services;
import org.example.repository.ServicesRepository;
import org.example.services.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.System.out;

public class AdminPanel {

    public static final Logger logger = LoggerFactory.getLogger(AdminPanel.class);

    //section panel
    public static void panel() {
        out.println("""
                _______________\s
                1. Confirm Expert
                1. Reject Expert \s
                3. Show All Request \s
                4. Service registration \s
                5. Register under the service \s
                6. Show All Service registration \s
                7. Logout""");
        out.print("Enter Your Number : ");
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
            case "6":
                showAllServices();
                break;
        }
    }

    private static void RegisterUnderService(Services services) {
        //todo add this code


    }


    // section registration service
    public static void ServiceRegistration() throws Exception {
        out.print("Enter your Service Registration name : ");
        String registrationName = Main.scanner.nextLine();
        Services services = new Services(registrationName);
        ServicesService servicesService = new ServicesService(new ServicesRepository());
        servicesService.create(services);
        logger.info("services {} added into database ", services);
        panel();
        select();
    }

    public static void showAllServices() throws Exception {
        ServicesService servicesService = new ServicesService(new ServicesRepository());
        List all = servicesService.findAll(Services.class);
        if (all.size() < 1)
            out.println("There is No Services");
        else {
            out.println("_________________________");
            final List<Services> services = ServicesService.showAllServices();
            services.forEach(services1 -> out.println(services1.getName()));
        }
        panel();
        select();
    }
}