import java.io.LineNumberInputStream;
import java.lang.annotation.Retention;
import java.util.*;

/**
 * Erstellt ein Artikel dialog in der CLI fur das Lager
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 0.2
 */
public class ArtikelDialog {
  private static final int OPTION_MENU_ZURUECK = 0;
  private static final int OPTION_HAUPTMENU_LAGERVERWALTUNG = 1;
  private static final int OPTION_LAGERMENU_SHOW_LAGERINFO = 1;
  private static final int OPTION_LAGERMENU_ERSTELLE_LAGER = 2;
  private static final int OPTION_LAGERMENU_ERSTELLEARTIKEL = 3;
  private static final int OPTION_LAGERMENU_BUCHE_ABGANG = 4;
  private static final int OPTION_LAGERMENU_BUCHE_ZUGANG = 5;
  private static final String FALSCHE_EINGABE = "FALSCHE EINGABE";
  private static final String FEHLERHAFTE_EINGABE_BITTE_EINE_GANZZAHL_INT_EINGEBEN = "Fehlerhafte Eingabe, Bitte eine Ganzzahl (int) eingeben.";
  private static final String HINT_ARTIKELART = "ArtikelArt (text)";
  private static final String HINT_ARTIKELNR = "ArtikelNummer (Zahl,Vierstellig)";
  private static final String HINT_ARTIKELPREIS = "Artikel Preis (Kommazahl)";
  private static final String HINT_ARTIKELBESTAND = "Artikel Bestand (Zahl)";
  private static final String KEIN_LAGER_VORHANDEN = "Kein Lager Vorhanden! Bitte Lager anlegen";
  private static final String ARTIKEL_BEREITS_IM_LAGER = "Artikel existiert Bereits";
  private static final Character STRING_INPUT_ESCAPE_CHAR = '_';
  private static final String FEHLERHAFTE_EINGABE_BITTE_INT = "Bitte (integer) eingeben!";
  private static final String ARTIKEL_VERWALTUNG = "ArtikelVerwaltung";
  private static final String FEHLERHAFTE_EINGABE_BITTE_STRING = "Bitte etwas eingeben";

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
    try {
      menuHauptMenu();
    } catch (Exception e) {
      fehlerAusgabeInKonsole(e);
      Start();
    }
    ausgabeInKonsole("Programm wird Beendet ...");
  }

  /**
   * Erstes Dialog des Programms - Hauptmenut
   *
   * @return
   */
  private void menuHauptMenu() {
    boolean loop = true;
    while (loop) {
      StringBuffer menuText = new StringBuffer();
      menuText.append("\n==========| HauptMenu |==========\n");
      menuText.append(OPTION_MENU_ZURUECK + "-->Programm Beenden\n");
      menuText.append(OPTION_HAUPTMENU_LAGERVERWALTUNG + "-->Lager Verwaltung\n");
      menuText.append("-->");
      ausgabeInKonsole(menuText.toString());
      int funktion = einlesenInt();

      switch (funktion) {
        case OPTION_MENU_ZURUECK:
          loop = false;
        case OPTION_HAUPTMENU_LAGERVERWALTUNG:
          menuLagerVerwaltung();
          break;
        default:
          ausgabeInKonsole(FALSCHE_EINGABE);
      }
    }
  }

  /**
   * * Menu zur LagerVerwaltung
   * 
   * @return die selektierte option
   */
  private void menuLagerVerwaltung() {
    boolean loop = true;
    while (loop) {
      StringBuffer menuText = new StringBuffer();
      menuText.append("\n==========| LagerVerwaltung |==========\n");
      menuText.append(OPTION_MENU_ZURUECK + "-->Menu schliessen\n");
      menuText.append(OPTION_LAGERMENU_SHOW_LAGERINFO + "-->LagerInfo Anzeigen\n");
      menuText.append(OPTION_LAGERMENU_ERSTELLE_LAGER + "-->Ein neues Lager erstellen\n");
      menuText.append(OPTION_LAGERMENU_ERSTELLEARTIKEL + "-->Artikel Erstellen\n");
      menuText.append(OPTION_LAGERMENU_BUCHE_ABGANG + "-->Artikel Abbuchen\n");
      menuText.append(OPTION_LAGERMENU_BUCHE_ZUGANG + "-->Artikel Einbuchen\n");
      menuText.append("--> ");
      LagerInfo();
      ausgabeInKonsole(menuText.toString());
      int funktion = einlesenInt();

      switch (funktion) {
        case OPTION_MENU_ZURUECK:
          loop = false;
        case OPTION_LAGERMENU_SHOW_LAGERINFO:
          LagerInfo();
          break;
        case OPTION_LAGERMENU_ERSTELLE_LAGER:
          erstelleLager();
          break;
        case OPTION_LAGERMENU_ERSTELLEARTIKEL:
          menuArtikelErstellung();
          break;
        case OPTION_LAGERMENU_BUCHE_ABGANG:
          bucheAbgang();
          break;
        case OPTION_LAGERMENU_BUCHE_ZUGANG:
          bucheZugang();
          break;
        default:
          ausgabeInKonsole(FALSCHE_EINGABE);
      }
    }
  }

  enum erstellBareArtikel {
    Video("Video"),
    CD("CD"),
    Buch("Buch");

    String name;

    erstellBareArtikel(String name) {
      this.name = name;
    }
  }

  /**
   * Menu zur ArtikelErstellung
   * 
   */
  private void menuArtikelErstellung() {
    /** liste zur iteration der zu erstellenden artikel? */
    StringBuffer menuText = new StringBuffer();
    menuText.append("\n==========| ArtikelErstellung |==========\n");
    menuText.append(OPTION_MENU_ZURUECK + "-->Menu schliessen\n");

    for (int i = 0; i < erstellBareArtikel.values().length; i++) {
      menuText.append(String.format("%d--> %s\n", i + 1, erstellBareArtikel.values()[i]));
    }
    menuText.append("--> ");
    ausgabeInKonsole(menuText.toString());
    int option = einlesenInt();

    int anzahlErstellbarerArtikel = erstellBareArtikel.values().length;
    if (!(option > 0 && option <= anzahlErstellbarerArtikel)) {
      return;

    }
    erstellBareArtikel zuErstellenderArtikel = null;
    for (int i = 0; i < anzahlErstellbarerArtikel && zuErstellenderArtikel == null; i++) {
      int enumOrdinal = erstellBareArtikel.values()[i].ordinal();
      if (option - 1 == enumOrdinal) {
        zuErstellenderArtikel = erstellBareArtikel.values()[i];
      }
    }

    Artikel neuerARtikel = null;
    switch (zuErstellenderArtikel) {
      case Buch:
        neuerARtikel = erstelleBuch();
        break;
      case CD:
        neuerARtikel = erstelleCD();
        break;
      case Video:
        neuerARtikel = erstelleVideo();
        break;
      default:
        return;
    }

    if (neuerARtikel != null) {
      lager.legeAnArtikel(neuerARtikel);
      ausgabeInKonsole("Neuer Artikel Erstellt: " + neuerARtikel.toString(), true);
    }
  }

  private Artikel erstelleBuch() {
    int artNr = einlesenArtikelnummer();
    if (artNr == -1)
      return null;
    int bestand = einlesenArtikelBestand();
    if (bestand == -1)
      return null;
    double preis = einlesenArtikelPreis();
    if (bestand == -1)
      return null;
    String autor = einlesenText("Autor");
    if (autor.charAt(0) == STRING_INPUT_ESCAPE_CHAR && autor.length() == 1)
      return null;
    String titel = einlesenText("Titel");
    if (titel.charAt(0) == STRING_INPUT_ESCAPE_CHAR && titel.length() == 1)
      return null;
    String verlag = einlesenText("Verlag");
    if (verlag.charAt(0) == STRING_INPUT_ESCAPE_CHAR && verlag.length() == 1)
      return null;

    Buch neueBuch = new Buch(artNr, bestand, preis, autor, titel, verlag);

    return neueBuch;
  }

  private Artikel erstelleCD() {
    return null;
  }

  private Artikel erstelleVideo() {
    return null;
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

    Artikel schonVorhandenerArtikel = lager.getArtikelbyArtNummer(artikelNr);
    if (schonVorhandenerArtikel != null) {
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
   * einlesen einer Artikelnummer in CUI
   * abbrechen durch eingabe von int: {@code -1}
   * 
   * @return die eingelesene artikelnummer. -1 bei abbruch
   */
  private int einlesenArtikelnummer() {
    boolean vorgangAbbrechen = false;
    Integer artikelNr = null;

    while (vorgangAbbrechen == false) {
      ausgabeInKonsole("\n" + HINT_ARTIKELNR + " (Abbrechen mit -1):");
      artikelNr = einlesenInt();
      if (Artikel.istArtikelnummerValide(artikelNr) || artikelNr == -1) {
        vorgangAbbrechen = true;
      }
    }
    /*
     * while (istartikelNummerOK == false && vorgangAbbrechen == false) {
     * try {
     * ausgabeInKonsole("\n" + HINT_ARTIKELNR + " (Abbrechen mit -1):");
     * artikelNr = einlesenInt();
     * istartikelNummerOK = Artikel.istArtikelnummerValide(artikelNr);
     * vorgangAbbrechen = (artikelNr == -1);
     * } catch (InputMismatchException e) {
     * ausgabeInKonsole("Bitte ganzzahligeArtikelNr eingeben. NUmmer darf nicht negativ sein. -1 um Abzubrechen"
     * );
     * }
     * }
     */
    return artikelNr;
  }

  /**
   * einlesen eines string in CUI
   * abbrechen durch eingabe von char: {@code _}
   * 
   * @return den eingelesenen string. "_" bei abbruch
   */
  private String einlesenArtikelArt() {
    boolean vorgangAbbrechen = false;
    String art = Character.toString(STRING_INPUT_ESCAPE_CHAR);
    while (vorgangAbbrechen == false) {
      ausgabeInKonsole("\n" + HINT_ARTIKELART + "(abbrechen mit: " + STRING_INPUT_ESCAPE_CHAR + " ):");
      try {
        art = einlesenText();
        if (art.isBlank()) {
          ausgabeInKonsole(FEHLERHAFTE_EINGABE_BITTE_STRING);
        }
        if (art.charAt(0) == STRING_INPUT_ESCAPE_CHAR || art.charAt(0) != STRING_INPUT_ESCAPE_CHAR) {
          vorgangAbbrechen = true;
        }
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
    boolean vorgangAbbrechen = false;
    double preis = 0d;
    while (vorgangAbbrechen == false) {
      ausgabeInKonsole("\n" + HINT_ARTIKELPREIS + "(Abbrechen mit -1):");
      try {
        preis = einlesenDouble();
        if (preis == -1 || preis >= 0) {
          vorgangAbbrechen = true;
        } else {
          ausgabeInKonsole("Bitte ArtikelPreis eingeben. NUmmer darf nicht negativ sein. -1 um Abzubrechen");
        }
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
    boolean vorgangAbbrechen = false;
    int bestand = 0;
    while (vorgangAbbrechen == false) {
      ausgabeInKonsole("\n" + HINT_ARTIKELBESTAND + "(Abbrechen mit -1):");
      try {
        bestand = einlesenInt();
        if (bestand == -1 || bestand > 0) {
          vorgangAbbrechen = true;
        } else {
          ausgabeInKonsole("Bitte ArtikelBestand eingeben. Bestand darf nicht negativ sein. -1 um Abzubrechen");
        }
      } catch (InputMismatchException e) {
        ausgabeInKonsole(FEHLERHAFTE_EINGABE_BITTE_INT);
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
   * Konsolenausgabe: Unformatiert, mit Newline
   */
  private void ausgabeInKonsole(String text) {
    System.out.print(text + "\n");
  }

  /**
   * Konsolenausgabe: Formatiert. fuer uberschriften
   */
  private String formatiereMenueTitelInKonsole(String uberschrift) {
    return String.format("<--> %s -->", uberschrift);
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
   * Bei Fehlerhafte eingabe wird erneute Eingabe angefordert.
   *
   * @return die eingelesene Zahl
   * @throws InputMismatchException bei fehlerhafter eingabe
   */
  private int einlesenInt() {
    Integer zahl = null;
    while (zahl == null) {

      try {
        zahl = input.nextInt();
        input.nextLine();
      } catch (Exception e) {
        input.nextLine();
        throw new InputMismatchException(FEHLERHAFTE_EINGABE_BITTE_INT);
      }

      // if (zahl == null) {
      // zahl = einlesenInt();
      // }
      // FEHLERHAFTE_EINGABE_BITTE_EINE_GANZZAHL_INT_EINGEBEN);
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
   * Einlesen einers Strings aus der Konsole (mit optionalem Hint)
   *
   * @return der eingelesene Text
   * @throws InputMismatchException wenn die eingabe leer ist oder aus leerzechen
   *                                besteht
   */
  private String einlesenText(String hint) {
    if (!hint.isBlank()) {
      System.out.println(hint + ": ");
    }
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
