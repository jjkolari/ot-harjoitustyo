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
    
    public boolean addExpense(Integer expense) {
        if (balance - expense >= 0) {
            balance = balance - expense;
            return true;
        }
        return false;
    }
}
