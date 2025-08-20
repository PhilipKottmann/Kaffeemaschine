import javax.crypto.spec.PSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author: Philip Kottmann
 * @Datum: 24.3.2025
 * @Inhalt: Klasse "Getränk"
 */

public class Getraenk {
    // Fields/Attribute
    private String bezeichnung;
    private int fuellmenge;
    private double bruehzeit;
    private boolean milchschaum;
    private double preis;

    // Konstruktoren
    public Getraenk(String bezeichnung, int fuellmenge, double bruehzeit, boolean milchschaum, double preis){
        setBezeichnung(bezeichnung);
        setFuellmenge(fuellmenge);
        setBruehzeit(bruehzeit);
        setMilchschaum(milchschaum);
        setPreis(preis);
    }

    // Methoden
    //-- Getränk ausgeben
    public void getraenkAusschenken(){
        int shots = 1;
        //-- Progress-Bar zum Brühfortschritt
        // Bruehfortschritt bruehfortschritt = new Bruehfortschritt(bruehzeit, milchschaum);
        System.out.println(bezeichnung + " zubereitet! Bitte schön!");
        System.out.println("Füllmenge: " + fuellmenge + " ml\n" +
                "Brühzeit: " + bruehzeit + " min\n" +
                (milchschaum == false ? "Ohne" : "Mit") + " Milchschaum");
        Bezuege.anzahlBezuegeErhoehen(shots);
    }

    // Getter-Methoden
    String getBezeichnung(){
        return bezeichnung;
    }

    public double getPreis() {
        return preis;
    }

    // Setter-Methoden
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setFuellmenge(int fuellmenge) {
        this.fuellmenge = fuellmenge;
    }

    public void setBruehzeit(double bruehzeit) {
        this.bruehzeit = bruehzeit;
    }

    public void setMilchschaum(boolean milchschaum) {
        this.milchschaum = milchschaum;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }
}