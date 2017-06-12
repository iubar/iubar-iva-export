package iubar.iva.export;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PositionalExport {

    private List<String> fRecords;

    private String record;
    private int b_records = 0;
    public static int next_b_record = 1;

    public static <T> String getRightValue(T value, String format, int length) {
        if (value instanceof String) {
            return IvaFields.getFormatField((String) value, format, length);
        } else if (value instanceof BigDecimal) {
            return IvaFields.getFormatField((BigDecimal) value, format, length);
        } else if (value instanceof Date) {
            return IvaFields.getFormatField((Date) value, format, length);
        } else {
            return IvaFields.getFormatField((Boolean) value, format, length);
        }
    }

    public String getFinalRecord(String value, int field, int position, int length) {
        this.setRecord(field);
        if (this.record.length() >= position - 1 &&
                this.record.length() < position + length - 1) {
            return this.record.substring(0, position - 1) +
                    value +
                    this.record.substring(position - 1);
        } else if (this.record.length() >= position + length - 1) {
            return this.record.substring(0, position - 1) +
                    value +
                    this.record.substring(position + length - 1);
        } else {
            String padding = "";
            for (int i = 0 ; i < position - 1; i++) {
                padding += " ";
            }
            if (this.record.length() > 0) {
                return this.record.substring(0, this.record.length() - 1) +
                        padding +
                        value;
            } else {
                return padding + value;
            }
        }
    }

    private void setRecord(int field) {
        if (this.b_records > 0 && field > 13 && field < 118) {
            if (field == 117) {
                this.b_records++;
            }
            if (field == 13) {
                next_b_record = NonPositionalExport.last_nrecord_index + 1;
            }
            this.record = getFRecord(next_b_record);
        } else {
            if (field > 124) {
                if (field == 125) {
                    this.fRecords.set(this.fRecords.size() - 1, this.fRecords.get(this.fRecords.size() - 1) + "\r\n");
                }
                this.record = getFRecord(NonPositionalExport.last_nrecord_index + 1);
            } else if (field > 117) {
                this.record = getFRecord(2);
            } else if (field > 13) {
                if (field == 117) {
                    this.b_records++;
                }
                this.record = getFRecord(1);
            } else if (field > 0) {
                this.record = getFRecord(0);
            } else {
                this.record = "";
            }
        }
    }

    public int getCurrentIndex(int field) {
        if (this.b_records > 0 && field > 13 && field <118) {
            return next_b_record;
        } else {
            if (field > 124) {
                return NonPositionalExport.last_nrecord_index + 1;
            } else if (field > 117) {
                return 2;
            } else if (field > 13) {
                return 1;
            } else if (field > 0) {
                return 0;
            } else {
                throw new IllegalArgumentException("field e' minore di 0");
            }
        }
    }

    private String getFRecord(int index) {
        if (this.fRecords.size() > index) {
            return this.fRecords.get(index);
        } else {
            return "";
        }
    }

    public PositionalExport (List<String> fRecords) {
        this.fRecords = fRecords;
    }

}