package org.example.utils;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;
import org.example.repository.RestaurantDB;

import java.util.ArrayList;
import java.util.Date;

public class Utilities {

    public static void createFakeTables(RestaurantDB r1){
        // create 5 tables
        Table t1 = new Table("Table 01", "Table type Modern", 4, false);
        Table t2 = new Table("Table 02", "Table type Modern", 2, false);
        Table t3 = new Table("Table 03", "Table type Modern", 2, false);
        Table t4 = new Table("Table 04", "Table type Modern", 4, false);
        Table t5 = new Table("Table 05", "Table type Modern", 4, false);
        //save tables to r1.tables HashMap tables

        r1.getTables().put("TABLE-01", t1);
        r1.getTables().put("TABLE-02", t2);
        r1.getTables().put("TABLE-03", t3);
        r1.getTables().put("TABLE-04", t4);
        r1.getTables().put("TABLE-05", t5);

    }

    public static void createFakeMenus(RestaurantDB r1){
        Menu m1 = new Menu("Menu Night", 8.5, "", true, true);
        Menu m2 = new Menu("Menu Vegan", 10.5, "", true, true);
        Menu m3 = new Menu("Menu Kids", 12.5, "", true, true);

        r1.getMenus().put("MENU-NIG", m1);
        r1.getMenus().put("MENU-VEG", m2);
        r1.getMenus().put("MENU-KID", m3);

    }

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

    public static void printStatusTable(RestaurantDB r1) {

        System.out.println("\n\nAvailable tables:\n");
        r1.getTables().get("TABLE-01").setBusy(true);
        for ( Table t : r1.getTables().values() ){

            if(!t.isBusy()) {
                System.out.println(t.getName());
            }
        }
        //for each tables hash
        // if table BUSY is available  PRINT
        //



    }

}
