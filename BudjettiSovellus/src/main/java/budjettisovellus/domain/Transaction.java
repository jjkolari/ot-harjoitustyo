/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.domain;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class gives the transaction the needed attributes
 */
public class Transaction {
    int amount;
    Boolean income;
    int balance;
    LocalDate date;
    
    
    public Transaction(int amount, Boolean income, int balance) {
        this.amount = amount;
        this.income = income;
        this.balance = balance;
        this.date = LocalDate.now();
    }
    
    public int getAmount() {
        return amount;
    }
    
    public boolean getIncome() {
        return income;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public LocalDate getDate() {
        return date;
    }
}
