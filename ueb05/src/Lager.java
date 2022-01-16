package src;

/**
 * ueb05
 * Klasse Lager zum verwalten von mehreren artikeln
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 09.01.2022
 */
public class Lager {

  private Artikel[] alleArtikel;

  /**
   * Erstellt ein lager
   */
  public Lager() {
    this.alleArtikel = new Artikel[10];
  }

  /**
   * Erstellt ein lager
   */
  public Lager(int dimension) {
    if (dimension == 0) {
      throw new IllegalArgumentException(
          "Erstellung eines Leeren lagers nicht erlaubt!");
    }
    this.alleArtikel = new Artikel[dimension];
  }

  /**
   * erhalte die anzahl der artikel im lager
   * 
   * @return summer der artikel in den lagerplätzen
   */
  public int getArtikelAnzahl() {
    int anzahl = 0;
    for (Artikel artikel : alleArtikel) {
      if (artikel != null) {
        anzahl++;
      }
    }
    return anzahl;
  }

  /**
   * erhalte die lagergroesse
   * 
   * @return die lagergroesse
   */
  public int getLagerGroesse() {
    return alleArtikel.length;
  }

  /**
   * ermitlung eines Artikel an einer bestimmten Stelle im Lager.
   * 
   * @param index die zu ermittelnde stelle
   * @return den artikel zur gegebenen Stelle. {@code null} wenn kein artikel
   *         gefunden
   * @throws IndexOutOfBoundsException wenn der index groesser als das lager ist
   * @throws IllegalArgumentException  bei eingabe eines negativen werts
   */
  public Artikel getArtikel(int index) {
    if (index > alleArtikel.length) {
      throw new IndexOutOfBoundsException(
          "Das Lager hat nur '" + alleArtikel.length + "' Stellen.");
    }
    if (index < 0) {
      throw new IllegalArgumentException("keine Negativen werte erlaubt");
    }

    return alleArtikel[index];
  }

  /**
   * informationen des lagers ausgeben
   */
  public String toString() {
    return ("Lagergroesse: '" +
        this.getLagerGroesse() +
        "\t'Artikelanzahl: '" +
        this.getArtikelAnzahl());
  }

  /**
   * einlegen eines neues objekts in das artikel-array des lagers
   * 
   * @param artikel neu anzulegender artikel
   * @throws IndexOutOfBoundsException wenn das lager voll ist
   * @throws IllegalArgumentException  wenn Artikel nicht gueltig(null)
   */
  public void legeAnArtikel(Artikel artikel) {
    int artieklanzahl = getArtikelAnzahl();

    if (artieklanzahl == alleArtikel.length) {
      throw new IndexOutOfBoundsException("Lager Voll");
    }
    if (artikel == null) {
      throw new IllegalArgumentException("Artikel nicht gueltig!");
    }

    boolean wurdeArtikelEingelegt = false;
    for (int i = 0; i < alleArtikel.length && wurdeArtikelEingelegt == false; i++) {
      if (alleArtikel[i] == null) {
        alleArtikel[i] = artikel;
        wurdeArtikelEingelegt = true;
      }
    }
  }

  /**
   * entfernen eines artikels aus dem Lager
   * 
   * @param artikelNr
   */
  public void entferneArtikel(int artikelNr) {
    if (Artikel.istArtikelnummerValide(artikelNr) == false) {
      throw new IllegalArgumentException("Artikelnummer ungultig!");
    }
    boolean wurdeArtikelEntfernt = false;
    for (int i = 0; i < alleArtikel.length && wurdeArtikelEntfernt == false; i++) {
      if (alleArtikel[i] != null) {
        if (alleArtikel[i].getArtikelNr() == artikelNr) {
          alleArtikel[i] = null;
          wurdeArtikelEntfernt = true;
        }
      }
    }
  }

