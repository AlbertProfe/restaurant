package org.example;

import org.example.controller.MainController;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("\nInit\n");
        MainController.start();
        System.out.println("\nFinish");
    }
}
