package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void kortinSaldoAlussaOikein()
    {
	assertEquals(10, kortti.saldo());
    }

    @Test
    public void kortinLataaminenToimii()
    {
	kortti.lataaRahaa(10);
	assertEquals(20, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikein()
    {
	kortti.otaRahaa(2);
	assertEquals(8, kortti.saldo());
    }

    @Test
    public void saldoEiMuutu()
    {
	kortti.otaRahaa(20);
	assertEquals(10, kortti.saldo());
    }

    @Test
    public void riittaakoRahatJosAlle()
    {
	assertTrue(kortti.otaRahaa(5));
    }

    @Test
    public void riittaakoRahatJosYli()
    {
	assertTrue(!kortti.otaRahaa(50));
    }
}
