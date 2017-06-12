package iubar.iva.export;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class CheckFile {

    private static final String FOLDER_PATH = System.getProperty("user.home") +
            System.getProperty("file.separator") +
            "Desktop" +
            System.getProperty("file.separator") +
            "Iva" +
            System.getProperty("file.separator");

    private static final String FILE_PATH = FOLDER_PATH + "iva_out.txt";

    private RandomAccessFile rw;
    private ArrayList<String> file = new ArrayList<String>();
    private int counter = 0;

    CheckFile() {
        String line;
        try {
            this.rw = new RandomAccessFile(FILE_PATH, "r");
            while (null != (line = this.rw.readLine())) {
                this.file.add(line);
            }
            //this.rw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        line = this.file.get(2);
        String[] lines = line.split("V[A-Z]{1}[0-9]{6}|V1{1}[0-9]{6}|V[A-Z]{1}[0-9]{5}[A-D]");
        for(String aLine : lines) {
            counter++;
            System.out.println(aLine + " - " + aLine.length());
        }
        System.out.println(counter);
    }

    public static void main(String[] args) {
        CheckFile a = new CheckFile();

    }

}
