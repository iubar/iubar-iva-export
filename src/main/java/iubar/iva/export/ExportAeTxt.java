package iubar.iva.export;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportAeTxt {

	private static final String DECIMAL_PATTERN = "###.###";
	private static final String DATE_PATTERN = "ddMMyyyy";

	public String buildPositionalField(BigDecimal value, String format, int length) {
		DecimalFormat myFormatter = new DecimalFormat(DECIMAL_PATTERN);

		String str = myFormatter.format(value);
		return buildPositionalField(str, format, length);
	}

	public String buildNonPositionalField(BigDecimal value, String format, int length) {
		DecimalFormat myFormatter = new DecimalFormat(DECIMAL_PATTERN);

		String str = myFormatter.format(value);
		return buildNonPositionalField(str, format, length);
	}

	public String buildPositionalField(Date value, String format, int length) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(DATE_PATTERN);

		String str = myFormatter.format(value);

		return buildPositionalField(str, format, length);
	}

	public String buildNonPositionalField(Date value, String format, int length) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(DATE_PATTERN);

		String str = myFormatter.format(value);

		return buildNonPositionalField(str, format, length);

	}

	public String buildPositionalField(String value, String format, int length) {
		Formatter fm = new Formatter();
		FormatChecker fc = new FormatChecker();
		String formattedString = new String();

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

	public String buildNonPositionalField(String value, String format, int length) {
		Formatter fm = new Formatter();
		FormatChecker fc = new FormatChecker();
		String formattedString = new String();

		int delta = length - value.length();

		if (delta >= 0) {
			switch (format.toUpperCase()) {
			case "AN":
				formattedString = fm.stringFormatterDelta(value, delta, 1);
				break;
			case "CB":
				if (fc.checkNonPositionalCB(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 3);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo CASELLA BARRATA");
				}
				break;
			case "CB12":
				if (fc.checkNonPositionalCB12(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 3);
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CASELLA BARRATA 12 VALORI");
				}
				break;
			case "CF":
				if (fc.checkNonPositionalCF(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 1);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo CODICE FISCALE");
				}
				break;
			case "CN":
				if (fc.checkNonPositionalCN(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 1);
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CODICE FISCALE NUMERICO");
				}
				break;
			case "PI":
				if (fc.checkNonPositionalPI(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 1);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo PARTITA IVA");
				}
				break;
			case "DA":
				if (fc.checkNonPositionalDA(value, length)) {
					formattedString = fm.stringFormatterDelta(value.substring(4, 8), delta + 4, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA ANNO");
				}
				break;
			case "DT":
				if (fc.checkNonPositionalDT(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA 1880 - OGGI");
				}
				break;
			case "DN":
				if (fc.checkNonPositionalDN(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA 1980 - 2050");
				}
				break;
			case "D4":
				if (fc.checkNonPositionalD4(value, length)) {
					formattedString = fm.stringFormatterDelta(value.substring(0, 4), delta + 4, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA GGMM");
				}
				break;
			case "D6":
				if (fc.checkNonPositionalD6(value, length)) {
					formattedString = fm.stringFormatterDelta(value.substring(2, 8), delta + 2, 2);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA MMAAAA");
				}
				break;
			case "NP":
				if (fc.checkNonPositionalNP(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO POSITIVO");
				}
				break;
			case "NU":
				if (fc.checkNonPositionalNU(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO");
				}
				break;
			case "NX":
				if (fc.checkNonPositionalNX(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 2);
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CAMPO NUMERICO MAX 16");
				}
				break;
			case "PC":
				if (fc.checkNonPositionalPC(value)) {
						formattedString = fm.stringFormatterDelta(value, delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione PERCENTUALE");
				}
				break;
			case "PR":
				if (fc.checkNonPositionalPR(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 1);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo SIGLA PROVINCIALE");
				}
				break;
			case "PN":
				if (fc.checkNonPositionalPN(value, length)) {
					formattedString = fm.stringFormatterDelta(value, delta, 1);
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo SIGLA PROVINCIALE");
				}
				break;
			case "QU":
				if (fc.checkNonPositionalQU(value)) {
					formattedString = fm.stringFormatterDelta(value, delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione VALORE DECIMALE ");
				}
				break;
			}

		} else if (delta < 0) {
			throw new IllegalArgumentException("La stringa inserita è di dimensioni maggiori al campo indicato");
		}
		return formattedString;
	}
}
