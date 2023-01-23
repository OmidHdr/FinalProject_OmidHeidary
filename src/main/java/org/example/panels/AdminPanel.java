package org.example.panels;

import org.example.Main;
import org.example.entity.Services;
import org.example.entity.SubServices;
import org.example.repository.ServicesRepository;
import org.example.repository.SubServiceRepository;
import org.example.services.ServicesService;
import org.example.services.SubServicesService;
import org.example.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import static java.lang.System.out;

public class AdminPanel {

    public static final Logger logger = LoggerFactory.getLogger(AdminPanel.class);

    //section panel
    public static void panel() {
        out.println("""
                __________________________________\s
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
                RegisterUnderService();
                break;
            case "6":
                showAllServices();
                panel();
                select();
                break;
            case "7":
                break;
        }
    }

    private static boolean RegisterUnderService() throws Exception {
        final int size = showAllServices();
        if (size < 1){
            out.println("There is no Service Registrtion in list\n" +
                    "First Add it and come back later !!! ");
            panel();
            select();
            return false;
        }
        out.print("Enter your Number : ");
        final String n = Main.scanner.nextLine();
        final String number = Validation.betweenShow(n, size);
        ServicesService service = new ServicesService(new ServicesRepository());
        final Services byId = service.findById(Long.parseLong(number), Services.class);
        out.print("Enter your SubService : ");
        final String sub = Main.scanner.nextLine();
        SubServices subServices = new SubServices(sub,byId);
        final SubServicesService subServicesService = new SubServicesService(new SubServiceRepository());
        subServicesService.create(subServices);
        panel();
        select();
        return true;


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

    public static int showAllServices() throws Exception {
        ServicesService servicesService = new ServicesService(new ServicesRepository());
        final List<Services> all = ServicesService.showAllServices();
        if (all.size() < 1)
            out.println("There is No Services");
        else {
            out.println("_________________________");
            all.forEach(services1 -> {
                out.print("ID : " + services1.getId()+"\t\t");
                out.println("Name : " + services1.getName());
            });
        }
        return all.size();
    }
}