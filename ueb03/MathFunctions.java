/**
 * Sammlung an Mathe Funktionen zur Berechnung vor Teilersummen, ISBN-Checksummen, Nullstellen
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version v1.0
 */
public class MathFunctions {

  /**
   * Konstruktor
   */
  public MathFunctions() {}

  /**
   * Berechnung einer Teilersumme
   * @param zahl die zu berechnende Zahl
   * @return die TeilerSumme
   */
  public static long berechneTeilersumme(long zahl) {
    check(0 < zahl, "zahl muss eine positive ganze Zahl sein!");
    long teilersumme = 0;

    for (long i = 1; i <= zahl; i++) {
      if (zahl % i == 0) {
        teilersumme += i;
      }
    }
    return teilersumme;
  }

  /**
   * Berechnung der Cheksumme einer ISBN-10
   * @param isbn die 9-stellige isbn
   * @return die Checksumme
   */
  public static String berechneChecksummeIsbn(long isbn) {
    check(
      isbn > 99999999 && isbn < 1000000000,
      "zahl muss eine 9-stellige ganze Zahl sein!"
    );

    long isbnZehner = 0;
    for (int i = 9; isbn > 0; i--) {
      isbnZehner = isbnZehner + (isbn % 10) * i;
      isbn = isbn / 10;
    }
    isbnZehner = isbnZehner % 11;

    if (isbnZehner == 10) {
      return "X";
    } else {
      return String.valueOf(isbnZehner);
    }
  }

  /**
   * NullstellenBerechnung mithilfe der PQ-Formel
   * @param p P
   * @param q Q
   * @return Formatierte Ausgabe des ergebnisses
   */
  public static String berechneNullstellen(double p, double q) {
    double diskriminante = Math.pow((p / 2), 2) - q;

    if (diskriminante < 0) {
      return "Komplexe Nullstellen";
    }

    var x1 = -(p / 2) + Math.sqrt(diskriminante);
    var x2 = -(p / 2) - Math.sqrt(diskriminante);

    if (diskriminante > 0) {
      return "Zwei Nullstellen: " + x1 + "|" + x2;
    } else {
      return "Doppelte Nullstelle: " + x1;
    }
  }

  /**
   * Ueberpruefung einer Bedingung und Ausgabe einer Exception mit nachricht bei Fehler
   * @param bedingung die Bedingung
   * @param msg die Nachricht
   */
  public static void check(boolean bedingung, String msg) {
    if (!bedingung) {
      throw new IllegalArgumentException(msg);
    }
  }
}
