import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * uebung 15 aufgbabe 2
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 10.05.2022
 */
public class Dialog {

    private static final String DIALOG_OPERATION_QUIT = "quit";
    private static final String DIALOG_OPERATION_SHOW = "show";
    private static final String DIALOG_OPERATION_OPERATION = "operation";
    private static final String DIALOG_OPERATION_FILL = "fill";

    private CrunchOperation[] alleCrunchOperationen;
    private ArrayList<String> userOperations;
    private ArrayList<String> alleDialogOperationen;

    private NumberCruncher cruncherAnonym;
    private NumberCruncher cruncherTopLevel;

    private float[] array;
    private Scanner scanner;

    /**
     * Dialog
     */
    public Dialog() {
        scanner = new Scanner(System.in);
        userOperations = new ArrayList<String>();
        alleDialogOperationen = new ArrayList<String>(Arrays.asList(DIALOG_OPERATION_FILL, DIALOG_OPERATION_OPERATION,
                DIALOG_OPERATION_SHOW, DIALOG_OPERATION_QUIT));
    }

    /**
     * Dialog - startpunkt
     */
    public void Start() {
        do {
            try {
                String operation = operationAuswahl();

                switch (operation) {
                case DIALOG_OPERATION_FILL:
                    array = Fill();
                    break;
                case DIALOG_OPERATION_OPERATION:
                    operation(array);
                    break;
                case DIALOG_OPERATION_SHOW:
                    show();
                    break;
                case DIALOG_OPERATION_QUIT:
                    quit();
                default:
                    System.out.println(String.format("operation '%s' gibt es nicht!! - Tipfehler?", operation));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            scanner.reset();
        } while (true);
    }

    /**
     * auswahl der operation vom benutzereingabe in konsole
     * 
     * @return
     */
    private String operationAuswahl() {
        StringBuffer sb = new StringBuffer();
        sb.append("Operation auswahl:\n");

        for (String operationString : alleDialogOperationen) {
            sb.append(String.format(" %s,", operationString));
        }
        sb.append(System.lineSeparator());
        sb.append("command: ");

        System.out.println(sb.toString());

        String eingabe = scanner.nextLine().trim().toLowerCase();
        return eingabe;
    }

    /**
     * fuellen des arrays automatisch oder manuell
     */
    private float[] Fill() {
        int anzahlElemente = 0;
        float[] array;
        String kwdAutomatik = "auto";
        String kwdManuell = "manuell";
        String fuellmethode = "";

        while (!(fuellmethode.equals(kwdAutomatik) || fuellmethode.equals(kwdManuell))) {
            System.out.print(String.format("\nfuellmethode auswahl(%s / %s): ", kwdAutomatik, kwdManuell));
            fuellmethode = scanner.nextLine().trim().toLowerCase();
        }

        while (anzahlElemente <= 0) {
            System.out.print("Array Groesse: ");
            try {
                anzahlElemente = Integer.parseInt(scanner.nextLine().trim().toLowerCase());
            } catch (Exception e) {
                System.out.println("---> BITTE eine Array Groesse (integer) eingeben <---");
            }
        }

        if (fuellmethode.equals(kwdAutomatik)) {
            System.out.println(String.format("Float array wird mit %d zufallszahlen gefuellt ...", anzahlElemente));
            array = new float[anzahlElemente];
            for (int i = 0; i < array.length; i++) {
                array[i] = new Random().nextFloat();
            }
        } else {
            System.out.println("Float zahlen eingeben (nicht erkannte zahlen werden mit 0.0 ersetzt!):");
            array = new float[anzahlElemente];
            for (int i = 0; i < array.length; i++) {
                System.out.print(String.format("\n(%d/%d): ", i, array.length - 1));
                try {
                    array[i] = Float.parseFloat(scanner.nextLine().trim().toLowerCase());
                } catch (Exception e) {
                    array[i] = 0F;
                }
            }
        }

        return array;
    }

    /**
     * eingabe der crunch operation durch benutzer in konsole
     */
    private void operation(float[] array) {

        String eingabe = "";
        while (!(eingabe.equals("anonym") || eingabe.equals("toplevel"))) {
            System.out.print("(anonym / toplevel): ");
            eingabe = scanner.nextLine();
        }

        System.out.println("verfuegbare operationen - zum abbrechen _ende_ eingeben: ");
        boolean crunchMitAnonym = eingabe.equals("anonym");
        if (crunchMitAnonym) {
            cruncherAnonym = new NumberCruncherAnonym(array);
            alleCrunchOperationen = cruncherAnonym.getOperations();
        } else {
            cruncherTopLevel = new NumberCruncherTopLevel(array);
            alleCrunchOperationen = cruncherTopLevel.getOperations();
        }

        for (var op : alleCrunchOperationen) {
            System.out.print(op.getName() + " ");
        }
        System.out.print(System.lineSeparator());
        while (!eingabe.equals("_ende_")) {
            System.out.print("auswahl: ");
            eingabe = scanner.nextLine();
            if (!eingabe.equals("_ende_")) {
                userOperations.add(eingabe);
            }

        }

        System.out.println("Operationen anwenden ...");
        if (crunchMitAnonym) {
            cruncherAnonym.crunch(userOperations.toArray(new String[userOperations.size()]));
        } else {
            cruncherTopLevel.crunch(userOperations.toArray(new String[userOperations.size()]));
        }
        System.out.println("---! Crunch Operationen wurden angewendet!---");
    }

    /**
     * Anzeigen des aktuellen Float arrays
     */
    private void show() {
        StringBuffer sb = new StringBuffer("\t");
        sb.append("[");
        for (float f : array) {
            sb.append(" ");
            sb.append(Float.toString(f));
            sb.append(" ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * programm beenden
     */
    private void quit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        Dialog app = new Dialog();
        app.Start();

    }
}