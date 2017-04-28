package iubar.iva.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Export {

	/* TO-DO
	*
	* Aggiustare imprecisioni (nonposizionali)
	* Classe data
	* Mettere le varie eccezioni particolari nell' inserimento nei campi
	* es: se campo1 esiste allora metti anche campo2
	* testare
	*
	 */

	private final static String FILE_PATH = "/home/yawn/Desktop/final_iva.txt";
	private final static String SPECS_PATH = "/home/yawn/temp/iva.cfg";

	private Map<String, String> pSpecs;
	private Map<String, String> nSpecs;

	private List<String> nKeys;

	private RandomAccessFile rw;

	private String record;
	private int last_record_line = 3;

	public void writeField(String value, int field) throws IOException {
		String format;
		int position = 0, length = 0,pos = 0;

		String[] split = pSpecs.get(Integer.toString(field)).split("\\s");

		if (split.length != 4) {
			System.out.println("Error with the specs");
		} else {
			position = Integer.parseInt(split[1]);
			length = Integer.parseInt(split[2]);
			format = split[3];
		}
		if (this.rw == null) {
			try {
				this.rw = new RandomAccessFile(FILE_PATH, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		//value = format(value, length) funzione di delonte
		this.record = this.getFinalRecord(value, field, position, length);

		try {
			this.rw.writeBytes(this.record);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getFinalRecord(String value, int field, int position, int length) {
		this.setRecord(field);
		//check if indexOf() starts counting from 0 or 1
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

	private void setRecord(int field) {
		try{
			//check if seek() starts counting from 0 or 1
			if (field >= 118) { 			//start of record D
				this.rw.seek(2);
			} else if (field >= 14) {	//start of record B
				this.rw.seek(1);
			} else {
				this.rw.seek(0);
			}
			this.record = rw.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeField(String value, String field) {
		String format;
		String[] split = nSpecs.get(field).split("\\s");

		if (split.length != 2) {
			System.out.println("Error with the specs");
		} else {
			format = split[1];
		}

		this.putValueInRecord(value, field);

		try {
			this.rw.writeBytes(this.record);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String putValueInRecord(String value, String field) {
		int line = this.last_record_line;
		int index;

		do {
			this.setRecord(field, line);
			index = getIndexOfLastNField(field, 0);
			line++;
		} while ((index == -1) && (this.record != null));

		if (this.record == null) {
			index = this.getIndexOfPrecedentField(field);
		} else {
			index += value.length() + 7;	//8 = number of digits of the header(field) - 1
		}

		return this.record.substring(0, index) +
				field +
				value +
				this.record.substring(index);
	}

	private int getIndexOfPrecedentField(String field) {
		int line = this.last_record_line;
		int index = 0;
		String before_field = null;
		try {
			do {
				rw.seek(line);
				this.record = rw.readLine();
				for (int i = nKeys.indexOf(field) - 1; i > -1; i--) {
					before_field = nKeys.get(i);
					index = getIndexOfLastNField(before_field, 0);
					if (index != -1) {
						break;
					}
				}
				line++;
			} while ((index == -1) && (this.record != null));

			if (this.record == null) {
				rw.seek(this.last_record_line);
				this.record = "";
				index = 0;
			} else {
				index += this.getValueSize(before_field, index) + 7;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return index;
	}

	private void setRecord(String field, int line) {
		try{
			this.rw.seek(line);
			this.record = rw.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			//check if seek() got the pointer beyond eof (not sure by reading docs)
		}
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

	private int getValueSize(String field, int startIndex) {
		if (this.record == null) {
			throw new IllegalArgumentException("record is null");
		}
		int index = -1;
		int counter = 1;
		while ((nKeys.indexOf(field) + counter < nKeys.size()) && (index == -1)) {
			String nextField = nKeys.get(nKeys.indexOf(field) + counter);
			index = this.record.indexOf(nextField, startIndex);
			counter++;
		}
		if (index == -1) {
			return -7;
		}
		return index - field.length() - 1;
	}

	public void close () {
		if (rw != null) {
			try {
				rw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Export() {
		pSpecs = new HashMap<>();
		nSpecs = new HashMap<>();
		nKeys = new LinkedList<>();

		try {
			rw = new RandomAccessFile(SPECS_PATH, "r");
			Pattern positional = Pattern.compile("[0-9]+");
			Pattern nonPositional = Pattern.compile("V[A-Z]{1}[0-9]{6}");
			String line;

			while (null != (line = rw.readLine())) {
				Matcher match = nonPositional.matcher(line);
				if (!match.find()) {
					pSpecs.put(positional.matcher(line).group(0), line);
				} else {
					nSpecs.put(match.group(0), line);
					nKeys.add(match.group(0));
				}
			}
			rw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}
