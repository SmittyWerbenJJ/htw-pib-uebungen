/**
 * Ausnahme wenn eine Datei nicht Lesbar ist
 */
public class DateiNichtLesbarException extends Exception {
    public DateiNichtLesbarException(String nachricht) {
        super(nachricht);
    }
}