package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test 
    public void saldoOnOikeinAlussa() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenToimiiOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiOikein() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 9.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuNegatiiviseksi() {
        kortti.otaRahaa(2000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaFalsen() {
        assertTrue(!kortti.otaRahaa(2000));
    }
    
    @Test
    public void metodiPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(100));
    }
    
}
