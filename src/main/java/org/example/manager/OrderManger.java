package org.example.manager;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;
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

        System.out.println("Total to pay:"+ calculateTotalPayment(o1));
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
        order1.setDate(new Date());
        // create waiter
        order1.setWaiter(Utilities.ask(scanner, "Waiter? ").toUpperCase());
        // people qty
        order1.setPeopleQty(peopleQtyInput(scanner));
        // table selection
        order1.setTable(tableSelection(scanner, r1));
        // create menus
        order1.setMenus(menuSelection(scanner, r1));
        // total payment
        order1.setTotalPayment(calculateTotalPayment(order1));
        // setPaid
        order1.setPaid(isPaid(scanner));
        // saver order to repo TODO
        r1.getOrders().put("OR-001", order1);
        System.out.println("\nOrder");
        System.out.println(order1);
        System.out.println("Order saved");

        return false;
    }
    private static int peopleQtyInput(Scanner scanner) {
        boolean inputOk = false;
        int peopleQty = 0;
        while (!inputOk) {
            String qty = Utilities.ask(scanner, "People qty? ");
            // while qty is not a number
            try {
                peopleQty = Integer.parseInt(qty);
                if (peopleQty < 20 && peopleQty > 0) {
                    inputOk = true;
                }else{
                    System.out.println("Please enter a number between 1 and 20");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number");
            }
        }
        return peopleQty;
    }
    private static Table tableSelection(Scanner scanner, RestaurantDB r1) {
        Table tableSelected = null;
        System.out.println("\nSelect table:");
        System.out.println("0 - Take Away");

        for (String tableKey : r1.getTables().keySet()) {
            Table t = r1.getTables().get(tableKey);
            if (!t.isBusy()) {
                String tableNumber = tableKey.replace("TABLE-0", "");
                System.out.println(tableNumber + " - " + t.getName());
            }
        }
        try {
            int tableSelectionNum = Integer.parseInt(Utilities.ask(scanner, "Table? "));

            if (tableSelectionNum == 0) {
                tableSelected = null;
            } else {
                String tableKey = "TABLE-0" + tableSelectionNum;
                tableSelected = r1.getTables().getOrDefault(tableKey, null);

                if (tableSelected == null) {
                    System.out.println("Invalid table number.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        tableSelected.setBusy(true);

        return tableSelected;
    }
    private static ArrayList<Menu> menuSelection(Scanner scanner, RestaurantDB r1) {
        ArrayList<Menu> menus = new ArrayList<>();
        while (true) {
            System.out.println("\nSelect menu:");
            boolean found = false;
            System.out.println("0 - Finish");

            for (String fullMenuKey : r1.getMenus().keySet()) {
                String menuLetter = fullMenuKey.replace("MENU-", "").substring(0, 1).toUpperCase();
                System.out.println(menuLetter + " - " + fullMenuKey + " - " + r1.getMenus().get(fullMenuKey).getName());
            }

            String menuSelection = Utilities.ask(scanner, "Menu? (ENTER THE LETTER OR 0)").toUpperCase();

            if (menuSelection.equals("0")) {
                break;
            }

            for (String fullMenuKey : r1.getMenus().keySet()) {
                if (fullMenuKey.replace("MENU-", "").toUpperCase().startsWith(menuSelection)) {
                    Menu menu = r1.getMenus().get(fullMenuKey);
                    menus.add(menu);
                    System.out.println(menu.getName() + " added to your selection.");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("Invalid menu selection. Please try again.");
            }
        }
        return menus;
    }
    private static double calculateTotalPayment(Order order1){

        double totalPyment = 0.0;
        for (Menu m : order1.getMenus()) {
            totalPyment = totalPyment + m.getPrice();
        }

        double totalPymentIVA =  calculateIVA(totalPyment);
        order1.setTotalPayment(totalPymentIVA);

        //System.out.println(this);
        return totalPymentIVA;
    }
    private static double calculateIVA(double number){  //Calculate IVA 21%
        double iva = 0.21;
        number = number * (1.0 + iva);
        return number;
    }
    private static boolean isPaid(Scanner scanner){
        System.out.println("Is paid?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        int option = Integer.parseInt(Utilities.ask(scanner, "Option? "));
        if(option == 1){
            return true;
        }else{
            return false;
        }
    }
}
