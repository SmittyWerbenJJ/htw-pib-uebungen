package src;

import java.util.*;

/**
 * Erstellt ein Artikel dialog in der CLI fur das Lager
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 0.2
 */
public class ArtikelDialog {
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
  private static final String HINT_ARTIKELNR = "ArtikelNummer (Zahl,Vierstellig)";
  private static final String HINT_ARTIKELPREIS = "Artikel Preis (Kommazahl)";
  private static final String HINT_ARTIKELBESTAND = "Artikel Bestand (Zahl)";
  private static final String KEIN_LAGER_VORHANDEN = "Kein Lager Vorhanden! Bitte Lager anlegen";
  private static final String ARTIKEL_BEREITS_IM_LAGER = "Artikel existiert Bereits";
  private static final Character STRING_INPUT_ESCAPE_CHAR = '_';

  private Scanner input;
  private Lager lager;
  private boolean exit;

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
    String menuText = "\n==========| HauptMenu |==========\n" +
        PROGRAMM_ENDE +
        ": Programm Beenden" +
        newline +
        ERSTELLE_OHNE_BESTAND +
        ": Neuer Artikel erstellen(ID,Art)" +
        newline +
        ERSTELLE_MIT_BESTAND +
        ": Neuer Artikel erstellen(ID,Bestand,Art)" +
        newline +
        ERSTELLE_MIT_Preis +
        ": Neuer Artikel erstellen(ID,Art,Bestand,Preis)" +
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

  /**
   * Erstellt ein Neues Lager mit 10 plätzen
   */
  private void erstelleLager() {
    lager = new Lager(10);
    ausgabeInKonsole("Es wurde ein Neues Lager mit 10 Plaetzen erstellt", true);
  }

  /**
   * Erstellt ein Neuen Artikel Ohne Bestand (ID,Art)
   * 
   * @throws Exception
   */
  private void erstelleArtikelOhneBestand() {
    if (checkLagerVorhanden() == false) {
      ausgabeInKonsole(formatiereMenueTitelInKonsole(KEIN_LAGER_VORHANDEN));
      return;
    }
    ausgabeInKonsole(formatiereMenueTitelInKonsole("Neuer Artikel: Ohne Bestand"));
    int artikelNr = einlesenArtikelnummer();
    if (artikelNr == -1) {
      return;
    }
    if (lager.istArtikelImLager(artikelNr)) {
      ausgabeInKonsole(ARTIKEL_BEREITS_IM_LAGER);
      return;
    }
    String art = einlesenArtikelArt();
    if (art == Character.toString(STRING_INPUT_ESCAPE_CHAR)) {
      return;
    }

    Artikel artikel = new Artikel(artikelNr, art);
    lager.legeAnArtikel(artikel);
    ausgabeInKonsole(formatiereMenueTitelInKonsole("ES wurde ein neuer Artikel angelegt!"));
    return;
  }

  /**
   * Erstellt ein Artikel mit Bestand (ID,Art,Bestand)
   */
  private void erstelleArtikelMitBestand() {
    ausgabeInKonsole(formatiereMenueTitelInKonsole("Neuer Artikel mit Bestand"));
    int artikelNr = einlesenArtikelnummer();
    if (artikelNr == -1) {
      return;
    }
    int bestand = einlesenArtikelBestand();
    if (bestand == -1) {
      return;
    }
    String art = einlesenArtikelArt();
    if (art == Character.toString(STRING_INPUT_ESCAPE_CHAR)) {
      return;
    }
    Artikel artikel = new Artikel(artikelNr, art, bestand);
    lager.legeAnArtikel(artikel);
  }

  /**
   * Erstellt einen Artikel mit Gegebenen Preis (ID,Art,Bestand,Preis)
   */
  private void erstelleArtikelMitPreis() {
    ausgabeInKonsole(formatiereMenueTitelInKonsole("Neuer Artikel mit Preis"));
    int artikelNr = einlesenArtikelnummer();
    if (artikelNr == -1) {
      return;
    }
    String art = einlesenArtikelArt();
    if (art == Character.toString(STRING_INPUT_ESCAPE_CHAR)) {
      return;
    }
    int bestand = einlesenArtikelBestand();
    if (bestand == -1) {
      return;
    }
    double preis = einlesenArtikelPreis();
    if (bestand == -1) {
      return;
    }

    Artikel artikel = new Artikel(artikelNr, art, bestand, preis);
    lager.legeAnArtikel(artikel);
  }

