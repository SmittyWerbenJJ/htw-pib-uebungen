/**
 * uebung 15 aufgbabe 2 - abstrakte klasse zum number crunchen hilfreiche und
 * erforderliche ueberschreibbare methoden
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 10.05.2022
 */
public abstract class NumberCruncher {
    protected float[] array;
    protected CrunchOperation[] operations;

    protected abstract void declareOperations();

    public abstract CrunchOperation[] getOperations();

    public NumberCruncher(final float[] array) {
        this.array = array;
    }

    public abstract void crunch(String[] operations);

    public abstract float[] getNumbers();

}
