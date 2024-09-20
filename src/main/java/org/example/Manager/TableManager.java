package org.example.Manager;


import org.example.model.Table;
import org.example.repository.RestaurantDB;

import java.util.ArrayList;
import java.util.List;

public class TableManager {

    public static void printAvailableTables(RestaurantDB r1) {
        // print available tables
        System.out.println("\nAvailable tables:\n");
        int i = 1;
        for (Table t:getAvailableTables(r1)){
            System.out.println( i + " - " + t.getName());
            i++;
        }
    }

    public static void deleteAll(){
        //todo
    }

    public static void createTable(){
        //todo
    }

    public static void updateTable(){
        //todo
    }

    public static List<Table> getAllTables(){
        //todo
        return null;
    }

    public static ArrayList<Table> getAvailableTables(RestaurantDB r1){
        ArrayList<Table> availableTables = new ArrayList<>();
        // let s fake a busy table
        r1.getTables().get("TABLE-01").setBusy(true);
        // iterate all tables from hashmap to check if they are available
        for ( Table t : r1.getTables().values() ){
            if(!t.isBusy()) {
                availableTables.add(t);
            }
        }
        return availableTables;
    }


    public static Table getOneTable(String id){
        //todo
        return null;
    }
}
