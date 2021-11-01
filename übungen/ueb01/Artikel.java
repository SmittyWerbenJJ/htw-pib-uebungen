/**
 * Klasse Artikel für eine einfache Bestandsführung Objektklasse mit den
 * Attributen: Artikelnummer, Artikelart und Bestand. Kann den Zu- und Abgang
 * vom Bestand buchen. Vom Objekt einzelne Attribute neu setzen. Die Werte der
 * Attribute auflisten und einzeln anzeigen.
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 2021.01.11
 */
public class Artikel {
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int artikelNr;
    private String art;
    private int bestand;

    // Getter und Setter
    /**
     * Erhalte die Artikelnummer
     * 
     * @return die ArtikelNummer
     */
    public int getArtikelNr() {
        return this.artikelNr;
    }

    /**
     * Erhalte die ArtikelArt.
     * 
     * @return die ArtikelArt
     */
    public String getArt() {
        return this.art;
    }

    /**
     * Erhalte den bestand des Artikels
     * 
     * @return den Bestand
     */
    public int getBestand() {
        return this.bestand;
    }

    /**
     * Setze die Artikelnummer
     * 
     * @param neueArtikelNr die neue Artikelnummer
     */
    public void setArtikelNr(int neueArtikelNr) {
        this.artikelNr = neueArtikelNr;
    }

    /**
     * Setze die Artikelart
     * 
     * @param neueArt die qNeue Artikelart
     */
    public void setArt(String neueArt) {
        this.art = neueArt;
    }

    // Konstruktoren
    /**
     * Standartkonstruktor wie Artikel() ergibt keinen sinn, da der artikel
     * mindestens eine artikelnummer und artikleart haben muss. Ansonsten gäbe es
     * einen Artikel aber keine Bestnadsverwaltung für diesen Artikel. Ausserdem
     * würden die methoden toString keine berechnbare Ausgaben, anhand der
     * gepflegten Artikeldaten generieren.
     */
    /**
     * Erstellung eines Artikels
     * 
     * @param artikelNr die ArtikelNUmmer
     * @param art       die ArtikelArt
     * @param bestand   Bestand des Artikels
     */
    public Artikel(int artikelNr, String art, int bestand) {
        this.artikelNr = artikelNr;
        this.art = art;
        this.bestand = bestand;
    }

    /**
     * Erstellung eines Artikels
     * 
     * @param artikelNr die ArtikelNUmmer
     * @param art       die ArtikelArt
     */
    public Artikel(int artikelNr, String art) {
        this.artikelNr = artikelNr;
        this.art = art;
        this.bestand = 0;
    }

    // Methoden
    /**
     * Hinzufügen von Artikeln.
     * 
     * @param menge hinzuzufügende Menge
     */
    public void bucheZugang(int menge) {
        if (menge > 0) {
            this.bestand += menge;
        } else {
            System.out.println("Zugangsmenge kann nicht negativ oder 0 sein!");
        }

    }

    /***
     * Entfernen von Artikeln
     * 
     * @param menge zu entfernende Menge
     */
    public void bucheAbgang(int menge) {
        if ((menge > 0) && (menge <= this.bestand) && (this.bestand > 0)) {
            this.bestand -= menge;
        } else {
            System.out.println("Kein Bestand oder nicht genügend Artikel zuer entahme verfügbar!");
        }
    }

    /***
     * Ausgabe der Infos über das Object
     */
    public String toString() {
        String ausgabe = "Artikel: " + this.artikelNr + ", Art: " + this.art + ", Bestand: " + this.bestand;
        System.out.println(ausgabe);
        return ausgabe;
    }

}
