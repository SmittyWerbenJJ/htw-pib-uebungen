
/**
 * uebung 15 aufgbabe 2 - crunch operation top level - divide
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 10.05.2022
 */
import java.util.Arrays;

public class Divide implements CrunchOperation {

    @Override
    public void crunch(float[] values) {
        Arrays.sort(values);

        for (int i = 0; i < values.length; i++) {
            int indexGreatestValue = values.length - 1 - i;
            values[indexGreatestValue] = values[indexGreatestValue] / values[i];
        }
    }

    @Override
    public String getName() {
        return "divide";
    }
}
