package com.abiodunajibike.app.expensestracker;

/**
 * Created by AJ on 13/05/2016.
 */
public class Expense {

    String description, date;
    double amount;

    public Expense(String description, double amount, String date){
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDescription(){
        return this.description;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getDate(){
        return this.date;
    }
}
