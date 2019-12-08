package budjettisovellus.domain;

public class Balance {
    
    int balance;
    User user;

    public void Balance() {
        this.balance = 0;
    }

    public int getBalance() {
        return this.balance;
    }

    public boolean addIncome(String income) {

        try {
            Integer.parseInt(income);
            balance = balance + Integer.parseInt(income);
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
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
