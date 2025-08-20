/**
 * @author: Philip Kottmann
 * @Datum: 27.3.2025
 * @Inhalt: Überschrift des Menüs erzeugen
 */
public class MenueText {
    private static int menueTextBreite = 42;

    public static void ausgeben(String ueberschrift) {
        Trennlinie.ausgeben(menueTextBreite);
        ueberSchriftAusgeben(ueberschrift);
        Trennlinie.ausgeben(menueTextBreite);
    }

    public static int getMenueTextBreite() {
        return menueTextBreite;
    }

    private static void ueberSchriftAusgeben(String ueberschrift) {
        int anzahlZeichenDerUeberschrift = ueberschrift.length();
        int anZahlLeerzeichen = 2;

        // Geteilt durch "2", weil die Gleichzeichen ja VOR und NACH der Überschrift ausgegeben werden
        int anZahlGleichZeichen = (menueTextBreite - anzahlZeichenDerUeberschrift - anZahlLeerzeichen) / 2;

        // Falls die Gesamtbreite ungerade ist, ein zusätzliches Gleichzeichen auf einer Seite hinzufügen
        String extraZeichen = (menueTextBreite % 2 == (anzahlZeichenDerUeberschrift + anZahlLeerzeichen) % 2) ? "" : "="; // ternärer Operator

        System.out.println(
                "=".repeat(anZahlGleichZeichen) +
                        " " + ueberschrift + " " +
                        "=".repeat(anZahlGleichZeichen) + extraZeichen);
    }
}