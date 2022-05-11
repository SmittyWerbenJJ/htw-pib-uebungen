 


import java.util.Scanner;

/**
 * Dialog der uebung 10
 * 
 * @since 08.05.2022 - Ergaenuzungen fur uebung 15: menue-punkte fuer
 *        lexikalisch kleinste vorname
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 08.05.2022
 */
public class Dialog {
    Scanner input;
    IQueue queue;

    public final int MENU_AUSWAHL_Q_ENQUEUE = 2;
    public final int MENU_AUSWAHL_Q_DEQUEUE = 3;
    public final int MENU_AUSWAHL_Q_PRINT_ELEMENT = 4;
    public final int MENU_AUSWAHL_Q_DISPLAY_SIZE = 5;
    public final int MENU_AUSWAHL_Q_CAN_INSERT = 6;
    public final int MENU_AUSWAHL_Q_IS_EMPTY = 7;
    public final int MENU_AUSWAHL_Q_SHOW_SMALLEST = 8;
    public final int MENU_AUSWAHL_Q_SHOW_ALL = 9;

    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        dialog.queue = new PersonQueue(10);
        for (int i = 10; i > 0; i--) {
            dialog.queue.addLast(new Person("Max " + i, "Mustermann " + i));
        }
        dialog.Start();

    }

    /**
     * konstruktor des dialogs
     */
    public Dialog() {
        queue = null;
        input = new Scanner(System.in);
    }

    /**
     * Der Dialog wird hier angezeigt
     */
    public void Start() {
        try {
            Hauptmenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Programm wird Beendet ...");
    }

    /**
     * das hauptmenu des dialogs
     */
    void Hauptmenu() {
        System.out.println(String.join("\n", "\nHauptMenu", "0 --> Programm ende", "1 --> Queue Erstellen"));
        if (queue != null) {
            System.out.println(String.join("\n", MENU_AUSWAHL_Q_ENQUEUE + " --> Q Element Am ende einfügen",
                    MENU_AUSWAHL_Q_DEQUEUE + " --> Q Element am anfang löschen",
                    MENU_AUSWAHL_Q_PRINT_ELEMENT + " --> Q Element ausgeben mit index",
                    MENU_AUSWAHL_Q_DISPLAY_SIZE + " --> Q Größe anzeigen",
                    MENU_AUSWAHL_Q_CAN_INSERT + " --> Freier platz in Q vorhanden?",
                    MENU_AUSWAHL_Q_IS_EMPTY + " --> Ist Q leer?",
                    MENU_AUSWAHL_Q_SHOW_SMALLEST + " --> Person mit Lexikalisch kleinstem Vornamen?",
                    MENU_AUSWAHL_Q_SHOW_ALL + " --> Alle elemente der Q anzeigen?"));
        }
        int auswahl = einlesenInt("--> ");

        if (queue == null) {
            switch (auswahl) {
                case 0:
                    return;
                case 1:
                    showMenuQueueErstellen();
                    break;
                default:
                    System.out.println("Falsche Eingabe!");
            }
        } else {
            switch (auswahl) {
                case 0:
                    return;
                case 1:
                    showMenuQueueErstellen();
                    break;
                case MENU_AUSWAHL_Q_ENQUEUE:
                    showMenuElementEinfuegen();
                    break;
                case MENU_AUSWAHL_Q_DEQUEUE:
                    showMenuelementEntfernen();
                    break;
                case MENU_AUSWAHL_Q_PRINT_ELEMENT:
                    showMenuelementAusgeben();
                    break;
                case MENU_AUSWAHL_Q_DISPLAY_SIZE:
                    anzeigenQGroesse();
                    break;
                case MENU_AUSWAHL_Q_CAN_INSERT:
                    anzeigenQFreiePlaetze();
                    break;
                case MENU_AUSWAHL_Q_SHOW_SMALLEST:
                    anzeigenSmallest();
                    break;
                case MENU_AUSWAHL_Q_IS_EMPTY:
                    printIstQueueLeer();
                    break;
                case MENU_AUSWAHL_Q_SHOW_ALL:
                    print(queue);
                    break;
                default:
                    println("Falsche Eingabe!");
            }
        }
        Hauptmenu();
    }

    /**
     * Ausgabe der Person mit dem Lexikalisch kleinstem Vornamen
     */
    private void anzeigenSmallest() {
        if (queue instanceof PersonQueue) {
            ((PersonQueue) queue).smallest();
        } else {
            System.out.println("Diese Funktion geht nur bei einer Personen-Warteschlange");
            return;
        }

    }

    /**
     * anzeigen ob die warteschlange leer ist
     */
    private void printIstQueueLeer() {
        if (queue.empty()) {
            println("Warteschlange leer");
        } else {
            println("Warteschlange NICHT leer");
        }
    }

    /**
     * menu zur erstellung einer queue
     */
    void showMenuQueueErstellen() {
        boolean wurdeQErstellt = false;
        int QGroesse = 0;
        while (wurdeQErstellt == false) {
            try {
                System.out.print("welche Queue soll erstellt werden(string/person): ");
                String eingabe = einlesenString().trim().toLowerCase();

                switch (eingabe) {
                    case "string":
                        QGroesse = einlesenInt("Queue groesse eingeben: ");
                        queue = new StringQueue(QGroesse);
                        wurdeQErstellt = true;
                        break;
                    case "person":
                        QGroesse = einlesenInt("Queue groesse eingeben: ");
                        queue = new PersonQueue(QGroesse);
                        wurdeQErstellt = true;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        println("Es wurde eine Q erstellt. Groesse:" + QGroesse);
    }

    /**
     * menu zum einfuegen eines elements in die queue
     */
    void showMenuElementEinfuegen() {
        println("Neues Element in Queue hinzufuegen");

        try {
            if (queue instanceof PersonQueue) {
                queue.addLast(EinlesenPersonObjekt());
            }
            if (queue instanceof StringQueue) {
                queue.addLast(EinlesenStringObjekt());
            }
        } catch (Exception e) {
            println(e.getMessage());
        }
    }

    /**
     * einlesen eines strings fur die StringQueue
     * 
     * @return der eingelesenen string
     */
    private String EinlesenStringObjekt() {
        String derString = "";

        while (derString.isBlank() == true) {
            System.out.print("Text-Objekt (string): ");
            derString = einlesenString().trim();
        }
        return derString;
    }

    /**
     * einlesen einer Person fur die PersonQueue
     * 
     * @return die eingelesene person
     */
    private Person EinlesenPersonObjekt() {
        Person neuePerson = null;
        String vorName;
        String nachName;

        System.out.print("Person - Vorname: ");
        vorName = einlesenString().trim();
        System.out.print("Person - Nachname: ");
        nachName = einlesenString().trim();

        try {
            neuePerson = new Person(vorName, nachName);
        } catch (Exception e) {
            println(e.getMessage());
            EinlesenPersonObjekt();
        }
        return neuePerson;
    }

    /**
     * menu zur entfernen eines elements in der queue
     */
    void showMenuelementEntfernen() {
        Object dasErsteElement = queue.removeFirst();
        if (dasErsteElement == null) {
            println("Queue ist bereits Leer. Es wurde nichts entfernt");
            return;
        }

        println(String.format("Das Erste Element wurde Gelöscht. 1. Element: < %s >.", dasErsteElement.toString()));
    }

    /**
     * menu zur ausgabe eines elementes aus der queue
     */
    void showMenuelementAusgeben() {
        System.out.println("Queue Element ausgeben");
        int index = einlesenInt("QueueIndex zur ausgabe auswählen(int): ");
        try {
            Object qObject = queue.get(index);
            System.out.println(qObject.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * ausgeben der queue groesse in der konsole
     */
    void anzeigenQGroesse() {
        println(String.format("Die Warteschlange hat eine groesse von: %d ", queue.size()));
    }

    /**
     * ausgeben ob es noch freuie plaetze in der queue gibt
     */
    void anzeigenQFreiePlaetze() {
        if (queue.full()) {
            println("In der Queue gibt es noch Freie Plaetze");
        } else {
            println("In der Queue gibt KEINE Freie Plaetze");
        }
    }

    /**
     * ausgeben aller queue elemente innerhalt einer queue
     * 
     * @param q die zu durchlaudende queue
     */
    public void print(IQueue q) {
        int qSize = q.size();
        println("Die elemente der Queue: ");

        if (qSize == 0) {
            println("--- WarteSchlange ist Leer ---");
        } else {

            for (int i = 0; i < q.size(); i++) {
                println(String.format("%d --| %s", i + 1, q.get(i).toString()));
            }
        }

    }

    /**
     * einlesen eines strings aus der konsole
     * 
     * @return (input.nextline)
     */
    String einlesenString() {
        return input.nextLine();
    }

    /**
     * einlesen eines integers aus der konsole
     * 
     * @param prompt eine beiliegende meldung
     * @return der eingelesene string
     */
    int einlesenInt(String prompt) {
        System.out.print(prompt);
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.print(prompt);
        }
        int i = input.nextInt();
        input.nextLine();
        return i;
    }

    /**
     * ausgeben eines strings in der konsole mit anschliessendem zeilen- u,bruch
     * 
     * @param str der ausgzugebene text
     */
    void println(String str) {
        System.out.println(str);
    }

}
