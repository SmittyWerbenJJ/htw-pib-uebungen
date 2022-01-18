import java.util.ArrayList;
import java.util.List;

/**
 * ueb09
 * Klasse Lager zum verwalten von mehreren artikeln
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 2021.01.29
 */
public class Lager {

  private Artikel[] alleArtikel;

  /* ===== Konstruktoren ===== */
  public Lager() {
    this.alleArtikel = new Artikel[10];
  }

  public Lager(int dimension) {
    if (dimension == 0) {
      throw new IllegalArgumentException(
          "Erstellung eines Leeren lagers nicht erlaubt!");
    }
    this.alleArtikel = new Artikel[dimension];
  }

  /* ===== Getter / Setter ===== */
  /**
   * bestimmung der aktuelle Anzahl der Artikel im Lager.
   * 
   * @return anzahl der artikel im lager
   */
  public int getArtikelAnzahl() {
    int anzahl = 0;
    for (int i = 0; i < alleArtikel.length; i++) {
      if (alleArtikel[i] != null) {
        anzahl++;
      }
    }
    return anzahl;
  }

  /**
   * ermittlung Anzahl der kapzitaet des lagers
   * 
   * @return
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
   * ermittlung eines Artikels nach der ArtikelNummer
   * 
   * @param ArtikelNummer die zu suchende Artikelnummer
   * @return Den Artikel oder null
   */
  public Artikel getArtikelbyArtNummer(int ArtikelNummer) {
    for (Artikel artikel : alleArtikel) {
      if (artikel != null) {
        if (artikel.getArtikelNr() == ArtikelNummer) {
          return artikel;
        }
      }
    }
    return null;
  }

  /* ===== Methoden ===== */

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

    if (artieklanzahl == 0) {
      alleArtikel[0] = artikel;
    } else {
      for (int i = 0; i <= artieklanzahl; i++) {
        if (alleArtikel[i] == null) {
          alleArtikel[i] = artikel;
          break;
        }
      }
    }
  }

  /**
   * entfernen eines artikels aus dem Lager
   * lueckenloses entfernen aus dem artikel-array
   * 
   * @param artikelNr
   */
  public void entferneArtikel(int artikelNr) {
    if (Artikel.istArtikelnummerValide(artikelNr) == false) {
      throw new IllegalArgumentException("Artikelnummer ungultig!");
    }

    Artikel[] ergebnisArray = new Artikel[alleArtikel.length];
    for (int i = 0; i < alleArtikel.length; i++) {
      if (alleArtikel[i].getArtikelNr() == artikelNr) {
        continue;
      }
      ergebnisArray[i] = alleArtikel[i];
    }
    alleArtikel = ergebnisArray;
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
          "zugang von 0 und kleiner nicht erlaubt");
    }
    boolean wurdeEinZugangGebucht = false;
    for (Artikel artikel : alleArtikel) {
      if (artikel == null) {
        continue;
      }
      if (artikel.getArtikelNr() == artikelNr) {
        artikel.bucheZugang(zugang);
        wurdeEinZugangGebucht = true;
        break;
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
      if (artikel != null) {

        if (artikel.getArtikelNr() == artikelNr) {
          if (abgang > artikel.getBestand()) {
            throw new IllegalArgumentException("abgang von mehr als vorhanden nicht erlaubt");
          }
          artikel.bucheAbgang(abgang);
        }
      }
    }

  }

  /**
   * preisanpassung eines artikels
   * 
   * @param artikelNr die artikelnummer des artikels
   * @param prozent   preisanpassung in prozent
   */
  public void aenderePreisEinesArtikels(int artikelNr, double prozent) {
    if (istZahlNull(prozent)) {
      prozent = 0d;
    }
    for (int i = 0; i < alleArtikel.length; i++) {
      if (alleArtikel[i].getArtikelNr() == artikelNr) {
        Double aktuellerPreis = alleArtikel[i].getPreis();
        Double neuerPreis = aktuellerPreis + (aktuellerPreis * prozent);
        alleArtikel[i].setPreis(neuerPreis);
        break;
      }
    }
  }

  /**
   * preisanpassung aller artikel um einen prozentsatz.
   * (positiv oder negativ)
   * 
   * @param prozent anzuwendende anpassung in prozent
   */
  public void aenderePreisAllerArtikel(double prozent) {
    if (istZahlNull(prozent)) {
      prozent = 0d;
    }
    Double aktuellerPreis = 0d;
    Double neuerPreis = 0d;

    for (Artikel artikel : alleArtikel) {
      if (artikel == null) {
        continue;
      }
      aktuellerPreis = artikel.getPreis();
      neuerPreis = aktuellerPreis * prozent;
      artikel.setPreis(neuerPreis);
    }
  }

  /**
   * informationen des lagers ausgeben
   */
  public String toString() {
    return ("Lagergroesse: '" +
        this.getLagerGroesse() +
        "' Artikelanzahl: '" +
        this.getArtikelAnzahl());
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
   * Erhalte eine Lueckenlose Liste aller artikel aus dem lager
   * 
   * @return die liste der artikel
   */
  public List<Artikel> getAlleArtikel() {
    List<Artikel> artikelListeRueckgabe = new ArrayList<Artikel>();
    for (Artikel artikel : alleArtikel) {
      if (artikel != null) {
        artikelListeRueckgabe.add(artikel);
      }
    }
    return artikelListeRueckgabe;
  }
}
