import java.util.Scanner;

/**
 * Erstellt ein Artikel dialog in der CLI
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 0.2
 */
public class ArtikelDialog {
    private Scanner input;
    private Boolean programmAktiv = true;
    private Artikel artikel;
    private static final int PROGRAMM_ENDE = -1;
    private static final int ERSTELLE_OHNE_BESTAND = 1;
    private static final int ERSTELLE_MIT_BESTAND = 2;
    private static final int BUCHE_ABGANG = 3;
    private static final int BUCHE_ZUGANG = 4;
    private static final int SHOW_INFO = 5;

    /**
     * Konstruktor für den ArtikelDialog
     */
    private ArtikelDialog() {
        input = new Scanner(System.in);
    }

    /**
     * Startet den Dialog
     * 
     */
    private void Start() {
        int funktion = -1;
        try {
            while (programmAktiv == true) {
                funktion = menu();
                funktionAusfuehren(funktion);
            }
        } catch (Exception e) {
            fehlerAusgabe(e);
        }
        log("Programm Beendet");
    }

    /**
     * Gibt in der Konsole die Exception und Backtrace aus
     * 
     * @param e die Auszugebene Exception
     */
    private void fehlerAusgabe(Exception e) {
        System.out.println(e);
    }

    /**
     * Das Formatierte CLI-Menu
     * 
     * @return die auszuführende Funktion
     */
    private int menu() {
        int funktion;
        String newline = "\n";
        String menuText = PROGRAMM_ENDE + ": Programm Beenden" + newline + ERSTELLE_OHNE_BESTAND
                + ": Neuer Artikel erstellen, ohne Bestand" + newline + ERSTELLE_MIT_BESTAND
                + ": Neuer Artikel erstellen, mit Bestand" + newline + BUCHE_ABGANG + ": Abgangsbuchung" + newline
                + BUCHE_ZUGANG + ": Zugangsbuchung" + newline + +SHOW_INFO + ": Artikel Info" + newline + "--> ";

        artikelInfo();
        System.out.print(menuText);
        funktion = einleseZahl("");
        // funktion = input.nextInt();
        // input.nextLine();
        return funktion;
    }

    /**
     * Wählt die zu Ausführende Funktion auf und führt sie aus.
     * 
     * @param funktion die auszuführende Funktion
     */
    private void funktionAusfuehren(int funktion) {
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
        case PROGRAMM_ENDE:
            BeendeProgramm();
            break;
        case SHOW_INFO:
            artikelInfo();
            break;
        default:
            log("FALSCHE EINGABE");
            break;
        }
    }

    /**
     * Einlesen einer Zahl aus der Konsole mit beschreibung
     * 
     * @param beschreibung beigelegter Text
     * @return die eingelesene Zahl
     */
    private int einleseZahl(String beschreibung) {
        if (beschreibung != "") {
            System.out.print(beschreibung + ":");
        }
        int zahl = input.nextInt();
        return zahl;
    }

    /**
     * Einlesen eines Texts aus der Konsole
     * 
     * @param beschreibung
     * @return der eingelesene Text
     */
    private String einleseText(String beschreibung) {
        input.nextLine();
        System.out.print(beschreibung + ":");
        return input.nextLine();
    }

    /**
     * Erstellt einene Artikel ohne Bestand
     * 
     * @return den erstellten Artikel
     */
    private void erstelleArtikelOhneBestand() {
        int artikelNummer = einleseZahl("Artikel Nummer (ganzzahl)");
        String artikelArt = einleseText("Artikel Art (text)");

        artikel = erstelleArtikel(artikelNummer, artikelArt);
        log(String.format("Es wurde eine Neuer Artikel '%s' mit der ArtikelNummer %s erstellt", artikel.getArt(),
                artikel.getArtikelNr()));
    }

    /***
     * Erstellt einen Artikel mit Bestand
     * 
     * @return den erstellten Artikel
     */
    private void erstelleArtikelMitBestand() {
        int artikelNummer = einleseZahl("Artikel Nummer (ganzzahl)");
        String artikelArt = einleseText("Artikel Art (text)");
        int bestand = einleseZahl("Bestand (ganzzahl)");

        artikel = erstelleArtikel(artikelNummer, artikelArt, bestand);
        log(String.format("Es wurde eine Neuer Artikel '%s' mit der ArtikelNummer '%s' und Bestand '%s' erstellt.",
                artikel.getArt(), artikel.getArtikelNr(), artikel.getBestand()));
    }

    /**
     * Erstellt einen Artikel
     * 
     * @param artikelnummer die artikelnummer
     * @param artikelart    die artikelart
     * @return den Artikel
     */
    private Artikel erstelleArtikel(int artikelnummer, String artikelart) {
        return new Artikel(artikelnummer, artikelart);
    }

    /**
     * Erstellt einen Artikel
     * 
     * @param artikelnummer die artikelnummer
     * @param artikelart    die artikelart
     * @param Bestand       der Bestand
     * @return den Artikel
     */
    private Artikel erstelleArtikel(int artikelnummer, String artikelart, int Bestand) {
        return new Artikel(artikelnummer, artikelart, Bestand);
    }

    /**
     * Bucht einen Zugang auf den Artikel
     * 
     * @param menge die zu Buchende Menge
     */
    private void bucheZugang() {
        int menge = einleseZahl("Buchungsmenge-Zugang");
        if (artikel == null) {
            log("Kein Artikel vorhanden | Artikel erstellen bevor ein Zugang gebucht werden kann");
            return;
        }
        if (menge <= 0) {
            log(menge + " | Buchungsmenge inkorrekt!");
            return;
        }
        artikel.bucheZugang(menge);
        log("Es wurden '" + menge + "'' zugebucht.");
    }

    /**
     * Bucht einen Abgang auf den Artikel
     * 
     * @param menge die Abzubuchende Menge
     */
    private void bucheAbgang() {
        int menge = einleseZahl("Buchungsmenge-Abgang");
        if (artikel == null) {
            log("Kein Artikel vorhanden | Artikel erstellen bevor ein Zugang gebucht werden kann");
            return;
        }
        if (menge <= 0) {
            log("Buchungsmenge inkorrekt! | " + menge);
            return;
        }
        if (menge > artikel.getBestand()) {
            log("Buchungsmenge inkorrekt! | Kann nicht mehr abbuchen als vorhanden ist!");
            return;
        }
        artikel.bucheAbgang(menge);
        log("Es wurden '" + menge + "'' abgebucht.");
    }

    /**
     * Gibt die aktuellen Informationen zum Artikel in der Konsole aus
     */
    private void artikelInfo() {
        String info = "";
        if (artikel == null) {
            info += "Aktueller Artikel: ---";
        } else {
            info = artikel.toString();
        }
        log(info);
    }

    /**
     * Formatierte Konsolenausgabe: Zeilenweise
     */
    private void log(String text) {
        System.out.println("==| " + text + " |===\n");
    }

    /**
     * Weist an, das Programm im nächsten update zu Beenden
     */
    private void BeendeProgramm() {
        this.programmAktiv = false;
        log("Programm Wird Beendet ...");
    }

    /**
     * Main Methode
     * 
     * @param args Argumente
     */
    public static void main(String[] args) {
        ArtikelDialog dialog = new ArtikelDialog();
        dialog.Start();
    }
}