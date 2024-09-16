package org.example.model;

import java.util.ArrayList;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Date date;
    private String waiter;
    private int peopleQty;
    private double totalPayment;
    private boolean paid;
    private Table table;
    private ArrayList<Menu> menus;


    public double calculateTotalPayment (){

        // WHAT? Calculate the amount to pay with IVA

        //o2.getMenus()
        //get all bojects from the array and sum up
        //calculate IVA
        // sum value + IVA
        // RETURN


        return 0.0;
    }

    public double calculateIVA(){

        // calculate the iva from the amount

        return 0.0;
    }



}