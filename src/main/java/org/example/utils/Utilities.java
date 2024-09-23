package org.example.utils;

import org.example.model.Menu;
import org.example.model.Table;
import org.example.repository.RestaurantDB;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    public static String ask(Scanner scanner, String textToAsk) {

        System.out.println(textToAsk);
        return scanner.nextLine();
    }

    public static void printMenus(RestaurantDB r1, boolean quitOption){
        System.out.println("\nSelect menu:");
        if (quitOption) System.out.println("0 - Quit");
        r1.getMenus().forEach((key, menu) -> {
            // if menu is active
            System.out.println( key + " - " + menu.getName());
        });

    }

    public static void printTables(RestaurantDB r1){
        System.out.println("\nSelect table:");
        System.out.println("0 - Take Away");
        r1.getTables().forEach((key, table) -> {
            // if table is not busy if (table.getName() == false)
            System.out.println( key + " - "+ table.getName());
        });
    }

}
