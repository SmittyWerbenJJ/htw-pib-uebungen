/**
 * uebung 15 aufgbabe 2 - crunch operation top level - average
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 10.05.2022
 */
public class Average implements CrunchOperation {

    @Override
    public void crunch(float[] values) {
        int indexOfMaxValue = 0;
        float max = 0.0F;
        float sum = 0.0f;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            if (values[i] > max) {
                indexOfMaxValue = i;
                max = values[i];
            }
        }
        values[indexOfMaxValue] = sum / values.length;
    }

    @Override
    public String getName() {
        return "average";
    }
}
