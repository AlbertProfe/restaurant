package org.example.controller;

import org.example.utils.Utilities;
import org.example.view.MainDisplay;

import java.util.Scanner;

public class AdminController {


    public static void start(Scanner scanner) {


        while (true){

            MainDisplay.ShowAdminMenu();
            String option = Utilities.ask(scanner, "Option? ");

            if (option.equals("0")) {
                break;
            } else if (option.equals("1")){
                MenuController.start(scanner);
            } else if (option.equals("2")){
                TableController.start(scanner);
            } else {
                System.out.println("Word unknown");
            }
        }
    }
}
