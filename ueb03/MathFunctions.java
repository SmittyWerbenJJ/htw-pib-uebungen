/**
 * 3. Uebung Programmierung 1
 *
 * @author Raphael Kimbula
 * @version v0.3
 */
public class MathFunctions {

  public static long berechneTeilersumme(long zahl) {
    long teilersumme = 0;
    for (int i = 1; i <= zahl; i++) {
      if (zahl % i == 0) {
        teilersumme = teilersumme + i;
      }
    }
    return teilersumme;
  }

  public static String berechneChecksummeIsbn(long isbn) {
    int anzahlZiffern = (int) Math.log10(isbn) + 1;
    int ergebnis = 0;

    for (int i = anzahlZiffern; i >= 1; i--) {
      ergebnis += (10 - i) * (int) Math.floor(isbn / Math.pow(10, i - 1) % 10);
    }

    ergebnis = ergebnis % 11;
    String pruefZiffer;

    if (ergebnis == 0) {
      pruefZiffer = "X";
    } else {
      pruefZiffer = String.valueOf(ergebnis);
    }
    return isbn + pruefZiffer;
  }

  static String berechneNullstellen(double p, double q) {
    double Diskriminante = Math.pow((p / 2), 2) - q;

    if (Diskriminante < 0) {
      return "Komplexe Nullstellen";
    }

    var x1 = -(p / 2) + Math.sqrt(Diskriminante);
    var x2 = -(p / 2) - Math.sqrt(Diskriminante);

    if (Diskriminante > 0) {
      return "Zwei Nullstellen: " + x1 + "|" + x2;
    } else {
      return "Doppelte Nullstelle: " + x1;
    }
  }

  public static void main(String[] args) {
    System.out.println(berechneTeilersumme(6));
    System.out.println(berechneChecksummeIsbn(383622862));
    System.out.println(berechneNullstellen(1.5, 2.5));
  }
}
