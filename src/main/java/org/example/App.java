package org.example;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;

import java.util.ArrayList;
import java.util.Date;


public class App 
{
    public static void main( String[] args )
    {


        Table t1 = new Table("Table 01", "Table type Modern", 4, false);
        Table t2 = new Table();
        Table t3 = new Table();
        Table t4 = new Table();
        Table t5 = new Table();

        Menu m1 = new Menu("Menu Night", 8.5, "", true, true);
        Menu m2 = new Menu();
        m2.setName("Menu Vegan");
        m2.setWater(false);
        m2.setActive(true);
        m2.setPrice(8.5);

        Menu m3 = new Menu();
        Menu m4 = new Menu();

        Order o1 = new Order(new Date(), "Jazz", 20, 200.0, false, t1, null);

        ArrayList<Menu> m = new ArrayList<>();
        m.add(m1);
        m.add(m2);
        m.add(m3);

        o1.setMenus(m);

        Order o2 = new Order(new Date(), "Toni", 3, 0.0, false,
                new Table("Table 01", "Table type Modern", 4, false) ,m);


        System.out.printf("table from o2: " +  o2.getTable() );


        System.out.println("\nHello world!");

    }
}
