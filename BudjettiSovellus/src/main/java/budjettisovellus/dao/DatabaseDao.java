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
    

    /**
     * Closes connection to database, must be done everytime after
     * connection to database
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
