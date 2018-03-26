/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class KassapaateTest {
	
	Kassapaate kp;

	public KassapaateTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp(){
		kp = new Kassapaate();
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void rahaTesti()
	{
		assertEquals(100000, kp.kassassaRahaa());
	}

	@Test
	public void lounaitaMyytyYhteensa()
	{
		assertEquals(0, kp.edullisiaLounaitaMyyty()+kp.maukkaitaLounaitaMyyty());
	}

	@Test
	public void kateismaksuEdullinenToimii()
	{
		assertEquals(0,kp.syoEdullisesti(240));
		assertEquals(1,kp.edullisiaLounaitaMyyty());
		assertEquals(100240, kp.kassassaRahaa());
	}

	@Test
	public void kateismaksuMaukasToimii()
	{
		assertEquals(40,kp.syoMaukkaasti(440));
		assertEquals(1,kp.maukkaitaLounaitaMyyty());
		assertEquals(100400, kp.kassassaRahaa());
	}

	@Test
	public void kateismaksuEdullinenEiToimi()
	{
		assertEquals(230,kp.syoEdullisesti(230));
		assertEquals(0,kp.edullisiaLounaitaMyyty());
		assertEquals(100000, kp.kassassaRahaa());
	}

	@Test
	public void kateismaksuMaukasEiToimi()
	{
		assertEquals(40,kp.syoMaukkaasti(40));
		assertEquals(0,kp.maukkaitaLounaitaMyyty());
		assertEquals(100000, kp.kassassaRahaa());
	}

	@Test
	public void korttiEdullinenToimii()
	{
		Maksukortti k = new Maksukortti(240);
		assertTrue(kp.syoEdullisesti(k));
		assertEquals(1,kp.edullisiaLounaitaMyyty());
		assertEquals(100000, kp.kassassaRahaa());
	}

	@Test
	public void korttiMaukasToimii()
	{
		Maksukortti k = new Maksukortti(440);
		assertTrue(kp.syoMaukkaasti(k));
		assertEquals(1,kp.maukkaitaLounaitaMyyty());
		assertEquals(100000, kp.kassassaRahaa());
	}

	@Test
	public void korttiEdullinenEiToimi()
	{
		Maksukortti k = new Maksukortti(230);
		assertTrue(!kp.syoEdullisesti(k));
		assertEquals(0,kp.edullisiaLounaitaMyyty());
		assertEquals(100000, kp.kassassaRahaa());
	}

	@Test
	public void korttiMaukasEiToimi()
	{
		Maksukortti k = new Maksukortti(44);
		assertTrue(!kp.syoMaukkaasti(k));
		assertEquals(0,kp.maukkaitaLounaitaMyyty());
		assertEquals(100000, kp.kassassaRahaa());
	}

	@Test
	public void kortilleLataus()
	{
		Maksukortti k = new Maksukortti(0);
		kp.lataaRahaaKortille(k, 10);
		assertEquals(100010, kp.kassassaRahaa());
		assertEquals(10, k.saldo());
	}

	@Test
	public void kortilleLatausHuono()
	{
		Maksukortti k = new Maksukortti(0);
		kp.lataaRahaaKortille(k, -10);
		assertEquals(100000, kp.kassassaRahaa());
		assertEquals(0, k.saldo());
	}
}
