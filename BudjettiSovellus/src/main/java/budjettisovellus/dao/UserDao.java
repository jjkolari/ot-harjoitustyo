/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.dao;

import budjettisovellus.domain.User;
import java.sql.*;

public class UserDao {
    
    private DatabaseDao db;
    private Integer id;
    
    public UserDao(DatabaseDao databaseDao) {
        this.db = databaseDao;
        this.id = 0;
    }
    
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
    
    public User createNewUser(String username) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User (id, username, balance) VALUES (?, ?, 0)");
        stmt.setInt(1, id);
        id++;
        stmt.setString(2, username);

        stmt.executeUpdate();
        stmt.close();
        db.closeConnection();
        return this.findByUsername(username);
    }
    
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
    
}
