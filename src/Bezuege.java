import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.*;

public class Bezuege {
    // static Attribute
    static int anzahlBezuege;
    private static final Path DATEI_PFAD = Path.of("zubereiteteGetraenke.txt");
    private static final String url = "jdbc:sqlite:kaffeemaschine.db";
    private static final String tabellenNameBezuege = "bezuege";
    private static final String attributNameBezuege = "bezuege";

    // Leerer Konstruktor, damit keine Instanz erzeugt werden kann.
    private Bezuege(){

    }

    // Getter-Methoden
    static int getAnzahlBezuege() {
        return anzahlBezuege;
    }

    public Path getDATEI_PFAD() {
        return DATEI_PFAD;
    }

    // Setter-Methoden
    private static void setAnzahlBezuege(int anzahlBezuege) {
        Bezuege.anzahlBezuege = anzahlBezuege;
    }

    public static void anzahlBezuegeErhoehen(int shots) {
        setAnzahlBezuege((anzahlAnzeigen() + shots));
        anzahlbezuegeSchreiben(getAnzahlBezuege());
    }

    // Eigene Methoden
    // Anzahl Bezüge in SQLite-DB schreiben
    private static void anzahlbezuegeSchreiben(int anzahlBezuege) {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                // Erstellen einer Tabelle
                String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tabellenNameBezuege + "(id INTEGER PRIMARY KEY, bezuege INTEGER)";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                }

                // Einfügen von Daten
                String insertSQL = "UPDATE " + tabellenNameBezuege
                        + " SET " + attributNameBezuege
                        + "="
                        + anzahlBezuege
                        + " WHERE id = 1";
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(insertSQL);
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    // Anzahl Bezüge in SQLite-DB schreiben:
    public static int anzahlAnzeigen(){
        int bezuegeInDatei = 0;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) { // verbindung zur Datenbank hergestellt
                // Abrufen und Anzeigen von Daten
                String selectSQL = "SELECT " + attributNameBezuege + " FROM " + tabellenNameBezuege + " WHERE id = 1";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(selectSQL)) {
                    while (rs.next()) {
                        //System.out.println("ID: " + rs.getInt("id") + ", Anzahl: " + rs.getString("bezuege"));
                        bezuegeInDatei = rs.getInt(attributNameBezuege);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        return bezuegeInDatei;
    }

    public static void resetBezuege(){
        setAnzahlBezuege(0);
        anzahlbezuegeSchreiben(getAnzahlBezuege());
    }
}
