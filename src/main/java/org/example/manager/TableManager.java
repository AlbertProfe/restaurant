package org.example.manager;


import org.example.model.Table;
import org.example.repository.RestaurantDB;
import java.util.List;
import java.util.Map;

public class TableManager {

    public static void printTestAvailableTables(RestaurantDB r1) {
        // print available tables
        System.out.println("\nAvailable tables:\n");
        // let s fake a busy table
        r1.getTables().get("TABLE-01").setBusy(true);
        // iterate all tables from hashmap to check if they are available
        int i = 1;
        for ( Table t : r1.getTables().values() ){
            if(!t.isBusy()) {
                System.out.println(i + " - " + t.getName());
                i++;
            }
        }
    }

    public static void printAvailableTables(RestaurantDB r1) {
        System.out.println("\nAvailable tables:\n");

        for (Map.Entry<String, Table> entry : r1.getTables().entrySet()) {
            String key = entry.getKey();
            Table table = entry.getValue();
            if (!table.isBusy()) {
                System.out.println(key + " - " + table.getName());
            }


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

    public static Table getOneTable(String id){
        //todo
        return null;
    }
}
