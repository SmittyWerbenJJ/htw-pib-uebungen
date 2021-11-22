import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Dialog Klasse fuer die Klasse MathFunctions
 * ubung04
 *
 * @author Raphael Kimbula
 * @version 0.2
 */
public class MathFunctionsDialog {

  private Scanner input = new Scanner(System.in);
  boolean programmAktiv = true;

  private static final int TEILERSUMME_BERECHNEN = 1;
  private static final int CHECKSUMME_ISBN_BERECHNEN = 2;
  private static final int NULLSTELLEN_BERECHNEN = 3;
  private static final int SUMME_VON_POTENZEN_BERECHNEN = 4;
  private static final int GGT_BERECHNEN = 5;
  private static final int FAKULTAET_BERECHNEN = 6;
  private static final int REIHENSUMME_BERECHNEN = 7;
  private static final int ENDE = 0;

  /**
   * Einstiegspunkt des Programms
   *
   * @param args Kommandozeilenargumente
   */
  public static void main(String[] args) {
    new MathFunctionsDialog().start();
  }

  /**
   *Hauptschleife des programms
   */
  public void start() {
    int funktion = -1;
    while (programmAktiv) {
      try {
        funktion = einlesenFunktion();
        ausfuehrenFunktion(funktion);
      } catch (InputMismatchException e) {
        System.out.println(
          "<FEHLER> Bitte Richtige Funktion aus dem menu waehlen! <FEHLER>"
        );
        input.nextLine();
        continue;
      } catch (Exception e) {
        zeigeFehlerInKonsole(e.getMessage());
      }
    }
  }

  /**
   * methode zur anweisung des beendens des programms im nachsten update
   */
  private void funktionProgrammEnde() {
    programmAktiv = false;
    System.out.print("Programm wird beendet ...");
  }

  /**
   * Einlesen der auszufuehrenden Funktion
   *
   * @return identifirzierer der auszufuehrende Funktion
   */
  private int einlesenFunktion() {
    int funktion;

    System.out.print(
      TEILERSUMME_BERECHNEN +
      ": Teilersumme berechnen \n" +
      CHECKSUMME_ISBN_BERECHNEN +
      ": Pruefziffer der ISBN berechne; \n" +
      NULLSTELLEN_BERECHNEN +
      ": Nullstellen von p und q berechnen \n" +
      SUMME_VON_POTENZEN_BERECHNEN +
      ": Summe von Potenzen berechnen \n" +
      GGT_BERECHNEN +
      ": GGT berechnen \n" +
      FAKULTAET_BERECHNEN +
      ": Fakultaet berechnen \n" +
      REIHENSUMME_BERECHNEN +
      ": Reihensumme berechnen \n" +
      ENDE +
      ": beenden\n-> "
    );
    funktion = input.nextInt();
    return funktion;
  }

  /**
   * methode zur auswahl der zu startenden Funktion
   *
   * @param funktion die auszufuehrende Funktion
   * @throws InputMismatchException bei falscher eingabe
   */

  private void ausfuehrenFunktion(int funktion)
    throws IllegalArgumentException {
    switch (funktion) {
      case TEILERSUMME_BERECHNEN:
        funktionTeilerSumme();
        break;
      case CHECKSUMME_ISBN_BERECHNEN:
        funktionISBN();
        break;
      case NULLSTELLEN_BERECHNEN:
        funktionNullstellen();
        break;
      case ENDE:
        funktionProgrammEnde();
        break;
      case SUMME_VON_POTENZEN_BERECHNEN:
        funktionSummeVonPotenzen();
        break;
      case GGT_BERECHNEN:
        funktionGGT();
        break;
      case FAKULTAET_BERECHNEN:
        funktionFakultaet();
        break;
      case REIHENSUMME_BERECHNEN:
        funktionReihensumme();
        break;
      default:
        throw new InputMismatchException();
    }
  }

  /**
   * methode zur ausgabe einer meldung in der konsole und befreiung des input buffer
   */
  private void zeigeFehlerInKonsole(String message) {
    System.out.println("\n<ACHTUNG> " + message + " <ACHTUNG>\n");
    input.nextLine();
  }

  /**
   * methode zur berechnung von TeilerSumme mit mit konsolen-gui
   */
  private void funktionTeilerSumme() {
    try {
      System.out.print("eine ganze positive Zahl eingeben: ");
      long zahl = input.nextLong();
      long teilersumme = MathFunctions.berechneTeilersumme(zahl);
      System.out.println(
        "\n<=== Teilersumme von " + zahl + " | " + teilersumme + "  ===>\n"
      );
    } catch (Exception e) {
      zeigeFehlerInKonsole("Bitte Positive Ganze Zahl eingeben!");
      funktionTeilerSumme();
    }
  }

