import java.util.*;

/**
 * Lager und ArtikelDialog
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 17.01.2022
 */
public class ArtikelDialog {
  private static final int OPTION_MENU_ZURUECK = 0;
  private static final int OPTION_HAUPTMENU_LAGERVERWALTUNG = 1;
  private static final int OPTION_LAGERMENU_SHOW_LAGERINFO = 1;
  private static final int OPTION_LAGERMENU_SHOW_BESTANDSLISTE = 2;
  private static final int OPTION_LAGERMENU_ERSTELLE_LAGER = 3;
  private static final int OPTION_LAGERMENU_ERSTELLEARTIKEL = 4;
  private static final int OPTION_LAGERMENU_BUCHE_ABGANG = 5;
  private static final int OPTION_LAGERMENU_BUCHE_ZUGANG = 6;
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
          break;
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
      boolean LagerVorhanden = lager != null;
      StringBuffer menuText = new StringBuffer();
      menuText.append("\n==========| LagerVerwaltung |==========\n");
      menuText.append(OPTION_MENU_ZURUECK + "-->Menu schliessen\n");
      menuText.append(OPTION_LAGERMENU_SHOW_LAGERINFO + "-->LagerInfo Anzeigen\n");
      menuText.append(OPTION_LAGERMENU_SHOW_BESTANDSLISTE + "-->Bestandsliste Anzeigen\n");
      menuText.append(OPTION_LAGERMENU_ERSTELLE_LAGER + "-->Ein neues Lager erstellen\n");
      if (LagerVorhanden) {
        menuText.append(OPTION_LAGERMENU_ERSTELLEARTIKEL + "-->Artikel Erstellen\n");
        menuText.append(OPTION_LAGERMENU_BUCHE_ABGANG + "-->Artikel Abbuchen\n");
        menuText.append(OPTION_LAGERMENU_BUCHE_ZUGANG + "-->Artikel Einbuchen\n");
      }
      menuText.append("--> ");
      LagerInfo();
      ausgabeInKonsole(menuText.toString());

