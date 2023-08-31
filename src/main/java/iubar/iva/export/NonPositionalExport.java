package iubar.iva.export;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class NonPositionalExport {

    private static final int FIELD_LENGHT = 16;
    private static final int KEY_LENGHT = 8;

    private List<String> fRecords;
    private List<String> nKeys;

    private String record;

    public static int last_nrecord_index = 2;

    public static <T> String[] getRightValue(T value, String format) {
        if (value instanceof String) {
            return IvaFields.getFormatField((String) value, format);
        } else if (value instanceof BigDecimal) {
            return IvaFields.getFormatField((BigDecimal) value, format);
        } else if (value instanceof Date){
            return IvaFields.getFormatField((Date) value, format);
        } else {
            return IvaFields.getFormatField((Boolean) value, format);
        }
    }

    public String getFinalRecord(String field, String[] value) {
        String tmp = "";
        int frecord_index = 0;
        int index = -1;

        while (this.setRecord(frecord_index) && index == -1) {
            index = getIndexOfLastNField(field, 0);
            frecord_index++;
        }

        if (index == -1) {
            index = this.getIndexOfPrecedentField(field);
        } else {
            index += FIELD_LENGHT + KEY_LENGHT;
        }
        if (index > this.record.length()) {
            index = this.record.length();
        }

        for (String aValue : value) {
            tmp += field + aValue;
        }

        return this.record.substring(0, index) +
                tmp +
                this.record.substring(index);
    }

    private boolean setRecord(int index) {
        if (this.fRecords.size() - 1 > PositionalExport.next_b_record
                && this.fRecords.size() - index -1 > PositionalExport.next_b_record
                && index < this.fRecords.size()) {

            this.record = this.fRecords.get(this.fRecords.size() - index -1);
            last_nrecord_index = this.fRecords.size() - index - 1;
            
            return true;
        } else {
            this.record = "";
            return false;
        }
    }

    private int getIndexOfLastNField(String field, int fromIndex) {
        if (this.record.length() <= 0)
            return -1;
        
        int response;
        int index = this.record.indexOf(field, fromIndex);

        if (index == -1)
            return -1;

        response = getIndexOfLastNField(field, index + 1);

        if (response == -1)
            return index;
        else
            return response;
    }

    private int getIndexOfPrecedentField(String field) {
        String precedent_field;
        int index = -1;
        int frecord_index = 0;

        for (int i = nKeys.indexOf(field) - 1; i > -1; i--) {
            precedent_field = nKeys.get(i);
            frecord_index = 0;

            while (this.setRecord(frecord_index)) {
                index = getIndexOfLastNField(precedent_field, 0);

                if (index != -1) {
                    i = -1;
                    break;
                }
                frecord_index++;
            }
        }

        if (index == -1) {
            this.setRecord(0);
            index = this.record.length();
        } else {
            index += FIELD_LENGHT + KEY_LENGHT;
        }
        
        return index;
    }

    public NonPositionalExport(List<String> fRecords, List<String> nKeys) {
        this.fRecords = fRecords;
        this.nKeys = nKeys;
    }

}