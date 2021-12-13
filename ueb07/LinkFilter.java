import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse LinkFilter.
 * 
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class LinkFilter {
    /**
     * Konstruktor für Objekte der Klasse LinkFilter
     */

    public LinkFilter() {

    }

    /**
     * Main Methode
     * 
     * @param args HTML Links
     */

    public static void main(String[] args) {
        Scanner _scanner = new Scanner(System.in);

        // einlesen von Cat
        String eingabe = "";// = AlleZeilenEinlesenVonScanner();

        // check if inputStream has data
        boolean eingabeVorhanden;
        try {
            if (System.in.available() == 0) {
                eingabeVorhanden = false;
            } else {
                eingabeVorhanden = true;
            }
        } catch (IOException e) {
            eingabeVorhanden = false;
        }

        // eingabe vorhanden? -> automatisch einlesen
        if (eingabeVorhanden == true) {
            while (_scanner.hasNextLine())
                eingabe += _scanner.nextLine();
        } else {
            // keine eingabe vorhanden? -> manuelle benutzereingabe
            System.out.print("HTML Links eingeben. Enter zum abschickeen:");
            eingabe = _scanner.nextLine();
        }
        System.out.println("eingabe: " + eingabe);

        /* Verarbeiten der Eingabe */
        String ergebnis = EingabeAuswerten(eingabe);

        System.out.print(ergebnis);
    }

    private static String EingabeAuswerten(String eingabe) {
        char[] charArray = eingabe.toCharArray();
        int[] oeffnungsTagindices = new int[] {};

        // link-oeffungstags suchen
        for (int i = 0; i < eingabe.length(); i++) {
            if (charArray[i] == '<' && charArray[i + 1] == 'a') {
                oeffnungsTagindices = Arrays.copyOf(oeffnungsTagindices, oeffnungsTagindices.length + 1);
                oeffnungsTagindices[oeffnungsTagindices.length - 1] = i;
            }
        }
        // link - schließungstags suchen
        int[] schließungsTagindices = new int[] {};

        for (int i = 0; i < eingabe.length() - 2; i++) {
            if (charArray[i] == '<' && charArray[i + 1] == '/' && charArray[i + 2] == 'a') {
                schließungsTagindices = Arrays.copyOf(schließungsTagindices, schließungsTagindices.length + 1);
                schließungsTagindices[schließungsTagindices.length - 1] = i;
            }
        }

        // LinkTags anhand der Filter rausfiltern
        String[] LinkTags = {};
        int i = 0;
        for (int index : oeffnungsTagindices) {
            String sub = eingabe.substring(index, schließungsTagindices[i] + 4);
            LinkTags = ergaenzeStringArray(LinkTags, sub);
            i++;
        }

        String ergebnis = "";
        for (String linktag : LinkTags) {
            /* LinkTags durchsuchen nach Links */
            int first;
            int last;

            // erstes " suchen
            first = linktag.indexOf('"') + 1;
            // zweites " suchen
            last = linktag.lastIndexOf('"');
            // link rausfiltern
            String link = linktag.substring(first, last);

            // LinkTag durchsuchen nach Namen
            first = linktag.indexOf('>', 0) + 1;
            last = linktag.indexOf("</a>", 0);
            String name = linktag.substring(first, last);

            // LinkTags durchsuchen nach Anzahl zeichen
            int anzahlZeichen = link.length();
            // htw saar: http://www.htwsaar.de, Anzahl Zeichen: 22
            ergebnis += String.format("%s:\t\t%s, Anzahl Zeichen: %d\n",
                    name, link, anzahlZeichen);

        }
        return ergebnis;
    }

    public static String[] ergaenzeStringArray(String[] ursprungsArray, String neuerWert) {
        String[] neuesArray = Arrays.copyOf(ursprungsArray, ursprungsArray.length + 1);
        neuesArray[neuesArray.length - 1] = neuerWert;
        return neuesArray;
    }

}
