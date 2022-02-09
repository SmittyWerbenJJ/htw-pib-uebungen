/**
 * Ausnahme wenn eine Datei keine Java Datei ist
 * 
 * @Author Raphael Kimbula & Siyamend Bozkurt
 * @version 06.02.2022
 * 
 */
public class DateiIstKeineJavaDateiException extends Exception {
    public DateiIstKeineJavaDateiException(String nachricht) {
        super(nachricht);
    }
}