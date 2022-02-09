/**
 * Ausnahme wenn eine Datei nicht Lesbar ist
 */
public class DateiExistiertNichtException extends Exception {
    public DateiExistiertNichtException(String nachricht) {
        super(nachricht);
    }
}