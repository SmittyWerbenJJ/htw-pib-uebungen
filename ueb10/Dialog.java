import java.util.*;

/**
 * Dialog der uebung 10
 * 
 * @author Raphale Kimbula & Siyamend Bozkurt
 * @version 26.01.2022
 */
public class Dialog {
    Scanner input;
    Queue q;

    public static void main(String[] args) {
        Dialog derDialog = new Dialog();
        derDialog.Start();
    }

    /**
     * konstruktor des dialogs
     */
    public Dialog() {
        q = null;
        input = new Scanner(System.in);
    }

    /**
     * Der Dialog wird hier angezeigt
     */
    public void Start() {
        try {
            Hauptmenu();
        } catch (Exception e) {
            println(e.getMessage());
        }
        println("Programm wird Beendet ...");
    }

    /**
     * +
     * das hauptmenu des dialogs
     */
    void Hauptmenu() {
        println("\nHauptMenu");
        println("0 --> Programm ende");
        println("1 --> Queue Erstellen");
        if (q != null) {
            println("2 --> Q Element Am ende einfügen       ");
            println("3 --> Q Element am anfang löschen");
            println("4 --> Q Element ausgeben mit index");
            println("5 --> Q Größe anzeigen");
            println("6 --> Freier platz in Q vorhanden?");
            println("7 --> Ist Q leer?");
            println("8 --> Alle elemente der Q anzeigen?");
        }
        int auswahl = einlesenInt("--> ");

        if (q == null) {
            switch (auswahl) {
                case 0:
                    return;
                case 1:
                    showMenuQueueErstellen();
                    break;
                default:
                    println("Falsche Eingabe!");
            }
        } else {
            switch (auswahl) {
                case 0:
                    return;
                case 1:
                    showMenuQueueErstellen();
                    break;
                case 2:
                    showMenuElementEinfuegen();
                    break;
                case 3:
                    showMenuelementEntfernen();
                    break;
                case 4:
                    showMenuelementAusgeben();
                    break;
                case 5:
                    anzeigenQGroesse();
                    break;
                case 6:
                    anzeigenQFreiePlaetze();
                    break;
                case 7:
                    anzeigenEingefuegteElemente();
                    break;
                case 8:
                    print(q);
                    break;
                default:
                    println("Falsche Eingabe!");
            }
        }
        Hauptmenu();
    }

    /**
     * anzeigen ob die warteschlange leer ist
     */
    private void anzeigenEingefuegteElemente() {
        if (q.empty()) {
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
        String eingabe;
        while (wurdeQErstellt == false) {
            try {
                System.out.print("welche Queue soll erstellt werden(string/person): ");
                eingabe = einlesenString().trim().toLowerCase();

                switch (eingabe) {
                    case "string":
                        QGroesse = einlesenInt("Queue groesse eingeben: ");
                        q = new StringQueue(QGroesse);
                        wurdeQErstellt = true;
                        break;
                    case "person":
                        QGroesse = einlesenInt("Queue groesse eingeben: ");
                        q = new PersonQueue(QGroesse);
                        wurdeQErstellt = true;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                println(e.getMessage());
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

            if (q instanceof PersonQueue) {
                q.addLast(EinlesenPersonObjekt());
            } else if (q instanceof StringQueue) {
                q.addLast(EinlesenStringObjekt());
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
        String derString;
        derString = "";
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
        Object dasErsteElement = q.removeFirst();
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
        println("Queue Element ausgeben");
        int index = einlesenInt("QueueIndex zur ausgabe auswählen(int): ");
        index--;
        try {
            Object qObject = q.get(index);
            println(qObject.toString());
        } catch (Exception e) {
            println(e.getMessage());
        }

    }

    /**
     * ausgeben der queue groesse in der konsole
     */
    void anzeigenQGroesse() {
        println(String.format("Die Warteschlange hat eine groesse von: %d ", q.size()));
    }

    /**
     * ausgeben ob es noch freuie plaetze in der queue gibt
     */
    void anzeigenQFreiePlaetze() {
        if (q.full()) {
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
    public void print(Queue q) {
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
     * ausgeben eines strings in der konsole mit anschliessendem zeilen-
     * u,bruch
     * 
     * @param str der ausgzugebene text
     */
    void println(String str) {
        System.out.println(str);
    }

}
