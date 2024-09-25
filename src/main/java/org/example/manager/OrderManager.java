package org.example.manager;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;
import org.example.repository.RestaurantDB;
import org.example.utils.Utilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class OrderManager {

    public static void testOrder(RestaurantDB r1){

        ArrayList<Menu> m = new ArrayList<>();
        m.add(r1.getMenus().get("MENU-NIG"));
        m.add(r1.getMenus().get("MENU-NIG"));
        m.add(r1.getMenus().get("MENU-VEG"));
        m.add(r1.getMenus().get("MENU-KID"));

        Order o1 = new Order(new Date(), "Jazz", 4,
                0.0, false, r1.getTables().get("TABLE-01"), null);
        o1.setMenus(m);

        r1.getTables().get("TABLE-01").setBusy(true);

        System.out.println("Total to pay:"+ o1.calculateTotalPayment());
        System.out.println(o1);

        o1.setPaid(true);

        System.out.println(o1);
        r1.getTables().get("TABLE-01").setBusy(false);

        System.out.println("Table status (" +
                r1.getTables().get("TABLE-01").getName() +
                "):" + r1.getTables().get("TABLE-01").isBusy());


    }

    public static void payOrder(){

        //to-do
        //hashMap orders from r1
        //filter by TODAY and NOT-PAY
        //SELECT order from a loop: quit, option and unknown
        //get total payment
        // isPaid to TRUE

    }

    public static boolean createOrder(Scanner scanner, RestaurantDB r1){

        boolean statusOperation  = false;
        // create object
        Order order1 = new Order();

        order1.setDate(new Date());

        // create waiter
        //String waiter = Utilities.ask(scanner, "Waiter? ");
        //order1.setWaiter(waiter);

        // create waiter

        order1.setWaiter(Utilities.ask(scanner, "Waiter? "));

   /*     // people qty
        while (true) {
            String qty = Utilities.ask(scanner, "People qty? ");
            try {
                int qtyInt = Integer.parseInt(qty);
                order1.setPeopleQty(qtyInt);
                break; // Exit the loop if input is valid
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }*/

        order1.setPeopleQty(qtyPeople(scanner));


        // create table
        order1.setTable(selectTable(r1, scanner));
        /*System.out.println("\nSelect table:");

        TableManager.printAvailableTables(r1);
        System.out.println("0 - Take Away");
        String tableSelection = Utilities.ask(scanner, "Table? ");

        if (tableSelection.equals("0")) order1.setTable(null);
        else
            order1.setTable(r1.getTables().get(tableSelection));*/


        // create menus
       /* System.out.println("\nSelect menus:");
        ArrayList<Menu> menus = new ArrayList();
        while(true) {

            System.out.println("0 - Quit");
            r1.getMenus().forEach((key, menu) -> {
                // if menu is active
                System.out.println( key + " - " + menu.getName());
            });

            String option = Utilities.ask(scanner, "Menu? ");
            if (option.equals("0")){ break; }
            else {
                menus.add(r1.getMenus().get(option));
            }

        }*/
        order1.setMenus(selectMenus(r1, scanner));


        // total payment
        order1.setTotalPayment(order1.calculateTotalPayment());

        // create paid
        order1.setPaid(false);


        // saver order to repo
        String uuid = UUID.randomUUID().toString();
        r1.getOrders().put(uuid, order1);
        Order orderSaved = r1.getOrders().get(uuid);

        if (orderSaved != null){
            statusOperation = true;

            System.out.println("\nOrder");
            System.out.println("Order ID: " + uuid);
            System.out.println(orderSaved);

            //r1.getTables().get(tableSelection).setBusy(true);
            //System.out.println(  "\nTable status BUSY(" +  r1.getTables().get(tableSelection).getName() + "):" + r1.getTables().get(tableSelection).isBusy());
            //TableManager.printAvailableTables(r1);
        }

        return statusOperation;
    }

    public static Table selectTable (RestaurantDB r1, Scanner scanner) {
        System.out.println("\nSelect table:");

        TableManager.printAvailableTables(r1);
        System.out.println("0 - Take Away");
        String tableSelection = Utilities.ask(scanner, "Table? ");

        if (tableSelection.equals("0")) {return null;}
        else { return r1.getTables().get(tableSelection);}

    }

    public static ArrayList<Menu> selectMenus (RestaurantDB r1, Scanner scanner) {

        System.out.println("\nSelect menus:");
        ArrayList<Menu> menus = new ArrayList();
        while(true) {

            System.out.println("0 - Quit");
            r1.getMenus().forEach((key, menu) -> {
                // if menu is active
                System.out.println( key + " - " + menu.getName());
            });

            String option = Utilities.ask(scanner, "Menu? ");
            if (option.equals("0")){  break; }
            else {
                menus.add(r1.getMenus().get(option));
            }

        }


        return menus;
    }

    public static int qtyPeople (Scanner scanner) {

        // people qty
        while (true) {
            String qty = Utilities.ask(scanner, "People qty? ");
            try {
                int qtyInt = Integer.parseInt(qty);
                return qtyInt;

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }


    }

}
