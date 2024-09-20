package org.example.Manager;

import org.example.model.Menu;
import org.example.repository.RestaurantDB;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {


    public static void deleteAll(){
        //todo
    }

    public static void createMenu(){
        //todo
    }

    public static void updateMenu(){
        //todo
    }

    public static ArrayList<Menu> getAllMenus(RestaurantDB r1){
        ArrayList<Menu> menus = new ArrayList<>();
        menus.addAll(r1.getMenus().values());
//        for(Menu m: r1.getMenus().values() ){
//            menus.add(m);
//        }
        return menus;
    }

    public static Menu getOneMenu(String id){
        //todo
        return null;
    }
}
