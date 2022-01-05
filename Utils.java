import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

class Utils {

    // Private instance of the SimpleDateFormat class which will utilize dates in the
    // "yyyy-MM-dd" format
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // The convertDate function parses the input which is in the form of "yyyy-MM-dd" and
    // returns it in word form and throws ParseException if String is in the wrong format
    static Date convertDate(String input) throws ParseException {
        return formatter.parse(input);
    }

    // The formattedDate function is used to format the data given in the "yyyy-MM-dd" form
    static String formattedDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
