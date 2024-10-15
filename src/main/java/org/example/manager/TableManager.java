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


/*    public static void deleteAll() {
        restaurantDB.getTables().clear();
        System.out.println("All tables have been deleted.");
    }

    public static void createTable(String name, String description, int capacity) {
        String tableId = "TABLE-" + UUID.randomUUID().toString().substring(0, 8);
        Table newTable = new Table(name, description, capacity, false);
        restaurantDB.getTables().put(tableId, newTable);
        System.out.println("New table created with ID: " + tableId);
    }

    public static void updateTable(String tableId, String name, String description, int capacity, boolean isBusy) {
        Table table = restaurantDB.getTables().get(tableId);
        if (table != null) {
            table.setName(name);
            table.setDescription(description);
            table.setCapacity(capacity);
            table.setBusy(isBusy);
            System.out.println("Table updated: " + tableId);
        } else {
            System.out.println("Table not found: " + tableId);
        }
    }

    public static List<Table> getAllTables() {
        return new ArrayList<>(restaurantDB.getTables().values());
    }

    public static Table getOneTable(String id) {
        return restaurantDB.getTables().get(id);
    }

    // Additional utility methods

    public static void printAllTables() {
        System.out.println("\nAll tables:");
        for (Map.Entry<String, Table> entry : restaurantDB.getTables().entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static boolean isTableAvailable(String tableId) {
        Table table = restaurantDB.getTables().get(tableId);
        return table != null && !table.isBusy();
    }

    public static void setTableBusy(String tableId, boolean isBusy) {
        Table table = restaurantDB.getTables().get(tableId);
        if (table != null) {
            table.setBusy(isBusy);
            System.out.println("Table " + tableId + " is now " + (isBusy ? "busy" : "available"));
        } else {
            System.out.println("Table not found: " + tableId);
        }
    }*/
}
