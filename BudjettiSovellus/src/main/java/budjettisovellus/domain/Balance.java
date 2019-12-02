package budjettisovellus.domain;


public class Balance {
    
    public int balance;
    public User user;
    
    public void Balance() {
        this.balance = 0;
    }
    
    public int getBalance() {
        return this.balance;
    }
    
    public void addIncome(int income) {
        balance = balance + income;
    }
    
    public boolean addExpense(int expense) {
        if (balance - expense >= 0) {
            balance = balance - expense;
            return true;
        }
        return false;
    }
}
