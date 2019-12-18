package budjettisovellus.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class creates the databases needed
 * @author jenni
 */
public class DatabaseDao {
    private String address;
    private Connection conn;
    
    public DatabaseDao(String address) throws SQLException {
        this.address = address;
    }
    
    public Connection getConnection() throws SQLException {
        this.conn = DriverManager.getConnection(address);
        return conn;
    }
    
    public void init() throws SQLException{
        //list.add("CREATE TABLE Transaction (id integer PRIMARY KEY, amount integer, income boolean, currentBalance integer, happened date, FOREIGN KEY (user_id) REFERENCES User(id));");
        try {
            this.conn = getConnection();
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE USER "
                    + "(id INTEGER PRIMARY KEY, "
                    + " username varchar(100), "
                    + " balance INTEGER)";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error >> " + ex.getMessage());        
        }        
    }
    

    
    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
