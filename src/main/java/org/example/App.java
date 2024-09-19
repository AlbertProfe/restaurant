package org.example;

import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;
import org.example.repository.RestaurantDB;
<<<<<<< HEAD
=======
import org.example.utils.Utilities;
>>>>>>> master

import java.util.ArrayList;
import java.util.Date;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println("\nInit\n");

        RestaurantDB r1 = new RestaurantDB();

<<<<<<< HEAD
        //create RestaurantDB r1; Done.
        RestaurantDB r1 = new RestaurantDB();
        /*
        * Although We need to create the hashmaps objects that will be binding r1 hashmaps properties.
        * */
        sandboxCreateObjects();
        //o1.calculateIVA();
=======
        //create RestaurantDB r1;
        Utilities.createFakeMenus(r1);
        Utilities.createFakeTables(r1);

        Utilities.testOrder(r1);
        Utilities.printStatusTable(r1);

>>>>>>> master
        System.out.println("\nFinish");
    }








}
