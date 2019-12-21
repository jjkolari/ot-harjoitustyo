/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.dao;

import budjettisovellus.domain.Transaction;
import budjettisovellus.domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles transactions database
 */
public class TransactDao {
    
    private DatabaseDao db;
    
    /**
     * Inits the wanted database
     * @param databaseDao 
     */
    public TransactDao(DatabaseDao databaseDao) {
        this.db = databaseDao;
    }
    
    /**
     * Method creates new transaction for user's database
     * @param t Transaction
     * @param u User
     * @throws SQLException 
     */
    public void createNewTransact(Transaction t, User u) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection
                .prepareStatement("INSERT INTO Transact "
                        + "(amount, income, currentBalance, date, user_username)"
                        + " VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, t.getAmount());
        stmt.setBoolean(2, t.getIncome());
        stmt.setInt(3, t.getBalance());
        stmt.setDate(4, Date.valueOf(t.getDate()));
        stmt.setString(5, u.getUsername());

        stmt.executeUpdate();
        stmt.close();
        db.closeConnection();
    }
    
    
    public List<Transaction> getAll(User user) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection
                .prepareStatement("SELECT * FROM Transact "
                + "WHERE user_username = ?");
        stmt.setString(1, user.getUsername());
        
        ResultSet rs = stmt.executeQuery();
        
        List<Transaction> transactions = new ArrayList<>();
        while (rs.next()) {
            transactions.add(new Transaction(rs.getInt("amount"), rs.getBoolean("income"), rs.getInt("currentBalance"), rs.getDate("date")));
        }
        
        stmt.executeUpdate();
        stmt.close();
        db.closeConnection();
        
        return transactions;
    }
    
    
}
