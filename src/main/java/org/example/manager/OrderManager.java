package org.example.manager;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;
import org.example.repository.RestaurantDB;
import org.example.utils.Utilities;

import java.util.*;

public class OrderManager {

    // test order from r1
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

        System.out.println("Total to pay:"+ calculateTotalPayment(o1));
        System.out.println(o1);

        o1.setPaid(true);

        System.out.println(o1);
        r1.getTables().get("TABLE-01").setBusy(false);

        System.out.println("Table status (" +
                r1.getTables().get("TABLE-01").getName() +
                "):" + r1.getTables().get("TABLE-01").isBusy());


    }

    // pay order from r1
    public static void payOrder(){

        //to-do
        //hashMap orders from r1
        //filter by TODAY and NOT-PAY
        //SELECT order from a loop: quit, option and unknown
        //get total payment
        // isPaid to TRUE

    }

    // create order with menus and tables from r1
    public static boolean createOrder(Scanner scanner, RestaurantDB r1){

        boolean statusOperation  = false;
        // create object
        Order order1 = new Order();

        // create date
        Date date = new Date();
        order1.setDate(date);

        // create waiter
        String waiter = Utilities.ask(scanner, "Waiter? ");
        order1.setWaiter(waiter);

        // people qty
        int qty = Utilities.askForPositiveInteger(scanner, "People qty? ");
        order1.setPeopleQty(qty);

        // select table
        Table table = selectTable(r1, scanner);
        order1.setTable(table);

        // select menus
        ArrayList<Menu> menus = selectMenus(r1, scanner);
        order1.setMenus(menus);

        // total payment
        double totalPayment = OrderManager.calculateTotalPayment(order1);
        order1.setTotalPayment(totalPayment);

        // create paid
        order1.setPaid(false);

        // save order to repo
        String uuid = UUID.randomUUID().toString();
        r1.getOrders().put(uuid, order1);
        Order orderSaved = r1.getOrders().get(uuid);

        // if order is saved print and update table
        if (orderSaved != null){
            statusOperation = true;

            System.out.println("\nOrder");
            System.out.println("Order ID: " + uuid);
            System.out.println(orderSaved);

            if (table != null) {
                table.setBusy(true);
                System.out.println("\nTable status BUSY (" + table.getName() + "): " + table.isBusy());
            }
        }

        return statusOperation;
    }

    //Calculate the amount to pay with IVA
    public static double calculateTotalPayment (Order order){

        double totalPyment = 0.0;
        for (Menu m : order.getMenus()) {
            totalPyment = totalPyment + m.getPrice();
        }

        double totalPymentIVA =  calculateIVA(totalPyment);
        order.setTotalPayment(totalPymentIVA);

        //System.out.println(this);
        return totalPymentIVA;
    }

    // calculate IVA with 21%
    public static double calculateIVA(double number){
        double iva = 0.21;
        number = number * (1.0 + iva);
        return number;
    }

    // select table from r1 and return table or null
    public static Table selectTable(RestaurantDB r1, Scanner scanner){
        // select table
        System.out.println("\nSelect table:");

        TableManager.printAvailableTables(r1);
        System.out.println("0 - Take Away");
        String tableSelection;
        Table table = null;

        while (true) {
            // ask for table selection or take away
            tableSelection = Utilities.ask(scanner, "Table? ");
            if (tableSelection.equals("0")) {
                // no table, take away
                break;
            } else if (r1.getTables().containsKey(tableSelection)) {
                // table exists
                table = r1.getTables().get(tableSelection);
                break;
            } else {
                System.out.println("Invalid table selection. Please try again.");
            }
        }
        return table;
    }

    // select menus from r1 and return menus
    public static ArrayList<Menu> selectMenus(RestaurantDB r1, Scanner scanner) {
        System.out.println("\nSelect menus:");
        ArrayList<Menu> menus = new ArrayList<>();
        while (true) {
            System.out.println("0 - Quit");
            r1.getMenus().forEach((key, menu) -> {
                // if menu is active
                System.out.println(key + " - " + menu.getName());
            });

            String menuSelection = Utilities.ask(scanner, "Menu? ");
            if (menuSelection.equals("0")) {
                break;
            } else if (r1.getMenus().containsKey(menuSelection)) {
                menus.add(r1.getMenus().get(menuSelection));
            } else {
                System.out.println("Invalid menu selection. Please try again.");
            }
        }
        return menus;
    }
}
