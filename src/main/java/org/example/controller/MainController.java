package org.example.controller;

import org.example.repository.RestaurantDB;
import org.example.utils.Utilities;
import org.example.view.MainDisplay;

import java.util.Scanner;

public class MainController {

    public static void start() {

        Scanner scanner = new Scanner(System.in);

        RestaurantDB r1 = new RestaurantDB();

        Utilities.createFakeMenus(r1);
        Utilities.createFakeTables(r1);

        while(true){

            MainDisplay.ShowMainMenu();
            String option = Utilities.ask(scanner, "Option? ");

            if(option.equals("0")) {
                break;
            } else if (option.equals("1")){
                Utilities.testOrder(r1);
            } else if(option.equals("2")){
                Utilities.printStatusTable(r1);
            } else{
                System.out.println("Unknown word");
            }
        }

    }
}
