/**
 * Klasse Artikel für eine einfache Bestandsführung Objektklasse mit den
 * Attributen: Artikelnummer, Artikelart und Bestand. Kann den Zu- und Abgang
 * vom Bestand buchen. Vom Objekt einzelne Attribute neu setzen. Die Werte der
 * Attribute auflisten und einzeln anzeigen.
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 2021.11.29
 */
public class Artikel {

  private static final String BESCHREIBUNG = "Ein Artikel";
  private static final String FEHLER_BESTAND = "Bestand:Bitte Positive Zahl eingeben";
  private static final String FEHLER_POSITIVE_ZAHL = "Preis: Bitte positive Zahl eingeben";
  private static final String FEHLER_ARTIKELART = "ArtikelArt: Bitte Text eingeben";
  private static final String FEHLER_ARTIKELNUMMER = "Artikelnummer: Bitte Zahl zwischen 1000 und 9999 eingeben";
  private int artikelNr;
  private String art;
  private int bestand;
  private double preis;

  public int getArtikelNr() {
    return this.artikelNr;
  }

  public String getArt() {
    return this.art;
  }

  public double getPreis() {
    return this.preis;
  }

  public int getBestand() {
    return this.bestand;
  }

  /**
   * Setze die Artikelnummer.
   * Artikelnummer muss zwischen 1000 und 9999 sein
   *
   * @param neueArtikelNr die neue Artikelnummer
   * @throws IllegalArgumentException wenn artikelNR nicht den Anforderungen
   *                                  entspricht
   */
  public void setArtikelNr(int neueArtikelNr) {
    if (istArtikelnummerValide(neueArtikelNr) == false) {
      throw new IllegalArgumentException(FEHLER_ARTIKELNUMMER);
    }
    this.artikelNr = neueArtikelNr;
  }

  /**
   * Setze die Artikelart
   *
   * @param neueArt die Neue Artikelart
   * @throws IllegalArgumentException bei leerer Eingabe
   */
  public void setArt(String neueArt) {
    if (neueArt.isBlank()) {
      throw new IllegalArgumentException(FEHLER_ARTIKELART);
    }
    this.art = neueArt;
  }

  /**
   * setze den preis dieses artikels
   * 
   * @return den Preis
   * @throws IllegalArgumentException wenn der Preis negativ ist
   */
  public void setPreis(Double neuerPreis) {
    if (neuerPreis < 0) {
      throw new IllegalArgumentException(FEHLER_POSITIVE_ZAHL);
    }
    this.preis = neuerPreis;
  }

  /**
   * toString Methode (ArtikelNR,Art,BEstand)
   */
  public String toString() {
    return String.format("ArtikelNr: %i - Art: %s - Bestand: %s - ", this.artikelNr, this.art, this.bestand);
  }

  /**
   * Erstellung eines Artikels
   *
   * @param artikelNr die ArtikelNummer
   * @param art       die ArtikelArt
   * @throws IllegalArgumentException bei fehlerhafter ArtikelNummer(nicht 4
   *                                  stellig) oder wenn art leer ist
   */
  public Artikel(int artikelNr, String art) {
    if (!istArtikelnummerValide(artikelNr)) {
      throw new IllegalArgumentException(FEHLER_ARTIKELNUMMER);
    }
    if (art.trim().isEmpty()) {
      throw new IllegalArgumentException(FEHLER_ARTIKELART);
    }
    this.artikelNr = artikelNr;
    this.art = art.trim();
  }

  /**
   * Erstellung eines Artikels
   *
   * @param artikelNr die ArtikelNummer
   * @param art       die ArtikelArt
   * @param bestand   Bestand des Artikels
   */
  public Artikel(int artikelNr, String art, int bestand) {
    this(artikelNr, art);
    if (bestand < 0) {
      throw new IllegalArgumentException(FEHLER_BESTAND);
    }
    this.bestand = bestand;
  }

  /**
   * Erstellung eines Artikels
   *
   * @param artikelNr die ArtikelNummer
   * @param art       die ArtikelArt
   * @param bestand   Bestand des Artikels
   * @param preis     preis des Artikels
   * @throws IllegalArgumentException wenn der Preis (nahe) 0 oder negativ ist
   */
  public Artikel(int artikelNr, String art, Double preis) {
    this(artikelNr, art);
    if (istZahlNull(preis)) {
      throw new IllegalArgumentException(FEHLER_POSITIVE_ZAHL);
    }
    if (preis < 0) {
      throw new IllegalArgumentException(FEHLER_POSITIVE_ZAHL);
    }
    this.preis = preis;
  }

  /**
   * Erstellung eines Artikels
   *
   * @param artikelNr die ArtikelNummer
   * @param art       die ArtikelArt
   * @param bestand   Bestand des Artikels
   * @param preis     preis des Artikels
   * @throws IllegalArgumentException wenn der Preis (nahe) 0 oder negativ ist
   */
  public Artikel(int artikelNr, String art, int bestand, Double preis) {
    this(artikelNr, art, bestand);
    if ((istZahlNull(preis)) || (preis < 0)) { // zahl "ist" 0 oder negativ
      throw new IllegalArgumentException(FEHLER_POSITIVE_ZAHL);
    }
    this.preis = preis;
  }

  public String getBeschreibung() {
    return BESCHREIBUNG;
  };

  /**
   * Hinzufügen von Artikeln.
   *
   * @param menge hinzuzufügende Menge
   */
  public void bucheZugang(int menge) {
    if (menge > 0) {
      this.bestand += menge;
    } else {
      System.out.println(FEHLER_POSITIVE_ZAHL);
    }
  }

  /***
   * Entfernen von Artikeln
   *
   * @param menge zu entfernende Menge
   * 
   */
  public void bucheAbgang(int menge) {
    if ((menge > 0) && (menge <= this.bestand) && (this.bestand > 0)) {
      this.bestand -= menge;
    } else {
      System.out.println(
          "Kein Bestand oder nicht genügend Artikel zuer entahme verfügbar!");
    }
  }

  /**
   * uberprufung ob eine zahl fast null ist - 3 nachkommastellen
   * 
   * @param zahl
   * @return true wenn zahl nahe 0
   */
  private boolean istZahlNull(Double zahl) {
    if (zahl <= 0.000d && zahl >= -0.000d) {
      return true;
    }
    return false;
  }

  /**
   * uberprufung ob eine artikelnummer 4 stellig und positiv ist
   * 
   * @param zahl
   * @return yes wenn die zahl im bereich 1000 und 9999
   */
  public static boolean istArtikelnummerValide(int zahl) {
    return zahl >= 1000 && zahl <= 9999;
  }
}
