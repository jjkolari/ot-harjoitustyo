package budjettisovellus.dao;

import java.sql.*;

/**
 * Class creates the databases needed
 * @author jenni
 */
public class DatabaseDao {
    private String address;
    private Connection conn;
    
    /**
     * sets the address for database given in start
     * @param address address for database
     * @throws SQLException 
     */
    public DatabaseDao(String address) throws SQLException {
        this.address = address;
    }
    
    /**
     * connects to database
     * @return connection to database
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        this.conn = DriverManager.getConnection(address);
        return conn;
    }
    
    /**
     * creates database for users
     * @throws SQLException 
     */
    public void init() throws SQLException{
        try {
            this.conn = getConnection();
            Statement stmt = conn.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS USER "
                    + "(id INTEGER PRIMARY KEY, "
                    + " username VARCHAR(100), "
                    + " balance INTEGER);";
            stmt.executeUpdate(sql);
            
            String sql2 = "CREATE TABLE IF NOT EXISTS TRANSACT "
                    + "(id INTEGER PRIMARY KEY, "
                    + " amount integer, "
                    + " income boolean, "
                    + " currentBalance integer, "
                    + " date date, "
                    + " user_username varchar(200),"
                    + " FOREIGN KEY (user_username) REFERENCES User(username));";
            stmt.executeUpdate(sql2);
            
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error >> " + ex.getMessage());        
        }        
    }
    

    /**
     * Closes connection to database, must be done everytime after
     * connection to database
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
