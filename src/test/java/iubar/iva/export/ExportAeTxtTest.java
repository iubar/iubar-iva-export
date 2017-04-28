package iubar.iva.export;

import iubar.iva.export.ExportAeTxt;

import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class ExportAeTxtTest {

	@Test
	public void testPositionalAN1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("ciao", "AN", 8);
		
		String expected = "ciao    ";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPositionalAN2() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("ciao", "an", 8);
		
		String expected = "ciao    ";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalAN3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("ciao", "an", 2);
	}
	
	@Test
	public void testPositionalCF1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("RSSDRL96F57I967O", "CF", 16);
		
		String expected = "RSSDRL96F57I967O";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPositionalCF2() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("02685978945", "CF", 16);
		
		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPositionalCF3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("1d1", "CF", 16);
	}
	
	@Test
	public void testPositionalCN1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("02685978945", "CN", 16);
		
		String expected = "02685978945";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalCN2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("568", "CN", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalCN3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("RSSDRL96F57I967O", "CN", 16);
	}
	
	@Test
	public void testPositionalPI1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("02685978945", "PI", 16);
		
		String expected = "02685978945";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalPI2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("568", "PI", 16);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalPI3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("RSSDRL96F57I967O", "PI", 16);
	}
	
	@Test
	public void testPositionalDT1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("31122017", "DT", 8);
		
		String expected = "31122017";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalDT2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("", "DT", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalDT3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("5012568017", "DT", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalDT4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("201DF017", "DT", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalDT5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("50122017", "DT", 8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalDT6() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("20252017", "DT", 8);
	}
	
	@Test
	public void testPositionalNU1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("25", "NU", 5);
		
		String expected = "00025";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalNU2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("252525", "DT", 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalNU3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("-254", "DT", 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalNU4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("DFE", "DT", 4);
	}
	
	@Test
	public void testPositionalPN1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("BO", "PN", 2);
		
		String expected = "BO";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalPN2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("DFE", "PN", 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalPN3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("89", "PN", 2);
	}
	
	@Test
	public void testPositionalPR1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("BO", "PR", 2);
		
		String expected = "BO";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalPR2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("DFE", "PR", 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalPR3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("89", "PR", 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalPR4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("EE", "PR", 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalPR5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("00", "PR", 2);
	}
	
	@Test
	public void testPositionalCB1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual = null;
		
		actual = export.buildPositionalField("1", "CB", 2);
		
		String expected = "1";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPositionalCB2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildPositionalField("6", "CB", 2);
	}
	
	@Test
	public void testNonPositionalAN1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("ciao", "AN");
		
		String expected = "ciao    ";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalAN2() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("ciao", "an");
		
		String expected = "ciao    ";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalAN3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("ciao", "an");
	}
	
	@Test
	public void testNonPositionalCB1() {
		ExportAeTxt export = new ExportAeTxt();
		String actual[] = null;
		
		actual = export.buildNonPositionalField("1", "CB");
		
		String expected = "0000000000000001";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("1", "CB");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("10", "CB");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("2", "CB");
	}
	
	@Test
	public void testNonPositionalCB121() {
		ExportAeTxt export = new ExportAeTxt();
		
		String[] actual = export.buildNonPositionalField("101101001011", "CB12");
		
		String[] expected = {"0000101101001011"};
		assertEquals(expected[0], actual[0]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB122() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("101101", "CB12");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB123() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("10", "CB12");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB124() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("0110g0111210", "CB12");
	}
	@Test
	public void testNonPositionalCF1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("RSSDRL96F57I967O", "CF");
		
		String expected = "RSSDRL96F57I967O";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalCF2() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("02685978945", "CF");
		
		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCF3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("RSSDRL96F57I967O", "CF");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCF4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("1d1", "CF");
	}
	
	@Test
	public void testNonPositionalCN1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("02685978945", "CN");
		
		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCN2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("02685978945", "CN");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalCN3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("568", "CN");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalCN4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("RSSDRL96F57", "CN");
	}
	
	@Test
	public void testNonPositionalPI1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("02685978945", "PI");
		
		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPI2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("02685978945", "PI");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPI3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("568", "PI");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPI4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("RSSDRL96F57", "PI");
	}
	
	@Test
	public void testNonPositionalDA1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("20102008", "DA");
		
		String expected = "            2008";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDA2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("20102008", "DA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDA3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("201020085645", "DA");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDA4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("5DSF5898", "DA");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDA5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("32202000", "DT");
	}
	
	@Test
	public void testNonPositionalDT1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("25062000", "DT");
		
		String expected = "        25062000";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("25062000", "DT");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDT3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("2106", "DT");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDT4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("25g62000", "DT");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDT5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("32202000", "DT");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDT6() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("25061800", "DT");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDT7() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("25062020", "DT");
	}

	@Test
	public void testNonPositionalDN1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("25062000", "DN");
		
		String expected = "        25062000";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("25062000", "DN");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDN3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("2106", "DN");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDN4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("25g62000", "DN");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDN5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("32202000", "DN");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDN6() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("25061900", "DN");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalDN7() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("25062100", "DN");
	}
	
	@Test
	public void testNonPositionalD41() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("20102008", "D4");
		
		String expected = "            2010";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD42() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("20102008", "D4");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalD43() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("2010", "D4");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalD44() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("20g020s8", "D4");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalD45() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("50102008", "D4");
	}
	
	@Test
	public void testNonPositionalD61() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("20102008", "D6");
		
		String expected = "          102008";
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD62() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("20102008", "D6");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalD63() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("102008", "D6");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalD64() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("20d020g8", "D6");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalD65() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("202000", "D6");
	}
	
	@Test
	public void testNonPositionalNP1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("25", "NP");
		
		String expected = "        25";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalNP2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("-25", "NP");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalNP3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("ghr", "NP");
	}
	
	@Test
	public void testNonPositionalNU1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("25", "NU");
		
		String expected = "        25";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalNU2() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("-25", "NU");
		
		String expected = "       -25";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalNU3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("ghr", "NU");
	}
	
	@Test
	public void testNonPositionalNX1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("25", "Nx");
		
		String expected = "        25";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalNX2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("56897894584455789553248654", "Nx");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalNX3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("ghr", "Nx");
	}
	
	@Test
	public void testNonPositionalPC1A() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("25", "PC");
		
		String expected = "        25";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPC1B() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("2", "PC");
		
		String expected = "         2";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPC1C() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("100", "PC");
		
		String expected = "       100";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPC1D() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("0.9", "PC");
		
		String expected = "       0.9";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPC1E() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("9.9", "PC");
		
		String expected = "       9.9";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPC1F() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("9.99", "PC");
		
		String expected = "      9.99";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPC1G() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("9.999", "PC");
		
		String expected = "     9.999";
		assertEquals(expected, actual);
	}
	@Test
	public void testNonPositionalPC1H() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("99.9", "PC");
		
		String expected = "      99.9";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPC1I() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("99.99", "PC");
		
		String expected = "     99.99";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPC1J() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("99.999", "PC");
		
		String expected = "    99.999";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPC2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("100.9", "PC");
	}
	
	@Test
	public void testNonPositionalPR1() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("BO", "PR");
		
		String expected = "BO              ";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPR2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("BO", "PR");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPR3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("BOG", "PR");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPR4() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("EE", "PR");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPR5() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("00", "PR");
	}
	
	@Test
	public void testNonPositionalPN1A() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("BO", "PN");
		
		String expected = "BO              ";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPN1B() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("EE", "PN");
		
		String expected = "EE              ";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalPN1C() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("00", "PN");
		
		String expected = "00              ";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPN2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("BO", "PN");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalPN3() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("BOG", "PN");
	}
	
	@Test
	public void testNonPositionalQU1A() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("0,2554", "QU");
		
		String expected = "          0,2554";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalQU1B() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("0.2554", "QU");
		
		String expected = "          0.2554";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonPositionalQU1C() {
		ExportAeTxt export = new ExportAeTxt();
		String[] actual = null;
		
		actual = export.buildNonPositionalField("500000000", "QU");
		
		String expected = "       500000000";
		assertEquals(expected, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNonPositionalQU2() throws IllegalArgumentException {
		ExportAeTxt export = new ExportAeTxt();
		export.buildNonPositionalField("5,589785489", "PN");
	}
	
	@Test
	public void testBuildBigDecimal1() {
		ExportAeTxt export = new ExportAeTxt();
		BigDecimal n = new BigDecimal("10");
		String actual = null;
		
		actual = export.buildPositionalField(n, "NU", 10);
		
		String expected = "0000000010";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildBigDecimal2() {
		ExportAeTxt export = new ExportAeTxt();
		BigDecimal n = new BigDecimal("10.568");
		String actual = null;
		
		actual = export.buildPositionalField(n, "NU", 10);
		
		String expected = "000010,568";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildNonPositionalBigDecimal1() {
		ExportAeTxt export = new ExportAeTxt();
		BigDecimal n = new BigDecimal("10.568");
		String[] actual = null;
		
		actual = export.buildNonPositionalField(n, "NU");
		
		String expected = "    10,568";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildDate1() {
		ExportAeTxt export = new ExportAeTxt();
		
		Date n = new Date(1224460800000L); //20/10/2008
		String actual = null;
		
		actual = export.buildPositionalField(n, "DT", 8);
		
		String expected = "20102008";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildNonPositionalDate1() {
		ExportAeTxt export = new ExportAeTxt();
		
		Date n = new Date(1224460800000L); //20/10/2008
		int l = export.buildNonPositionalField(n, "DT").length;
		
		String[] actual = new String[l];
		
		for(int i = 0; i < l; i++) {
			actual = export.buildNonPositionalField(n, "DT");
		}
		
		String[] expected = {"        20102008"};
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBuildNonPositionalDate2() {
		ExportAeTxt export = new ExportAeTxt();
		
		Date n = new Date(1224460800000L); //20/10/2008
		String[] actual = null;
		
		actual = export.buildNonPositionalField(n, "DA");
		
		String expected = "            2008";
		assertEquals(expected, actual);
	}
}