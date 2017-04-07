package iubar.iva.export;

public class Formatter {
	/*
	 * public String stringFormatter(String value, int lenght, int type) {
	 * String local = value; int tmp; if(type >= 0 && type < 4) { tmp = lenght -
	 * local.length(); if (tmp < 0) { throw new IllegalArgumentException(
	 * "La lunghezza del valore non è compatibile con la sua massima estensione"
	 * ); } else if (tmp > 0 && tmp < lenght) { if(type == 0) { //NO
	 * FORMATTAZIONE return local; } else if(type == 1) { //sinistra + spazi for
	 * (int i = 0; i < tmp; i++) { local += " "; } } else if(type == 2) {
	 * //destra + spazi String prefix = new String(); for (int i = 0; i < tmp;
	 * i++) { prefix += " "; } local = prefix + local; } else { //destra + zeri
	 * String prefix = new String(); for (int i = 0; i < tmp; i++) { prefix +=
	 * "0"; } local = prefix + local; } } } else { throw new
	 * IllegalArgumentException("Tipo di formattazzione errato"); } return
	 * local; }
	 */
	public String stringFormatterDelta(String value, int delta, int type) {
		String local = value;

		if (type >= 0 && type < 4) {
			if (type == 0) { // NO FORMATTAZIONE
				return local;
			} else if (type == 1) { // sinistra + spazi
				for (int i = 0; i < delta; i++) {
					local += " ";
				}
			} else if (type == 2) { // destra + spazi
				String prefix = new String();
				for (int i = 0; i < delta; i++) {
					prefix += " ";
				}
				local = prefix + local;
			} else { // destra + zeri
				String prefix = new String();
				for (int i = 0; i < delta; i++) {
					prefix += "0";
				}
				local = prefix + local;
			}
		} else {
			throw new IllegalArgumentException("Tipo di formattazzione errato");
		}
		return local;
	}
}
