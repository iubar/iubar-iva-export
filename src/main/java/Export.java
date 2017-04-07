import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Export {

	private final static String FILE_PATH = "/home/yawn/temp/final_iva.txt";
	private final static String SPECS_PATH = "/home/yawn/temp/iva.cfg";

	private Map<String, String> pSpecs = new HashMap<String, String>();
	private Map<String, String> nSpecs = new HashMap<String, String>();

	public void dumpData(String value, int field) {

		String[] split = pSpecs.get(Integer.toString(field)).split("\\s"); // get
																			// the
																			// specs
																			// corresponding
																			// to
																			// field
																			// already
																			// split
		if (split.length != 4) {
			System.out.println("Error with the specs");
		} else {
			this.writeField(field, Integer.parseInt(split[1]), Integer.parseInt(split[2]), split[3], value);
		}
	}

	public void dumpData(String value, String field) {
		String[] split = nSpecs.get(field).split("\\s");
		if (split.length != 4) {
			System.out.println("Error with the specs");
		} else {
			this.writeField(field, split[1], value);
		}
	}

	private void writeField(int field, int position, int length, String format, String value) {

		/*
		 * FileChannel rwChannel = new RandomAccessFile("textfile.txt",
		 * "rw").getChannel(); ByteBuffer wrBuf =
		 * rwChannel.map(FileChannel.MapMode.READ_WRITE, 0,
		 * textToSave.length());
		 * 
		 * wrBuf.put(textToSave.getBytes());
		 * 
		 * rwChannel.close();
		 */
	}

	public void writeField(String field, String format, String value) {

	}

	public Export() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(SPECS_PATH));
			Pattern nonPositional = Pattern.compile("V[A-Z]{1}[0-9]{6}");
			Pattern positional = Pattern.compile("[0-9]+");
			String line;

			while (null != (line = reader.readLine())) {
				Matcher match = nonPositional.matcher(line);
				if (!match.find()) {
					pSpecs.put(positional.matcher(line).group(1), line);
				} else {
					nSpecs.put(match.group(1), line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

	}

}
