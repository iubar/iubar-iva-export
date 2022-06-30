package iubar.iva.export;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IvaFields {

	private static final String DATE_PATTERN = "ddMMyyyy";
	private static final int NON_POSITIONAL_STD_LENGHT = 16;

	public static String getFormatField(BigDecimal value, String format, int length) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ITALY);
		DecimalFormat myFormatter = (DecimalFormat) nf;

		String str = myFormatter.format(value);

		return getFormatField(str, format, length);
	}

	public static String[] getFormatField(BigDecimal value, String format) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ITALY);
		DecimalFormat myFormatter = (DecimalFormat) nf;

		String str = myFormatter.format(value);

		return getFormatField(str, format);
	}

	public static String getFormatField(Date value, String format, int length) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(DATE_PATTERN);

		String str = myFormatter.format(value);

		return getFormatField(str, format, length);
	}

	public static String[] getFormatField(Date value, String format) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(DATE_PATTERN);

		String str = myFormatter.format(value);

		return getFormatField(str, format);
	}

	public static String getFormatField(Boolean value, String format, int length) {
		String str = "";

		if (value.booleanValue()) {
			str = "1";
		} else {
			str = "0";
		}

		return getFormatField(str, format, length);
	}

	public static String[] getFormatField(Boolean value, String format) {
		String str = "";

		if (value.booleanValue()) {
			str = "1";
		} else {
			str = "0";
		}

		return getFormatField(str, format);
	}

	public static String getFormatField(String value, String format, int length) {
		Formatter fm = new Formatter();
		FormatChecker fc = new FormatChecker();
		String formattedString = "";

		int delta = length - value.length();

		if (delta >= 0) {	// Uhm, da riscrivere evitando tutto questo nesting di switch e if
			switch (format.toUpperCase()) {
			case "AN":
				formattedString = fm.stringFormatterDelta(value, delta, FormatType.SXSPACE);
				break;
			case "CF":
				if (fc.checkPositionalCF(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, FormatType.SXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' un CODICE FISCALE" + "\nValore: " + value
							+ " " + "Lunghezza Campo" + ": " + length);
				}
				break;
			case "CN":
				if (fc.checkPositionalCN(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, FormatType.DXZERO);
				} else {
					throw new IllegalArgumentException("La stringa non e' un CODICE FISCALE NUMERICO" + "\nValore: "
							+ value + "Lunghezza Campo: " + length);
				}
				break;
			case "PI":
				if (fc.checkPositionalPI(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, FormatType.DXZERO);
				} else {
					throw new IllegalArgumentException(
							"La stringa non e' una PARTITA IVA" + "\nValore: " + value + "Lunghezza Campo: " + length);
				}
				break;
			case "DT":
				if (fc.checkPositionalDT(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, FormatType.DXZERO);
				} else {
					throw new IllegalArgumentException(
							"La stringa non e' una DATA" + "\nValore: " + value + "Lunghezza Campo: " + length);
				}
				break;
			case "NU":
				if (fc.checkPositionalNU(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, FormatType.DXZERO);
				} else {
					throw new IllegalArgumentException("La stringa non e' un CAMPO NUMERICO POSITIVO" + "\nValore: "
							+ value + "Lunghezza Campo: " + length);
				}
				break;
			case "PN":
				if (fc.checkPositionalPN(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, FormatType.SXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' una SIGLA PROVINCIALE" + "\nValore: " + value
							+ "Lunghezza Campo: " + length);
				}
				break;
			case "PR":
				if (fc.checkPositionalPR(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, FormatType.SXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' una SIGLA PROVINCIALE" + "\nValore: " + value
							+ "Lunghezza Campo: " + length);
				}
				break;
			case "CB":
				if (fc.checkPositionalCB(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, FormatType.DXZERO);
				} else {
					throw new IllegalArgumentException(
							"La stringa non e' un CAMPO BARRATO" + "\nValore: " + value + "Lunghezza Campo: " + length);
				}
				break;
			default:
				break;
			}
		} else if (delta < 0) {
			throw new IllegalArgumentException("LA STRINGA E' MAGGIORE DEL CAMPO" + "\nValore: " + value + " "
					+ value.length() + "Lunghezza Campo: " + length);
		}
		return formattedString;
	}

	public static String[] getFormatField(String value, String format) {
		Formatter fm = new Formatter();
		FormatChecker fc = new FormatChecker();

		String[] split = splitter(value);
		int fieldNumber = split.length;
		String[] formattedString = new String[fieldNumber];

		for (int i = 0; i < fieldNumber; i++) {
			int delta = NON_POSITIONAL_STD_LENGHT - split[i].length();

			switch (format.toUpperCase()) {
			case "AN":
				formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.SXSPACE);
				break;
			case "CB":
				if (fc.checkNonPositionalCB(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' un CAMPO BARRATO" + "\nValore: " + value);
				}
				break;
			case "CB12":
				if (fc.checkNonPositionalCB12(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException(
							"La stringa non e' un CAMPO BARRATO A 12 VALORI" + "\nValore: " + value);
				}
				break;
			case "CF":
				if (fc.checkNonPositionalCF(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.SXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' un CODICE FISCALE" + "\nValore: " + value);
				}
				break;
			case "CN":
				if (fc.checkNonPositionalCN(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.SXSPACE);
				} else {
					throw new IllegalArgumentException(
							"La stringa non e' un CODICE FISCALE NUMERICO" + "\nValore: " + value);
				}
				break;
			case "PI":
				if (fc.checkNonPositionalPI(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.SXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' una PARTITA IVA" + "\nValore: " + value);
				}
				break;
			case "DA":
				if (fc.checkNonPositionalDA(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i].substring(4, 8), delta + 4,
							FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' una DATA ANNO" + "\nValore: " + value);
				}
				break;
			case "DT":
				if (fc.checkNonPositionalDT(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La data deve essere tra 1880 - OGGI" + "\nValore: " + value);
				}
				break;
			case "DN":
				if (fc.checkNonPositionalDN(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' una DATA 1980 - 2050" + "\nValore: " + value);
				}
				break;
			case "D4":
				if (fc.checkNonPositionalD4(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i].substring(0, 4), delta + 4,
							FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' una DATA GGMM" + "\nValore: " + value);
				}
				break;
			case "D6":
				if (fc.checkNonPositionalD6(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i].substring(2, 8), delta + 2,
							FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' una DATA MMAAAA" + "\nValore: " + value);
				}
				break;
			case "NP":
				if (fc.checkNonPositionalNP(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException(
							"La stringa non e' un CAMPO NUMERICO POSITIVO" + "\nValore: " + value);
				}
				break;
			case "NU":
				formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				break;
			case "N1":
			case "N2":
			case "N3":
			case "N4":
			case "N5":
			case "N6":
			case "N7":
			case "N8":
			case "N9":
			case "N10":
			case "N11":
			case "N12":
			case "N13":
			case "N14":
			case "N15":
			case "N16":
				if (fc.checkNonPositionalNX(split[i], format)) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' un CAMPO NUMERIDO DI MASSIMO"
							+ format.substring(1) + "CIFRE" + "\nValore: " + value);
				}
				break;
			case "PC":
				if (fc.checkNonPositionalPC(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' una PERCENTUALE" + "\nValore: " + value);
				}
				break;
			case "PR":
				if (fc.checkNonPositionalPR(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.SXSPACE);
				} else {
					throw new IllegalArgumentException(
							"La stringa non e' una SIGLA PROVINCIALE" + "\nValore: " + value);
				}
				break;
			case "PN":
				if (fc.checkNonPositionalPN(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.SXSPACE);
				} else {
					throw new IllegalArgumentException(
							"La stringa non e' una SIGLA PROVINCIALE" + "\nValore: " + value);
				}
				break;
			case "QU":
				if (fc.checkNonPositionalQU(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, FormatType.DXSPACE);
				} else {
					throw new IllegalArgumentException("La stringa non e' un VALORE DECIMALE" + "\nValore: " + value);
				}
				break;
			default:
				break;
			}
		}
				
		return formattedString;	
	}

	private static String[] splitter(String value) {
		String tmp = value;

		if(tmp.length() == 0) {
			tmp = "                ";
		}
		
		int fieldNumber = tmp.length() / NON_POSITIONAL_STD_LENGHT; 
		int modulo = tmp.length() % NON_POSITIONAL_STD_LENGHT;
		String[] out;
		
		if (modulo == 0) {
			out = new String[fieldNumber];
		} else {
			out = new String[fieldNumber + 1];
			out[out.length - 1] = tmp.substring(NON_POSITIONAL_STD_LENGHT * fieldNumber,
					(NON_POSITIONAL_STD_LENGHT * fieldNumber) + modulo);
		}

		for (int i = 0; i < fieldNumber; i++) {
			out[i] = tmp.substring(NON_POSITIONAL_STD_LENGHT * i,
					NON_POSITIONAL_STD_LENGHT * i + (NON_POSITIONAL_STD_LENGHT));
		}
		
		return out;
	}
}
