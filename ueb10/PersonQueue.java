import java.util.*;

/**
 * PersonQueue der uebung 10
 * 
 * @author Raphale Kimbula & Siyamend Bozkurt
 * @version 24.01.2022
 * 
 */
public class PersonQueue implements Queue {

    private static final String FEHLER_INDEX_IST_NICHT_IN_Q = "Index ist nicht in Q";
    private static final String FEHLER_PERSON_Q_IST_LEER = "Person Q ist leer";
    private static final String FEHLER_QUEUE_IST_VOLL_PERSON_KANN_NICHT_HINZUGEFUEGT_WERDEN = "Queue ist Voll, Person kann nicht hinzugefuegt werden!";
    private static final String FEHLER_OBJEKT_IST_KEINE_INSTANZ_VON_PERSON = "Objekt ist keine Instanz von  <Person>";
    private static final String FEHLER_OBJEKT_IST_NULL = "Objekt ist null!";
    private static final String FEHLER_BITTE_QUEUE_GROESSE_AB_1_EINGEBEN = "Bitte Queue Groesse ab 1 eingeben";

    private Person[] persons;
    private int QSize;
    private int vorne;
    private int hinten;

    private PersonQueue() {
    };

    public PersonQueue(int queueGroesse) {
        if (queueGroesse < 1) {
            throw new IllegalArgumentException(FEHLER_BITTE_QUEUE_GROESSE_AB_1_EINGEBEN);
        }
        persons = new Person[queueGroesse];
        QSize = queueGroesse;
        vorne = 0;
        hinten = 0;
    }

    /**
     * Fuegt das Objekt als string der Warteschlange hinzu
     */
    @Override
    public void addLast(Object o) {
        if (o == null) {
            throw new IllegalArgumentException(FEHLER_OBJEKT_IST_NULL);
        }
        if (o instanceof Person == false) {
            throw new IllegalArgumentException(FEHLER_OBJEKT_IST_KEINE_INSTANZ_VON_PERSON);
        }

        if (hinten == QSize) {
            throw new IllegalStateException(FEHLER_QUEUE_IST_VOLL_PERSON_KANN_NICHT_HINZUGEFUEGT_WERDEN);
        }

        persons[hinten] = (Person) o;
        hinten++;
    }

    /**
     * entfernt das erste element in der warteschlange.
     * anschliessend einruecken aller elemente
     */
    @Override
    public Object removeFirst() {
        if (vorne == hinten) {
            throw new NoSuchElementException(FEHLER_PERSON_Q_IST_LEER);
        }
        Object erstePerson = persons[vorne];
        persons[vorne] = null;
        updateQueue();
        return erstePerson;
    }

    /**
     * erhalte das element an einer bestimmten stelle
     * 
     * @param i der index des zu erhaltenden erlements
     */
    @Override
    public Object get(int i) {
        if (i < vorne) {
            throw new IndexOutOfBoundsException(FEHLER_INDEX_IST_NICHT_IN_Q);
        }
        if (i > hinten) {
            throw new IndexOutOfBoundsException(FEHLER_INDEX_IST_NICHT_IN_Q);
        }
        return persons[i];
    }

    /**
     * ist die Q leer?
     * 
     * @return true wenn leer, false wenn nciht
     */
    @Override
    public boolean empty() {
        return vorne == hinten;
    }

    /**
     * ist die Q voll?
     * 
     * @return true wenn voll /false wenn nicht
     */
    @Override
    public boolean full() {
        return !(hinten == QSize);
    }

    /**
     * gibt die anzahl an belegten stellen in der Q zuruck.
     * 
     * @return anzahl elemente in der Q die nicht {@code NULL} sind
     */
    @Override
    public int size() {
        int occupiedQSlots = 0;
        for (Person p : persons) {
            if (p != null) {
                occupiedQSlots++;
            }
        }
        return occupiedQSlots;
    }

    /**
     * einruecken aller elemente in der Q
     */
    @Override
    public void updateQueue() {
        for (int i = 0; i < persons.length - 1; i++) {
            if (persons[i] == null) {
                persons[i] = persons[i + 1];
                persons[i + 1] = null;
            }
        }
    }
}