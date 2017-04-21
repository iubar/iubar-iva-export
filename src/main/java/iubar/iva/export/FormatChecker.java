package iubar.iva.export;

import java.time.Year;

public class FormatChecker {

	public boolean checkPositionalCF(String value) {
		if ((value.length() == 16 || value.length() == 11) && value.matches("^[a-zA-Z0-9]*$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPositionalCN(String value) {
		if (value.length() == 11 && value.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPositionalPI(String value) {
		return this.checkPositionalCN(value);

		// if (value.length() == 11 && value.matches("[0-9]+")) {
		// return true;
		// } else {
		// return false;
		// }
	}

	public boolean checkPositionalDT(String value) {
		if (value.length() == 8 && value.matches("[0-9]+") && dateFormatCheck(value)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPositionalNU(String value) {
		if (value.indexOf("-") == -1 && (value.matches("[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+"))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPositionalPN(String value) {
		if (value.length() == 2 && (value.matches("[a-zA-Z]+") || value.matches("[0]{2}"))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPositionalPR(String value) {
		if (value.length() == 2 && value.matches("[a-zA-Z]+") && !value.equals("EE") && !value.equals("00")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPositionalCB(String value) {
		if (value.length() == 1 && (value.equals("0") || value.equals("1"))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalCB(String value, int length) {
		// if (length == 16 && value.length() == 1 && (value.equals("0") ||
		// value.equals("1"))) {
		if (length == 16 && this.checkPositionalCB(value)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalCB12(String value, int length) {
		if (length == 16 && value.length() == 12 && value.matches("[0-1]+")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalCF(String value, int length) {
		// if (length == 16 && (value.length() == 16 || value.length() == 11) &&
		// value.matches("[A-Za-z0-9]")) {
		if (length == 16 && this.checkPositionalCF(value)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalCN(String value, int length) {
		// if (length == 16 && value.length() == 11 && value.matches("[0-9]+"))
		// {
		if (length == 16 && this.checkPositionalCN(value)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalPI(String value, int length) {
		return this.checkNonPositionalCN(value, length);
		// if (length == 16 && value.length() == 11 && value.matches("[0-9]+"))
		// {
		// return true;
		// } else {
		// return false;
		// }
	}

	public boolean checkNonPositionalDA(String value, int length) {
		// if (length == 16 && value.length() == 8 && value.matches("[0-9]+") &&
		// this.dateFormatCheck(value)) {
		if (length == 16 && this.checkPositionalDT(value)) {

			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalDT(String value, int length) {
		// if (lenght == 16 && value.length() == 8 && value.matches("[0-9]+") &&
		// this.dateFormatCheck(value) && this.dateRange(value, 1880,
		// Year.now().getValue()))
		if (this.checkNonPositionalDA(value, length) && this.dateRange(value, 1880, Year.now().getValue())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalDN(String value, int length) {
		// if (length == 16 && value.length() == 8 && value.matches("[0-9]+") &&
		// this.dateFormatCheck(value) && this.dateRange(value, 1980, 2050)) {
		if (this.checkNonPositionalDA(value, length) && this.dateRange(value, 1980, 2050)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalD4(String value, int length) {
		return this.checkNonPositionalDA(value, length);
		// if (length == 16 && value.length() == 8 && value.matches("[0-9]+") &&
		// this.dateFormatCheck(value)) {
		// return true;
		// } else {
		// return false;
		// }
	}

	public boolean checkNonPositionalD6(String value, int length) {
		return this.checkNonPositionalDA(value, length);
		// if (length == 16 && value.length() == 8 && value.matches("[0-9]+") &&
		// this.dateFormatCheck(value)) {
		// return true;
		// } else {
		// return false;
		// }
	}

	public boolean checkNonPositionalNP(String value) {
		return this.checkPositionalNU(value);
		// if (value.indexOf("-") == -1 && (value.matches("[0-9]+") ||
		// value.matches("[0-9]+[,.]?[0-9]+"))) {
		// return true;
		// } else {
		// return false;
		// }
	}

	public boolean checkNonPositionalNU(String value) {
		if (value.matches("[0-9]+") || value.matches("[-]{1}[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+")
				|| value.matches("[-]{1}[0-9]+[,.]?[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalNX(String value, int length) {
		if (length < 17 && (value.matches("[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+"))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalPC(String value) {
		if (Double.parseDouble(value) <= 100
				&& (value.matches("[0-9]") || value.matches("[0-9]{1,3}[,.]?[0-9]{1,3}"))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalPR(String value, int length) {
		// if (length == 16 && value.length() == 2 && value.matches("[a-zA-Z]+")
		// && (!value.equals("EE") || !value.equals("00"))) {
		if (length == 16 && this.checkPositionalPR(value)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalPN(String value, int length) {
		// if (length == 16 && value.length() == 2 && value.matches("[a-zA-Z]+")
		// && (!value.equals("EE") || !value.equals("00"))) {
		if (length == 16 && this.checkPositionalPN(value)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalQU(String value) {
		if (value.matches("[0-9]") || value.matches("[0-9]+[,.]?[0-9]{1,5}")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean dateFormatCheck(String date) {
		if (date.length() == 8 || date.length() == 4) {
			String s1 = (String) date.subSequence(0, 2);
			String s2 = (String) date.subSequence(2, 4);

			if (Integer.parseInt(s1) > 0 && Integer.parseInt(s1) < 32) {
				if (Integer.parseInt(s2) > 0 && Integer.parseInt(s2) < 13) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else if (date.length() == 6) {
			String s1 = (String) date.subSequence(0, 2);

			if (Integer.parseInt(s1) > 0 && Integer.parseInt(s1) < 13) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	private boolean dateRange(String date, int start, int end) {
		Integer year = Integer.parseInt((String) date.subSequence(4, 8));
		if (year > start && year < end) {
			return true;
		} else {
			return false;
		}
	}
}
