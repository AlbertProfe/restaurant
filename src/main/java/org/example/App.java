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
        System.out.println("\nInit\n");

        RestaurantDB r1 = new RestaurantDB();

        //create RestaurantDB r1;
        Utilities.createFakeMenus(r1);
        Utilities.createFakeTables(r1);

        Utilities.testOrder(r1);
        Utilities.printStatusTable(r1);

        System.out.println("\nFinish");
    }








}
