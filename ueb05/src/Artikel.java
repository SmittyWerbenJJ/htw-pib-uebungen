package src;

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

  private int artikelNr;
  private String art;
  private int bestand;
  private double preis;

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
      throw new IllegalArgumentException(
          "arikelNr muss vierstellig und groesser 0 sein!");
    }
    if (art.trim().isEmpty()) {
      throw new IllegalArgumentException("arikelArt darf nicht leer sein");
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
      throw new IllegalArgumentException("Bestand kann nicht negativ sein!");
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
      throw new IllegalArgumentException("Preis darf nicht '0' (Null) sein");
    }
    if (preis < 0) {
      throw new IllegalArgumentException("Preis darf nicht negativ sein");
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
    if (istZahlNull(preis)) {
      throw new IllegalArgumentException("Preis darf nicht '0' (Null) sein");
    }
    if (preis < 0) {
      throw new IllegalArgumentException("Preis darf nicht negativ sein");
    }
    this.preis = preis;
  }

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
   * erhalte den preis dieses artikels
   * 
   * @return den Preis
   */
  public double getPreis() {
    return this.preis;
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
    if (istArtikelnummerValide(neueArtikelNr) == false) {
      throw new IllegalArgumentException("Artikelnummer muss eine Positive 4-Stellige Zahl sein");
    }
    this.artikelNr = neueArtikelNr;
  }

  /**
   * Setze die Artikelart
   *
   * @param neueArt die qNeue Artikelart
   */
  public void setArt(String neueArt) {
    if (neueArt.isBlank()) {
      throw new IllegalArgumentException("neueArt darf nicht leer oder aus nur aus leerzeichen bestehen");
    }
    this.art = neueArt;
  }

  /**
   * setze den preis dieses artikels
   * 
   * @return den Preis
   */
  public void setPreis(Double neuerPreis) {
    if (neuerPreis < 0) {
      throw new IllegalArgumentException("Preis darf nicht negativ sein");
    }
    this.preis = neuerPreis;
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

  /***
   * Ausgabe der Infos über das Object
   */
  public String toString() {
    String ausgabe = "Artikel: " +
        this.artikelNr +
        ", Art: " +
        this.art +
        ", Bestand: " +
        this.bestand;
    return ausgabe;
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

  // #endregion
}
