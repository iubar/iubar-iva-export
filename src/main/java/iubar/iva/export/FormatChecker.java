package iubar.iva.export;

//import java.util.Calendar;
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
	}

	public boolean checkPositionalDT(String value) {
		if (value.length() == 8 && value.matches("[0-9]+") && dateFormatCheck(value)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPositionalNU(String value) {
		if (value.indexOf("-") == -1)
			return (value.matches("[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+"));
		return false;
	}

	public boolean checkPositionalPN(String value) {
		if (value.length() == 2)
			return (value.matches("[a-zA-Z]+") || value.matches("[0]{2}"));
		return false;
	}

	public boolean checkPositionalPR(String value) {
		if (value.length() == 2 && value.matches("[a-zA-Z]+") && !"EE".equals(value) && !"00".equals(value)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkPositionalCB(String value) {
		if (value.length() == 1 && ("0".equals(value) || "1".equals(value))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalCB(String value) {
		return this.checkPositionalCB(value);
	}

	public boolean checkNonPositionalCB12(String value) {
		if (value.length() == 12 && value.matches("[0-1]+")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalCF(String value) {
		return this.checkPositionalCF(value);
	}

	public boolean checkNonPositionalCN(String value) {
		return this.checkPositionalCN(value);
	}

	public boolean checkNonPositionalPI(String value) {
		return this.checkNonPositionalCN(value);
	}

	public boolean checkNonPositionalDA(String value) {
		return this.checkPositionalDT(value);
	}

	public boolean checkNonPositionalDT(String value) {
		
		
		if (this.checkNonPositionalDA(value) && this.dateRange(value, 1880,Year.now().getValue()/*Calendar.getInstance().get(Calendar.YEAR)*/)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalDN(String value) {
		if (this.checkNonPositionalDA(value) && this.dateRange(value, 1980, 2050)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalD4(String value) {
		return this.checkNonPositionalDA(value);
	}

	public boolean checkNonPositionalD6(String value) {
		return this.checkNonPositionalDA(value);
	}

	public boolean checkNonPositionalNP(String value) {
		return this.checkPositionalNU(value);
	}

	public boolean checkNonPositionalNU(String value) {
		if (value.matches("[0-9]+") || value.matches("[-]{1}[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+")
				|| value.matches("[-]{1}[0-9]+[,.]?[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkNonPositionalNX(String value, String format) {
		if (value.length() == Integer.parseInt(format.substring(1)) && value.length() < 17 && (value.matches("[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+"))) {
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

	public boolean checkNonPositionalPR(String value) {
		return this.checkPositionalPR(value);
	}

	public boolean checkNonPositionalPN(String value) {
		return this.checkPositionalPN(value);
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
