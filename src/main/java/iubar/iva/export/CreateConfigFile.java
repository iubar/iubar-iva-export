package iubar.iva.export;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public final class CreateConfigFile {

	private static final String PDF_PATH = "/home/yawn/temp/SpecificheIva2017";
	private static final String FILE_PATH = "/home/yawn/temp/iva.cfg";

	private PrintWriter writer;
	private int field_count;

	public void getTextFromPDF(int start_page, int end_page) {
		try {

			PDDocument pdf = PDDocument.load(new File(PDF_PATH));
			Writer stream = new StringWriter();
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(start_page);
			stripper.setEndPage(end_page);
			stripper.setSortByPosition(true);
			stripper.writeText(pdf, stream);
			String[] split = stream.toString().split("\\R");

			for (String obj : split) {
				obj = obj.replaceAll("[a-z\\W ]{1,}", " ");
				if (start_page > 20 && end_page < 41) {
					this.dumpData(obj, false);
				} else if (start_page == 41 && end_page == 41) {
					this.dumpLastNField(obj);
				} else {
					this.dumpData(obj, true);
				}
			}

			pdf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void dumpData(String line, boolean field_type) {
		String pdfLine = line;

		if (field_type) {
			pdfLine = checkType1(pdfLine);

			if (pdfLine != null) {
				String[] split = pdfLine.split("\\+");

				try {
					//int field = Integer.parseInt(split[0]);
					int position = Integer.parseInt(split[split.length - 3]);
					int length = Integer.parseInt(split[split.length - 2]);
					String format = split[split.length - 1];

					/*
					 * writer.print("Field: " + field + " " + "Position: " +
					 * position + " " + "Length: " + length + " " + "Format: " +
					 * format + "\n");
					 */

					this.field_count++;
					writer.print(this.field_count + " " + position + " " + length + " " + format + "\n");

				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}

		} else {
			pdfLine = checkType2(pdfLine);

			if (pdfLine != null) {
				String[] split = pdfLine.split("\\+");
				if (split.length == 2) {
					writer.print(split[0] + " " + split[1] + "\n");
				}
			}
		}
	}

	private void dumpLastNField(String line) {
		String pdfLine;
		pdfLine = checkType2(line);

		if (pdfLine != null) {
			String[] split = pdfLine.split("\\+");
			if (split.length == 2) {
				writer.print(split[0] + " " + split[1] + "\n");
			}
		} else {
			pdfLine = checkType1(line);

			if (pdfLine != null) {
				String[] split = pdfLine.split("\\+");

				try {
					//int field = Integer.parseInt(split[0]);
					int position = Integer.parseInt(split[split.length - 3]);
					int length = Integer.parseInt(split[split.length - 2]);
					String format = split[split.length - 1];

					/*
					 * writer.print("Field: " + field + " " + "Position: " +
					 * position + " " + "Length: " + length + " " + "Format: " +
					 * format + "\n");
					 */

					this.field_count++;
					writer.print(this.field_count + " " + position + " " + length + " " + format + "\n");

				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String checkType1(String pdfLine) {
		String[] split = pdfLine.split("\\s+");

		if (split.length > 0) {
			if (StringUtils.isNumeric(split[0])) {

				for (int i = 0; i < split.length; i++) {
					if ((i > 0) && split[i].matches("AN|CF|CN|PI|DT|NU|PN|PR|CB|D4|N1")) {
						if (split[i].equals("N1")) {
							split[i] = "NU";
						}
						return split[0] + "+" + split[i - 2] + "+" + split[i - 1] + "+" + split[i];
					}
				}
			}
		} else {
			System.out.println("split is length 0");
		}
		return null;
	}

	private String checkType2(String pdfLine) {
		String[] split = pdfLine.split("\\s+");

		if ((split.length > 0) && (split[0].matches("V[A-Z]{1}[0-9]{6}"))) {

			for (int i = 0; i < split.length; i++) {

				if (split[i].matches(
						"AN|CB|CB12|CF|CN|PI|" +
								"DA|DT|DN|D4|D6|NP|NU|" +
								"N1|N2|N3|N4|N5|N6|N7|N8|N9|N10|N11|N12|N13|N14|N15|N16|" +
								"PC|PR|PN|QU")) {

					return split[0] + "+" + split[i];
				}
			}
		}
		return null;
	}

	public void close() {
		writer.close();
	}

	public CreateConfigFile() {
		try {
			writer = new PrintWriter(FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		iubar.iva.export.CreateConfigFile file = new iubar.iva.export.CreateConfigFile();
		file.getTextFromPDF(16, 20);
		file.getTextFromPDF(21, 40);
		file.getTextFromPDF(41, 41);
		file.getTextFromPDF(42, 42);
		file.close();
	}

}