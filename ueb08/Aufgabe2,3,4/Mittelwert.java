/**
 * Class for Saving a Average Value
 * 
 * @author  (Raphael Kimbula & Siyamend Bozkurt)
 * @Version 04.01.2022
 */
public class Mittelwert {
    private double average;
    private double furthest;
    private double closest;

    public Mittelwert(double average, double closest, double furthest) {
        this.setAverage(average);
        this.setClosest(closest);
        this.setFurthest(furthest);
    }
    
    public String toString() {
        
        return String.format("Closest: %.2f - Furthest %.2f - Average %.2f",this.getClosest(),this.getFurthest(),this.getAverage());
    }

    public double getClosest() {
        return closest;
    }

    public void setClosest(double closest) {
        this.closest = closest;
    }

    public double getFurthest() {
        return furthest;
    }

    public void setFurthest(double furthest) {
        this.furthest = furthest;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