      int funktion = einlesenInt();
      switch (funktion) {
        case OPTION_MENU_ZURUECK:
          loop = false;
          break;
        case OPTION_LAGERMENU_SHOW_LAGERINFO:
          LagerInfo();
          break;
        case OPTION_LAGERMENU_ERSTELLE_LAGER:
          erstelleLager();
          break;
        case OPTION_LAGERMENU_SHOW_BESTANDSLISTE:
          ausgebenBestandsListe();
          break;
        default:
          if (LagerVorhanden) {
            switch (funktion) {
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
          } else {
            ausgabeInKonsole(FALSCHE_EINGABE);
          }
      }
    }
  }

  /**
   * 
   * Enum fur die Benutzer auswahl des zu erstellenden artikels
   */
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
    StringBuffer menuText = new StringBuffer();
    menuText.append("\n==========| ArtikelErstellung |==========\n");
    menuText.append(OPTION_MENU_ZURUECK + "-->Menu schliessen\n");

    // Auflistung der Artikel aus dem Enum
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

  /**
   * Erstellt ein Buch Artikel
   * 
   * @return den erstellten Artikel oder null
   */
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

  /**
   * Erstellt ein CD Artikel
   * 
   * @return den erstellten Artikel oder null
   */
  private Artikel erstelleCD() {
    int artNr = einlesenArtikelnummer();
    if (artNr == -1)
      return null;
    int bestand = einlesenArtikelBestand();
    if (bestand == -1)
      return null;
    double preis = einlesenArtikelPreis();
    if (bestand == -1)
      return null;
    String interpret = einlesenText("Interpret");
    String titel = einlesenText("Titel");
    int anzahltitel = einlesenInt("Anzahltitel");
    CD neueCD = new CD(artNr, bestand, preis, interpret, titel, anzahltitel);
    return neueCD;
  }

  /**
   * Erstellt ein Video Artikel
   * 
   * @return den erstellten Artikel oder null
   */
  private Artikel erstelleVideo() {
    int artikelNr = einlesenArtikelnummer();
    if (artikelNr == -1)
      return null;
    int bestand = einlesenArtikelBestand();
    if (bestand == -1)
      return null;
    double preis = einlesenArtikelPreis();
    if (bestand == -1)
      return null;
    String titel = einlesenText("Video - Titel");
    int jahr = einlesenInt("Video - Erscheinungsjahr");
    int spieldauer = einlesenInt("Video - Spieldauer");

    Video neueVideo = new Video(artikelNr, bestand, preis, titel, spieldauer, jahr);
    return neueVideo;
  }

  /**
   * Erstellt ein Neues Lager mit 10 plätzen
   */
  private void erstelleLager() {
    lager = new Lager(10);
    boolean eingabeOK = false;

    while (eingabeOK == false) {
      ausgabeInKonsole("Lagergroesse eingebene (0 eingeben->groesse 10): ");
      int size = einlesenInt();
      if (size <= 0) {
        size = 10;
      }
      eingabeOK = true;
    }
    ausgabeInKonsole("Es wurde ein Neues Lager mit 10 Plaetzen erstellt", true);
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
    boolean eingabeOk = false;

    while (eingabeOk == false) {
      ausgabeInKonsole("Buchungsmenge-Zugang: ", true);
      ausgabeInKonsole("ArtikelNR: ");
      artikelNr = einlesenInt();
      ausgabeInKonsole("\nZugangsMenge: ");
      menge = einlesenInt();
      if (menge < 0) {
        ausgabeInKonsole("Bitte Zahl groesser 0 eingeben");
      } else {
        eingabeOk = true;
      }
    }

    if (lager.getArtikelbyArtNummer(artikelNr) == null) {
      ausgabeInKonsole("Artikel nicht im Lager!", true);
      return;
    }
    try {
      lager.bucheZugang(artikelNr, menge);

    } catch (Exception e) {
      fehlerAusgabeInKonsole(e);
      return;
    }
    ausgabeInKonsole(String.format("Es wurden %d von %d aus dem eingebucht", menge, artikelNr), true);
    return;
  }

  /**
   * Bucht einen Abgang auf den Artikel
   *
   * @param menge die Abzubuchende Menge
   */
  private void bucheAbgang() {
    int artikelNr = 0;
    int menge = 0;
    boolean eingabeOk = false;

    while (eingabeOk == false) {
      ausgabeInKonsole("Buchungsmenge-Abgang: ", true);
      ausgabeInKonsole("ArtikelNR: ");
      artikelNr = einlesenInt();
      ausgabeInKonsole("\nAbgangsMenge: ");
      menge = einlesenInt();
      if (menge < 0) {
        ausgabeInKonsole("Bitte Zahl groesser 0 eingeben");
      } else {
        eingabeOk = true;
      }
    }

    if (lager.getArtikelbyArtNummer(artikelNr) == null) {
      ausgabeInKonsole("Artikel nicht im Lager!", true);
      return;
    }
    try {
      lager.bucheAbgang(artikelNr, menge);

    } catch (Exception e) {
      fehlerAusgabeInKonsole(e);
      return;
    }
    ausgabeInKonsole(String.format("Es wurden %d von %d aus dem ausgebucht", menge, artikelNr), true);
    return;

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
    ausgabeInKonsole(exception.getMessage(), true);
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
    }
    return zahl;

  }

  /**
   * Einlesen einer Integer Zahl aus der Konsole mit kurzer anweisung
   * Bei Fehlerhafte eingabe wird erneute Eingabe angefordert.
   *
   * @return die eingelesene Zahl
   * @throws InputMismatchException bei fehlerhafter eingabe
   */
  private int einlesenInt(String hint) {
    if (!hint.isBlank()) {
      System.out.println(hint + ": ");
    }
    Integer zahl = null;
    while (zahl == null) {
      try {
        zahl = input.nextInt();
        input.nextLine();
      } catch (Exception e) {
        input.nextLine();
        throw new InputMismatchException(FEHLERHAFTE_EINGABE_BITTE_INT);
      }
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

  /** Ausgeben der aktuellen bestandsliste */
  public void ausgebenBestandsListe() {
    double GesamtPreisAlles = 0;
    System.out.println("ArtNr\t\tBeschreibung\t\tPreis\t\tBestand\t\tGesamt");
    // for (int i = 0; i != Lager.alleArtikel[i]; i++) {
    for (Artikel artikel : lager.getAlleArtikel()) {
      double GesamtPreis = artikel.getPreis() * artikel.getBestand();
      int artikelNR = artikel.getArtikelNr();
      String beschreibung = artikel.getBeschreibung();
      double preis = artikel.getPreis();
      int bestand = artikel.getBestand();
      System.out.println(
          artikelNR + "\t\t" +
              beschreibung + "\t\t" +
              preis + "\t\t" +
              bestand + "\t\t" +
              GesamtPreis);

      GesamtPreisAlles = GesamtPreisAlles + GesamtPreis;
    }
    System.out.println("----------------------------------------------------------------------------------------");
    System.out.println("Gesamtpreis:                                                         " + GesamtPreisAlles);
  }
}
