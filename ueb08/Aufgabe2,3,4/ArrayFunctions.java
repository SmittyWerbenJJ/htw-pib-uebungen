import java.util.*;

/***
 * Aufgabe 2 ArrayFunktions
 * 
 * @author  (Raphael Kimbula & Siyamend Bozkurt)
 * @Version 04.01.2022
 */
public class ArrayFunctions {

    public static void main(String[] args) {
        System.out.println(berechneMittelwert(new double[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        
        System.out.println(stringsAuswerten(new String[] { "HTW", "saarland", " Saarbruecken" }));
        System.out.println(
                stringsAuswerten(new String[] { "HTW", "saarland", " Saarbruecken", "MERZIG", "UNIversitaet" }));
    }

    private ArrayFunctions() { };
    
    /**
     * calculates the average value given an array of values
     * 
     * @param messwerte
     * @return
     */
    public static Mittelwert berechneMittelwert(double[] messwerte) {
        if (messwerte.length == 0) {
            throw new IllegalArgumentException("No Values Provided!");
        }

        double sumOfValues = 0.0;
        double avgValue = 0d;
        double closestValue = 0d;
        double furthest = 0d;        

        // sort the array
        Arrays.sort(messwerte);

        // calc: avg value
        for (double d : messwerte) {
            sumOfValues += d;
        }
        avgValue = sumOfValues / messwerte.length;

        furthest = calculateFurthestValue(messwerte, avgValue);
        closestValue = calculateClosestValueFromArray(messwerte, avgValue);

        Mittelwert newAverage = new Mittelwert(avgValue, closestValue, furthest);

        return newAverage;
    }

    /**
     * determine the furthest value from an array of values, given a predefinedvalue
     * 
     * @param messwerte
     * @param avgValue
     * @return
     */
    public static double calculateFurthestValue(double[] messwerte, double avgValue) {        
        // give furthes value from avg
        double max1 = messwerte[0] - avgValue;
        double max2 = messwerte[messwerte.length - 1] - avgValue;
        
        Arrays.sort(messwerte);
        double furthest=avgValue;
        
        double max=messwerte[messwerte.length-1];
        double min=messwerte[0];
        
       /* for(double wert : messwerte){
            if(wert>avgValue){
                avgValue=wert;
            }
        }*/
        //count left
        int stepsLeft=0;
        for(int i=0;i<messwerte.length;i++){
            if(messwerte[i]<avgValue){
                stepsLeft++;
            }else{
                break;
            }
        }
        //Count right
        int stepsRight=0;
        for(int i=messwerte.length-1;i>avgValue;i--){
            if(messwerte[i]>avgValue){
                stepsRight++;
            }else{
                break;
            }
        }
        if(stepsLeft>stepsRight){
            return messwerte[0];
        }else{
            return messwerte[messwerte.length-1];
        }
    }

    /**
     * Calculate the closest to a target off of array values
     * 
     * @param values
     * @param target
     * @return
     */
    public static double calculateClosestValueFromArray(double values[], double target) {
        // avg value's approx index in sorted-list && closest value
        double closestValue = 0;
        int approximatedIndex=0;
        int n= values.length;
        
        if(target<=values[0]){
         return values[0];   
        }
        
        if(target>=values[n-1]){
            return values[n-1];
        }
        
        for (int i = 0; i < n; i++) {
            if (values[i] > target) {
                approximatedIndex = i;
                break;
            }
        }
        double roundedValue = target-Math.floor(target);
        if(roundedValue<0.5){
            roundedValue=Math.floor(target);
        }else{
            roundedValue=Math.ceil(target);
        }
        
        if(roundedValue == values[approximatedIndex-1]){
            return values[approximatedIndex-1];
        }
        else{
            return values[approximatedIndex];
        }
        
        
    }

    // -------------------------------------------------
    // Aufgabe 3
    // -------------------------------------------------

    public static int stringsAuswerten(String[] strings) {
        // int upperAndLowerCaseStrings = strings.length;
        int hits = 0;
        int upperCases;
        int lowerCases;        

        for (String str : strings) {
            upperCases = 0;
            lowerCases = 0;            
            str = str.trim();
            
            for (char c : str.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    upperCases++;
                } else if (Character.isLowerCase(c)) {
                    lowerCases++;
                }
                if (upperCases == str.length() || lowerCases == str.length())
                    hits++;
            }
        }

        /*
         * 
         * for (String str : strings) { upperCases = 0; lowerCases = 0; CharCount = 0;
         * 
         * for (char c : str.toCharArray()) { if (Character.isUpperCase(c)) {
         * upperCases++; } else if (Character.isLowerCase(c)) { lowerCases++; }
         * CharCount++; } if ((upperCases == CharCount || lowerCases == CharCount) ==
         * false) { upperAndLowerCaseStrings--; } }
         */
        return hits;
    }

}
