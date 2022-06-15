package Package;
import java.sql.Date;

//для коректности даты
public class Util {
    public static Date getCorrectDate(Date date) {
        return (new Date(2022, 6, 17));
    }
}