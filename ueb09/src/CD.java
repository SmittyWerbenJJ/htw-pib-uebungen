import java.util.Objects;

/**
 * CD Class
 * 
 * @Author Raphael Kimbula
 * 
 * @version v2022.01.16
 *
 */
public class CD extends Artikel {
    private String interpret;
    private String titel;
    private int anzahlTitel;

    private static final String ARTIKEL_ART = "MEDIEN";
    private static final String FEHLER_LEERER_INTERPRET = "Interpret darf nicht leer sein";
    private static final String FEHLER_TITEL_KLEINER_NULL = "Anzahl Titel muss >0 sein";
    private static final String FEHLER_LEERER_TITEL = "Titel darf nicht leer sein";

    public String getInterpret() {
        return interpret;
    }

    public int getAnzahlTitel() {
        return anzahlTitel;
    }

    public void setAnzahlTitel(int anzahlTitel) {
        if (!(anzahlTitel > 0)) {
            throw new IllegalArgumentException(FEHLER_TITEL_KLEINER_NULL);
        }
        this.anzahlTitel = anzahlTitel;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        if (titel.isBlank()) {
            throw new IllegalArgumentException(FEHLER_LEERER_TITEL);
        }
        this.titel = titel;
    }

    public void setInterpret(String interpret) {
        if (interpret.isBlank()) {
            throw new IllegalArgumentException(FEHLER_LEERER_INTERPRET);
        }
        this.interpret = interpret;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CD)) {
            return false;
        }

        CD cd = (CD) obj;
        return getArtikelNr() == cd.getArtikelNr() &&
                anzahlTitel == cd.getAnzahlTitel() &&
                interpret == cd.getInterpret() &&
                titel == cd.getTitel();
    }

    @Override
    public int hashCode() {
        /*
         * final int prime = 31;
         * int result = 29;
         * return prime * result + getArtikelNr() + titel.hashCode();
         */
        return Objects.hash(getArtikelNr(), titel, 29);
    }

    public CD(int artikelNr, int bestand, double preis, String interpret, String titel, int anzahlTitel) {
        super(artikelNr, ARTIKEL_ART, bestand, preis);
        this.setTitel(titel);
        this.setAnzahlTitel(anzahlTitel);
    }

    @Override
    public String getBeschreibung() {
        return String.format("%s:%s", interpret, titel);
    }

}
