package it.iubar.iva.export.iubar_iva;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExportAeTxt {
	private static final String DECIMAL_PATTERN = "###.###";
	private static final String DATE_PATTERN = "ddMMyyyy";

	public String buildPositionalField(BigDecimal value, String format, int lenght) {
		DecimalFormat myFormatter = new DecimalFormat(DECIMAL_PATTERN);

		String str = myFormatter.format(value);
		return buildPositionalField(str, format, lenght);
	}

	public String buildNonPositionalField(BigDecimal value, String format, int lenght) {
		DecimalFormat myFormatter = new DecimalFormat(DECIMAL_PATTERN);

		String str = myFormatter.format(value);
		return buildNonPositionalField(str, format, lenght);
	}

	public String buildPositionalField(Date value, String format, int lenght) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(DATE_PATTERN);

		String str = myFormatter.format(value);

		return buildPositionalField(str, format, lenght);
	}

	public String buildNonPositionalField(Date value, String format, int lenght) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(DATE_PATTERN);

		String str = myFormatter.format(value);

		return buildNonPositionalField(str, format, lenght);

	}

	public String buildPositionalField(String value, String format, int lenght) {
		Formatter fm = new Formatter();
		String formattedString = new String();
		Utility ut = new Utility();

		int delta = lenght - value.length();

		if (delta >= 0) {
			switch (format.toUpperCase()) {
			case "AN":
				formattedString = fm.stringFormatterDelta(value, delta, 1);
				break;
			case "CF":
				if (value.length() == 16 || value.length() == 11) {
					formattedString = fm.stringFormatterDelta(value, delta, 1);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE");
				}
				break;
			case "CN":
				if (value.length() == 11) {
					if (value.matches("[0-9]+")) {
						formattedString = fm.stringFormatterDelta(value, delta, 0);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE NUMERICO");
					}
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE NUMERICO");
				}
				break;
			case "PI":
				if (value.length() == 11) {
					if (value.matches("[0-9]+")) {
						formattedString = fm.stringFormatterDelta(value, delta, 0);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE NUMERICO");
					}
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione PARTITA IVA");
				}
				break;
			case "DT":

				if (value.length() == 8) {
					if (value.matches("[0-9]+")) {
						if (ut.dateFormatCheck(value)) {
							formattedString = fm.stringFormatterDelta(value, delta, 0);
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione DATA");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione DATA");
					}
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione DATA");
				}
				break;
			case "NU":
				if (value.indexOf("-") == -1) {
					if (value.matches("[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+")) {
						formattedString = fm.stringFormatterDelta(value, delta, 3);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO POSITIVO");
					}
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO POSITIVO");
				}
				break;
			case "PN":
				if (value.length() == 2) {
					if (value.matches("[a-zA-Z]+")) {
						formattedString = fm.stringFormatterDelta(value, delta, 0);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
					}
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
				}
				break;
			case "PR":
				if (value.length() == 2) {
					if (value.matches("[a-zA-Z]+")) {
						if (value.equals("EE") || value.equals("00")) {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
						} else {
							formattedString = fm.stringFormatterDelta(value, delta, 0);
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
					}
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
				}
				break;
			case "CB":
				if (value.length() == 1) {
					if (value.equals("0") || value.equals("1")) {
						formattedString = fm.stringFormatterDelta(value, delta, 0);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CASELLA BARRATA");
					}
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

	public String buildNonPositionalField(String value, String format, int lenght) {
		Formatter fm = new Formatter();
		String formattedString = new String();
		Utility ut = new Utility();

		int delta = lenght - value.length();

		if (delta >= 0) {
			switch (format.toUpperCase()) {
			case "AN":
				formattedString = fm.stringFormatterDelta(value, delta, 1);
				break;
			case "CB":
				if (lenght == 16) {
					if (value.length() == 1) {
						if (value.equals("0") || value.equals("1")) {
							formattedString = fm.stringFormatterDelta(value, delta, 3);
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione CASELLA BARRATA");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CASELLA BARRATA");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo CASELLA BARRATA");
				}
				break;
			case "CB12":
				if (lenght == 16) {
					if (value.length() == 12) {
						if (value.matches("[0-1]+")) {
							formattedString = fm.stringFormatterDelta(value, delta, 3);
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione CASELLA BARRATA 12 VALORI");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CASELLA BARRATA 12 VALORI");
					}
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CASELLA BARRATA 12 VALORI");
				}
				break;
			case "CF":
				if (lenght == 16) {
					if (value.length() == 16 || value.length() == 11) {
						formattedString = fm.stringFormatterDelta(value, delta, 1);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo CODICE FISCALE");
				}
				break;
			case "CN":
				if (lenght == 16) {
					if (value.length() == 11) {
						if (value.matches("[0-9]+")) {
							formattedString = fm.stringFormatterDelta(value, delta, 1);
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE NUMERICO");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE NUMERICO");
					}
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CODICE FISCALE NUMERICO");
				}
				break;
			case "PI":
				if (lenght == 16) {
					if (value.length() == 11) {
						if (value.matches("[0-9]+")) {
							formattedString = fm.stringFormatterDelta(value, delta, 1);
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione CODICE FISCALE NUMERICO");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione PARTITA IVA");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo PARTITA IVA");
				}
				break;
			case "DA":
				if (lenght == 16) {
					if (value.length() == 8) {
						if (value.matches("[0-9]+")) {
							if (ut.dateFormatCheck(value)) {
								formattedString = fm.stringFormatterDelta(value.substring(4, 8), delta + 4, 2);
							} else {
								throw new IllegalArgumentException(
										"La stringa inserita non è coerente con il tipo di formattazione DATA ANNO");
							}
						} else {
							throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA ANNO");
						}
					} else {
						throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA ANNO");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA ANNO");
				}
				break;
			case "DT":
				if (lenght == 16) {
					if (value.length() == 8) {
						if (value.matches("[0-9]+")) {
							if (ut.dateFormatCheck(value)) {
								if (ut.dateRange(value, 1880, Year.now().getValue())) {
									formattedString = fm.stringFormatterDelta(value, delta, 2);
								} else {
									throw new IllegalArgumentException(
											"La stringa inserita non è coerente con il tipo di formattazione DATA 1880 - OGGI");
								}
							} else {
								throw new IllegalArgumentException(
										"La stringa inserita non è coerente con il tipo di formattazione DATA 1880 - OGGI");
							}
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione DATA 1880 - OGGI");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione DATA 1880 - OGGI");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA 1880 - OGGI");
				}
				break;
			case "DN":
				if (lenght == 16) {
					if (value.length() == 8) {
						if (value.matches("[0-9]+")) {
							if (ut.dateFormatCheck(value)) {
								if (ut.dateRange(value, 1980, 2050)) {
									formattedString = fm.stringFormatterDelta(value, delta, 2);
								} else {
									throw new IllegalArgumentException(
											"La stringa inserita non è coerente con il tipo di formattazione DATA 1980 - 2050");
								}
							} else {
								throw new IllegalArgumentException(
										"La stringa inserita non è coerente con il tipo di formattazione DATA 1980 - 2050");
							}
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione DATA 1980 - 2050");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione DATA 1980 - 2050");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA 1980 - 2050");
				}
				break;
			case "D4":
				if (lenght == 16) {
					if (value.length() == 8) {
						if (value.matches("[0-9]+")) {
							if (ut.dateFormatCheck(value)) {
								formattedString = fm.stringFormatterDelta(value.substring(0, 4), delta + 4, 2);
							} else {
								throw new IllegalArgumentException(
										"La stringa inserita non è coerente con il tipo di formattazione DATA GGMM");
							}
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione DATA GGMM");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione DATA GGMM");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA GGMM");
				}
				break;
			case "D6":
				if (lenght == 16) {
					if (value.length() == 8) {
						if (value.matches("[0-9]+")) {
							if (ut.dateFormatCheck(value)) {
								formattedString = fm.stringFormatterDelta(value.substring(2, 8), delta + 2, 2);
							} else {
								throw new IllegalArgumentException(
										"La stringa inserita non è coerente con il tipo di formattazione DATA MMAAAA");
							}
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione DATA MMAAAA");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione DATA MMAAAA");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo DATA MMAAAA");
				}
				break;
			case "NP":
				if (value.indexOf("-") == -1) {
					if (value.matches("[0-9]+")) {
						formattedString = fm.stringFormatterDelta(value, delta, 2);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO POSITIVO");
					}
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO POSITIVO");
				}
				break;
			case "NU":
				if (value.matches("[0-9]+") || value.matches("[-]{1}[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+") || value.matches("[-]{1}[0-9]+[,.]?[0-9]+")) {
					formattedString = fm.stringFormatterDelta(value, delta, 2);
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO");
				}
				break;
			case "NX":
				if (lenght < 17) {
					if (value.matches("[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+")) {
						formattedString = fm.stringFormatterDelta(value, delta, 2);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione CAMPO NUMERICO MAX 16");
					}
				} else {
					throw new IllegalArgumentException(
							"Lunghezza del campo non conforme al tipo CAMPO NUMERICO MAX 16");
				}
				break;
			case "PC":
				if (Double.parseDouble(value) <= 100) {
					if (value.matches("[0-9]") || value.matches("[0-9]{1,3}[,.]?[0-9]{1,3}")) {
						formattedString = fm.stringFormatterDelta(value, delta, 2);
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione PERCENTUALE");
					}
				} else {
					throw new IllegalArgumentException(
							"La stringa inserita non è coerente con il tipo di formattazione PERCENTUALE");
				}
				break;
			case "PR":
				if (lenght == 16) {
					if (value.length() == 2) {
						if (value.matches("[a-zA-Z]+")) {
							if (value.equals("EE") || value.equals("00")) {
								throw new IllegalArgumentException(
										"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
							} else {
								formattedString = fm.stringFormatterDelta(value, delta, 1);
							}
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo SIGLA PROVINCIALE");
				}
				break;
			case "PN":
				if (lenght == 16) {
					if (value.length() == 2) {
						if (value.matches("[a-zA-Z]+") || value.matches("[0]{2}")) {
							formattedString = fm.stringFormatterDelta(value, delta, 1);
						} else {
							throw new IllegalArgumentException(
									"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
						}
					} else {
						throw new IllegalArgumentException(
								"La stringa inserita non è coerente con il tipo di formattazione SIGLA PROVINCIALE");
					}
				} else {
					throw new IllegalArgumentException("Lunghezza del campo non conforme al tipo SIGLA PROVINCIALE");
				}
				break;
			case "QU":
				if (value.matches("[0-9]") || value.matches("[0-9]+[,.]?[0-9]{1,5}")) {
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
