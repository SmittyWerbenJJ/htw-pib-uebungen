import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Dialog Klasse fuer die Klasse MathFunctions
 * ubung04
 *
 * @author Raphael Kimbula
 * @version 0.1
 */
public class MathFunctionsDialog {

  private Scanner input = new Scanner(System.in);
  boolean programmAktiv = true;

  private static final int TEILERSUMME_BERECHNEN = 1;
  private static final int CHECKSUMME_ISBN_BERECHNEN = 2;
  private static final int NULLSTELLEN_BERECHNEN = 3;
  private static final int GGT_BERECHNEN = 4;
  private static final int FAKULTAET_BERECHNEN = 5;
  private static final int REIHENSUMME_BERECHNEN = 6;
  private static final int ENDE = 0;

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
      }
    }
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
  private void zeigeEingabeFehlerInKonsole(String message) {
    System.out.println("\n<ACHTUNG> " + message + "<ACHTUNG>\n");
    input.nextLine();
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
      } catch (Exception e) {
        zeigeEingabeFehlerInKonsole("Bitte Natürliche Zahlen eingeben!");
      }
      try {
        System.out.print("Reihensumme Berechnen  | x (double) : ");
        x = input.nextDouble();
      } catch (Exception e) {
        zeigeEingabeFehlerInKonsole("Bitte Kommazahl eingeben!");
      }
      canExecute = true;
    }
    double reihensumme = MathFunctions.berechneReihensumme(anzahl, x);

    System.out.printf(
      "\n<=== Reihensumme von n=%d und x=%f) | %f ===>\n",
      anzahl,
      x,
      reihensumme
    );
  }

  /**
   * methode zur berechnung von groesster gemeinsamer teiler (ggt)  mit konsolen-gui
   */

  private void funktionGGT() {
    try {
      System.out.print("GGT Berechnen  | zahl1 : ");
      int zahl1 = input.nextInt();
      System.out.print("GGT Berechnen | zahl2 : ");
      int zahl2 = input.nextInt();
      int ggt = MathFunctions.berechneGGT(zahl1, zahl2);

      System.out.printf(
        "\n<=== GGT von (%d,%d) | %d ===>\n",
        zahl1,
        zahl2,
        ggt
      );
    } catch (Exception e) {
      zeigeEingabeFehlerInKonsole("Bitte Natürliche Zahlen eingeben!");
      funktionGGT();
    }
  }

  /**
   * methode zur berechnung von fakultaet  mit konsolen-gui
   */
  private void funktionFakultaet() {
    try {
      System.out.print("Fakultaet Berechnen | zahl : ");
      int zahl = input.nextInt();
      long fak = MathFunctions.berechneFakultaet(zahl);
      if (fak == 0) {
        System.out.printf(
          "\n<=== Fakultaet von %d | >%d ===>\n",
          zahl,
          Long.MAX_VALUE
        );
      } else {
        System.out.printf("\n<=== Fakultaet von %d | %d ===>\n\n", zahl, fak);
      }
    } catch (ArithmeticException e) {
      zeigeEingabeFehlerInKonsole(
        "Berechnete Fakultaet zu gross fur die darstellung als {long} !"
      );
    } catch (Exception e) {
      zeigeEingabeFehlerInKonsole("Bitte Natürliche Zahlen eingeben!");
      funktionFakultaet();
    }
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
      zeigeEingabeFehlerInKonsole("Bitte Positive Ganze Zahl eingeben!");
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
      zeigeEingabeFehlerInKonsole("Bitte korrekte ISBN eingeben!");
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
      zeigeEingabeFehlerInKonsole(
        "Bitte korrekte Werte fuer p und q eingeben!"
      );
      funktionNullstellen();
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
   * Einstiegspunkt des Programms
   *
   * @param args Kommandozeilenargumente
   */
  public static void main(String[] args) {
    new MathFunctionsDialog().start();
  }
}
