package iubar.iva.export;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Export {

	/* TO-DO
	*
	* Classe data
	* Testing
	*
	 */

	private final static String FILE_PATH = "/home/yawn/Desktop/iva_out.txt";
	private final static String SPECS_PATH = "/home/yawn/temp/iva.cfg";

	private final static int N_LENGTH = 16;
	private final static int N_BEGINNING = 3;

	private Map<String, String> pSpecs;
	private Map<String, String> nSpecs;

	private List<String> nKeys;
	private List<String> fRecords;

	private RandomAccessFile rw;

	private String record;
	private int last_record;

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

		String val = getRightValue(value, format, length);

		this.fRecords.set(this.last_record,
				this.getFinalRecord(val, field, position, length));

		this.writeOnFile();
	}

	private <T> String getRightValue(T value, String format, int length) {
		if (value instanceof String) {
			return IvaFields.getFormatField((String) value, format, length);
		} else if (value instanceof BigDecimal) {
			return IvaFields.getFormatField((BigDecimal) value, format, length);
		} else {
			return IvaFields.getFormatField((Date) value, format, length);
		}
	}

	private String getFinalRecord(String value, int field, int position, int length) {
		this.record = this.getRecordToExamine(field);
		if (this.record.length() >= position + length) {
			return record.substring(0, position - 1) +
					value +
					record.substring(position - 1);
		} else {
			String padding = null;
			for (int i = 0 ; i < position ; i++) {
				padding += " ";
			}
			return record.substring(0, this.record.length() - 1) +
					padding +
					value;
		}
	}

	private String getRecordToExamine(int field) {
		if (field >= 118) { 			//start of record D
			this.last_record = 2;
			return fRecords.get(2);
		} else if (field >= 14) {	//start of record B
			this.last_record = 1;
			return fRecords.get(1);
		} else {
			this.last_record = 0;
			return fRecords.get(0);
		}
	}

	public <T> void writeField(T value, String field) {
		String format = null;
		String[] split = nSpecs.get(field).split("\\s");

		if (split.length != 2) {
			System.out.println("Error with the specs");
		} else {
			format = split[1];
		}

		String[] val = this.getRightValue(value, format);

		this.record = this.getFinalRecord(field, val);
		if (this.record.length() > 1800) {
			this.fRecords.set(this.last_record, this.record.substring(0, 1800));
			this.fRecords.set(this.last_record, this.record.substring(1800) +
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
		} else {
			return IvaFields.getFormatField((Date) value, format);
		}
	}

	private String getFinalRecord(String field, String[] value) {
		int line = N_BEGINNING;
		int index = 0;
		String tmp = null;

		do {
			if (-1 != this.setNRecord(line)) {
				index = getIndexOfLastNField(field, 0);
				line++;
			} else {
				System.out.println("setNRecord(line) == -1");
			}

		} while ((index == -1) && (this.record != null));

		this.last_record = line;

		if (this.record == null) {
			index = this.getIndexOfPrecedentField(field);
		} else {
			index += N_LENGTH + 7;	//8 = number of digits of the header(field) - 1
		}

		for (String aValue : value) {
			tmp += field + aValue;
		}

		return this.record.substring(0, index) +
				tmp +
				this.record.substring(index);
	}

	private int setNRecord(int line) {
		if (line < this.fRecords.size()) {
			this.record = this.fRecords.get(line);
			return 0;
		}
		return -1;
	}

	private int getIndexOfLastNField(String field, int fromIndex) {
		int response;
		int index = this.record.indexOf(field, fromIndex);
		if (index == -1) {
			return -1;
		} else {
			response = getIndexOfLastNField(field, this.record.indexOf(field, index));
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
		String before_field;
		do {
			if (-1 != this.setNRecord(line)) {
				for (int i = nKeys.indexOf(field) - 1; i > -1; i--) {
					before_field = nKeys.get(i);
					index = getIndexOfLastNField(before_field, 0);
					if (index != -1) {
						break;
					}
				}
				line++;
			} else {
				System.out.println("setNRecord(line) == -1");
			}
		} while ((index == -1) && (this.record != null));

		if (this.record == null) {
			this.record = "";
			index = 0;
			this.last_record = 3;
		} else {
			index += N_LENGTH + 7;
			this.last_record = line;
		}
		return index;
	}

	private void writeOnFile() {
		try {
			this.rw.seek(0);
			for (String line : fRecords) {
				this.rw.writeBytes(line);
				this.rw.writeBytes("\n");
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
			Pattern nonPositional = Pattern.compile("V[A-Z]{1}[0-9]{6}");
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
