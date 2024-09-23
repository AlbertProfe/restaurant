package org.example.manager;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.repository.RestaurantDB;
import org.example.utils.Utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class OrderManger {

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

        // create object
        Order order1 = new Order();

        // create date
        Date date = new Date();
        order1.setDate(date);

        // create waiter
        String waiter = Utilities.ask(scanner, "Waiter? ");
        order1.setWaiter(waiter);

        // people qty
        String qty = Utilities.ask(scanner, "People qty? ");
        try{
            int qtyInt = Integer.parseInt(qty);
            order1.setPeopleQty(qtyInt);
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }

        // create table
        System.out.println("\nSelect table:");
        System.out.println("0 - Take Away");
        r1.getTables().forEach((key, table) -> {
            // if table is not busy if (table.getName() == false)
            System.out.println( key + " - "+ table.getName());
        });
        String tableSelection = Utilities.ask(scanner, "Table? ");

        if (tableSelection.equals("0")) order1.setTable(null);
        else
            order1.setTable(r1.getTables().get(tableSelection));


        // create menus
        System.out.println("\nSelect menus:");
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

        }
        order1.setMenus(menus);


        // total payment
        double totalPayment = order1.calculateTotalPayment();
        order1.setTotalPayment(totalPayment);

        // create paid
        order1.setPaid(false);

        // saver order to repo
        r1.getOrders().put("OR-001", order1);

        System.out.println("\nOrder");
        System.out.println(order1);


        return false;
    }
}
