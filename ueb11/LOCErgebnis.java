
/**
 * Container zur zwischenspeichern von attributen bei Lines-of-Code (LOC)
 * ergebnissen
 */
public class LOCErgebnis {
    private String dateiName;
    private int anzahlLOC;

    public LOCErgebnis() {
        anzahlLOC = 0;
        dateiName = "";
    }

    public void addAnzahlLOC() {
        anzahlLOC++;
    }

    public void removeAnzahlLOC() {
        if (anzahlLOC > 0) {
            anzahlLOC--;
        }
    }

    public int getAnzahlLOC() {
        return anzahlLOC;
    }

    public void setDateiName(String neuerName) {
        dateiName = neuerName;
    }

    public String getDateiName() {
        return dateiName;
    }
}
