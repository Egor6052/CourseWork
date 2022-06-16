package Package;
import java.util.Date;
import java.text.SimpleDateFormat;

//для коректности даты
public class Util {
    public static String getCorrectDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
