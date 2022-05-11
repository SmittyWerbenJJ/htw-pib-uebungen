/**
 * uebung 15 aufgbabe 2 - crunch operation top level
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 10.05.2022
 */
public class NumberCruncherTopLevel extends NumberCruncher {
    /**
     * konstruktor
     * 
     * @param array das float array
     */
    public NumberCruncherTopLevel(float[] array) {
        super(array);
        declareOperations();
    }

    public float[] getNumbers() {
        return array;
    }

    /**
     * ausfuehrung der crunch operation
     */
    public void crunch(String[] operations) {
        for (String string : operations) {
            for (CrunchOperation op : this.operations) {
                if (op.getName().equals(string.toLowerCase())) {
                    op.crunch(array);
                }
            }
        }
    }

    @Override
    protected void declareOperations() {
        operations = new CrunchOperation[] {
                new Sum(), new Swirl(), new Divide(), new Subtract(), new Average()
        };
    }

    @Override
    public CrunchOperation[] getOperations() {
        return operations;
    }
}