package org.example.controller;

import org.example.manager.OrderManger;
import org.example.manager.TableManager;
import org.example.repository.RestaurantDB;
import org.example.utils.Utilities;
import org.example.view.MainDisplay;
import java.util.Scanner;

public class MainController {

    public static void start() {

        Scanner scanner = new Scanner(System.in);

        RestaurantDB r1 = new RestaurantDB();
        r1.setName("Plaça Catalunya Restaurant");
        r1.setSize(10);

        Utilities.createFakeMenus(r1);
        Utilities.createFakeTables(r1);

        while(true){

            MainDisplay.ShowMainMenu();
            String option = Utilities.ask(scanner, "Option? ");

            if (option.equals("0")) {
                break;
            } else if (option.equals("1")){
                //OrderManger.testOrder(r1);
                OrderManger.createOrder(scanner, r1);
            } else if (option.equals("2")){
                TableManager.printAvailableTables(r1);
            } else if (option.equals("3")){
               OrderManger.payOrder();
            } else if (option.equals("4")){
              AdminController.start(scanner);
            } else {
                System.out.println("Unknown word");
            }
        }

    }
}
