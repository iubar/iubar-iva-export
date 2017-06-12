package iubar.iva.export;

//import java.util.Calendar;
import java.time.Year;

public class FormatChecker {

	private static final String cfPattern = "[a-zA-Z]{6}[0-9]{2}[a-zA-Z]{1}[0-9]{2}[a-zA-Z]{1}[0-9]{3}[a-zA-Z]{1}";
	private static final String cnPattern = "[0-9]{11}";
	private static final String pnPattern = "[a-zA-Z0]{2}";

	public boolean checkPositionalCF(String value) {
		return ((value.length() == 0 || value.length() == 16 || value.length() == 11) && ("".equals(value) || value.matches(cfPattern) || value.matches(cnPattern)));
	}

	public boolean checkPositionalCN(String value) {
		return ((value.length() == 0 || value.length() == 11) && (value.matches(cnPattern) || "".equals(value)));
	}

	public boolean checkPositionalPI(String value) {
		return this.checkPositionalCN(value);
	}

	public boolean checkPositionalDT(String value) {
		return (value.length() == 0 || value.length() == 8);
	}

	public boolean checkPositionalNU(String value) {
		return (value.indexOf("-") == -1);
	}

	public boolean checkPositionalPN(String value) {
		return ((value.length() == 0 || value.length() == 2) && (value.matches(pnPattern) || "".equals(value)));
	}

	public boolean checkPositionalPR(String value) {
		return ((value.length() == 0 || value.length() == 2) && ((value.matches("[a-zA-Z]{2}") && !"EE".equals(value) && !"00".equals(value)) || "".equals(value)));
	}

	public boolean checkPositionalCB(String value) {
		return (value.length() == 0 || value.length() == 1) && ("0".equals(value) || "1".equals(value));
	}

	public boolean checkNonPositionalCB(String value) {
		return this.checkPositionalCB(value);
	}

	public boolean checkNonPositionalCB12(String value) {
		if (value.length() == 12) {
			return value.matches("[0-1]+");
		}
		return false;
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
		if (this.checkNonPositionalDA(value)) {
			return this.dateRange(value, 1880, Year.now().getValue());
		}
		return false;
	}

	public boolean checkNonPositionalDN(String value) {
		if (this.checkNonPositionalDA(value)) {
			return this.dateRange(value, 1980, 2050);
		}
		return false;
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
		return true;
	}

	public boolean checkNonPositionalNX(String value, String format) {
		return (value.length() == Integer.parseInt(format.substring(1)) && value.length() < 17
				&& (value.matches("[0-9]+") || value.matches("[0-9]+[,.]?[0-9]+")));

	}

	public boolean checkNonPositionalPC(String value) {

		return Double.parseDouble(value.replace(',', '.')) <= 100
				&& (value.matches("[0-9]") || value.matches("[0-9]{1,3}[,.]?[0-9]{1,3}"));
	}

	public boolean checkNonPositionalPR(String value) {
		return this.checkPositionalPR(value);
	}

	public boolean checkNonPositionalPN(String value) {
		return this.checkPositionalPN(value);
	}

	public boolean checkNonPositionalQU(String value) {
		return (value.matches("[0-9]") || value.matches("[0-9]+[,.]?[0-9]{1,5}"));
	}

	private boolean dateRange(String date, int start, int end) {
		Integer year = Integer.parseInt((String) date.subSequence(4, 8));

		return (year > start && year < end);
	}
}