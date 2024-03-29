package iubar.iva.export;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Export {

	private static final String FOLDER_PATH = System.getProperty("user.home") +
			System.getProperty("file.separator") +
			"Desktop" +
			System.getProperty("file.separator") +
			"Iva" +
			System.getProperty("file.separator");

	private static final String FILE_PATH = FOLDER_PATH + "iva_out.txt";
	private static final String SPECS_PATH = FOLDER_PATH + "iva.cfg";

	private PositionalExport pos_export;
	private NonPositionalExport n_pos_export;

	private Map<String, String> pSpecs;
	private Map<String, String> nSpecs;

	private List<String> nKeys;
	private List<String> fRecords;

	private RandomAccessFile file;

	public String getFieldToString(int field) {
		if (field > 124 & field < 1153) {
			return nKeys.get(field - 125);
		} else {
			return null;
		}
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

		String val = PositionalExport.getRightValue(value, format, length);
		String finalRecord = pos_export.getFinalRecord(val,
			field,
			position,
			length);

		int index = pos_export.getCurrentIndex(field);

		this.fixFRecordSize(index);
		this.fRecords.set(index, finalRecord);
		this.writeOnFile();
	}

	private void fixFRecordSize(int index) {
		if (this.fRecords.size() - 1 < index) {

			for (int i = this.fRecords.size() - 1 ; i < index ; i++) {
				this.fRecords.add("");
			}
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

		String[] val = NonPositionalExport.getRightValue(value, format);
		String finalRecord = n_pos_export.getFinalRecord(field, val);

		if (finalRecord.length() > 1889) {
			int next_index = NonPositionalExport.last_nrecord_index + 1;
			PositionalExport.next_b_record++;
			
			this.fixFRecordSize(next_index);
			this.fRecords.set(next_index - 1, finalRecord.substring(0, 1890));
			this.fRecords.set(next_index, finalRecord.substring(1890) +
				this.fRecords.get(next_index));
		} else {
			int index = NonPositionalExport.last_nrecord_index;
			
			this.fixFRecordSize(index);
			this.fRecords.set(index, finalRecord);
		}
		this.writeOnFile();
	}

	public void writeOnFile() {
		try {
			this.file.seek(0);

			for (String line : fRecords) {
				this.file.writeBytes(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getSpecs() {
		Pattern positional = Pattern.compile("[0-9]+");
		Pattern nonPositional = Pattern.compile("V[A-Z]{1}[0-9]{6}|V1{1}[0-9]{6}|V[A-Z]{1}[0-9]{5}[A-D]");
		String line;

		try {
			file = new RandomAccessFile(SPECS_PATH, "r");

			while (null != (line = file.readLine())) {
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

			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getFile() {
		String line;

		try {
			this.file = new RandomAccessFile(FILE_PATH, "rw");
			
			while (null != (line = this.file.readLine())) {
				this.fRecords.add(line);
			}

			this.file.close();
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

		pos_export = new PositionalExport(this.fRecords);
		n_pos_export = new NonPositionalExport(this.fRecords, this.nKeys);
	}


}