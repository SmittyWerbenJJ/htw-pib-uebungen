/**
 * Queue-Interface der uebung 10
 * 
 * @author Raphale Kimbula & Siyamend Bozkurt
 * @version 24.01.2022
 */
public interface Queue {

    /**
     * Fuegt ein Objekt am Ende der Warteschlange hinzu
     */
    public void addLast(Object o);

    /**
     * entferne das erste Element und gibt eine Referenz
     */
    public Object removeFirst();

    /**
     * Das i−t e Element z u r u e c k g e b e n
     * 
     * @param i der index
     * @return das element
     */
    public Object get(int i);

    /**
     * Tes ten , ob schon Elemen te e i n g e f u e g t wurden
     * 
     * @return
     */
    public boolean empty();

    /**
     * Tes ten , ob noch Elemen te e i n f u e g b a r s i n d
     * 
     */
    public boolean full();

    /**
     * Anzahl e i n g e f u e g t e r Elemen te *
     */
    public int size();

    public void updateQueue();
}