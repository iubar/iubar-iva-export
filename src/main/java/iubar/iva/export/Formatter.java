package iubar.iva.export;

public class Formatter {

	public String stringFormatterDelta(String value, int delta, FormatType type) {
		String local = value;
		String prefix = "";

		if (type.equals(FormatType.NOFORMAT))	// NO FORMATTAZIONE
			return local;

		if (type.equals(FormatType.SXSPACE)) {	// sinistra + spazi
			for (int i = 0; i < delta; i++) {
				local += " ";
			}
		} 
		
		if (type.equals(FormatType.DXSPACE)) {	// destra + spazi
			for (int i = 0; i < delta; i++) {
				prefix += " ";
			}
			local = prefix + local;
		} 
		
		if (type.equals(FormatType.DXZERO)) {	// destra + zeri
			for (int i = 0; i < delta; i++) {
				prefix += "0";
			}
			local = prefix + local;
		}

		return local;
	}
}
