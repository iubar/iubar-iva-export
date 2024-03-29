package iubar.iva.export;

import iubar.iva.export.Formatter;

import static org.junit.Assert.*;
import org.junit.Test;

public class FormatterTest {
	/*
	 * @Test public void test0() { // Formatter fm = new Formatter(); String
	 * actual = null;
	 * 
	 * actual = fm.stringFormatter("ciao", 8, 0); String expected = "ciao";
	 * assertEquals(expected, actual); }
	 * 
	 * @Test public void test1() { // Formatter fm = new Formatter(); String
	 * actual = null;
	 * 
	 * actual = fm.stringFormatter("ciao", 8, 1); String expected = "ciao    ";
	 * assertEquals(expected, actual); }
	 * 
	 * @Test public void test2() { // Formatter fm = new Formatter(); String
	 * actual = null;
	 * 
	 * actual = fm.stringFormatter("ciao", 8, 2); String expected = "    ciao";
	 * assertEquals(expected, actual); }
	 * 
	 * @Test public void test3() { // Formatter fm = new Formatter(); String
	 * actual = null;
	 * 
	 * actual = fm.stringFormatter("ciao", 8, 3); String expected = "0000ciao";
	 * assertEquals(expected, actual); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void test4()
	 * throws IllegalArgumentException { Formatter fm = new Formatter(); String
	 * actual = null; actual = fm.stringFormatter("ciao", 2, 1); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void test5()
	 * throws IllegalArgumentException { Formatter fm = new Formatter(); String
	 * actual = null; actual = fm.stringFormatter("ciao", 8, 4); }
	 */

	@Test
	public void test0Delta() { //
		Formatter fm = new Formatter();
		String actual = null;
		String expected = "ciao";

		actual = fm.stringFormatterDelta("ciao", 0, FormatType.NOFORMAT);
		assertEquals(expected, actual);
	}

	@Test
	public void test1Delta() { //
		Formatter fm = new Formatter();
		String actual = null;
		String expected = "ciao    ";

		actual = fm.stringFormatterDelta("ciao", 4, FormatType.SXSPACE);
		assertEquals(expected, actual);
	}

	@Test
	public void test2Delta() { //
		Formatter fm = new Formatter();
		String actual = null;
		String expected = "    ciao";

		actual = fm.stringFormatterDelta("ciao", 4, FormatType.DXSPACE);
		assertEquals(expected, actual);
	}

	@Test
	public void test3Delta() { //
		Formatter fm = new Formatter();
		String actual = null;
		String expected = "0000ciao";

		actual = fm.stringFormatterDelta("ciao", 4, FormatType.DXZERO);
		assertEquals(expected, actual);
	}
}
