/**
 * @author: Philip Kottmann
 * @Datum: 27.3.2025
 * @Inhalt: Trennlinie dynamisch erzeugen
 */
public class Trennlinie {
    private static String trennLinie = "=+";

    public static void ausgeben(int breite) {
        System.out.println(trennLinie.repeat(breite / 2));
    }
}