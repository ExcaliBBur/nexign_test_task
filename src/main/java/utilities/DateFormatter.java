package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat toDateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
    private final SimpleDateFormat toStringFormatter = new SimpleDateFormat(PATTERN);

    public String formatToString(String s) {
        try {
            Date date = toDateFormatter.parse(s);
            return toStringFormatter.format(date);
        } catch (ParseException e) {
            System.out.println("Impossible to parse data");
            return null;
        }
    }

    public Date formatToDate(String s) {
        try {
            return toDateFormatter.parse(s);
        } catch (ParseException e) {
            System.out.println("Impossible to parse string");
            return null;
        }
    }

}
