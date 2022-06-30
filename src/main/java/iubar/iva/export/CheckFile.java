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

    private RandomAccessFile file;
    private ArrayList<String> text = new ArrayList<String>();
    private int counter = 0;

    CheckFile() {
        String curr;

        try {
            this.file = new RandomAccessFile(FILE_PATH, "r");
            while (null != (curr = this.file.readLine())) {
                this.text.add(curr);
            }
            this.file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        curr = this.text.get(2);
        String[] lines = curr.split("V[A-Z]{1}[0-9]{6}|V1{1}[0-9]{6}|V[A-Z]{1}[0-9]{5}[A-D]");

        for (String line : lines) {
            counter++;
            System.out.println(line + " - " + line.length());
        }
        System.out.println(counter);
    }

    public static void main(String[] args) {
        new CheckFile();
    }

}
