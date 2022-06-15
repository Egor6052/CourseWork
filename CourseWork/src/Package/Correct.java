package Package;
// Сокращает число до 2 знаков после запятой;
public class Correct {
    public static String formatDouble(double n) {
        return String.format("%.2f", n);
    }
}
