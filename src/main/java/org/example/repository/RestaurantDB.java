package org.example.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Menu;
import org.example.model.Order;
import org.example.model.Table;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDB {

    private String name;
    private int size;
    private HashMap<String, Menu> menus = new HashMap<>();
    private HashMap<String, Order> orders =  new HashMap<>();
    private HashMap<String, Table> tables = new HashMap<>();

}
