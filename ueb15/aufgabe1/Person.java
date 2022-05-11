
/**
 * Person Klasse
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 26.01.2022
 */
public class Person {

    private static final String NACHNAME_KANN_NICHT_LEER_SEIN = "Nachname kann nicht Leer sein";
    private static final String VORNAME_KANN_NICHT_LEER_SEIN = "Vorname kann nicht Leer sein";
    private static final String VOR_NACHNAME_KANN_NICHT_NULL_SEIN = "Vor/Nachname kann nicht NULL sein";

    private String vorName;
    private String nachName;

    private Person() {
    };

    public Person(String vorname, String nachname) {
        if (vorname == null || nachname == null) {
            throw new IllegalArgumentException(VOR_NACHNAME_KANN_NICHT_NULL_SEIN);
        }
        setVorname(vorname);
        setNachname(nachname);
    }

    public String getVorname() {
        return vorName;
    }

    public String getNachname() {
        return nachName;
    }

    public void setVorname(String vorName) {
        if (vorName.isBlank()) {
            throw new IllegalArgumentException(VORNAME_KANN_NICHT_LEER_SEIN);
        }
        this.vorName = vorName;
    }

    public void setNachname(String nachName) {
        if (nachName.isBlank()) {
            throw new IllegalArgumentException(NACHNAME_KANN_NICHT_LEER_SEIN);
        }
        this.nachName = nachName;
    }

    public String toString() {
        return String.format("Vorname: %s, Nachname: %s", this.vorName, this.nachName);
    }

}