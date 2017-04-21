package iubar.iva.export;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {

    private final static String SPECS_PATH = "/home/yawn/temp/iva.cfg";

    private ArrayList<String> db = new ArrayList<>();
    private ArrayList<Integer> pfield = new ArrayList<>();
    private ArrayList<String> nfield = new ArrayList<>();

    private int dbIndex;
    private int fieldIndex;

    /* mette solo numeri da (fino a 1147 che è il numero di tipi di campi totale)
     * non è assolutamente giusta, pero l'ho fatta un paio di settimane fa cosi e per ora la lascio
     * a meno che non la modificate voi
     * è sbagliata quindi potete anche cancellare tutto, io intanto la metto uguale
     */

    public Data(){
        try {
            RandomAccessFile reader = new RandomAccessFile(SPECS_PATH, "r");
            Pattern nonPositional = Pattern.compile("V[A-Z]{1}[0-9]{6}");
            Pattern positional = Pattern.compile("[0-9]");
            String line;

            while (null != (line = reader.readLine())) {
                Matcher match = nonPositional.matcher(line);
                if (!match.find()) {
                    match = positional.matcher(line);
                    if (match.find()) {
                        pfield.add(Integer.parseInt(match.group()));
                    }
                } else {
                    nfield.add(match.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0 ; i < 1147 ; i++) {
            db.add(Integer.toString(i));
        }
    }

    public String nextLine () {
        this.dbIndex++;
        return this.db.get(this.dbIndex - 1);
    }

    public Object nextField () {
        this.fieldIndex++;
        if (this.fieldIndex < 1147) {
            if ((this.fieldIndex - 1) >= 124) {
                return this.nfield.get(this.fieldIndex - 124 - 1);
            } else {
                return this.pfield.get(this.fieldIndex - 1);
            }
        }
        else return null;
    }

    /* MAI usare object, l'ho messo solo perche mi faceva comodo in quel momento */

    public static void main(String[] args) {
        Data data = new Data();
        Export export = new Export();
        for( int i = 0 ; i < 1447 ; i++) {
            Object field = data.nextField();
            if (field instanceof String) {
                export.writeField(data.nextLine(), field.toString());
            } else if(field instanceof Integer) {
                try {
                    export.writeField(data.nextLine(), ((Integer) field).intValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
