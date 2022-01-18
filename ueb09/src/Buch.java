import java.util.*;

/**
 * ueb09
 * Arikel Klasse - Buch
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 2021.01.29
 */
public class Buch extends Artikel {
    private String verlag;
    private String autor;
    private String titel;

    private static final String ARTIKEL_ART = "MEDIEN";
    private static final String FEHLER_AUTOR_LEER = "Autor muss vorhanden sein";
    private static final String FEHLER_VERLAG_LEER = "Verlag muss vorhanden sein";
    private static final String FEHLER_TITEL_LEER = "Titel darf nicht leer sein";

    public String getTitel() {
        return titel;
    }

    public String getverlag() {
        return verlag;
    }

    public String getAutor() {
        return autor;
    }

    public void setTitel(String titel) {
        if (titel.isBlank()) {
            throw new IllegalArgumentException(FEHLER_TITEL_LEER);
        }
        this.titel = titel;
    }

    public void setverlag(String verlag) {
        if (verlag.isBlank()) {
            throw new IllegalArgumentException(FEHLER_VERLAG_LEER);
        }
        this.verlag = verlag;
    }

    public void setAutor(String autor) {
        if (autor.isBlank()) {
            throw new IllegalArgumentException(FEHLER_AUTOR_LEER);
        }
        this.autor = autor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Buch)) {
            return false;
        }

        Buch buch = (Buch) obj;
        return getArtikelNr() == buch.getArtikelNr() &&
                verlag == buch.getverlag() &&
                autor == buch.getAutor() &&
                titel == buch.getTitel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtikelNr(), verlag, autor, titel);
    }

    @Override
    public String getBeschreibung() {
        return String.format("%s:%s", autor, titel);
    }

    /**
     * Konstruktor zum erstellen einer CD
     * 
     * @param artikelNr die ArtikelNummer
     * @param bestand   der Bestand
     * @param preis     der preis
     * @param autor     Der autor
     * @param titel     der Titel
     * @param verlag    der verlag dieses Buches
     */
    public Buch(int artikelNr, int bestand, double preis, String autor, String titel, String verlag) {
        super(artikelNr, ARTIKEL_ART, bestand, preis);
        this.setTitel(titel);
        this.setverlag(verlag);
        this.setAutor(autor);
    }

}
