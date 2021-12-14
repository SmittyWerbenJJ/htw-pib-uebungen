import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse LinkFilter.
 * 
 * @author Raphael Kimbula
 * @version v0.2
 */
public class LinkFilter {
    public LinkFilter() {
    }

    /**
     * Main Methode
     *
     */
    public static void main(String[] args) {

        String eingabe = EinlesenVonKonsole();
        String[] LinkTags = auslesenHyperTextLinks(eingabe);
        String ergebnis = sucheLinksAusLinkTags(LinkTags);
        // ergebnis += String.format("%d links wurden gefunden\n", LinkTags.length);
        System.out.print(ergebnis);
    }

    /**
     * Suche alle Linkadressen, link namen und anzahlZeichen aus LinkTags
     * 
     * @param LinkTags die zu durchsuchenden LinkTags (string Array)
     * @return formatierte ausgabe der gefundenen informationen
     */
    private static String sucheLinksAusLinkTags(String[] LinkTags) {
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
            last = linktag.indexOf("<", first + 1);
            if (last == -1) {
                last = linktag.trim().length();
            }
            String name = linktag.substring(first, last);

            // LinkTags durchsuchen nach Anzahl zeichen
            int anzahlZeichen = link.length();
            // htw saar: http://www.htwsaar.de, Anzahl Zeichen: 22
            ergebnis += String.format("%s:\t\t%s, Anzahl Zeichen: %d\n",
                    name, link, anzahlZeichen);
        }
        return ergebnis;
    }

    /**
     * Filtern aller 'a herf' tags von einer zeichenkette
     * 
     * 
     * @param html_String die zu filternde zeichenkette
     * @return ein String array mit allen html link-Tags
     */
    public static String[] auslesenHyperTextLinks(String html_String) {

        String[] HTMLLinks = {};

        // href tags suchen
        int[] hrefIndexes = new int[] {};
        int index = 0;
        while (index != -1) {
            index = html_String.indexOf("<a", index + 1);
            if (index != -1) {
                hrefIndexes = ergaenzeIntArray(hrefIndexes, index);
                index++;
            }
        }

        // fur jeden 'href', den Index des naechsten </a> suchen und alles dazwischen
        // bei ungeschlossene Tags, bis zum naechsten oeffungstag suchen
        // alles dazwischen rauskopieren und dem ergebnis anhaengen
        for (int i : hrefIndexes) {
            // int schliessendes_a_element_Index = html_String.indexOf("</a>", i);
            // if (index == -1) {
            int schliessendes_a_element_Index = html_String.indexOf("<", i + 1);

            String beschreibung = html_String.substring(i, schliessendes_a_element_Index);
            // zum ergebnis hinzufugen
            HTMLLinks = ergaenzeStringArray(HTMLLinks, beschreibung);
        }
        return HTMLLinks;

    }

    /**
     * Einlesen des HTML von der Konsole.
     * Kann Automatisch erfolgen durch unix cat
     * oder manuell durch direkte eingabe in Konsole.
     * Das Eingeben wird durch Newline beendet und zur verabeitung weitergegeben
     * 
     * @return die EIngelesenen zeilen
     * @throws IllegalArgumentException wenn die Eingabe Leer ist
     */
    private static String EinlesenVonKonsole() {
        Scanner _scanner = new Scanner(System.in);
        String eingabe = "";

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

        // eingabe vorhanden? -> automatisch einlesen fortfahren
        if (eingabeVorhanden == true) {
            while (_scanner.hasNextLine())
                eingabe += _scanner.nextLine();
        } else {
            // keine eingabe vorhanden? -> manuelle benutzereingabe
            System.out.print("HTML Text eingeben.\nEingabeTaste Bestaetight die Eingabe:");
            eingabe = _scanner.nextLine();
            eingabe = eingabe.trim();
        }
        _scanner.close();

        // ===== Exception =====
        if (eingabe.isEmpty()) {
            throw new IllegalArgumentException("Es wurde nichts eingegeben");
        }
        return eingabe;
    }

    /**
     * erweiterung eines STRING arrays um 1 integer element. Es wird ein komplett
     * neues array erstell und keine referenz kopiert
     * 
     * @param ursprungsArray das ursprungliche array
     * @param neuerWert      der hinzuzufugnende wert
     * @return ein NEUES array , inkl. dem neuen wert
     */
    public static String[] ergaenzeStringArray(String[] ursprungsArray, String neuerWert) {
        String[] neuesArray = Arrays.copyOf(ursprungsArray, ursprungsArray.length + 1);
        neuesArray[neuesArray.length - 1] = neuerWert;
        return neuesArray;
    }

    /**
     * erweiterung eines INTEGER arrays um 1 integer element. Es wird ein komplett
     * neues array erstell und keine referenz kopiert
     * 
     * @param ursprungsArray das ursprungliche array
     * @param neuerWert      der hinzuzufugnende wert
     * @return ein NEUES array , inkl. dem neuen wert
     */
    public static int[] ergaenzeIntArray(int[] ursprungsArray, int neuerWert) {
        int[] neuesArray = Arrays.copyOf(ursprungsArray, ursprungsArray.length + 1);
        neuesArray[neuesArray.length - 1] = neuerWert;
        return neuesArray;
    }

    /**
     * filtern aller html tags aus einem text
     * 
     * @param text der text der die html tags beinhaltet
     * @return ein array mit jedem tag von oeffnung bis schließung des tags
     */
    public static String[] leseAlleHTMLTagsAusString(String text) {
        int index = 0;
        String[] tags = new String[] {};
        while (index != -1) {
            index = text.indexOf('<', index);
            if (index != -1) {
                int indexSchliessung = text.indexOf('>', index) + 1;
                String tag = text.substring(index, indexSchliessung);
                tags = ergaenzeStringArray(tags, tag);
                index++;
            }
        }
        return tags;
    }

    /**
     * uberprufung ob ein html tag kleingeschrieben ist
     * 
     * @param htmlTag der zu prufende html tag
     * @return true wenn alles kleingeschrieben, false falls nicht
     */
    public static boolean istTagKleingeschrieben(String htmlTag) {
        if ((htmlTag.startsWith("<") && htmlTag.endsWith(">")) == false) {
            throw new IllegalArgumentException("bereitgestellter text ist kein HTML tag");
        }
        String[] text_splitted_by_space = htmlTag.split("\"");

        for (String s : text_splitted_by_space) {
            if (s.startsWith("\"") && s.endsWith("\"")) {
                continue;
            }
            for (char c : s.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * zaehlen der anzahl der zeilen eines strings
     * 
     * @param text der zu prufende string
     * @return die anzahl der zeilen
     */
    public static int anzahlZeilen(String text) {
        String[] splStrings = text.split("\n");
        return splStrings.length;
    }

    /**
     * check ob ein hypertextLink 1 zeile lang ist
     * 
     * @param hyperTextLink der hyperText
     * @return true wenn 1 zeile lang, false falls nicht
     */
    public static boolean istHyperTextLink_1_Zeile_Lang(String hyperTextLink) {
        String[] splStrings = hyperTextLink.split("\n");
        if (splStrings.length == 1) {
            return true;
        } else {
            return false;
        }
    }
}
