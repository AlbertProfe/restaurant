package org.example.Manager;

import org.example.model.Table;
import org.example.repository.RestaurantDB;

public class TableManager {

    public static void printStatusTable(RestaurantDB r1) {
        // print available tables
        System.out.println("\n\nAvailable tables:\n");
        // let s fake a busy table
        r1.getTables().get("TABLE-01").setBusy(true);
        // iterate all tables from hashmap to check if they are available
        for ( Table t : r1.getTables().values() ){
            if(!t.isBusy()) {
                System.out.println(t.getName());
            }
        }
    }
}
