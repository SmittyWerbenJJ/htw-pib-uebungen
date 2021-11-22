/**
 * Sammlung an Mathe Funktionen zur Berechnung vor Teilersummen, ISBN-Checksummen, Nullstellen
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version v1.0
 */

public class MathFunctions {

  /**
   * Berechnung einer Teilersumme
   * @param zahl die zu berechnende Zahl
   * @return die TeilerSumme
   */
  public static long berechneTeilersumme(long zahl) {
    if (zahl < 0 == true) {
      throw new IllegalArgumentException("Zahl muss eine positive Zahl sein");
    }

    long teilersumme = 1;
    long loopzahl = zahl / 2;

    if (zahl == 1) {
      teilersumme = 1;
    } else if (zahl == 0) {
      teilersumme = 0;
    } else if (zahl % 2 == 0) {
      /*gerade zahl */
      for (long i = loopzahl; i > 1; i--) {
        if (zahl % i == 0) {
          teilersumme += i;
        }
      }
    } else {
      /*ungerade zahl */
      //1. durchlauf:hälfte der zahl
      long range = zahl / (long) 2;
      //2. durchlauf von hälfte der zahl bis >1
      for (long i = range; i > 1; i--) {
        if (zahl % i == 0) {
          teilersumme += i;
        }
        //3. eins ist immer dabei
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
    double diskriminante = Math.pow((p / 2.0), 2.0) - q;

    if (diskriminante < 0.0) {
      return "Komplexe Nullstellen";
    }

    var x1 = -(p / 2) + Math.sqrt(diskriminante);
    var x2 = -(p / 2) - Math.sqrt(diskriminante);

    if (diskriminante > 0.0) {
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