  /**
   * Zugangsbuchung fur einen Artikel
   * 
   * @param artikelNr zu buchender artikel
   * @param zugang    menge des zugangs
   * @throws IllegalArgumentException wenn artikelnummer/zugangsmenge fehlerhaft
   */
  public void bucheZugang(int artikelNr, int zugang) {
    if (Artikel.istArtikelnummerValide(artikelNr) == false) {
      throw new IllegalArgumentException("Artikelnummer nicht korrekt");
    }
    if (zugang < 0) {
      throw new IllegalArgumentException(
          "artikelbuchung nur mit menge > 0 erlaubt");
    }
    boolean wurdeEinZugangGebucht = false;
    Artikel artikel;
    for (int i = 0; i < alleArtikel.length && wurdeEinZugangGebucht == false; i++) {
      artikel = alleArtikel[i];
      if (artikel != null) {
        if (artikel.getArtikelNr() == artikelNr) {
          artikel.bucheZugang(zugang);
          wurdeEinZugangGebucht = true;
        }
      }
    }
    if (wurdeEinZugangGebucht == false) {
      throw new IllegalArgumentException("ArtikelNr nicht im Lager");
    }
  }

  /**
   * abgang buchen fur einen Artikel.
   * 
   * @param artikelNr abzugehender artikel
   * @param abgang    abgangsmenge
   */
  public void bucheAbgang(int artikelNr, int abgang) {
    if (Artikel.istArtikelnummerValide(artikelNr) == false) {
      throw new IllegalArgumentException("Artikelnummer nicht korrekt");
    }
    if (abgang < 0) {
      throw new IllegalArgumentException(
          "abgang von 0 und kleiner nicht erlaubt");
    }

    for (Artikel artikel : alleArtikel) {
      if (artikel.getArtikelNr() == artikelNr) {
        artikel.bucheAbgang(abgang);
        break;
      }
    }

    boolean wurdeEinAbgangGebucht = false;
    Artikel artikel;
    for (int i = 0; i < alleArtikel.length && wurdeEinAbgangGebucht == false; i++) {
      artikel = alleArtikel[i];
      if (artikel != null) {
        if (artikel.getArtikelNr() == artikelNr) {
          artikel.bucheAbgang(abgang);
          wurdeEinAbgangGebucht = true;
        }
      }
    }
    if (wurdeEinAbgangGebucht == false) {
      throw new IllegalArgumentException("ArtikelNr nicht im Lager");
    }
  }

  /**
   * preisanpassung EINES artikels.
   * 
   * beispiel: alterpreis: 100€. anpassung: -10.0 neuerPreis:90€
   * 
   * @param artikelNr die artikelnummer des artikels
   * @param prozent   preisanpassung in prozent
   */
  public void aenderePreisEinesArtikels(int artikelNr, double prozent) {
    if (istZahlNull(prozent)) {
      prozent = 0d;
    }
    boolean wurdePreisAngepasst = false;
    for (int i = 0; i < alleArtikel.length && wurdePreisAngepasst == false; i++) {
      if (alleArtikel[i] != null) {
        if (alleArtikel[i].getArtikelNr() == artikelNr) {
          Double aktuellerPreis = alleArtikel[i].getPreis();
          Double neuerPreis = aktuellerPreis + (aktuellerPreis * prozent / 100);
          alleArtikel[i].setPreis(neuerPreis);
          wurdePreisAngepasst = true;
        }
      }
    }
  }

  /**
   * preisanpassung ALLER artikels.
   * 
   * beispiel: alterpreis: 100€. anpassung: -10.0 neuerPreis:90€
   * 
   * @param prozent preisanpassung in prozent
   */
  public void aenderePreisAllerArtikel(double prozent) {
    if (istZahlNull(prozent)) {
      prozent = 0d;
    }
    for (int i = 0; i < alleArtikel.length; i++) {
      if (alleArtikel[i] != null) {
        Double aktuellerPreis = alleArtikel[i].getPreis();
        Double neuerPreis = aktuellerPreis + (aktuellerPreis * prozent / 100);
        alleArtikel[i].setPreis(neuerPreis);
      }
    }
  }

  /**
   * prufung ob ein artikel bereits im lager vorhanden ist
   * 
   * @param artikelNr die zu prufende artikelnummer
   * @return true falls vofhanden, false falls nicht
   */
  public boolean istArtikelImLager(int artikelNr) {
    for (int i = 0; i < alleArtikel.length; i++) {
      if (alleArtikel[i] != null) {
        if (alleArtikel[i].getArtikelNr() == artikelNr) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * uberprufung ob eine zahl fast null ist - 7 nachkommastellen
   * 
   * @param zahl
   * @return true wenn zahl nahe 0
   */
  private boolean istZahlNull(Double zahl) {
    return zahl <= 1e-7D && zahl >= -1e-7D;
  }
}
