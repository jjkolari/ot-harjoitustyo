/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.domain;

import budjettisovellus.dao.DatabaseDao;
import budjettisovellus.dao.TransactDao;
import budjettisovellus.dao.UserDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * the application logic class
 */
public class BudgetService {

    private User userLoggedIn;
    private UserDao userDao;
    private TransactDao transactDao;

    private int balance;
    private List<Transaction> transactions;

    public BudgetService(DatabaseDao database) throws SQLException {
        this.userDao = new UserDao(database);
        this.transactDao = new TransactDao(database);
    }

    /**
     * Creates new user
     *
     * @param username Submitted username
     * @return
     */
    public boolean createUser(String username) throws SQLException {

        if (userDao.findByUsername(username) != null) {
            return false;
        }

        try {
            userDao.createNewUser(username);
        } catch (Exception e) {
            return false;
        }

        userLoggedIn = userDao.findByUsername(username);
        this.balance = getBalanceFromDatabase();
        return true;
    }

    /**
     * Logs in user with given username
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean login(String username) throws SQLException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }

        userLoggedIn = user;
        this.balance = getBalanceFromDatabase();

        return true;
    }

    public boolean addIncome(String income) throws SQLException {

        try {
            int amount = Integer.parseInt(income);
            this.balance = this.balance + amount;
            Transaction t = new Transaction(amount, true, getBalance() + amount);
            transactions.add(t);
            try {
                transactDao.createNewTransact(t, this.userLoggedIn);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public boolean addExpense(String expense) throws SQLException {

        try {
            int amount = Integer.parseInt(expense);
            this.balance = this.balance - amount;
            Transaction t = new Transaction(amount, false, getBalance() - amount);
            transactions.add(t);
            try {
                transactDao.createNewTransact(t, this.userLoggedIn);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }

    public int getBalance() {
        return this.balance;
    }

    /**
     *
     * @return
     */
    private int getBalanceFromDatabase() throws SQLException {
        this.transactions = getAllTransactions();
        if (this.transactions.isEmpty()) {
            return 0;
        }

        return this.transactions.get(this.transactions.size() - 1).getBalance();
    }
    
    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        try {
            return transactDao.getAll(userLoggedIn);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public UserDao getUserDao() {
        return this.userDao;
    }
    
    public TransactDao getTransactDao() {
        return this.transactDao;
    }

}
