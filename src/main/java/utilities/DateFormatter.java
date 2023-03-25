package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for date formatting
 */
public class DateFormatter {
    private final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat toDateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
    private final SimpleDateFormat toStringFormatter = new SimpleDateFormat(PATTERN);

    /**
     * Convert String from "yyyyMMddHHmmss" format to String "yyyy-MM-dd HH:mm:ss"
     * @param s String in the "yyyyMMddHHmmss" format
     * @return String in the "yyyy-MM-dd HH:mm:ss" format
     */
    public String formatToString(String s) {
        try {
            Date date = toDateFormatter.parse(s);
            return toStringFormatter.format(date);
        } catch (ParseException e) {
            System.out.println("Impossible to parse data");
            return null;
        }
    }

    /**
     * Convert String from "yyyyMMddHHmmss" format to Date
     * @param s String in the "yyyyMMddHHmmss" format
     * @return Date
     */
    public Date formatToDate(String s) {
        try {
            return toDateFormatter.parse(s);
        } catch (ParseException e) {
            System.out.println("Impossible to parse string");
            return null;
        }
    }

}
