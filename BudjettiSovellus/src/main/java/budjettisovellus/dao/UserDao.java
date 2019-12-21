/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.dao;

import budjettisovellus.domain.User;
import java.sql.*;

/**
 * Handles User database
 */
public class UserDao {
    
    private DatabaseDao db;
    
    /**
     * Inits the wanted database
     * @param databaseDao 
     */
    public UserDao(DatabaseDao databaseDao) {
        this.db = databaseDao;
    }
    
    /**
     * Finds a User from database with its id
     * @param id users given id 0...
     * @return User finded
     * @throws SQLException 
     */
    public User findOne(int id) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        String username = rs.getString("username");

        rs.close();
        stmt.close();
        connection.close();

        User u = new User(id, username);
        return u;
    }
    
    /**
     * Creates/registers new user to database
     * @param username given users wanted username
     * @return created user
     * @throws SQLException 
     */
    public User createNewUser(String username) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User (username) VALUES (?)");
        stmt.setString(1, username);

        stmt.executeUpdate();
        stmt.close();
        db.closeConnection();
        return this.findByUsername(username);
    }
    
    /**
     * Find user by username, used in login
     * @param username login
     * @return found user
     * @throws SQLException 
     */
    public User findByUsername(String username) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        int id = rs.getInt("id");
        rs.close();
        stmt.close();
        db.closeConnection();

        User u = new User(id, username);
        return u;
    }
    
    public User updateBalance(String username) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        int id = rs.getInt("id");
        rs.close();
        stmt.close();
        db.closeConnection();

        User u = new User(id, username);
        return u;
    }
    
}
