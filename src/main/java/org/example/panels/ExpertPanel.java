package org.example.panels;

import org.example.Enum.JobStatus;
import org.example.Main;
import org.example.entity.Expert;
import org.example.entity.Order;
import org.example.entity.User;
import org.example.repository.ExpertRepository;
import org.example.repository.OrderRepository;
import org.example.services.ExpertService;
import org.example.services.OrderService;
import org.example.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static java.lang.System.out;

public class ExpertPanel {

    public static final Logger logger = LoggerFactory.getLogger(ExpertPanel.class);

    //section panel
    public static void panel() {
        out.println("""
                __________________________________\s
                1. Change Password \s
                2. Taking money in the My bank account \s
                3. Show Inventory \s
                4. Take a job \s
                5. Request for new job \s
                6. Logout""");
        out.print("Enter Your Number : ");
    }


    //section select
    public static void select(User user) {
        ExpertService expertService = new ExpertService(new ExpertRepository());
        final Expert expert = expertService.findById(user.getId(), Expert.class);
        switch (Main.scanner.nextLine()) {
            case "1":
                changePassword(expert);
                panel();
                select(expert);
                break;
            case "2":
                takeMoney(expert);
                panel();
                select(expert);
                break;
            case "3":
                showInventory(expert);
                panel();
                select(expert);
                break;
            case "4":
                takeJob(expert);
                panel();
                select(expert);
                break;
            case "5":
                //todo
                out.println("5");
                panel();
                select(expert);
                break;
            case "6":
                break;
            default:
                panel();
                select(expert);


        }
    }

    //section take a job
    public static boolean takeJob(Expert expert){
        ExpertService expertService = new ExpertService(new ExpertRepository());
        OrderService orderService = new OrderService(new OrderRepository());
        final List<Order> orders = expertService.allJobs(expert);
        if (orders.size() < 1) {
            out.println("There is no Job for you\n" +
                    "Try again later !!! ");
            panel();
            select(expert);
            return false;
        }
        orders.forEach(order -> {
            int i = 1 ;
            out.println("______________________________________________________________________________________________");
            out.print("ID : " + i +"\t\t");
            out.print("UserLastName : " + order.getUser().getLastName()+"\t\t");
            out.print("price : "+order.getProposedPrice()+"\t\t");
            out.print("Description : " + order.getDescription()+"\t\t");
            out.print("Start Date : "+ order.getStartDate()+"\t\t");
            out.print("Address : " + order.getAddress()+"\t\t");
            out.println("______________________________________________________________________________________________");
            i += 1;
        });
        out.print("Enter your Number : ");
        final String n = Main.scanner.nextLine();
        final String number = Validation.betweenShow(n, orders.size());
        final Order order = orders.get(Integer.parseInt(number)-1);
        final Order byId = orderService.findById(order.getId(), Order.class);
        final Long proposedPrice = byId.getProposedPrice();
        byId.setJobStatus(JobStatus.started);
        Long inventory = expert.getInventory();
        inventory += proposedPrice;
        expert.setInventory(inventory);
        orderService.update(byId);
        logger.info("order {} request accepted ",byId.getId());
        expertService.update(expert);
        logger.info("Expert {} updated inventory successfully",expert.getUsername());
        return true;
    }

    //section take money
    public static void takeMoney(Expert expert){
        ExpertService expertService = new ExpertService(new ExpertRepository());
        out.print("Enter your Inventory you want take it : ");
        String take = Main.scanner.nextLine();
        Long newInventory = Validation.validNumber(take);
        Long oldInventory = expert.getInventory();
        if (oldInventory >= newInventory){
            expert.setInventory(oldInventory-newInventory);
            expertService.update(expert);
            out.println("Inventory changed successfully ");
            logger.info("Expert {} take it {} inventory from his wallet successfully ",expert.getUsername(),newInventory);
        }
    }
    //section show inventory
    public static void showInventory(Expert expert) {
        ExpertService expertService = new ExpertService(new ExpertRepository());
        final long id = expert.getId();
        final User byId = expertService.findById(id, Expert.class);
        out.println("You have : " + byId.getInventory() + " Money");
    }

    //section change password
    private static void changePassword(Expert expert) {
        ExpertService expertService = new ExpertService(new ExpertRepository());
        out.print("Enter your new password : ");
        final String newPassword = Validation.validPassword(Main.scanner.nextLine());
        expert.setPassword(newPassword);
        expertService.update(expert);
        out.println("password changed successfully ");
        logger.info("Expert {} Changed his password successfully ", expert.getUsername());
    }

}
