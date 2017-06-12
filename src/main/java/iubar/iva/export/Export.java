package iubar.iva.export;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Export {

	private final static String FILE_PATH = "C:\\Users\\Utente\\Desktop\\iva_out.txt";
	private final static String SPECS_PATH = "C:\\Users\\Utente\\Desktop\\iva.cfg";

	private final static int N_LENGTH = 16;
	private final static int N_BEGINNING = 2;

	private Map<String, String> pSpecs;
	private Map<String, String> nSpecs;

	private List<String> nKeys;
	private List<String> fRecords;

	private RandomAccessFile rw;
	
	private Pattern nField = Pattern.compile("V[A-Z]([0-9]{6}|[0-9]{5}[A-D])|V1{1}[0-9]{6}");

	private String record;
	private int last_record;
	private int next_b_record = -1;

	public String getFieldToString(int field) {
		if (field > 124 & field < 1153) {
			return nKeys.get(field - 125);
		} else return null;
	}

	public <T> void writeField(T value, int field) {
		String format = "";
		int position = 0;
		int length = 0;

		String[] split = pSpecs.get(Integer.toString(field)).split("\\s");

		if (split.length != 4) {
			System.out.println("Error with the specs");
		} else {
			position = Integer.parseInt(split[1]);
			length = Integer.parseInt(split[2]);
			format = split[3];
		}

		this.record = this.getFinalRecord(getRightValue(value, format, length), field, position, length);
		this.fixFRecordSize();
		this.fRecords.set(this.last_record, this.record);
		this.writeOnFile();
	}

	private void fixFRecordSize() {
		if (this.fRecords.size() - 1 < this.last_record) {
			for (int i = this.fRecords.size() - 1 ; i < this.last_record ; i++) {
				this.fRecords.add("");
			}
		}
	}

	private <T> String getRightValue(T value, String format, int length) {
		if (value instanceof String) {
			return IvaFields.getFormatField((String) value, format, length);
		} else if (value instanceof BigDecimal) {
			return IvaFields.getFormatField((BigDecimal) value, format, length);
		} else if (value instanceof Date) {
			return IvaFields.getFormatField((Date) value, format, length);
		} else {
			return IvaFields.getFormatField((Boolean) value, format, length);
		}
	}

	private String getFinalRecord(String value, int field, int position, int length) {
		this.setRecordToExamine(field);
		if (this.record == null) {
			this.record = "";
		}
		if (this.record.length() >= position - 1 && this.record.length() < position + length - 1) {
			return this.record.substring(0, position - 1) +
					value +
					this.record.substring(position - 1);
		} else if (this.record.length() >= position + length - 1) {
			return this.record.substring(0, position - 1) +
					value +
					this.record.substring(position + length - 1);
		} else {
			String padding = "";
			for (int i = 0 ; i < position - 1; i++) {
				padding += " ";
			}
			if (this.record.length() > 0) {
				return this.record.substring(0, this.record.length() - 1) +
						padding +
						value;
			} else {
				return this.record.substring(0, this.record.length()) +
						padding +
						value;
			}
		}
	}

	private void setRecordToExamine(int field) {
		if (next_b_record > -1 && field > 13 && field < 118) {
			if (field == 117) {
				this.next_b_record++;
			}
			this.record = getNextBRecord(next_b_record);
		} else {
			if (field > 117) { 			//start of record D
				this.last_record = 2;
				if (fRecords.size() >= 3) {
					this.record = fRecords.get(2);
				}
			} else if (field > 13) {	//start of record B
				if (field == 117) {
					this.next_b_record++;
				}
				this.last_record = 1;
				if (fRecords.size() >= 2) {
					this.record = fRecords.get(1);
				}
			} else if (field > 0) {
				this.last_record = 0;
				if (fRecords.size() >= 1) {
					this.record = fRecords.get(0);
				}
			} else {
				this.record = "";
			}
		}

	}
	
	private String getNextBRecord(int number) {
		int counter = 0;
		for (int i = 3 ; i < this.fRecords.size() ; i++) {
			if (! this.nField.matcher(this.fRecords.get(i)).find()) {
				counter++;
				if (counter == number) {
					this.last_record = i;
					return this.fRecords.get(i);
				}
			}
		}
		this.last_record = this.fRecords.size();
		this.fRecords.set(this.last_record, "");
		return "";
	}

	public <T> void writeField(T value, String field) {
		String format = null;
		String[] split = nSpecs.get(field).split("\\s");

		if (split.length != 2) {
			System.out.println("Error with the specs");
		} else {
			format = split[1];
		}

		this.record = this.getFinalRecord(field, this.getRightValue(value, format));

		if (this.record.length() > 1889) {
			this.last_record++;
			this.fixFRecordSize();
			this.fRecords.set(this.last_record - 1, this.record.substring(0, 1890));
			this.fRecords.set(this.last_record, this.record.substring(1890) +
					this.fRecords.get(this.last_record));
		} else {
			this.fRecords.set(this.last_record, this.record);
		}

		this.writeOnFile();
	}

	/*private <T> boolean checkCB(T value) {
		if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue();
		}
		return true;
	}*/

	private <T> String[] getRightValue(T value, String format) {
		if (value instanceof String) {
			return IvaFields.getFormatField((String) value, format);
		} else if (value instanceof BigDecimal) {
			return IvaFields.getFormatField((BigDecimal) value, format);
		} else if (value instanceof Date){
			return IvaFields.getFormatField((Date) value, format);
		} else {
			return IvaFields.getFormatField((Boolean) value, format);
		}
	}

	private String getFinalRecord(String field, String[] value) {
		int line = N_BEGINNING;
		int index = -1;
		int counter = 1;
		String tmp = "";

		if (this.next_b_record > 0) {
			while (this.setNRecord(line)) {
				index = getIndexOfLastNField(field, 0);
				if (index != -1 && counter == this.next_b_record) {
					break;
				} else {
					counter++;
				}
				line++;
			}
		} else {
			while (this.setNRecord(line)) {
				index = getIndexOfLastNField(field, 0);
				if (index != -1) {
					break;
				}
				line++;
			}
		}

		if (index == -1) {
			index = this.getIndexOfPrecedentField(field);
		} else {
			this.last_record = line;
			index += N_LENGTH + 7;	//8 = number of digits of the header(field) - 1
		}

		for (String aValue : value) {
			tmp += field + aValue;
		}

		return this.record.substring(0, index) +
				tmp +
				this.record.substring(index);
	}

	private boolean setNRecord(int line) {
		if (line < fRecords.size() - 1) {
			this.record = this.fRecords.get(line);
			return true;
		}
		return false;
	}

	private int getIndexOfLastNField(String field, int fromIndex) {
		int response;
		int index = this.record.indexOf(field, fromIndex);
		if (index == -1) {
			return -1;
		} else {
			response = getIndexOfLastNField(field, index + 1);
			if (response == -1) {
				return index;
			} else {
				return response;
			}
		}
	}

	private int getIndexOfPrecedentField(String field) {
		int line = N_BEGINNING;
		int index = 0;
		int counter = 0;
		String before_field;

		if (this.next_b_record > 0) {
			for (int i = nKeys.indexOf(field) - 1; i > -1; i--) {
				before_field = nKeys.get(i);
				while (this.setNRecord(line)) {
					index = getIndexOfLastNField(before_field, 0);
					if (index != -1 && counter == this.next_b_record) {
						i = -1;
						break;
					} else {
						counter++;
					}
					line++;
				}
			}
		} else {
			for (int i = nKeys.indexOf(field) - 1; i > -1; i--) {
				before_field = nKeys.get(i);
				while (this.setNRecord(line)) {
					index = getIndexOfLastNField(before_field, 0);
					if (index != -1) {
						break;
					}
					line++;
				}
			}
		}

		if (index == -1) {
			index = this.record.length() - 1;
			this.last_record = 2;
		} else {
			index += N_LENGTH + 7;
			this.last_record = line;
		}
		return index;
	}

	public void writeOnFile() {
		try {
			this.rw.seek(0);
			for (String line : fRecords) {
				this.rw.writeBytes(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public void close () {
		if (rw != null) {
			try {
				rw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

	private void getSpecs() {
		try {
			rw = new RandomAccessFile(SPECS_PATH, "r");
			Pattern positional = Pattern.compile("[0-9]+");
			Pattern nonPositional = Pattern.compile("V[A-Z]{1}[0-9]{6}|V1{1}[0-9]{6}|V[A-Z]{1}[0-9]{5}[A-D]");
			String line;

			while (null != (line = rw.readLine())) {
				Matcher n_match = nonPositional.matcher(line);
				Matcher p_match = positional.matcher(line);
				if (!n_match.find()) {
					if (p_match.find()) {
						pSpecs.put(p_match.group(0), line);
					}
				} else {
					nSpecs.put(n_match.group(0), line);
					nKeys.add(n_match.group(0));
				}
			}
			rw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getFile() {
		try {
			this.rw = new RandomAccessFile(FILE_PATH, "rw");
			String line;
			while (null != (line = this.rw.readLine())) {
				this.fRecords.add(line);
			}
			//this.rw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Export() {
		pSpecs = new HashMap<>();
		nSpecs = new HashMap<>();
		nKeys = new LinkedList<>();
		fRecords= new LinkedList<>();

		getSpecs();
		getFile();
	}

}
