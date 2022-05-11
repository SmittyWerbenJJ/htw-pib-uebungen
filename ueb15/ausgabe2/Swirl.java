import java.util.Random;

/**
 * uebung 15 aufgbabe 2 - crunch operation top level - swirl
 * 
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 10.05.2022
 */
public class Swirl implements CrunchOperation {

    @Override
    public void crunch(float[] values) {
        int n = values.length;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
            float buffer = values[b];
            values[a] = values[b];
            values[b] = buffer;
        }
    }

    @Override
    public String getName() {
        return "swirl";
    }

}
