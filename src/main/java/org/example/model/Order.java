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
    public String toString(){
        String out = "ORDER id=TODO";
        out += "\n\t" + "date: " + this.date;
        out += "\n\t" + "waiter: " + this.waiter;
        out += "\n\t" + "people: " + this.peopleQty;
        out += "\n\t" + "amount: " + this.totalPayment;
        out += "\n\t" + "is paid: " + this.paid;
        out += "\n\t" + "table: " + this.table;
        out += "\n\t" + "menus:";
        for(Menu menu:this.menus){
            out += "\n\t\t" + menu.getName() + ": " + menu.getPrice();
        }
//        // TODO variable used in lambda expression should be final
//        this.menus.forEach(menu -> {
//            out = out + ("\n\t\t" + menu.getName() + ": " + menu.getPrice())
//        });
        return out;
    }

}