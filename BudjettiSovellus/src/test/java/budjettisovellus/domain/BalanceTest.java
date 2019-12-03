/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.domain;

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
public class BalanceTest {
    
    Balance balance;
    
    @Before
    public void setUp() {
        balance = new Balance();
    }
    
    
    @Test
    public void testGetBalance() {
        assertEquals(balance.getBalance(), 0);
    }
    
    @Test
    public void testAddIncome() {
        balance.addIncome("100");
        assertEquals(balance.getBalance(), 100);
    }
    
    @Test
    public void testAddExpense() {
        balance.addIncome("100");
        balance.addExpense("20");
        assertEquals(balance.getBalance(), 80);
    }

    @Test
    public void testAddExpenseDoesNotGoNegative() {
        balance.addExpense("100");
        assertEquals(balance.getBalance(), 0);
    }
    
}
