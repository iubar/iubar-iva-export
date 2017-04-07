package iubar.iva.export;

public class Utility {

	public boolean dateFormatCheck(String date) {
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

	public boolean dateRange(String date, int start, int end) {
		Integer year = Integer.parseInt((String) date.subSequence(4, 8));
		if (year > start && year < end) {
			return true;
		} else {
			return false;
		}
	}
}
