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
    // calculate IVA with 21%
    public double calculateIVA(double number){
        double iva = 0.21;
        number = number * (1.0 + iva);
        return number;
    }


    @Override
    public String toString() {
        return
                "date: " + date + "\n"  +
                "waiter: " + waiter + '\'' + "\n"  +
                "peopleQty: " + peopleQty +"\n"  +
                "totalPayment: " + totalPayment +" euros\n"  +
                "paid: " + paid +"\n"  +
                "table: " + table +"\n"  +
                "Menus quantity: " + menus.size() +"\n"  +
                "menus: " + menus
                ;
    }

}