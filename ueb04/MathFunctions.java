import java.math.BigInteger;

/**
 * Sammlung an Mathe Funktionen
 * ubung04
 *
 * @author Raphael Kimbula
 * @version v0.1
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
   * uberprufung ob eine zahl eine summe von potenzen ist
   * zahl = a^4 + a^3 + c^2
   * @param zahl die zu prufende zahl
   * @return ergebnis der prufung
   */
  public static boolean istSummeVonPotenzen(long zahl) {
    /* a b c suchen
      prufen ob zahl == a^4 + b^3 + c^2
    */
    boolean ergebnis = false;
    // long berechneSumme(int a ,int b,int c){
    //   return (a*a*a*a) + (b*b*b) + (c*c);
    // }
    return ergebnis;
  }

  /***
   * den ggt von 2 zahlen berechnen
   * @param zahl1
   * @param zahl2
   * @return den ggt
   */

  public static int berechneGGT(int zahl1, int zahl2) {
    int bigNumber = Math.max(zahl1, zahl2);
    int smallNumber = Math.min(zahl1, zahl2);
    int ggt = 1;

    //GGT bei bleicher zahl
    if (zahl1 == zahl2) {
      ggt = zahl1;
    } else {
      //GGT
      while (true) {
        if (smallNumber % bigNumber == 0) {
          ggt = bigNumber;
          break;
        } else {
          if (bigNumber <= 0) {
            ggt = -1;
            break;
          }
          bigNumber = bigNumber - (smallNumber % bigNumber);
        }
      }
    }
    return ggt;
  }

  /**
   * berechnet die fakultaet
   * @param zahl die zu berechnende zahl
   * @return die berechnetefakultaet. ist
   * @throws ArithmeticException wenn die fakultaet zu groß für {@code long} ist
   */
  public static long berechneFakultaet(int zahl) {
    BigInteger fakultaet = BigInteger.ONE;

    for (int i = 2; i <= zahl; i++) {
      fakultaet = fakultaet.multiply(BigInteger.valueOf(i));
    }
    return fakultaet.longValueExact();
  }

  /**
   * berechnet die Reihensumme
   * @param anzahl die Anzahl n
   * @param x das X
   * @return die berechnete Reihensumme
   */
  public static double berechneReihensumme(int anzahl, double x) {
    int n = anzahl;

    double reihensumme = 0.0;

    if (x != 0) {
      for (int i = 1; i <= n; i++) {
        var divident = Math.pow(x - 1.0, i);
        var divisor = (double) i * Math.pow(x, (double) i);

        reihensumme += divident / divisor;
      }
    }
    return reihensumme;
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
