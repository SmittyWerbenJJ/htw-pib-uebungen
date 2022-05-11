/**
 * uebung 15 aufgbabe 2 - crunch operation top level - sum
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 10.05.2022
 */
public class Sum implements CrunchOperation {

    @Override
    public void crunch(float[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            values[i + 1] = values[i] + values[i + 1];
        }
    }

    @Override
    public String getName() {
        return "sum";
    }
}