package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    
    @Test
    public void alkuSaldoOnOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void lounaitaOnMyytyAlussaNolla() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanVaihtorahaOikein() {
        assertEquals(60, kassapaate.syoEdullisesti(300));
    }
    
    @Test
    public void edullisenLounaanJalkeenKassaOikein(){
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanVaihtorahaOikein() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void lounaidenMaaraKasvaa1() {
        kassapaate.syoEdullisesti(500);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void lounaidenMaaraKasvaa2() {
        kassapaate.syoMaukkaasti(500);
        kassapaate.syoMaukkaasti(500);
        assertEquals(2, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullistaLounastaEiMyyty() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukastaLounastaEiMyyty() {
        assertEquals(200, kassapaate.syoMaukkaasti(200));
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullinenLounasJaKorttiToimii() {
        assertEquals(true, kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukasLounasJaKorttiToimii() {
        assertEquals(true, kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortillaOstettuEdLounasMyytiin() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortillaOstettuMauLounasMyytiin() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edLounasEiMeneLapi() {
        kortti = new Maksukortti(100);
        assertEquals(false, kassapaate.syoEdullisesti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void mauLounasEiMeneLapi() {
        kortti = new Maksukortti(100);
        assertEquals(false, kassapaate.syoMaukkaasti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassassaOlevaRahaEiMuutuKortillaOstaessa() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleLataaminenToimiiHalutusti() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void negatiivisestiEiVoiLataa() {
        kassapaate.lataaRahaaKortille(kortti, -40);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
