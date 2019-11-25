package budjettisovellus.domain;


public class Balance {
    
    public Integer balance;
    public User user;
    
    public void Balance() {
        this.balance = 0;
    }
    
    public int getBalance() {
        return this.balance;
    }
    
    public void addIncome(Integer income) {
        balance = balance + income;
    }
    
    public void addExpense(Integer expense) {
        balance = balance - expense;
    }
}
