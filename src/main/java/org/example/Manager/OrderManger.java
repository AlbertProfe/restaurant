package org.example.Manager;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;
import org.example.repository.RestaurantDB;
import org.example.utils.Utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static org.example.Manager.TableManager.printAvailableTables;

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

    private static Table selectOrderingTable(Scanner scanner, RestaurantDB r1){
        ArrayList<Table> tables = TableManager.getAvailableTables(r1);
        TableManager.printAvailableTables(r1);
        int tableSelected = Integer.valueOf(Utilities.ask(scanner, "Which table? "));
        return tables.get(tableSelected-1);
    }

    private static Menu selectOneMenu(Scanner scanner, RestaurantDB r1){
        ArrayList<Menu> menus = MenuManager.getAllMenus(r1);
        System.out.println("\nSelect menu:\n");
        int i = 1;
        for (Menu m:menus){
            System.out.println( i + " - " + m.getName());
            i++;
        }
        int menuSelected = Integer.valueOf(Utilities.ask(scanner, "Which menu? "));
        return menus.get(menuSelected-1);
    }

    public static boolean createOrder(Scanner scanner, RestaurantDB r1){
//        private Date date;
//        private String waiter;
//        private int peopleQty;
//        private double totalPayment;
//        private boolean paid;
//        private Table table;
//        private ArrayList<Menu> menus;

        String waiter = Utilities.ask(scanner, "Waiter's name? ");
        // TODO catch error
        int people = Integer.valueOf(Utilities.ask(scanner, "How many people? "));
        // TODO catch error
        Table tableSelected = selectOrderingTable(scanner, r1);
        ArrayList<Menu> menusSelected = new ArrayList<>();
        for( int i = 0; i < people; i++ ){
            // TODO catch error
            menusSelected.add(selectOneMenu(scanner, r1));
        }

        Order order = new Order();
        order.setDate(new Date());
        order.setWaiter("Pepe");
        order.setPeopleQty(people);
        order.setTable(tableSelected);
        order.setMenus(menusSelected);
        order.setTotalPayment(order.getTotalPayment());
        tableSelected.setBusy(true);


        return true;
    }
}
