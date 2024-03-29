package iubar.iva.export;

import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class IvaFieldTest {

	@Test
	public void testPositionalAN1() {
		String actual = null;
		String expected = "ciao    ";

		actual = IvaFields.getFormatField("ciao", "AN", 8);
		assertEquals(expected, actual);
	}

	@Test
	public void testPositionalAN2() {
		String actual = null;
		String expected = "ciao    ";

		actual = IvaFields.getFormatField("ciao", "an", 8);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalAN3() throws IllegalArgumentException {

		IvaFields.getFormatField("ciao", "an", 2);
	}

	@Test
	public void testPositionalCF1() {
		String actual = null;
		String expected = "RSSDRL96F57I967O";

		actual = IvaFields.getFormatField("RSSDRL96F57I967O", "CF", 16);
		assertEquals(expected, actual);
	}

	@Test
	public void testPositionalCF2() {
		String actual = null;
		String expected = "02685978945     ";

		actual = IvaFields.getFormatField("02685978945", "CF", 16);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalCF3() throws IllegalArgumentException {

		IvaFields.getFormatField("1d1", "CF", 16);
	}

	@Test
	public void testPositionalCN1() {
		String actual = null;
		String expected = "0000002685978945";

		actual = IvaFields.getFormatField("02685978945", "CN", 16);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalCN2() throws IllegalArgumentException {

		IvaFields.getFormatField("568", "CN", 16);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalCN3() throws IllegalArgumentException {

		IvaFields.getFormatField("RSSDRL96F57I967O", "CN", 16);
	}

	@Test
	public void testPositionalPI1() {
		String actual = null;
		String expected = "0000002685978945";

		actual = IvaFields.getFormatField("02685978945", "PI", 16);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalPI2() throws IllegalArgumentException {

		IvaFields.getFormatField("568", "PI", 16);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalPI3() throws IllegalArgumentException {

		IvaFields.getFormatField("RSSDRL96F57I967O", "PI", 16);
	}

	@Test
	public void testPositionalDT1() {
		String actual = null;
		String expected = "31122017";

		actual = IvaFields.getFormatField(new Date(1514678400000L), "DT", 8); // 31122017
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalDT2() throws IllegalArgumentException {

		IvaFields.getFormatField("5012568017", "DT", 8);
	}

	@Test
	public void testPositionalNU1() {
		String actual = null;
		String expected = "00009";

		actual = IvaFields.getFormatField(new BigDecimal("9"), "NU", 5);
		assertEquals(expected, actual);
	}

	@Test
	public void testPositionalNU2() {
		String actual = null;
		String expected = "9.999";

		actual = IvaFields.getFormatField(new BigDecimal("9999"), "NU", 5);
		assertEquals(expected, actual);
	}

	@Test
	public void testPositionalNU3() {
		String actual = null;
		String expected = "09.999,999";

		actual = IvaFields.getFormatField(new BigDecimal("9999.999"), "NU", 10);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalNU4() throws IllegalArgumentException {
		
		IvaFields.getFormatField(new BigDecimal("9999"), "DT", 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalNU5() throws IllegalArgumentException {

		IvaFields.getFormatField("-254", "DT", 4);
	}

	@Test
	public void testPositionalPN1() {
		String actual = null;
		String expected = "BO";

		actual = IvaFields.getFormatField("BO", "PN", 2);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalPN2() throws IllegalArgumentException {

		IvaFields.getFormatField("DFE", "PN", 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalPN3() throws IllegalArgumentException {

		IvaFields.getFormatField("89", "PN", 2);
	}

	@Test
	public void testPositionalPR1() {
		String actual = null;
		String expected = "BO";

		actual = IvaFields.getFormatField("BO", "PR", 2);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalPR2() throws IllegalArgumentException {

		IvaFields.getFormatField("DFE", "PR", 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalPR3() throws IllegalArgumentException {

		IvaFields.getFormatField("89", "PR", 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalPR4() throws IllegalArgumentException {

		IvaFields.getFormatField("EE", "PR", 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalPR5() throws IllegalArgumentException {

		IvaFields.getFormatField("00", "PR", 2);
	}

	@Test
	public void testPositionalCB1() {
		String actual = null;
		String expected = "01";

		actual = IvaFields.getFormatField("1", "CB", 2);
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalCB2() throws IllegalArgumentException {

		IvaFields.getFormatField("6", "CB", 2);
	}

	// -----------------------------------------------------------------------------------------

	// controllo funzionamento AN Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalAN1() {
		String[] actual = IvaFields.getFormatField("ciao", "AN");
		String[] expected = { "ciao            " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento AN Non posizionale, inserimento corretto di due
	// campi
	@Test
	public void testNonPositionalAN2() {
		String[] actual = IvaFields.getFormatField("|              ||      |", "AN");
		String[] expected = { "|              |", "|      |        " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento AN Non posizionale, errore nella formattazione
	@Test
	public void testNonPositionalAN3() {
		String[] actual = IvaFields.getFormatField("ciao", "an");
		String[] expected = { "ciao            " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento CB Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalCB1() {
		String[] actual = IvaFields.getFormatField("1", "CB");
		String[] expected = { "               1" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento CB Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB2() throws IllegalArgumentException {

		IvaFields.getFormatField("10", "CB");
	}

	// controllo funzionamento CB Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB3() throws IllegalArgumentException {

		IvaFields.getFormatField("2", "CB");
	}

	// controllo funzionamento CB12 Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalCB121() {
		String[] actual = IvaFields.getFormatField("101101001011", "CB12");
		String[] expected = { "    101101001011" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento CB12 Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB122() throws IllegalArgumentException {

		IvaFields.getFormatField("101101", "CB12");
	}

	// controllo funzionamento CB12 Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB123() throws IllegalArgumentException {

		IvaFields.getFormatField("0110g0111210", "CB12");
	}

	// controllo funzionamento CF Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalCF1() {
		String[] actual = IvaFields.getFormatField("RSSDRL96F57I967O", "CF");
		String[] expected = { "RSSDRL96F57I967O" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento CF Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalCF2() {
		String[] actual = IvaFields.getFormatField("02685978945", "CF");
		String[] expected = { "02685978945     " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento CF Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCF3() throws IllegalArgumentException {

		IvaFields.getFormatField("1d1", "CF");
	}

	// controllo funzionamento CN Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalCN1() {
		String[] actual = IvaFields.getFormatField("02685978945", "CN");
		String[] expected = { "02685978945     " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento CN Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCN2() throws IllegalArgumentException {

		IvaFields.getFormatField("568", "CN");
	}

	// controllo funzionamento CN Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCN3() throws IllegalArgumentException {

		IvaFields.getFormatField("RSSDRL96F57", "CN");
	}

	// controllo funzionamento PI Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPI1() {
		String[] actual = IvaFields.getFormatField("02685978945", "PI");
		String[] expected = { "02685978945     " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PI Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPI2() throws IllegalArgumentException {

		IvaFields.getFormatField("568", "PI");
	}

	// controllo funzionamento PI Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPI3() throws IllegalArgumentException {

		IvaFields.getFormatField("RSSDRL96F57", "PI");
	}

	// controllo funzionamento DA Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalDA1() {
		String[] actual = IvaFields.getFormatField(new Date(1224460800000L), "DA");// 20102008
		String[] expected = { "            2008" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento DA Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDA2() throws IllegalArgumentException {

		IvaFields.getFormatField("201020085645", "DA");
	}

	// controllo funzionamento DT Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalDT1() {
		String[] actual = IvaFields.getFormatField(new Date(961891200000L), "DT"); // 25062000
		String[] expected = { "        25062000" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento DT Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT2() throws IllegalArgumentException {

		IvaFields.getFormatField("2106", "DT");
	}

	// controllo funzionamento DT Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT3() throws IllegalArgumentException {

		IvaFields.getFormatField("25061800", "DT");
	}

	// controllo funzionamento DT Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT4() throws IllegalArgumentException {
		IvaFields.getFormatField("25063099", "DT");
	}

	// controllo funzionamento DN Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalDN1() {
		String[] actual = IvaFields.getFormatField(new Date(961891200000L), "DN"); // 25062000
		String[] expected = { "        25062000" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento DN Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN2() throws IllegalArgumentException {

		IvaFields.getFormatField("2106", "DN");
	}

	// controllo funzionamento DN Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN3() throws IllegalArgumentException {

		IvaFields.getFormatField("25061900", "DN");
	}

	// controllo funzionamento DN Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN4() throws IllegalArgumentException {

		IvaFields.getFormatField("25062100", "DN");
	}

	// controllo funzionamento D4 Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalD41() {
		String[] actual = IvaFields.getFormatField(new Date(1224460800000L), "D4"); // 20102008
		String[] expected = { "            2010" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento D4 Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD42() throws IllegalArgumentException {

		IvaFields.getFormatField("2010", "D4");
	}

	// controllo funzionamento D6 Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalD61() {
		String[] actual = IvaFields.getFormatField(new Date(1224460800000L), "D6"); // 20102008
		String[] expected = { "          102008" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento D6 Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD62() throws IllegalArgumentException {

		IvaFields.getFormatField("102008", "D6");
	}

	// controllo funzionamento NP Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalNP1() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("25"), "NP");
		String[] expected = { "              25" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento NP Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalNP2() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("999999999999999999999999"), "NP");
		String[] expected = { "999.999.999.999.", " 999.999.999.999" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento NP Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalNP3() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("999999999999999999999.999"), "NP");
		String[] expected = { "999.999.999.999.", " 999.999.999,999" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento NP Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalNP4() throws IllegalArgumentException {

		IvaFields.getFormatField(new BigDecimal("-25"), "NP");
	}

	// controllo funzionamento NU Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalNU1() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("25"), "NU");
		String[] expected = { "              25" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento NU Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalNU2() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("-25"), "NU");
		String[] expected = { "             -25" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento NU Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalNU3() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("999999999999999999999999"), "NU");
		String[] expected = { "999.999.999.999.", " 999.999.999.999" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento NU Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalNU4() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("-999999999999999999999999"), "NU");
		String[] expected = { "-999.999.999.999", ".999.999.999.999" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento NX Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalNX1() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("25"), "N2");
		String[] expected = { "              25" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento NX Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalNX2() throws IllegalArgumentException {

		IvaFields.getFormatField(new BigDecimal("56897894584455789553248654"), "N2");
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1A() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("25"), "PC");
		String[] expected = { "              25" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1B() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("2"), "PC");
		String[] expected = { "               2" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1C() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("100"), "PC");
		String[] expected = { "             100" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1D() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("0.9"), "PC");
		String[] expected = { "             0,9" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1E() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("9.9"), "PC");
		String[] expected = { "             9,9" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1F() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("9.99"), "PC");
		String[] expected = { "            9,99" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1G() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("9.999"), "PC");
		String[] expected = { "           9,999" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1H() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("99.9"), "PC");
		String[] expected = { "            99,9" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1I() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("99.99"), "PC");
		String[] expected = { "           99,99" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPC1J() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("99.999"), "PC");
		String[] expected = { "          99,999" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PC Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPC2() throws IllegalArgumentException {

		IvaFields.getFormatField(new BigDecimal("100.9"), "PC");
	}

	// controllo funzionamento PR Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPR1() {
		String[] actual = IvaFields.getFormatField("BO", "PR");
		String[] expected = { "BO              " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PR Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPR2() throws IllegalArgumentException {

		IvaFields.getFormatField("BOG", "PR");
	}

	// controllo funzionamento PR Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPR3() throws IllegalArgumentException {

		IvaFields.getFormatField("EE", "PR");
	}

	// controllo funzionamento PR Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPR4() throws IllegalArgumentException {

		IvaFields.getFormatField("00", "PR");
	}

	// controllo funzionamento PN Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPN1A() {
		String[] actual = IvaFields.getFormatField("BO", "PN");
		String[] expected = { "BO              " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PN Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPN1B() {
		String[] actual = IvaFields.getFormatField("EE", "PN");
		String[] expected = { "EE              " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PN Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalPN1C() {
		String[] actual = IvaFields.getFormatField("00", "PN");
		String[] expected = { "00              " };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento PN Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPN2() throws IllegalArgumentException {

		IvaFields.getFormatField("BOG", "PN");
	}

	// controllo funzionamento QU Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalQU1A() {
		String[] actual = IvaFields.getFormatField("0,2554", "QU");
		String[] expected = { "          0,2554" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	public void testNonPositionalQU1B() {
		String[] actual = IvaFields.getFormatField("0.2554", "QU");
		String[] expected = { "          0.2554" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	public void testNonPositionalQU1C() {
		String[] actual = IvaFields.getFormatField("500000000", "QU");
		String[] expected = { "       500000000" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalQU2() throws IllegalArgumentException {

		IvaFields.getFormatField("5,589785489", "PN");
	}

	@Test
	public void testBuildBigDecimal1() {
		String actual = IvaFields.getFormatField(new BigDecimal("10"), "NU", 10);
		String expected = "0000000010";

		assertEquals(expected, actual);
	}

	@Test
	public void testBuildBigDecimal2() {
		String actual = IvaFields.getFormatField(new BigDecimal("10.568"), "NU", 10);
		String expected = "000010,568";

		assertEquals(expected, actual);
	}

	@Test
	public void testBuildNonPositionalBigDecimal1() {
		String[] actual = IvaFields.getFormatField(new BigDecimal("10.568"), "NU");
		String[] expected = { "          10,568" };

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	public void testBuildDate1() {
		String actual = IvaFields.getFormatField(new Date(1224460800000L), "DT", 8);// 20/10/2008
		String expected = "20102008";

		assertEquals(expected, actual);
	}

	@Test
	public void testBuildNonPositionalDate1() {
		String[] actual = IvaFields.getFormatField(new Date(1224460800000L), "DT");// 20/10/2008
		String[] expected = { "        20102008" };

		assertEquals(expected[0], actual[0]);
	}

	@Test
	public void testBuildNonPositionalDate2() {
		String[] actual = IvaFields.getFormatField(new Date(1224460800000L), "DA");// 20/10/2008
		String[] expected = { "            2008" };
		
		assertEquals(expected[0], actual[0]);
	}

}