package src;

import java.util.*;

/**
 * Erstellt ein Artikel dialog in der CLI fur das Lager
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 0.2
 */
public class ArtikelDialog {

  private Scanner input;
  private Lager lager;
  private boolean exit;

  private static final int PROGRAMM_ENDE = 0;
  private static final int ERSTELLE_OHNE_BESTAND = 1;
  private static final int ERSTELLE_MIT_BESTAND = 2;
  private static final int ERSTELLE_MIT_Preis = 3;
  private static final int BUCHE_ABGANG = 4;
  private static final int BUCHE_ZUGANG = 5;
  private static final int SHOW_INFO = 6;
  private static final int ERSTELLE_LAGER = 7;

  private static final String FALSCHE_EINGABE = "FALSCHE EINGABE";
  private static final String FEHLERHAFTE_EINGABE_BITTE_EINE_GANZZAHL_INT_EINGEBEN = "Fehlerhafte Eingabe, Bitte eine Ganzzahl (int) eingeben.";

  private static final String HINT_ARTIKELART = "ArtikelArt (text)";
  private static final String HINT_ARTIKELNR = "ArtikelNummer (Zahl)";
  private static final String HINT_ARTIKELPREIS = "Artikel Preis (Kommazahl)";
  private static final String HINT_ARTIKELBESTAND = "Artikel Bestand (Zahl)";

  /**
   * Main Methode
   *
   * @param args Argumente
   */
  public static void main(String[] args) {
    ArtikelDialog dialog = new ArtikelDialog();
    dialog.Start();
  }

  /**
   * Konstruktor fuer den ArtikelDialog
   */
  private ArtikelDialog() {
    input = new Scanner(System.in);
    exit = false;
  }

  /**
   * Startet den Dialog
   *
   */
  private void Start() {
    while (exit == false) {
      try {
        int funktion = zeigeMenu();
        auswahlDerFunktion(funktion);
      } catch (Exception e) {
        fehlerAusgabeInKonsole(e);
      }
    }
    ausgabeInKonsole("Programm Beendet");
  }

  /**
   * Das Formatierte CLI-Menu
   *
   * @return die auszufuehrende Funktion
   * @throws IllegalargumentException bei falscher eingabe
   */
  private int zeigeMenu() {
    String newline = "\n";
    String menuText = "\n==========| HauptMenu | ==========\n" +
        PROGRAMM_ENDE +
        ": Programm Beenden" +
        newline +
        ERSTELLE_OHNE_BESTAND +
        ": Neuer Artikel erstellen, ohne Bestand" +
        newline +
        ERSTELLE_MIT_BESTAND +
        ": Neuer Artikel erstellen, mit Bestand" +
        newline +
        ERSTELLE_MIT_Preis +
        ": Neuer Artikel erstellen, mit Preis" +
        newline +
        BUCHE_ABGANG +
        ": Abgangsbuchung" +
        newline +
        BUCHE_ZUGANG +
        ": Zugangsbuchung" +
        newline +
        +SHOW_INFO +
        ": Artikel Info" +
        newline +
        ERSTELLE_LAGER +
        ": Ein neues Lager erstellen" +
        newline +
        "--> ";

    ausgabeInKonsole(menuText);
    int funktion = einlesenInt();
    return funktion;
  }

  /**
   * auswahl und exekution der Ausfuehrende Funktion
   *
   * @param funktion die auszufuehrende Funktion
   */
  private void auswahlDerFunktion(int funktion) {
    switch (funktion) {
      case BUCHE_ABGANG:
        bucheAbgang();
        break;
      case BUCHE_ZUGANG:
        bucheZugang();
        break;
      case ERSTELLE_MIT_BESTAND:
        erstelleArtikelMitBestand();
        break;
      case ERSTELLE_OHNE_BESTAND:
        erstelleArtikelOhneBestand();
        break;
      case ERSTELLE_MIT_Preis:
        erstelleArtikelMitPreis();
        break;
      case PROGRAMM_ENDE:
        BeendeProgramm();
        break;
      case ERSTELLE_LAGER:
        erstelleLager();
      case SHOW_INFO:
        LagerInfo();
        break;
      default:
        ausgabeInKonsole(FALSCHE_EINGABE);
    }
  }

