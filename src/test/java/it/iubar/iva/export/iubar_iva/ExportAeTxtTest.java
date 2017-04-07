package it.iubar.iva.export.iubar_iva;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class ExportAeTxtTest {

	@Test
	public void testP_AN_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("ciao", "AN", 8);
		
		String expected = "ciao    ";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testP_AN_2() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("ciao", "an", 8);
		
		String expected = "ciao    ";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_AN_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("ciao", "an", 2);
	}
	
	@Test
	public void testP_CF_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("RSSDRL96F57I967O", "CF", 16);
		
		String expected = "RSSDRL96F57I967O";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testP_CF_2() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("02685978945", "CF", 16);
		
		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testP_CF_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("1d1", "CF", 16);
	}
	
	@Test
	public void testP_CN_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("02685978945", "CN", 16);
		
		String expected = "02685978945";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_CN_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("568", "CN", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_CN_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("RSSDRL96F57I967O", "CN", 16);
	}
	
	@Test
	public void testP_PI_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("02685978945", "PI", 16);
		
		String expected = "02685978945";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_PI_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("568", "PI", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_PI_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("RSSDRL96F57I967O", "PI", 16);
	}
	
	@Test
	public void testP_DT_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("31122017", "DT", 8);
		
		String expected = "31122017";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_DT_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("", "DT", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_DT_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("5012568017", "DT", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_DT_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("201DF017", "DT", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_DT_5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("50122017", "DT", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_DT_6() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("20252017", "DT", 8);
	}
	
	@Test
	public void testP_NU_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("25", "NU", 5);
		
		String expected = "00025";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_NU_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("252525", "DT", 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_NU_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("-254", "DT", 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_NU_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("DFE", "DT", 4);
	}
	
	@Test
	public void testP_PN_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("BO", "PN", 2);
		
		String expected = "BO";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_PN_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("DFE", "PN", 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_PN_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("89", "PN", 2);
	}
	
	@Test
	public void testP_PR_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("BO", "PR", 2);
		
		String expected = "BO";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_PR_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("DFE", "PR", 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_PR_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("89", "PR", 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_PR_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("EE", "PR", 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_PR_5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("00", "PR", 2);
	}
	
	@Test
	public void testP_CB_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("1", "CB", 2);
		
		String expected = "1";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testP_CB_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildPositionalField("6", "CB", 2);
	}
	
	@Test
	public void testNP_AN_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("ciao", "AN", 8);
		
		String expected = "ciao    ";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_AN_2() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("ciao", "an", 8);
		
		String expected = "ciao    ";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_AN_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("ciao", "an", 2);
	}
	
	@Test
	public void testNP_CB_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("1", "CB", 16);
		
		String expected = "0000000000000001";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CB_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("1", "CB", 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CB_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("10", "CB", 16);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CB_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("2", "CB", 16);
	}
	
	@Test
	public void testNP_CB12_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("101101001011", "CB12", 16);
		
		String expected = "0000101101001011";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CB12_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("101101", "CB12", 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CB12_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("10", "CB12", 16);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CB12_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("0110g0111210", "CB12", 16);
	}
	@Test
	public void testNP_CF_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("RSSDRL96F57I967O", "CF", 16);
		
		String expected = "RSSDRL96F57I967O";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_CF_2() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("02685978945", "CF", 16);
		
		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CF_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("RSSDRL96F57I967O", "CF", 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CF_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("1d1", "CF", 16);
	}
	
	@Test
	public void testNP_CN_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("02685978945", "CN", 16);
		
		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_CN_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("02685978945", "CN", 6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_CN_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("568", "CN", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_CN_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("RSSDRL96F57", "CN", 16);
	}
	
	@Test
	public void testNP_PI_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("02685978945", "PI", 16);
		
		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_PI_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("02685978945", "PI", 6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PI_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("568", "PI", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PI_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("RSSDRL96F57", "PI", 16);
	}
	
	@Test
	public void testNP_DA_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("20102008", "DA", 16);
		
		String expected = "            2008";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_DA_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("20102008", "DA", 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_DA_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("201020085645", "DA", 6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DA_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("5DSF5898", "DA", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DA_5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("32202000", "DT", 16);
	}
	
	@Test
	public void testNP_DT_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("25062000", "DT", 16);
		
		String expected = "        25062000";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_DT_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("25062000", "DT", 6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DT_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("2106", "DT", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DT_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("25g62000", "DT", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DT_5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("32202000", "DT", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DT_6() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("25061800", "DT", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DT_7() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("25062020", "DT", 16);
	}

	@Test
	public void testNP_DN_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("25062000", "DN", 16);
		
		String expected = "        25062000";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_DN_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("25062000", "DN", 6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DN_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("2106", "DN", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DN_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("25g62000", "DN", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DN_5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("32202000", "DN", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DN_6() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("25061900", "DN", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_DN_7() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("25062100", "DN", 16);
	}
	
	@Test
	public void testNP_D4_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("20102008", "D4", 16);
		
		String expected = "            2010";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_D4_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("20102008", "D4", 6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_D4_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("2010", "D4", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_D4_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("20g020s8", "D4", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_D4_5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("50102008", "D4", 16);
	}
	
	@Test
	public void testNP_D6_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("20102008", "D6", 16);
		
		String expected = "          102008";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNP_D6_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("20102008", "D6", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_D6_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("102008", "D6", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_D6_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("20d020g8", "D6", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_D6_5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("202000", "D6", 16);
	}
	
	@Test
	public void testNP_NP_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("25", "NP", 10);
		
		String expected = "        25";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_NP_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("-25", "NP", 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_NP_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("ghr", "NP", 10);
	}
	
	@Test
	public void testNP_NU_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("25", "NU", 10);
		
		String expected = "        25";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_NU_2() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("-25", "NU", 10);
		
		String expected = "       -25";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_NU_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("ghr", "NU", 10);
	}
	
	@Test
	public void testNP_NX_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("25", "Nx", 10);
		
		String expected = "        25";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_NX_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("56897894584455789553248654", "Nx", 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_NX_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("ghr", "Nx", 10);
	}
	
	@Test
	public void testNP_PC_1a() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("25", "PC", 10);
		
		String expected = "        25";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PC_1b() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("2", "PC", 10);
		
		String expected = "         2";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PC_1c() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("100", "PC", 10);
		
		String expected = "       100";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PC_1d() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("0.9", "PC", 10);
		
		String expected = "       0.9";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PC_1e() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("9.9", "PC", 10);
		
		String expected = "       9.9";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PC_1f() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("9.99", "PC", 10);
		
		String expected = "      9.99";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PC_1g() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("9.999", "PC", 10);
		
		String expected = "     9.999";
		assertEquals(expected, actual);
	}
	@Test
	public void testNP_PC_1h() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("99.9", "PC", 10);
		
		String expected = "      99.9";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PC_1i() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("99.99", "PC", 10);
		
		String expected = "     99.99";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PC_1j() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("99.999", "PC", 10);
		
		String expected = "    99.999";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PC_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("100.9", "PC", 10);
	}
	
	@Test
	public void testNP_PR_1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("BO", "PR", 16);
		
		String expected = "BO              ";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PR_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("BO", "PR", 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PR_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("BOG", "PR", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PR_4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("EE", "PR", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PR_5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("00", "PR", 16);
	}
	
	@Test
	public void testNP_PN_1a() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("BO", "PN", 16);
		
		String expected = "BO              ";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PN_1b() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("EE", "PN", 16);
		
		String expected = "EE              ";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_PN_1c() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("00", "PN", 16);
		
		String expected = "00              ";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PN_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("BO", "PN", 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_PN_3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("BOG", "PN", 16);
	}
	
	@Test
	public void testNP_QU_1a() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("0,2554", "QU", 16);
		
		String expected = "          0,2554";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_QU_1b() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("0.2554", "QU", 16);
		
		String expected = "          0.2554";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNP_QU_1c() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildNonPositionalField("500000000", "QU", 16);
		
		String expected = "       500000000";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNP_QU_2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		actual = export.buildNonPositionalField("5,589785489", "PN", 16);
	}
	
	@Test
	public void testBuildBigDecimal_1() {
		ExportAeTxt export = new ExportAeTxt();
		BigDecimal n = new BigDecimal("10");
		String actual = null;
		
		actual = export.buildPositionalField(n, "NU", 10);
		
		String expected = "0000000010";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildBigDecimal_2() {
		ExportAeTxt export = new ExportAeTxt();
		BigDecimal n = new BigDecimal("10.568");
		String actual = null;
		
		actual = export.buildPositionalField(n, "NU", 10);
		
		String expected = "000010,568";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildNPBigDecimal_2() {
		ExportAeTxt export = new ExportAeTxt();
		BigDecimal n = new BigDecimal("10.568");
		String actual = null;
		
		actual = export.buildNonPositionalField(n, "NU", 10);
		
		String expected = "    10,568";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildDate_1() {
		ExportAeTxt export = new ExportAeTxt();
		
		Date n = new Date(1224460800000L); //20/10/2008
		String actual = null;
		
		actual = export.buildPositionalField(n, "DT", 8);
		
		String expected = "20102008";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildNPDate_1() {
		ExportAeTxt export = new ExportAeTxt();
		
		Date n = new Date(1224460800000L); //20/10/2008
		String actual = null;
		
		actual = export.buildNonPositionalField(n, "DT", 16);
		
		String expected = "        20102008";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildNPDate_2() {
		ExportAeTxt export = new ExportAeTxt();
		
		Date n = new Date(1224460800000L); //20/10/2008
		String actual = null;
		
		actual = export.buildNonPositionalField(n, "DA", 16);
		
		String expected = "            2008";
		assertEquals(expected, actual);
	}
}