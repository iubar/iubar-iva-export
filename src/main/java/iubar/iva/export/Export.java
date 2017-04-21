package iubar.iva.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
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

	private final static String FILE_PATH = "/home/yawn/temp/final_iva.txt";
	private final static String SPECS_PATH = "/home/yawn/temp/iva.cfg";

	private Map<String, String> pSpecs;
	private Map<String, String> nSpecs;
	/*se usavo l'interfaccia poi dovevo sempre castarli a linkedhashmap per usare i metodi di essa*/

	private RandomAccessFile rw;

	private String record;
	private int last_position_record_line;

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
				this.rw = new RandomAccessFile("/home/yawn/Desktop/iva_out", "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		//value = format(value, length) funzione di delonte
		this.putValueInRecord(value, field, position, length);

		try {
			this.rw.writeBytes(this.record);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void putValueInRecord(String value, int field, int position, int length) {
		this.getRecord(field);

		//check if indexOf() starts counting from 0 or 1
		if (this.record.length() >= position + length) {
			this.record = record.substring(0, position - 1) +
					value +
					record.substring(position - 1);
		} else {
			String padding = null;
			for (int i = 0 ; i < position ; i++) {
				padding += " ";
			}
			this.record = record.substring(0, this.record.length() - 1) +
					padding +
					value;
		}
	}

	private void getRecord(int field) {
		try{
			//check if seek() starts counting from 0 or 1
			if (field >= 118) { 			//start of record D
				this.rw.seek(2);
				this.last_position_record_line = 2;
			} else if (field >= 14) {	//start of record B
				this.rw.seek(1);
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

	private void putValueInRecord(String value, String field) {
		int line = this.last_position_record_line;
		int index;
		line++;
		this.getRecord(field, line + 1);

		index = getIndexOfLastNField(field, 0);
		while ((index == -1) && (this.record != null)) {
			line++;
			this.getRecord(field, line + 1);
			index = getIndexOfLastNField(field, 0);
		}

		if (this.record == null) {
			try {
				rw.seek(this.last_position_record_line + 1);
				this.record = "";
				index = this.record.length();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			index += value.length() + 7;	//8 = number of digits of the header(field) - 1
		}

		this.record = this.record.substring(0, index) +
				field +
				value +
				this.record.substring(index);
	}

	private void getRecord(String field, int line) {
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

		try {
			rw = new RandomAccessFile(SPECS_PATH, "r");
			Pattern nonPositional = Pattern.compile("V[A-Z]{1}[0-9]{6}");
			Pattern positional = Pattern.compile("[0-9]+");
			String line;

			while (null != (line = rw.readLine())) {
				Matcher match = nonPositional.matcher(line);
				if (!match.find()) {
					pSpecs.put(positional.matcher(line).group(1), line);
				} else {
					nSpecs.put(match.group(1), line);
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
