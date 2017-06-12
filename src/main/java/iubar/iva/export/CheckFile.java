package iubar.iva.export;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class CheckFile {

    private RandomAccessFile rw;
    private ArrayList<String> file = new ArrayList<String>();

    CheckFile() {
        String line;
        try {
            this.rw = new RandomAccessFile("/home/yawn/Desktop/iva_out.txt", "r");
            while (null != (line = this.rw.readLine())) {
                this.file.add(line);
            }
            //this.rw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        line = this.file.get(2);
        String[] lines = line.split("\\S+");
        for(String aLine : lines) {
            System.out.println(aLine + " - " + aLine.length());
        }
    }

    public static void main(String[] args) {

    }

}