  private void erstelleLager() {
    lager = new Lager(10);
    ausgabeInKonsole("Es wurde ein Neues Lager mit 10 Plaetzen erstellt", true);
  }

  private void erstelleArtikelOhneBestand() {
    int artikelNr = 0;
    String art = "art";

    ausgabeInKonsole("\n" + HINT_ARTIKELNR + ":");
    try {
      artikelNr = einlesenInt();
    } catch (InputMismatchException e) {
      fehlerAusgabeInKonsole(e);
      return;
    }

    ausgabeInKonsole(HINT_ARTIKELART + ":");
    try {
      art = einlesenText();
    } catch (InputMismatchException e) {
      fehlerAusgabeInKonsole(e);
      return;
    }

    Artikel artikel = new Artikel(artikelNr, art);
    ausgabeInKonsole("Neuer Artikel erstellt: '" + artikel.toString(), true);
  }

  private void erstelleArtikelMitBestand() {
    int artikelNr = 0;
    int bestand = 0;
    String art = "art";

    ausgabeInKonsole("\n Neuer Artikel mit Bestand:\n", true);
    ausgabeInKonsole("\n" + HINT_ARTIKELNR + ":");
    try {
      artikelNr = einlesenInt();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }

    ausgabeInKonsole("\n" + HINT_ARTIKELART + ":");
    try {
      art = einlesenText();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }

    ausgabeInKonsole("\n" + HINT_ARTIKELBESTAND + ":");
    try {
      bestand = einlesenInt();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }
    Artikel artikel = new Artikel(artikelNr, art, bestand);
    lager.legeAnArtikel(artikel);
  }

  private void erstelleArtikelMitPreis() {
    int artikelNr = 0;
    String art = "art";
    int bestand = 0;
    double preis = 0d;

    ausgabeInKonsole("\n Neuer Artikel mit Preis:\n", true);
    ausgabeInKonsole("\n" + HINT_ARTIKELNR + ":");
    try {
      artikelNr = einlesenInt();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }
    ausgabeInKonsole("\n" + HINT_ARTIKELART + ":");
    try {
      art = einlesenText();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }
    ausgabeInKonsole("\n" + HINT_ARTIKELBESTAND + ":");
    try {
      bestand = einlesenInt();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }
    ausgabeInKonsole("\n" + HINT_ARTIKELPREIS + ":");
    try {
      preis = einlesenDouble();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }

    Artikel artikel = new Artikel(artikelNr, art, bestand, preis);
    lager.legeAnArtikel(artikel);
  }

  /**
   * Bucht einen Zugang auf den Artikel
   *
   * @param menge die zu Buchende Menge
   */
  private void bucheZugang() {
    int artikelNr = 0;
    int menge = 0;

    ausgabeInKonsole("Buchungsmenge-Zugang: ", true);
    try {
      ausgabeInKonsole("ArtikelNR: ");
      artikelNr = einlesenInt();
      ausgabeInKonsole("\nZugangsMenge: ");
      menge = einlesenInt();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }

    try {
      lager.bucheZugang(artikelNr, menge);
    } catch (IllegalArgumentException e) {
      fehlerAusgabeInKonsole(e);
    }

    ausgabeInKonsole(
        "Es wurden '" +
            menge +
            "' von '" +
            artikelNr +
            "' in das Lager zugebucht.",
        true);
  }

