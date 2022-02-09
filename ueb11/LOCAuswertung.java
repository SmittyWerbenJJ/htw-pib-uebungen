import java.util.*;
import java.io.*;

/**
 * Lines-of-Code (LOC) Auswertung von Java Quellcode-dateien.
 * Ausgeben der Anzahl der Codezeilen
 * 
 * @Author Raphael Kimbula & Siyamend Bozkurt
 * @version 06.02.2022
 */
public class LOCAuswertung {

    /**
     *
     */
    private static final String FEHLER_BEI_LESEN_DER_DATEI = "Fehler Bei Lesen der Datei - IO Exception";
    private static final String DATEI_IST_NICHT_LESBAR = "Datei ist nicht Lesbar!";

    private int gesamtAnzahlLOC;
    ArrayList<LOCErgebnis> locErgebnisse;
    ArrayList<String> skippedFiles;
    ArrayList<String> fehlerNachrichten;

    /**
     * Konstruktor - initialisierung der arraylists
     */
    public LOCAuswertung() {
        gesamtAnzahlLOC = 0;
        locErgebnisse = new ArrayList<LOCErgebnis>();
        skippedFiles = new ArrayList<String>();
        fehlerNachrichten = new ArrayList<String>();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Bitte Mindestens eine Datei ubergeben.");
            System.out.println("Format:\n\t LOCAuswertung.java Datei1.java Datei2.java ...");
            return;
        }
        LOCAuswertung auswertung = new LOCAuswertung();
        auswertung.start(args);
    }

    /**
     * Programmstart LOC zaehlung
     * 
     * @param argumente die Konsolenargumente
     */
    public void start(String[] argumente) {
        for (String argument : argumente) {
            if (argument == null) {
                continue;
            }

            File datei = new File(argument);

            try {
                boolean existiert = checkDateiExistiert(datei);
                boolean istJavaDatei = checkDateiIstJavaDatei(datei);
                boolean lesbar = checkDateiLesbar(datei);
                boolean istDateiValide = lesbar && istJavaDatei && existiert;

                if (istDateiValide == false) {
                    skippedFiles.add(datei.getName());
                } else {
                    LOCErgebnis locErgebnis = zaehlungLOCAusDatei(datei);
                    locErgebnisse.add(locErgebnis);
                }
            } catch (DateiExistiertNichtException ex) {
                fehlerNachrichten.add(ex.getMessage() + datei.getName());
                skippedFiles.add(datei.getName());
            } catch (DateiNichtLesbarException ex) {
                fehlerNachrichten.add(ex.getMessage() + datei.getName());
                skippedFiles.add(datei.getName());
            } catch (DateiIstKeineJavaDateiException ex) {
                fehlerNachrichten.add(ex.getMessage() + datei.getName());
                skippedFiles.add(datei.getName());
            }
        }
        ausgebenErgebnis();
    }

    /**
     * die LOC zaehlung
     * 
     * @param datei die zu zaehlende datei
     * @return das Eergebnis der zaehlung
     * @throws DateiNichtLesbarException bei IO Exception
     */
    private LOCErgebnis zaehlungLOCAusDatei(File datei) throws DateiNichtLesbarException {
        String zeile = null;
        LOCErgebnis aktuelleLOCZaehlung = new LOCErgebnis();
        aktuelleLOCZaehlung.setDateiName(datei.getName());

        /*
         * Datei muss nicht geschlossen werden, der Try Block macht es schon:
         * Resource Statement
         * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose
         * .html
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(datei))) {
            while ((zeile = reader.readLine()) != null) {
                if (istCodeZeile(zeile)) {
                    aktuelleLOCZaehlung.addAnzahlLOC();
                    gesamtAnzahlLOC += 1;
                }
            }
            return aktuelleLOCZaehlung;
        } catch (IOException ex) {
            throw new DateiNichtLesbarException(FEHLER_BEI_LESEN_DER_DATEI);
        }
    }

    /**
     * prufung ob datei lesbar ist
     * 
     * @param datei die datei
     * @return true wenn lesbar
     * @throws DateiNichtLesbarException wenn nicht lesbar
     */
    boolean checkDateiLesbar(File datei) throws DateiNichtLesbarException {
        try {
            boolean lesbar = datei.canRead();
            /*
             * datei.canRead() kann falsches ergebnis geben, wenn
             * die java VM mit admin rechten gestartet wird
             */

            if (lesbar == true) {
                return true;
            } else {
                throw new DateiNichtLesbarException(DATEI_IST_NICHT_LESBAR);
            }
        } catch (SecurityException ex) {
            // Es ist ein Fehler beim Aufruf von datei.canRead aufgetreten
            // -> hat irgendwas mit securitymanager zu tun
            // wir gehen davon aus dass die datei nicht lesbar ist
            throw new DateiNichtLesbarException(DATEI_IST_NICHT_LESBAR);
        }
    }

    /**
     * prufung ob datei existert
     * 
     * @param datei die datei
     * @return true wenn existiert, false wenn nicht
     * @throws DateiExistiertNichtException wenn datei nicht existert
     */
    boolean checkDateiExistiert(File datei) throws DateiExistiertNichtException {
        try {
            boolean lesbar = datei.exists();
            if (lesbar == true) {
                return true;
            } else {
                throw new DateiExistiertNichtException("Die Datei Existiert nicht!");
            }
        } catch (SecurityException ex) {
            // Es ist ein Fehler beim Aufruf von datei.exists aufgetreten
            // -> hat irgendwas mit securitymanager zu tun
            // wir gehen davon aus dass die datei nicht existiert
            throw new DateiExistiertNichtException("Die Datei Existiert nicht!");
        }
    }

    /**
     * prufung ob eine dateie eine java datei ist-> dateiname endet mit .java
     * 
     * @param datei die datei
     * @return immer true, falls wahr
     * @throws DateiIstKeineJavaDateiException wenn datei keine java datei ist
     */
    boolean checkDateiIstJavaDatei(File datei) throws DateiIstKeineJavaDateiException {
        boolean istJavaDatei = datei.getPath().toLowerCase().endsWith(".java");
        if (istJavaDatei == false) {
            throw new DateiIstKeineJavaDateiException("Die Datei ist keine Java Datei");
        }
        return true;
    }

    /**
     * prufung ob eine zeile eine codezeile ist durch trimmen und
     * wenn eine der folgenden ausdruecke unwahr ist:
     * -> nicht null
     * -> blank
     * -> startswith "//"
     * 
     * @param zeile die codezeile
     * @return true falls codezeile, false falls nicht
     */
    boolean istCodeZeile(String zeile) {
        if (zeile == null || zeile.isBlank() || zeile.trim().startsWith("//")) {
            return false;
        }
        return true;
    }

    /**
     * ausgabe des ergebnis der operation in der konsole
     */
    void ausgebenErgebnis() {
        ArrayList<String> str = new ArrayList<String>();

        str.add("---------------------[ Auswertung Lines OF CODE (LOC) ]---------------------\n");
        int indexLetzeZahl = 0;
        for (LOCErgebnis ergebnis : locErgebnisse) {
            int anzahlLOC = ergebnis.getAnzahlLOC();
            String locStr = "LOC: " + anzahlLOC;
            String dateiName = ergebnis.getDateiName();
            str.add(locStr + " --> " + dateiName + "\n");
        }
        str.add("Gesamt:\n");
        str.add("LOC: " + gesamtAnzahlLOC + " --> " + locErgebnisse.size() + " Dateien\n");

        if (skippedFiles.size() > 0) {
            str.add("\n---------------------[ Ubersprungene Dateien ]---------------------\n");
            for (String file : skippedFiles) {
                str.add(file + "\n");
            }
        }
        for (String string : str) {
            System.out.print(string.toString());
        }

    }
}