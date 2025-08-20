/**
 * @author: Philip Kottmann
 * @Datum: 23. März 2025
 * @Inhalt: Kaffeemaschine in OOP
 */

/* TODO:
- Wert der Variablen "anzahlBezuege" persistent speichern.
- Eingezahltes Guthaben beachten, zu wenig Guthaben abfangen, Rückgeld herausgeben
 */

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class KaffeemaschineOOP {
    public static void main(String[] args) throws Exception {
        GetraenkeAngebot getraenkeAngebot = new GetraenkeAngebot();

        String ueberschrift = "Kaffee-Automat";
        MenueText.ausgeben(ueberschrift);
        System.out.println(getraenkeAngebot.getAnzahlGetraenke() + " verschiedene Getränke stehen zur Auswahl");

        var consoleScanner = new Scanner(System.in);

        int getraenkeWunsch = 0;
        int einstellungsMenueAnzeigen = 0;  // wird in der do-while-Schleife zyklisch aktualisiert,
                                            // damit "getraenkeAngebot.getAnzahlGetraenke()" aktualisiert wird.

        do {
            // Dyn. Variable, die es ermöglicht, ins Einstellungsmenü abzubiegen => Vermeidung von "Magic Numbers"
            einstellungsMenueAnzeigen = getraenkeAngebot.getAnzahlGetraenke() + 1;

            getraenkeAngebotAnzeigen(getraenkeAngebot);
            erweiterteAnzeigeGetraenkeAngebot(getraenkeAngebot);

            // Nutzer zur Eingabe auffordern:
            System.out.print("Bitte Auswahl treffen (1 - " + (getraenkeAngebot.getAnzahlGetraenke()+2) + "): ");

            // Eingabe prüfen und verifizieren
            try {
                String eingabe = consoleScanner.nextLine().trim(); // Ganze Zeile ohne vor- oder nachgestellte Leerzeichen einlesen
                if (eingabe.isEmpty()) { // Prüfung, ob Eingabe leer
                    System.out.println("Bitte eine Zahl eingeben.");
                    continue;
                }
                getraenkeWunsch = Integer.parseInt(eingabe);

                if (getraenkeWunsch >= 1 && getraenkeWunsch <= getraenkeAngebot.getAnzahlGetraenke()){ // valide Getränke-Auswahl
                    // Getränk zubereiten
                    getraenkeAngebot.getGetraenke().get(getraenkeWunsch - 1).getraenkAusschenken();

                    System.out.println(); // Leerzeichen zur Trennung der Bestellvorgänge
                } else if(getraenkeWunsch == einstellungsMenueAnzeigen){    // Einstellungsmenü anzeigen
                    Einstellungen.anzeigen(getraenkeAngebot, consoleScanner); // Übergabe von "consoleScanner" um das Objekt wiederzuverwenden
                } else if (getraenkeWunsch == getraenkeAngebot.getAnzahlGetraenke() + 2) {  // Ausschalten der Maschine
                    System.out.println("Kaffeeautomat wird ausgeschaltet...");
                } else {        // Fehlerhafte Eingabe
                    System.out.println("Auswahl nicht verfügbar. Bitte eine Zahl im angegebenen Bereich eingeben");
                }
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben");
            }
            // warteSchleife(consoleScanner);
        } while (getraenkeWunsch != (getraenkeAngebot.getAnzahlGetraenke() + 2));
        consoleScanner.close();
    }

    private static void getraenkeAngebotAnzeigen(GetraenkeAngebot getraenkeAngebot) {
        // Definition um "double" mit zwei Dezimalstellen anzugeben
        Locale.setDefault(Locale.GERMAN);
        DecimalFormat dezimalAngabe = new DecimalFormat("##0.00");

        String menueUeberschrift = "Menü";
        MenueText.ausgeben(menueUeberschrift);

        // Ausgabe der Getränkeauswahl
        for (int i = 0; i < getraenkeAngebot.getAnzahlGetraenke(); i++) {
            System.out.println((i+1) + ". " + getraenkeAngebot.getGetraenke().get(i).getBezeichnung() +
                    " (€ " + dezimalAngabe.format(getraenkeAngebot.getGetraenke().get(i).getPreis()) + ")");
        }
    }

    // Die letzte beiden statischen Anzeigen herauslösen, damit Methode
    // "getraenkeAngebotAnzeigen()" wiederverwendet werden kann:
    private static void erweiterteAnzeigeGetraenkeAngebot(GetraenkeAngebot getraenkeAngebot){
        System.out.println((getraenkeAngebot.getAnzahlGetraenke() + 1) + ". Einstellungen");
        System.out.println((getraenkeAngebot.getAnzahlGetraenke() + 2) + ". Ausschalten");
    }

    // TODO: Warteschleife analog "Drücke beliebige Taste um fortzufahren"...
    // hier ist noch offen, wo und wann genau diese Methode greifen soll
    public static void warteSchleife(Scanner consoleScanner){
        System.out.println("Drücke eine beliebige Taste, um fortzufahren...");
        consoleScanner.nextLine(); // Wartet auf die Eingabe
        }
}