import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * @author: Philip Kottmann
 * @Datum: 7.4.2025
 * @Inhalt: Einstellungen der Kaffeemaschine anzeigen und ändern
 */

public class Einstellungen {
    private static int einstellungenAuswahl;
    private static List<String> menu;
    private static Fuellstand bohnenBehaelter = new Fuellstand("Bohnenbehälter", 1000);
    private static Fuellstand milchBehaelter = new Fuellstand("Milchbehälter", 1000);
    private static Fuellstand wasserTank = new Fuellstand("Wassertank", 2500);


    // leerer Konstruktor der verhindert, dass eine Instanz erstellt wird.
    private Einstellungen(){
    }

    public static void setEinstellungenAuswahl(int einstellungenAuswahl) {
        Einstellungen.einstellungenAuswahl = einstellungenAuswahl;
    }

    // Menüpunkte über ein ArrayList definiert, über das später per for-Schleife iteriert wird.
    protected static void einstellungsMenueAnzeigen(){
        MenueText.ausgeben("Einstellungen");

        menu = new ArrayList<String>();

        menu.add("Getränke-Einstellungen ändern");
        menu.add("Neues Getränk hinzufügen");
        menu.add("Anzahl zubereiteter Getränke anzeigen");
        menu.add("Umsatz der Maschine anzeigen");
        menu.add("Füllstand Bohnenbehälter anzeigen");
        menu.add("Füllstand Milchbehälter anzeigen");
        menu.add("Füllstand Wassertank anzeigen");
        menu.add("Reset Bezüge");
        menu.add("Zurück");
    }

    protected static void auswahlBehandeln(GetraenkeAngebot getraenkeAngebot, Scanner consoleScanner){
        try{
            String eingabe = consoleScanner.nextLine().trim();
            if (eingabe.isEmpty()){
                System.out.print("Bitte eine Zahl eingeben!");
            }

            setEinstellungenAuswahl(Integer.parseInt(eingabe));

            switch (einstellungenAuswahl){
                case 1 -> getraenkeEinstellungenAendern();
                case 2 -> neuesGetraenkHinzufuegen(getraenkeAngebot, consoleScanner);
                case 3 -> System.out.println("Insgesamt zubereitete Getränke: " + Bezuege.anzahlAnzeigen());
                case 4 -> umsatzAnzeigen();
                case 5 -> bohnenBehaelter.fuellstandAnzeigen();
                case 6 -> milchBehaelter.fuellstandAnzeigen();
                case 7 -> wasserTank.fuellstandAnzeigen();
                case 8 -> Bezuege.resetBezuege();
                case 9 -> System.out.println("Zurück...");
                default -> System.out.println("Bitte eine verfügbare Auswahl treffen!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // KaffeemaschineOOP.warteSchleife(consoleScanner);
    }

    private static void getraenkeEinstellungenAendern(){
        System.out.println("Hier können später die Getränke-Einstellungen geändert werden " +
                "(Füllmenge, Brühzeit, Preis)");
    }

    public static void neuesGetraenkHinzufuegen(GetraenkeAngebot getraenkeAngebot, Scanner consoleScanner){
        System.out.println("Hier kann später ein neues Getränk hinzugefügt werden.");
        System.out.println("Testdaten werden hinzugefügt: Testkaffee - 600 ml - 2.0 min - ja - 8.0€");
        Getraenk neuesGetraenk = new Getraenk(
                "Testkaffee",
                600,
                2.0,
                true,
                8.0);
        getraenkeAngebot.addNeuesGetraenk(neuesGetraenk);
    }

    private static void umsatzAnzeigen(){
        System.out.println("Hier wird später der an dieser Maschine gemachte Umsatz angezeigt.");
    }

    public static void anzeigen(GetraenkeAngebot getraenkeAngebot, Scanner consoleScanner){
        do {
            einstellungsMenueAnzeigen();
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i));
            }
            System.out.print("Bitte Auswahl treffen: (1 - " + menu.size() + "): ");

            auswahlBehandeln(getraenkeAngebot, consoleScanner);

        }while (einstellungenAuswahl < menu.size());
    }
}