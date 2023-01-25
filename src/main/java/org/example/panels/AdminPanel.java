package org.example.panels;

import org.example.Main;
import org.example.entity.Expert;
import org.example.entity.Services;
import org.example.entity.SubServices;
import org.example.entity.User;
import org.example.repository.ExpertRepository;
import org.example.repository.ServicesRepository;
import org.example.repository.SubServiceRepository;
import org.example.repository.UserRepository;
import org.example.services.ExpertService;
import org.example.services.ServicesService;
import org.example.services.SubServicesService;
import org.example.services.UserService;
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
                2. Delete User \s
                3. Delete Expert \s
                4. Show All Request \s
                5. Add Service registration \s
                6. Add Sub Service Registration \s
                7. Show All Service registration \s
                8. Show All Sub Service registration \s
                9. Edit Description and Base Price \s
                10. Logout""");
        out.print("Enter Your Number : ");
    }

    //section select
    public static void select() throws Exception {
        switch (Main.scanner.nextLine()) {
            case "1":
                confirmExpert();
                panel();
                select();
                break;
            case "2":
                deleteUser();
                panel();
                select();
                break;
            case "3":
                deleteExpert();
                panel();
                select();
                break;
            case "4":
                showAllRequestForBeanExpert();
                panel();
                select();
                break;
            case "5":
                ServiceRegistration();
                break;
            case "6":
                RegisterUnderService();
                break;
            case "7":
                showAllServices();
                panel();
                select();
                break;
            case "8":
                showAllSubServices();
                panel();
                select();
                break;
            case "9":
                editSubservice();
                panel();
                select();
                break;
            case "10":
                break;
            default:

        }
    }


    private static boolean RegisterUnderService() throws Exception {
        final int size = showAllServices();
        if (size < 1) {
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
        out.print("Enter your discrioption : ");
        final String discription = Main.scanner.nextLine();
        out.print("Enter Base Price : ");
        final String basePrice = Main.scanner.nextLine();
        Long price = Validation.validNumber(basePrice);
        SubServices subServices = new SubServices(sub, byId,discription,price);
        final SubServicesService subServicesService = new SubServicesService(new SubServiceRepository());
        subServicesService.create(subServices);
        logger.info("SubServices {} created successfully ",subServices.getName());
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

    //section show all subservices
    public static int showAllSubServices() {
        SubServicesService subservice = new SubServicesService(new SubServiceRepository());
        final List<SubServices> allService = subservice.findAll(SubServices.class);
        if (allService.size() < 1)
            out.println("There is No Services");
        else {
            out.println("_________________________");
            allService.forEach(services1 -> {
                Long id = services1.getId();
                ServicesService service = new ServicesService(new ServicesRepository());
                Long serviceId = services1.getServices().getId();
                Services byId = service.findById(serviceId, Services.class);
                out.print("Services : " + byId.getName() + "\t\t");
                out.println("Name : " + services1.getName());
            });
        }
        return allService.size();
    }


    //section showall services
    public static int showAllServices() throws Exception {
        ServicesService servicesService = new ServicesService(new ServicesRepository());
        final List<Services> all = servicesService.findAll(Services.class);
        if (all.size() < 1)
            out.println("There is No Services");
        else {
            out.println("_________________________");
            all.forEach(services1 -> {
                out.print("ID : " + services1.getId() + "\t\t");
                out.println("Name : " + services1.getName());
            });
        }
        return all.size();
    }

    //section show all request
    public static int showAllRequestForBeanExpert() throws Exception {
        UserService userService = new UserService(new UserRepository());
        List<Expert> inactiveUsers = userService.findInactiveUsers();
        inactiveUsers.forEach(subServices -> {
            out.print("ID : " + subServices.getId() + "\t\t");
            out.print("FirstName : " + subServices.getFirstName() + "\t\t");
            out.print("LastName : " + subServices.getLastName() + "\t\t");
            out.print("Email : " + subServices.getEmail() + "\t\t");
            out.print("Role : " + subServices.getRole() + "\t\t");
            out.print("Services : " + subServices.getServices().getName() + "\t\t");
            out.print("Sub Services : " + subServices.getSubServices().getName() + "\t\t");
        });
        return inactiveUsers.size();
    }

    //section confirm Expert
    public static void confirmExpert() throws Exception {
        int size = showAllRequestForBeanExpert();
        out.print("\nEnter your Expert To active : ");
        String item = Main.scanner.nextLine();
        String valid = Validation.betweenShow(item, size);
        UserService userService = new UserService(new UserRepository());
        final User byId = userService.findById(Long.parseLong(valid), User.class);
        byId.setStatus(true);
        userService.update(byId);
        logger.info("user {} confirmed successfully ",byId.getUsername());
    }

    //section delete User
    public static void deleteUser() throws Exception {
        UserService userService = new UserService(new UserRepository());
        final List<User> allUsers = userService.findAll(User.class);
        allUsers.forEach(user -> {
            out.print("ID : " + user.getId() + "\t\t");
            out.print("FirstName : " + user.getFirstName() + "\t\t");
            out.print("LastName : " + user.getLastName() + "\t\t");
            out.print("Email : " + user.getEmail() + "\t\t");
            out.print("Role : " + user.getRole() + "\t\t");
        });
        out.print("\nEnter your id to delete : ");
        String item = Main.scanner.nextLine();
        String valid = Validation.betweenShow(item, allUsers.size());
        User byId = userService.findById(Long.parseLong(valid), User.class);
        userService.delete(byId);
        logger.info("user {} deleted successfully ",byId.getUsername());
    }

    // section delete Expert
    public static void deleteExpert(){
        ExpertService expertService = new ExpertService(new ExpertRepository());
        final List<Expert> allExperts = expertService.findAll(Expert.class);
        allExperts.forEach(expert -> {
            out.print("ID : " + expert.getId() + "\t\t");
            out.print("FirstName : " + expert.getFirstName() + "\t\t");
            out.print("LastName : " + expert.getLastName() + "\t\t");
            out.print("Email : " + expert.getEmail() + "\t\t");
            out.print("Role : " + expert.getRole() + "\t\t");
            out.print("Services : " + expert.getServices().getName() + "\t\t");
            out.print("Sub Services : " + expert.getSubServices().getName() + "\t\t");
        });
        out.print("\nEnter your id to delete : ");
        String item = Main.scanner.nextLine();
        String valid = Validation.betweenShow(item, allExperts.size());
        Expert byId = expertService.findById(Long.parseLong(valid), Expert.class);
        expertService.delete(byId);
        logger.info("Expert {} deleted successfully ",byId.getUsername());
    }

    //section edit subservice
    public static void editSubservice(){
        SubServicesService servicesService = new SubServicesService(new SubServiceRepository());
        final List<SubServices> allSubServices = servicesService.findAll(SubServices.class);
        allSubServices.forEach(subServices -> {
            out.println("_______________________________________________________________");
            out.print("ID : " + subServices.getId() + "\t\t");
            out.print("Name : " + subServices.getName() + "\t\t");
            out.print("Service Name : " + subServices.getServices().getName() + "\t\t");
            out.print("Base Price : " + subServices.getBasePrice() + "\t\t");
            out.print("Description : " + subServices.getDescription() + "\t\t");
        });
        out.print("\nEnter your id to edit : ");
        String item = Main.scanner.nextLine();
        String valid = Validation.betweenShow(item, allSubServices.size());
        final SubServices byId = servicesService.findById(Long.parseLong(valid), SubServices.class);
        out.print("Enter New Description : ");
        final String desc = Main.scanner.nextLine();
        out.print("Enter New Base Price : ");
        final String base = Main.scanner.nextLine();
        final long newBasePrice = Validation.validNumber(base);
        byId.setDescription(desc);
        byId.setBasePrice(newBasePrice);
        servicesService.update(byId);
        logger.info("subServices {} updated successfully ",byId.getName());

    }

}