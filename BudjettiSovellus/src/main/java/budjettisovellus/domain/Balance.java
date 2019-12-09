package budjettisovellus.domain;

import java.util.ArrayList;
import java.util.List;

public class Balance {
    
    int balance;
    User user;
    List<Transaction> transactions;

    public Balance() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }
    
    
    public int getBalance() {
        return this.balance;
    }

    public boolean addIncome(String income) {

        try {
            int amount = Integer.parseInt(income);
            balance = balance + Integer.parseInt(income);
            Transaction t = new Transaction(amount, true, balance);
            transactions.add(t);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public boolean addExpense(String expense) {

        try {
            int exp = Integer.parseInt(expense);

            if (balance - exp >= 0) {
                balance = balance - exp;
                Transaction t = new Transaction(exp, true, balance);
                transactions.add(t);
                return true;
            }

            return false;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