  /**
   * einlesen eines string in CUI
   * abbrechen durch eingabe von int: {@code -1}
   * 
   * @return die eingelesene artikelnummer. -1 bei abbruch
   */
  private int einlesenArtikelnummer() {
    boolean validInput = false;
    boolean cancel = false;
    int artikelNr = -1;
    while (validInput == false && cancel == false) {
      try {
        ausgabeInKonsole("\n" + HINT_ARTIKELNR + " (Abbrechen mit -1):");
        artikelNr = einlesenInt();
        validInput = Artikel.istArtikelnummerValide(artikelNr);
        cancel = artikelNr == -1;
      } catch (InputMismatchException e) {
        ausgabeInKonsole("Bitte ganzzahligeArtikelNr eingeben. NUmmer darf nicht negativ sein. -1 um Abzubrechen");
      }
    }
    return artikelNr;
  }

  /**
   * einlesen eines string in CUI
   * abbrechen durch eingabe von char: {@code _}
   * 
   * @return den eingelesenen string. "_" bei abbruch
   */
  private String einlesenArtikelArt() {
    String art = Character.toString(STRING_INPUT_ESCAPE_CHAR);
    while (art.charAt(0) == STRING_INPUT_ESCAPE_CHAR || art.isBlank()) {
      ausgabeInKonsole("\n" + HINT_ARTIKELART + "(abbrechen mit: _ ):");
      try {
        art = einlesenText();
      } catch (InputMismatchException e) {
        ausgabeInKonsole("\n" + HINT_ARTIKELART + "(abbrechen mit: _ ):");
      }
    }
    return art;
  }

  /**
   * einlesen ArtikelPreis in CUI
   * 
   * @return den eingelesneen ArtikelPres. -1 bei abbruch
   */
  private double einlesenArtikelPreis() {
    double preis = -1;
    while (preis == -1) {
      ausgabeInKonsole("\n" + HINT_ARTIKELPREIS + "(Abbrechen mit -1):");
      try {
        preis = einlesenDouble();
      } catch (InputMismatchException e) {
        ausgabeInKonsole("Bitte ArtikelPreis eingeben. NUmmer darf nicht negativ sein. -1 um Abzubrechen");
      }
    }
    return preis;
  }

  /**
   * einlesen des ArtikelBestands in CUI
   * 
   * @return der eingelesene bestand. -1 bei abbruch
   */
  private int einlesenArtikelBestand() {
    ausgabeInKonsole("\n" + HINT_ARTIKELBESTAND + "(Abbrechen mit -1):");
    int bestand = -1;
    while (bestand == -1) {
      try {
        bestand = einlesenInt();
      } catch (InputMismatchException e) {
        ausgabeInKonsole("Bitte ArtikelBestand eingeben. Bestand darf nicht negativ sein. -1 um Abzubrechen");
      }
    }
    return bestand;
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
   * uberprufung ob ein lager vorhanden ist
   * 
   * @return true wenn vorhanden, false falls nicht
   */
  private boolean checkLagerVorhanden() {
    return lager != null;
  }

  /**
   * Gibt die aktuellen Informationen zum Artikel in der Konsole aus
   */
  private void LagerInfo() {
    if (lager == null) {
      ausgabeInKonsole("Lager: ---", true);
    } else {
      ausgabeInKonsole(lager.toString(), true);
    }
  }

  /**
   * gibt die beschreibung einer exception in der konsole aus
   */
  private void fehlerAusgabeInKonsole(Exception exception) {
    ausgabeInKonsole(exception.getMessage());
  }

  /**
   * Konsolenausgabe: Unformatiert, ohne Newline
   */
  private void ausgabeInKonsole(String text) {
    System.out.print(text);
  }

  /**
   * Konsolenausgabe: Formatiert. fuer uberschriften
   */
  private String formatiereMenueTitelInKonsole(String uberschrift) {
    return String.format("<-- %s -->", uberschrift);
  }

  /**
   * Konsolenausgabe: Zeilenweise, optionale Formatierung mit muster
   * 
   * @param text               auszugebener text
   * @param ausgabeFormatieren optionales visuelles muster
   */
  private void ausgabeInKonsole(String text, boolean ausgabeFormatieren) {
    text = text.replaceAll("\t", " ");
    if (ausgabeFormatieren) {
      StringBuffer top = new StringBuffer();
      StringBuffer mid = new StringBuffer();
      StringBuffer bot = new StringBuffer();

      top.append("+");
      mid.append("+");
      bot.append("+");

      for (int i = 0; i <= text.toCharArray().length + 1; i++) {
        top.append("-");
        mid.append(" ");
        bot.append("-");
      }
      top.append("+");
      bot.append("+");
      mid.append(" ");
      mid.setCharAt(0, '|');
      mid.setCharAt(mid.length() - 1, '|');

      int txtStartIndex = mid.length() / 2 - text.length() / 2;
      int endIndex = txtStartIndex + text.length();
      mid.replace(txtStartIndex, endIndex, text);
      String finalString = String.format("\n%s\n%s\n%s", top, mid, bot);
      System.out.println(finalString);
    } else {
      System.out.println(text + "\n");
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
      input.nextLine();
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
      input.useLocale(Locale.GERMAN);
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
   * Einlesen einers Strings aus der Konsole
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
