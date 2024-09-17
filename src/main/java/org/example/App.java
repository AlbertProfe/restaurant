package org.example;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println("\nInit");

        //create RestaurantDB r1;
        sandboxCreateObjects();
        //o1.calculateIVA();
        System.out.println("\nFinish");
    }

    public static void sandboxCreateObjects (){

        // create 5 tables
        Table t1 = new Table("Table 01", "Table type Modern", 4, false);
        Table t2 = new Table("Table 02", "Table type Modern", 2, false);
        Table t3 = new Table("Table 03", "Table type Modern", 2, false);
        Table t4 = new Table("Table 04", "Table type Modern", 4, false);
        Table t5 = new Table("Table 05", "Table type Modern", 4, false);
        //save tables to r1.tables HashMap tables

        Menu m1 = new Menu("Menu Night", 8.5, "", true, true);
        Menu m2 = new Menu("Menu Vegan", 10.5, "", true, true);
        Menu m3 = new Menu("Menu Kids", 12.5, "", true, true);
       // save to HashMap ...



        ArrayList<Menu> m = new ArrayList<>();
        m.add(m1);
        m.add(m1);
        m.add(m2);
        m.add(m3);;


        Order o1 = new Order(new Date(), "Jazz", 20, 0.0, false, t1, null);
        o1.setMenus(m);

        System.out.println("Total to pay:"+ o1.calculateTotalPayment());
        System.out.println(o1);

        System.out.println("");



        //HashMap<String, Menu> menus = new HashMap<String, Menu>();

        //menus.put("M101", m1);


    }


}
