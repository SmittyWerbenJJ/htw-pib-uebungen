import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * uebung 15 aufgbabe 2 - crunch operation anonym
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 10.05.2022
 */
public class NumberCruncherAnonym extends NumberCruncher {
    public interface OperationAnonym {
        public void crunch();

        public String getName();
    }

    public float[] array;
    public OperationAnonym[] operationsAnon;

    @Override
    protected void declareOperations() {
        operationsAnon = new OperationAnonym[] {
                Sum(), Swirl(), Divide(), Subtract(), Average()
        };
    }

    @Override
    public CrunchOperation[] getOperations() {
        ArrayList<CrunchOperation> ops = new ArrayList<CrunchOperation>();
        for (OperationAnonym opAnon : operationsAnon) {
            ops.add(new CrunchOperation() {

                @Override
                public void crunch(float[] values) {
                    opAnon.crunch();
                }

                @Override
                public String getName() {
                    return opAnon.getName();
                }
            });
        }
        return ops.toArray(new CrunchOperation[ops.size()]);
    }

    public float[] getNumbers() {
        return array;
    }

    public NumberCruncherAnonym(float[] array) {
        super(array);
        this.array = array;
        declareOperations();
    }

    public void crunch(String[] operations) {
        for (String string : operations) {
            for (OperationAnonym op : operationsAnon) {
                if (op.getName().equals(string.toLowerCase())) {
                    op.crunch();
                }
            }
        }
    }

    /**
     * Führt n zufällige Vertauschungen der Datenfelder durch; n ist durch die Länge
     * des float-Arrays gegeben
     */
    private OperationAnonym Swirl() {
        return new OperationAnonym() {
            @Override
            public String getName() {
                return "swirl";
            }

            @Override
            public void crunch() {
                int n = array.length;
                final Random rand = new Random();
                for (int i = 0; i < n; i++) {
                    int index1 = rand.nextInt(n - 1);
                    int index2 = rand.nextInt(n - 1);
                    float buffer = array[index2];
                    array[index1] = array[index2];
                    array[index2] = buffer;
                }
            }
        };
    }

    /**
     * Teilt die n/2 größten Werte im Array durch die n/2 Kleinsten und speichert
     * den neuen Wert im Datenfeld des jeweils größeren Wertes. D.h. der größte Wert
     * wird durch den Kleinsten geteilt. Der Zweitgrößte durch den Zweitkleinsten
     * usw.
     */
    private OperationAnonym Divide() {
        return new OperationAnonym() {
            @Override
            public String getName() {
                return "divide";
            }

            @Override
            public void crunch() {
                Arrays.sort(array);
                for (int i = 0; i < array.length; i++) {
                    array[array.length - 1 - i] /= array[i];
                }
            }

        };
    }

    /**
     * SUBTRAHIERT die Elemente des Arrays paarweise von links nach rechts auf und
     * speichert den neuen Wert in dem jeweils rechten Datenfeld. D.h.: a[1] = a[0]
     * - a[1]; a[2] = a[1] - a[2]; usw.
     */
    private OperationAnonym Subtract() {
        return new OperationAnonym() {
            @Override
            public String getName() {
                return "subtract";
            }

            @Override
            public void crunch() {
                for (int i = 0; i < array.length - 1; i++) {
                    array[i + 1] = array[i] - array[i + 1];
                }
            }
        };
    }

    /**
     * Bestimmt den Durchschnitt aller Werte im Array und speichert den
     * Durchschnittswert im Datenfeld mit dem größten Wert.
     */
    private OperationAnonym Average() {
        return new OperationAnonym() {
            @Override
            public String getName() {
                return "average";
            }

            @Override
            public void crunch() {
                int indexOfMax = 0;
                float max = 0.0F;
                float sum = 0.0f;
                for (int i = 0; i < array.length; i++) {
                    sum += array[i];
                    if (array[i] > max) {
                        indexOfMax = i;
                        max = array[i];
                    }
                }
                array[indexOfMax] = sum / array.length;
            }
        };
    }

    /**
     * ADDIERT die Elemente des Arrays paarweise von links nach rechts auf und
     * speichert den neuen Wert in dem jeweils rechten Datenfeld. D.h.: a[1] = a[0]
     * + a[1]; a[2] = a[1] + a[2]; usw.
     */
    private OperationAnonym Sum() {
        return new OperationAnonym() {
            @Override
            public String getName() {
                return "sum";
            }

            @Override
            public void crunch() {
                for (int i = 0; i < array.length - 1; i++) {
                    array[i + 1] = array[i] + array[i + 1];
                }
            }
        };
    }
}