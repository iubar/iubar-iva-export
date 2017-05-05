package iubar.iva.export;

import iubar.iva.export.IvaFields;

import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class IvaFieldTest {

	@Test
	public void testPositionalAN1() {

		String actual = null;

		actual = IvaFields.getFormatField("ciao", "AN", 8);

		String expected = "ciao    ";
		assertEquals(expected, actual);
	}

	@Test
	public void testPositionalAN2() {

		String actual = null;

		actual = IvaFields.getFormatField("ciao", "an", 8);

		String expected = "ciao    ";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalAN3() throws IllegalArgumentException {

		IvaFields.getFormatField("ciao", "an", 2);
	}

	@Test
	public void testPositionalCF1() {

		String actual = null;

		actual = IvaFields.getFormatField("RSSDRL96F57I967O", "CF", 16);

		String expected = "RSSDRL96F57I967O";
		assertEquals(expected, actual);
	}

	@Test
	public void testPositionalCF2() {

		String actual = null;

		actual = IvaFields.getFormatField("02685978945", "CF", 16);

		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalCF3() throws IllegalArgumentException {

		IvaFields.getFormatField("1d1", "CF", 16);
	}

	@Test
	public void testPositionalCN1() {

		String actual = null;

		actual = IvaFields.getFormatField("02685978945", "CN", 16);

		String expected = "02685978945";
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

		actual = IvaFields.getFormatField("02685978945", "PI", 16);

		String expected = "02685978945";
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

		actual = IvaFields.getFormatField("31122017", "DT", 8);

		String expected = "31122017";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalDT2() throws IllegalArgumentException {

		IvaFields.getFormatField("", "DT", 8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalDT3() throws IllegalArgumentException {

		IvaFields.getFormatField("5012568017", "DT", 8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalDT4() throws IllegalArgumentException {

		IvaFields.getFormatField("201DF017", "DT", 8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalDT5() throws IllegalArgumentException {

		IvaFields.getFormatField("50122017", "DT", 8);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalDT6() throws IllegalArgumentException {

		IvaFields.getFormatField("20252017", "DT", 8);
	}

	@Test
	public void testPositionalNU1() {

		String actual = null;

		actual = IvaFields.getFormatField("25", "NU", 5);

		String expected = "00025";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalNU2() throws IllegalArgumentException {

		IvaFields.getFormatField("252525", "DT", 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalNU3() throws IllegalArgumentException {

		IvaFields.getFormatField("-254", "DT", 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPositionalNU4() throws IllegalArgumentException {

		IvaFields.getFormatField("DFE", "DT", 4);
	}

	@Test
	public void testPositionalPN1() {

		String actual = null;

		actual = IvaFields.getFormatField("BO", "PN", 2);

		String expected = "BO";
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

		actual = IvaFields.getFormatField("BO", "PR", 2);

		String expected = "BO";
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

		actual = IvaFields.getFormatField("1", "CB", 2);

		String expected = "1";
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

		String[] expected = { "0000000000000001" };
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento CB Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB3() throws IllegalArgumentException {

		IvaFields.getFormatField("10", "CB");
	}

	// controllo funzionamento CB Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCB4() throws IllegalArgumentException {

		IvaFields.getFormatField("2", "CB");
	}

	// controllo funzionamento CB12 Non posizionale, inserimento corretto
	@Test
	public void testNonPositionalCB121() {

		String[] actual = IvaFields.getFormatField("101101001011", "CB12");

		String[] expected = { "0000101101001011" };
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
	public void testNonPositionalCB124() throws IllegalArgumentException {

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

		String[] expected = { "02685978945" };
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	// controllo funzionamento CF Non posizionale, errore di inserimento
	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCF4() throws IllegalArgumentException {

		IvaFields.getFormatField("1d1", "CF");
	}

	@Test
	public void testNonPositionalCN1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("02685978945", "CN");

		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCN2() throws IllegalArgumentException {

		IvaFields.getFormatField("02685978945", "CN");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCN3() throws IllegalArgumentException {

		IvaFields.getFormatField("568", "CN");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalCN4() throws IllegalArgumentException {

		IvaFields.getFormatField("RSSDRL96F57", "CN");
	}

	@Test
	public void testNonPositionalPI1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("02685978945", "PI");

		String expected = "02685978945     ";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPI2() throws IllegalArgumentException {

		IvaFields.getFormatField("02685978945", "PI");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPI3() throws IllegalArgumentException {

		IvaFields.getFormatField("568", "PI");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPI4() throws IllegalArgumentException {

		IvaFields.getFormatField("RSSDRL96F57", "PI");
	}

	@Test
	public void testNonPositionalDA1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("20102008", "DA");

		String expected = "            2008";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDA2() throws IllegalArgumentException {

		IvaFields.getFormatField("20102008", "DA");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDA3() throws IllegalArgumentException {

		IvaFields.getFormatField("201020085645", "DA");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDA4() throws IllegalArgumentException {

		IvaFields.getFormatField("5DSF5898", "DA");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDA5() throws IllegalArgumentException {

		IvaFields.getFormatField("32202000", "DT");
	}

	@Test
	public void testNonPositionalDT1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("25062000", "DT");

		String expected = "        25062000";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT2() throws IllegalArgumentException {

		IvaFields.getFormatField("25062000", "DT");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT3() throws IllegalArgumentException {

		IvaFields.getFormatField("2106", "DT");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT4() throws IllegalArgumentException {

		IvaFields.getFormatField("25g62000", "DT");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT5() throws IllegalArgumentException {

		IvaFields.getFormatField("32202000", "DT");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT6() throws IllegalArgumentException {

		IvaFields.getFormatField("25061800", "DT");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDT7() throws IllegalArgumentException {

		IvaFields.getFormatField("25062020", "DT");
	}

	@Test
	public void testNonPositionalDN1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("25062000", "DN");

		String expected = "        25062000";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN2() throws IllegalArgumentException {

		IvaFields.getFormatField("25062000", "DN");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN3() throws IllegalArgumentException {

		IvaFields.getFormatField("2106", "DN");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN4() throws IllegalArgumentException {

		IvaFields.getFormatField("25g62000", "DN");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN5() throws IllegalArgumentException {

		IvaFields.getFormatField("32202000", "DN");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN6() throws IllegalArgumentException {

		IvaFields.getFormatField("25061900", "DN");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalDN7() throws IllegalArgumentException {

		IvaFields.getFormatField("25062100", "DN");
	}

	@Test
	public void testNonPositionalD41() {

		String[] actual = null;

		actual = IvaFields.getFormatField("20102008", "D4");

		String expected = "            2010";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD42() throws IllegalArgumentException {

		IvaFields.getFormatField("20102008", "D4");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD43() throws IllegalArgumentException {

		IvaFields.getFormatField("2010", "D4");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD44() throws IllegalArgumentException {

		IvaFields.getFormatField("20g020s8", "D4");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD45() throws IllegalArgumentException {

		IvaFields.getFormatField("50102008", "D4");
	}

	@Test
	public void testNonPositionalD61() {

		String[] actual = null;

		actual = IvaFields.getFormatField("20102008", "D6");

		String expected = "          102008";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD62() throws IllegalArgumentException {

		IvaFields.getFormatField("20102008", "D6");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD63() throws IllegalArgumentException {

		IvaFields.getFormatField("102008", "D6");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD64() throws IllegalArgumentException {

		IvaFields.getFormatField("20d020g8", "D6");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalD65() throws IllegalArgumentException {

		IvaFields.getFormatField("202000", "D6");
	}

	@Test
	public void testNonPositionalNP1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("25", "NP");

		String expected = "        25";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalNP2() throws IllegalArgumentException {

		IvaFields.getFormatField("-25", "NP");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalNP3() throws IllegalArgumentException {

		IvaFields.getFormatField("ghr", "NP");
	}

	@Test
	public void testNonPositionalNU1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("25", "NU");

		String expected = "        25";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalNU2() {

		String[] actual = null;

		actual = IvaFields.getFormatField("-25", "NU");

		String expected = "       -25";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalNU3() throws IllegalArgumentException {

		IvaFields.getFormatField("ghr", "NU");
	}

	@Test
	public void testNonPositionalNX1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("25", "Nx");

		String expected = "        25";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalNX2() throws IllegalArgumentException {

		IvaFields.getFormatField("56897894584455789553248654", "Nx");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalNX3() throws IllegalArgumentException {

		IvaFields.getFormatField("ghr", "Nx");
	}

	@Test
	public void testNonPositionalPC1A() {

		String[] actual = null;

		actual = IvaFields.getFormatField("25", "PC");

		String expected = "        25";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1B() {

		String[] actual = null;

		actual = IvaFields.getFormatField("2", "PC");

		String expected = "         2";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1C() {

		String[] actual = null;

		actual = IvaFields.getFormatField("100", "PC");

		String expected = "       100";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1D() {

		String[] actual = null;

		actual = IvaFields.getFormatField("0.9", "PC");

		String expected = "       0.9";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1E() {

		String[] actual = null;

		actual = IvaFields.getFormatField("9.9", "PC");

		String expected = "       9.9";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1F() {

		String[] actual = null;

		actual = IvaFields.getFormatField("9.99", "PC");

		String expected = "      9.99";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1G() {

		String[] actual = null;

		actual = IvaFields.getFormatField("9.999", "PC");

		String expected = "     9.999";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1H() {

		String[] actual = null;

		actual = IvaFields.getFormatField("99.9", "PC");

		String expected = "      99.9";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1I() {

		String[] actual = null;

		actual = IvaFields.getFormatField("99.99", "PC");

		String expected = "     99.99";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPC1J() {

		String[] actual = null;

		actual = IvaFields.getFormatField("99.999", "PC");

		String expected = "    99.999";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPC2() throws IllegalArgumentException {

		IvaFields.getFormatField("100.9", "PC");
	}

	@Test
	public void testNonPositionalPR1() {

		String[] actual = null;

		actual = IvaFields.getFormatField("BO", "PR");

		String expected = "BO              ";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPR2() throws IllegalArgumentException {

		IvaFields.getFormatField("BO", "PR");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPR3() throws IllegalArgumentException {

		IvaFields.getFormatField("BOG", "PR");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPR4() throws IllegalArgumentException {

		IvaFields.getFormatField("EE", "PR");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPR5() throws IllegalArgumentException {

		IvaFields.getFormatField("00", "PR");
	}

	@Test
	public void testNonPositionalPN1A() {

		String[] actual = null;

		actual = IvaFields.getFormatField("BO", "PN");

		String expected = "BO              ";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPN1B() {

		String[] actual = null;

		actual = IvaFields.getFormatField("EE", "PN");

		String expected = "EE              ";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalPN1C() {

		String[] actual = null;

		actual = IvaFields.getFormatField("00", "PN");

		String expected = "00              ";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPN2() throws IllegalArgumentException {

		IvaFields.getFormatField("BO", "PN");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalPN3() throws IllegalArgumentException {

		IvaFields.getFormatField("BOG", "PN");
	}

	@Test
	public void testNonPositionalQU1A() {

		String[] actual = null;

		actual = IvaFields.getFormatField("0,2554", "QU");

		String expected = "          0,2554";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalQU1B() {

		String[] actual = null;

		actual = IvaFields.getFormatField("0.2554", "QU");

		String expected = "          0.2554";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonPositionalQU1C() {

		String[] actual = null;

		actual = IvaFields.getFormatField("500000000", "QU");

		String expected = "       500000000";
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonPositionalQU2() throws IllegalArgumentException {

		IvaFields.getFormatField("5,589785489", "PN");
	}

	@Test
	public void testBuildBigDecimal1() {

		BigDecimal n = new BigDecimal("10");
		String actual = null;

		actual = IvaFields.getFormatField(n, "NU", 10);

		String expected = "0000000010";
		assertEquals(expected, actual);
	}

	@Test
	public void testBuildBigDecimal2() {

		BigDecimal n = new BigDecimal("10.568");
		String actual = null;

		actual = IvaFields.getFormatField(n, "NU", 10);

		String expected = "000010,568";
		assertEquals(expected, actual);
	}

	@Test
	public void testBuildNonPositionalBigDecimal1() {

		BigDecimal n = new BigDecimal("10.568");
		String[] actual = null;

		actual = IvaFields.getFormatField(n, "NU");

		String expected = "    10,568";
		assertEquals(expected, actual);
	}

	@Test
	public void testBuildDate1() {

		Date n = new Date(1224460800000L); // 20/10/2008
		String actual = null;

		actual = IvaFields.getFormatField(n, "DT", 8);

		String expected = "20102008";
		assertEquals(expected, actual);
	}

	@Test
	public void testBuildNonPositionalDate1() {

		Date n = new Date(1224460800000L); // 20/10/2008

		String[] actual = IvaFields.getFormatField(n, "DT");

		String[] expected = { "        20102008" };
		assertEquals(expected[0], actual[0]);
	}

	@Test
	public void testBuildNonPositionalDate2() {

		Date n = new Date(1224460800000L); // 20/10/2008
		String[] actual = IvaFields.getFormatField(n, "DA");

		String[] expected = { "            2008" };
		assertEquals(expected[0], actual[0]);
	}
}