package budjettisovellus.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import budjettisovellus.dao.DatabaseDao;
import budjettisovellus.dao.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jenni
 */
public class BudgetServiceTest {
    
    static DatabaseDao db;
    BudgetService budgetService;
    UserDao userDao;
    User loggedinUser;
    
    
    @Before
    public void setUp() throws SQLException {
        db = new DatabaseDao("jdbc:sqlite:./BudjettiSovellus.db");
        db.init();
        
        budgetService = new BudgetService(db);
        userDao = budgetService.getUserDao();
        
        budgetService.createUser("test_user");
        loggedinUser = userDao.findByUsername("test_user");
    }
    
    @After
    public void tearDown() {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM User WHERE "
                    + "username = 'test_user'");
            stmt.executeUpdate();
            stmt.close();
            PreparedStatement stmtForReg = conn.prepareStatement("DELETE FROM User "
                    + "WHERE username = 'test'");
            stmtForReg.executeUpdate();
            stmtForReg.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    @Test
    public void registerationWorks() throws SQLException {
        boolean registeration = budgetService.createUser("test");
        assertEquals(true, registeration);
    }
    
    @Test
    public void loginWorks() throws SQLException {
        boolean login = budgetService.login("test_user");
        assertEquals(true, login);
    }

    @Test
    public void registerationWithOldUserDoesntWork() throws SQLException {
        boolean registeration = budgetService.createUser("test_user");
        assertEquals(false, registeration);
    }
    
    @Test
    public void incomeAddWorks() throws SQLException {
        budgetService.addIncome("100");
        assertEquals(100, budgetService.getBalance());
    }
    
    @Test
    public void addIncomeWorksRight() throws SQLException {
        budgetService.addIncome("should not add anything");
        assertEquals(0, budgetService.getBalance());
    }
    
    @Test
    public void addExpenseWorks() throws SQLException {
        budgetService.addExpense("20");
        assertEquals(-20, budgetService.getBalance());
    }
    
    @Test
    public void addExpenseWorksRight() throws SQLException {
        budgetService.addExpense("should not add anything");
        assertEquals(0, budgetService.getBalance());
    }
    
    @Test
    public void multipleTransactionsWorks() throws SQLException {
        budgetService.addIncome("100");
        budgetService.addExpense("20");
        budgetService.addExpense("30");
        budgetService.addIncome("100");
        budgetService.addIncome("10");
        assertEquals(160, budgetService.getBalance());
    }
    
}