  /**
   * Bucht einen Abgang auf den Artikel
   *
   * @param menge die Abzubuchende Menge
   */
  private void bucheAbgang() {
    int artikelNr = 0;
    int menge = 0;

    ausgabeInKonsole("Buchungsmenge-Zugang: ", true);
    try {
      ausgabeInKonsole("ArtikelNR: ");
      artikelNr = einlesenInt();
      ausgabeInKonsole("\nAbgangsMenge: ");
      menge = einlesenInt();
    } catch (InputMismatchException e) {
      ausgabeInKonsole(e.getMessage(), false);
      return;
    }
    lager.bucheAbgang(artikelNr, menge);
    ausgabeInKonsole(
        "Es wurden '" +
            menge +
            "' von '" +
            artikelNr +
            "' aus dem Lager abgebucht.",
        true);
  }

  /**
   * Gibt die aktuellen Informationen zum Artikel in der Konsole aus
   */
  private void LagerInfo() {
    String info = "";
    if (lager == null) {
      info += "Lager: ---";
    } else {
      info = lager.toString();
    }
    ausgabeInKonsole(info, true);
  }

  // ========== HilfsMethoden ==========

  /**
   * gibt die beschreibung einer exception in der konsole aus
   */
  private void fehlerAusgabeInKonsole(Exception exception) {
    // input.nextLine();
    ausgabeInKonsole(exception.getMessage());
  }

  /**
   * Konsolenausgabe: Unformatiert, ohne Newline
   */
  private void ausgabeInKonsole(String text) {
    System.out.print(text);
  }

  /**
   * Konsolenausgabe: Zeilenweise, Formatiert mit muster
   * 
   * @param text               auszugebener text
   * @param ausgabeFormatieren optionales visuelles muster
   */
  private void ausgabeInKonsole(String text, boolean ausgabeFormatieren) {
    if (ausgabeFormatieren) {
      System.out.println("\n/*\n*\n* " + text + "\n*\n*/");
    } else {
      System.out.println(text + "\n");
    }
  }

  // /**
  // * Gibt in der Konsole die Exception und Backtrace aus
  // *
  // * @param e die Auszugebene Exception
  // */
  // private void ausgabeBeiTextZumEinlesen(String text) {
  // System.out.print(text + ": ");
  // }

  // /**
  // * Gibt in der Konsole die Exception und Backtrace aus
  // *
  // * @param e die Auszugebene Exception
  // */
  // private void ausgabeFehlerinKonsole(Exception e) {
  // System.out.println(e);
  // }

  /**
   * Freigeben des Inputs
   *
   * 
   * 
   */
  private void freigebenInput() {
    if (input != null) {
      input.nextLine();
    }
  }

  /**
   * Einlesen einer Integer Zahl aus der Konsole
   *
   * @return die eingelesene Zahl
   * @throws InputMismatchException bei fehlerhafter eingabe
   */
  private int einlesenInt() {
    int zahl;
    try {
      zahl = input.nextInt();
      input.nextLine();
    } catch (InputMismatchException e) {
      throw new InputMismatchException(
          FEHLERHAFTE_EINGABE_BITTE_EINE_GANZZAHL_INT_EINGEBEN);
    }
    return zahl;
  }

  /**
   * Einlesen einer Double Zahl aus der Konsole
   *
   * @return der eingelesene Text
   */
  private double einlesenDouble() {
    Double zahl;
    try {
      zahl = input.nextDouble();
      input.nextLine();
    } catch (InputMismatchException e) {
      input.nextLine();
      throw new InputMismatchException(
          "Fehlerhafte Eingabe, Bitte eine Kommazahl (double) eingeben.");
    }
    return zahl;
  }

  /**
   * Einlesen einers Stringsaus der Konsole
   *
   * @return der eingelesene Text
   * @throws InputMismatchException wenn die eingabe leer ist oder aus leerzechen
   *                                besteht
   */
  private String einlesenText() {
    String eingabe = input.nextLine();
    if (eingabe.isBlank()) {
      throw new InputMismatchException("Bitte etwas eingeben.");
    }
    return eingabe;
  }

  /**
   * Weist an, das Programm im nächsten update zu Beenden
   */
  private void BeendeProgramm() {
    this.exit = true;
    ausgabeInKonsole("Programm Wird Beendet ...", false);
  }
}
