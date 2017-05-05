package iubar.iva.export;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IvaFields {

	private static final String DECIMAL_PATTERN = "###.###";
	private static final String DATE_PATTERN = "ddMMyyyy";
	private static final int NON_POSITIONAL_STD_LENGHT = 16;

	public static String getFormatField(BigDecimal value, String format, int length) {
		DecimalFormat myFormatter = new DecimalFormat(DECIMAL_PATTERN);

		String str = myFormatter.format(value);
		return getFormatField(str, format, length);
	}

	public static String[] getFormatField(BigDecimal value, String format) {
		DecimalFormat myFormatter = new DecimalFormat(DECIMAL_PATTERN);

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

	public static String getFormatField(String value, String format, int length) {
		Formatter fm = new Formatter();
		FormatChecker fc = new FormatChecker();
		String formattedString = "";

		int delta = length - value.length();

		if (delta >= 0) {
			switch (format.toUpperCase()) {
			case "AN":
				formattedString = fm.stringFormatterDelta(value, delta, 1);
				break;
			case "CF":
				if (fc.checkPositionalCF(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 1);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE");
				}
				break;
			case "CN":
				if (fc.checkPositionalCN(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 0);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE NUMERICO");
				}
				break;
			case "PI":
				if (fc.checkPositionalPI(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 0);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione PARTITA IVA");
				}
				break;
			case "DT":
				if (fc.checkPositionalDT(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 0);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione DATA");
				}
				break;
			case "NU":
				if (fc.checkPositionalNU(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 3);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO POSITIVO");
				}
				break;
			case "PN":
				if (fc.checkPositionalPN(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 0);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
				}
				break;
			case "PR":
				if (fc.checkPositionalPR(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 0);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
				}
				break;
			case "CB":
				if (fc.checkPositionalCB(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 0);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CASELLA BARRATA");
				}
				break;
			}
		} else if (delta < 0) {
			throw new IllegalArgumentException("La stringa inserita è di dimensioni maggiori al campo indicato");
		}
		return formattedString;
	}

	private static String[] splitter(String value) {
		int fieldNumber = value.length() / NON_POSITIONAL_STD_LENGHT;
		int modulo = value.length() % NON_POSITIONAL_STD_LENGHT;
		String[] out;

		if (modulo == 0) {
			out = new String[fieldNumber];
		} else {
			out = new String[fieldNumber + 1];
			out[out.length - 1] = value.substring(NON_POSITIONAL_STD_LENGHT * fieldNumber,
					(NON_POSITIONAL_STD_LENGHT * fieldNumber) + modulo);
		}

		for (int i = 0; i < fieldNumber; i++) {
			out[i] = value.substring(NON_POSITIONAL_STD_LENGHT * i,
					NON_POSITIONAL_STD_LENGHT * i + (NON_POSITIONAL_STD_LENGHT));
		}

		return out;
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
				formattedString[i] = fm.stringFormatterDelta(split[i], delta, 1);
				break;
			case "CB":
				if (fc.checkNonPositionalCB(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 3);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo CASELLA BARRATA");
				}
				break;
			case "CB12":
				if (fc.checkNonPositionalCB12(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 3);
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CASELLA BARRATA 12 VALORI");
				}
				break;
			case "CF":
				if (fc.checkNonPositionalCF(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 1);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo CODICE FISCALE");
				}
				break;
			case "CN":
				if (fc.checkNonPositionalCN(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 1);
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CODICE FISCALE NUMERICO");
				}
				break;
			case "PI":
				if (fc.checkNonPositionalPI(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 1);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo PARTITA IVA");
				}
				break;
			case "DA":
				if (fc.checkNonPositionalDA(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i].substring(4, 8), delta + 4, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA ANNO");
				}
				break;
			case "DT":
				if (fc.checkNonPositionalDT(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA 1880 - OGGI");
				}
				break;
			case "DN":
				if (fc.checkNonPositionalDN(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA 1980 - 2050");
				}
				break;
			case "D4":
				if (fc.checkNonPositionalD4(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i].substring(0, 4), delta + 4, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA GGMM");
				}
				break;
			case "D6":
				if (fc.checkNonPositionalD6(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i].substring(2, 8), delta + 2, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA MMAAAA");
				}
				break;
			case "NP":
				if (fc.checkNonPositionalNP(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO POSITIVO");
				}
				break;
			case "NU":
				if (fc.checkNonPositionalNU(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO");
				}
				break;
			case "NX":
				if (fc.checkNonPositionalNX(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 2);
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CAMPO NUMERICO MAX 16");
				}
				break;
			case "PC":
				if (fc.checkNonPositionalPC(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione PERCENTUALE");
				}
				break;
			case "PR":
				if (fc.checkNonPositionalPR(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 1);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo SIGLA PROVINCIALE");
				}
				break;
			case "PN":
				if (fc.checkNonPositionalPN(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 1);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo SIGLA PROVINCIALE");
				}
				break;
			case "QU":
				if (fc.checkNonPositionalQU(split[i])) {
					formattedString[i] = fm.stringFormatterDelta(split[i], delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione VALORE DECIMALE ");
				}
				break;
			}
		}
		return formattedString;
	}

	public static void main(String[] args) {

		String a = "abcdefghijklmnopabcdefgh";

		String[] out = getFormatField(a, "AN");

		for (int i = 0; i < out.length; i++) {
			System.out.println(out[i].toString() + "a");
		}

	}
}