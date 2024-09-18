package org.example;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;
import org.example.repository.RestaurantDB;
import org.example.utils.Utilities;

import java.util.ArrayList;
import java.util.Date;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println("\nInit");

        RestaurantDB r1 = new RestaurantDB();

        //create RestaurantDB r1;
        Utilities.createFakeMenus();
        Utilities.createTables(r1);

        testOrder(r1);


        System.out.println("\nFinish");
    }

    public static void testOrder(RestaurantDB r1){

        ArrayList<Menu> m = new ArrayList<>();
       // m.add(m1);
       // m.add(m1);
       // m.add(m2);
       // m.add(m3);;
        Order o1 = new Order(new Date(), "Jazz", 20,
                0.0, false, r1.getTables().get("T1"), null);
        o1.setMenus(m);

        System.out.println("Total to pay:"+ o1.calculateTotalPayment());
        System.out.println(o1);

        System.out.println("");



    }






}
