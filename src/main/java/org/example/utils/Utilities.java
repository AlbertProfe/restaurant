package org.example.utils;

import org.example.model.Menu;
import org.example.model.Table;
import org.example.repository.RestaurantDB;

import java.util.ArrayList;

public class Utilities {


    public static void createTables(RestaurantDB r1){
        // create 5 tables
        Table t1 = new Table("Table 01", "Table type Modern", 4, false);
        Table t2 = new Table("Table 02", "Table type Modern", 2, false);
        Table t3 = new Table("Table 03", "Table type Modern", 2, false);
        Table t4 = new Table("Table 04", "Table type Modern", 4, false);
        Table t5 = new Table("Table 05", "Table type Modern", 4, false);
        //save tables to r1.tables HashMap tables

        r1.getTables().put("T1", t1);

    }


    public static void createFakeMenus(){
        Menu m1 = new Menu("Menu Night", 8.5, "", true, true);
        Menu m2 = new Menu("Menu Vegan", 10.5, "", true, true);
        Menu m3 = new Menu("Menu Kids", 12.5, "", true, true);
        // save to HashMap ...




    }

}
