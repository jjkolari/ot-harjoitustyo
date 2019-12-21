/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budjettisovellus.domain;

import java.time.LocalDate;
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
public class TransactionTest {

    Transaction transaction;

    @Before
    public void setUp() {
        transaction = new Transaction(100, true, 0);
    }

    @Test
    public void amountSetsRight() {
        assertEquals(100, transaction.getAmount());

    }
    
    @Test
    public void booleanSetsRight() {
        assertEquals(true, transaction.getIncome());
    }
    
    @Test
    public void balanceSetsRight() {
        assertEquals(0, transaction.getBalance());
    }
}