  /**
   * methode zur berechnung von ISBN prufsumme  mit konsolen-gui
   */
  private void funktionISBN() {
    try {
      System.out.print("eine 9-stellige ISBN eingeben: ");
      long isbn = input.nextLong();
      String pruefziffer = MathFunctions.berechneChecksummeIsbn(isbn);
      System.out.println(
        "\n<=== ISBN-Pruefziffer | " + pruefziffer + " ===>\n"
      );
    } catch (Exception e) {
      zeigeFehlerInKonsole("Bitte korrekte ISBN eingeben!");
      funktionISBN();
    }
  }

  /**
   * methode zur Berechnung von Nullstellen  mit konsolen-gui
   */
  private void funktionNullstellen() {
    try {
      double p;
      double q;
      System.out.print("Nullstellenberechnung - p (zahl): ");
      p = input.nextDouble();
      System.out.print("Nullstellenberechnung - q (zahl): ");
      q = input.nextDouble();
      String nullstellen = MathFunctions.berechneNullstellen(p, q);
      System.out.println("\n<=== Nullstellen | " + nullstellen + " ===>\n");
    } catch (Exception e) {
      zeigeFehlerInKonsole("Bitte korrekte Werte fuer p und q eingeben!");
      funktionNullstellen();
    }
  }

  /**
   * methode zur berechnung von Summe von Potenzen mit konsolen-gui
   */
  private void funktionSummeVonPotenzen() {
    boolean canExecute = false;
    long zahl = 0;

    while (!canExecute) {
      try {
        System.out.print("Summe von Potenzen Berechnen  | zahl (long) : ");
        zahl = input.nextLong();
      } catch (Exception e) {
        zeigeFehlerInKonsole("Bitte eine Natuerliche Zahl eingeben!");
      }
      canExecute = true;
    }
    boolean ergebnis = MathFunctions.istSummeVonPotenzen(zahl);

    String ergebnisText = "";
    if (ergebnis == false) {
      ergebnisText = "KEINE";
    }
    System.out.printf(
      "\n<=== %d ist %s Summe von Potenzen a^4 + b^3 + c^2  ===>\n\n",
      zahl,
      ergebnisText,
      ergebnis
    );
  }

  /**
   * methode zur berechnung von reihensumme mit konsolen-gui
   */
  private void funktionReihensumme() {
    boolean canExecute = false;
    int anzahl = 0;
    double x = 0.0;

    while (!canExecute) {
      try {
        System.out.print("Reihensumme Berechnen  | anzahl n (int) : ");
        anzahl = input.nextInt();
        if (anzahl < 1) {
          throw new Exception();
        }
      } catch (Exception e) {
        zeigeFehlerInKonsole("Bitte Natuerliche Zahlen eingeben!");
        continue;
      }

      try {
        System.out.print("Reihensumme Berechnen  | x (double) : ");
        x = input.nextDouble();
      } catch (Exception e) {
        zeigeFehlerInKonsole("Bitte Kommazahl eingeben!");
        continue;
      }

      canExecute = true;
    }
    double reihensumme = MathFunctions.berechneReihensumme(anzahl, x);
    if (Double.isNaN(reihensumme)) {
      zeigeFehlerInKonsole("Zahl zu gross fuer den datentyp Double!");
    } else {
      System.out.printf(
        "\n<=== Reihensumme von n=%d und x=%f) | %g ===>\n",
        anzahl,
        x,
        reihensumme
      );
    }
  }

  /**
   * methode zur berechnung von groesster gemeinsamer teiler (ggt)  mit konsolen-gui
   */

  private void funktionGGT() {
    int zahl1 = 0;
    int zahl2 = 0;
    try {
      System.out.print("GGT Berechnen  | zahl1 : ");
      zahl1 = input.nextInt();
      System.out.print("GGT Berechnen | zahl2 : ");
      zahl2 = input.nextInt();
    } catch (Exception e) {
      zeigeFehlerInKonsole("Bitte Natuerliche Zahlen eingeben!");
      return;
    }

    int ggt = MathFunctions.berechneGGT(zahl1, zahl2);
    System.out.printf("\n<=== GGT von (%d,%d) | %d ===>\n", zahl1, zahl2, ggt);
  }

  /**
   * methode zur berechnung von fakultaet  mit konsolen-gui
   */
  private void funktionFakultaet() {
    System.out.print("Fakultaet Berechnen | zahl : ");
    int zahl = 0;
    try {
      zahl = input.nextInt();
    } catch (InputMismatchException e) {
      zeigeFehlerInKonsole("Bitte naturliche zahle eingeben");
    }

    if (zahl > 20) {
      zeigeFehlerInKonsole("Zahl zu gross zum darstellen");
      return;
    }
    if (zahl < 0) {
      zeigeFehlerInKonsole("Keine Negative zahl erlaubt");
      return;
    }

    long fak = MathFunctions.berechneFakultaet(zahl);
    System.out.printf("\n<=== Fakultaet von %d | %d ===>\n\n", zahl, fak);
  }
}
