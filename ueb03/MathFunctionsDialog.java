import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Dialog Klasse fuer die Klasse MathFunctions.
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 1.0
 */
public class MathFunctionsDialog {

  private Scanner input = new Scanner(System.in);
  boolean programmAktiv = true;

  /**
   *Klassenkonstanten
   */
  private static final int TEILERSUMME_BERECHNEN = 1;
  private static final int CHECKSUMME_ISBN_BERECHNEN = 2;
  private static final int NULLSTELLEN_BERECHNEN = 3;
  private static final int ENDE = 0;

  /**
   *Hauptschleife des Testprogramms
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
      ": Teilersumme berechnen; \n" +
      CHECKSUMME_ISBN_BERECHNEN +
      ": Pruefziffer der ISBN berechnen; \n" +
      NULLSTELLEN_BERECHNEN +
      ": Nullstellen von p und q berechnen; \n" +
      ENDE +
      ": beenden -> \n"
    );
    funktion = input.nextInt();
    return funktion;
  }

  /**
   * Starten der ausgewaehlten Funktion
   *
   * @param funktion die auszufuehrende Funktion
   *@throws IllegalArgumentException bei falscher eingabe
   *
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
      default:
        throw new InputMismatchException();
    }
  }

  private void funktionProgrammEnde() {
    programmAktiv = false;
    System.out.print("Programm wird beendet ...");
  }

  private void zeigeEingabeFehlerInKonsole(String message) {
    System.out.println(message);
    input.nextLine();
  }

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

  /**4
   * Einstiegspunkt des Programms
   *
   * @param args Kommandozeilenargumente
   */
  public static void main(String[] args) {
    new MathFunctionsDialog().start();
  }
}
