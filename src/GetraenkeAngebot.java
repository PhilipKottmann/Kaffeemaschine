/**
 * @author: Philip Kottmann
 * @Datum: 27.3.2025
 * @Inhalt: Getränke-Angebot mit Java Generics erzeugen
 */

import java.util.ArrayList;
import java.util.List;

public class GetraenkeAngebot {
    private List<Getraenk> getraenke;

    public GetraenkeAngebot() {
        getraenke = new ArrayList<Getraenk>(); // Klassenbezeichnung in spitzen Klammern nicht nötig

        getraenke.add(new Getraenk("Café Crème",200, 1.0, false, 3.20));
        getraenke.add(new Getraenk("Espresso",50, 0.5, false, 1.90));
        getraenke.add(new Getraenk("Espresso doppio",900, 0.9, false, 2.80));
        getraenke.add(new Getraenk("Café Cortado", 60, 1.0, true, 3.00));
        getraenke.add(new Getraenk("Cappuccino", 250, 1.5, true, 3.90));
        getraenke.add(new Getraenk("Latte Macchiato", 300, 1.5, true, 4.30));
    }

    public List<Getraenk> getGetraenke() {
        return getraenke;
    }

    public int getAnzahlGetraenke() {
        return getraenke.size();
    }

    // TODO: Füge ich so korrekterweise eine neues Getränk hinzu?
    // TODO: Muss ich, um das Array "getraenke" persistent zu speichern (und zu erweitern) auslagern (.txt oder SQL)?
    public void addNeuesGetraenk(Getraenk neuesGetraenk){
        getraenke.add(neuesGetraenk);
    }
}