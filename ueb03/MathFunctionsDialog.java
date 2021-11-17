import java.util.Scanner;

/**
 * Dialog Klasse fuer die Klasse MathFunctions.
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 1.0
 */
public class MathFunctionsDialog {

  private Scanner input = new Scanner(System.in);

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
    while (funktion != ENDE) {
      try {
        funktion = einlesenFunktion();
        ausfuehrenFunktion(funktion);
      } catch (Exception e) {
        System.out.println("Fehlerhafte Eingabe");
        input.nextLine();
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
    input.nextLine();
    return funktion;
  }

  /**
   * Starten der ausgewaehlten Funktion
   *
   * @param funktion die auszufuehrende Funktion
   *
   */
  private void ausfuehrenFunktion(int funktion) {
    long zahl;
    long isbn;
    long n;
    double p;
    double q;
    if (funktion == TEILERSUMME_BERECHNEN) {
      try {
        System.out.print("eine ganze positive Zahl eingeben: ");
        zahl = input.nextLong();
        System.out.print("\nTeilersumme von " + zahl + " = ");
        System.out.println(MathFunctions.berechneTeilersumme(zahl) + "\n\n");
      } catch (Exception e) {
        System.out.println("Fehler bei Teilsummen Berechnung");
      }
    } else if (funktion == CHECKSUMME_ISBN_BERECHNEN) {
      try {
        System.out.print("eine 9-stellige ISBN eingeben: ");
        isbn = input.nextLong();
        System.out.println(
          "\nDie Pruefziffer lautet: " +
          MathFunctions.berechneChecksummeIsbn(isbn) +
          "\n"
        );
      } catch (Exception e) {
        System.out.println("Fehler bei ISBN Berechnung ");
      }
    } else if (funktion == NULLSTELLEN_BERECHNEN) {
      try {
        System.out.print("Wert fuer p eingeben: ");
        p = input.nextDouble();
        System.out.print("Wert fuer q eingeben: ");
        q = input.nextDouble();
        System.out.println(MathFunctions.berechneNullstellen(p, q) + "\n");
      } catch (Exception e) {
        System.out.println("Fehler bei ISBN Berechnung ");
      }
    } else if (funktion == ENDE) {
      System.out.print("Programmende");
      funktion = 0;
    } else {
      System.out.println("Falsche Funktion!");
    }
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
