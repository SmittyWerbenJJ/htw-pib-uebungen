import java.util.Objects;

/**
 * ueb09
 * Arikel Klasse - Video
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 2021.01.29
 */
public class Video extends Artikel {

    private int spieldauer;
    private int erscheinungsjahr;
    private String titel;

    private static final String ARTIKEL_ART = "MEDIEN";
    private static final String FEHLER_ERSCHEINUNGSJAHR = "Erscheinungsnjahr muss zwischen 1900 und 2022 sein";
    private static final String FEHLER_SPIELDAUER_KLEINER_NULL = "Spieldauer muss >0 sein";
    private static final String FEHLER_LEERER_TITEL = "Titel darf nicht leer sein";

    public String getTitel() {
        return titel;
    }

    public int getSpieldauer() {
        return spieldauer;
    }

    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setTitel(String titel) {
        if (titel.isBlank()) {
            throw new IllegalArgumentException(FEHLER_LEERER_TITEL);
        }
        this.titel = titel;
    }

    public void setSpieldauer(int spieldauer) {
        if (!(spieldauer > 0)) {
            throw new IllegalArgumentException(FEHLER_SPIELDAUER_KLEINER_NULL);
        }
        this.spieldauer = spieldauer;
    }

    public void setErscheinungsJahr(int erscheinungsjahr) {
        if (!(erscheinungsjahr >= 1900 && erscheinungsjahr <= 2022)) {
            throw new IllegalArgumentException(FEHLER_ERSCHEINUNGSJAHR);
        }
        this.erscheinungsjahr = erscheinungsjahr;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Video)) {
            return false;
        }

        Video video = (Video) obj;
        return getArtikelNr() == video.getArtikelNr() &&
                erscheinungsjahr == video.getErscheinungsjahr() &&
                titel == video.getTitel() &&
                spieldauer == video.getSpieldauer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArtikelNr(), erscheinungsjahr, titel, spieldauer);
    }

    @Override
    public String getBeschreibung() {
        return String.format("%s", titel);
    }

    public Video(int artikelNr, int bestand, double preis, String titel, int spieldauer, int jahr) {
        super(artikelNr, ARTIKEL_ART, bestand, preis);
        this.setTitel(titel);
        this.setSpieldauer(spieldauer);
        this.setErscheinungsJahr(jahr);
    }

}
