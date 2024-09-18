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

    //Calculate the amount to pay with IVA
    public double calculateTotalPayment (){

        double totalPyment = 0.0;
        for (Menu m : this.getMenus()) {
            totalPyment = totalPyment + m.getPrice();
        }

        double totalPymentIVA =  calculateIVA(totalPyment);
        this.setTotalPayment(totalPymentIVA);

        //System.out.println(this);
        return totalPymentIVA;
    }

    public double calculateIVA(double number){
        number = number * 1.21;
        return number;
    }



}