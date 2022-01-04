import static org.junit.Assert.*;
import org.junit.*;

/**
 * Die Test-Klasse testArrayFunctions.
 *
 * @author  (Raphael Kimbula & Siyamend Bozkurt)
 * @Version 04.01.2022
 */
public class testArrayFunctions
{
    public double delta;
    public double[] mittelwertTestWerte1;
    public double[] mittelwertTestWerte2;
    
    @Before
    public void init(){
        delta = 1E-7;
        mittelwertTestWerte1 = new double [] {5,5,5};
        mittelwertTestWerte2=new double [] { 5,12.5,12.5,40,5};  
        
    }
    
    @Test
    public void test_ArrayFunctionsBerechneMittelwert_erwarted_5_mit_Testwerten1_gesamt15() {
        double [] messwerte = mittelwertTestWerte1;        
        Mittelwert derMittelWert = ArrayFunctions.berechneMittelwert(messwerte);
        double erwartet = 5;
        double erhalten = derMittelWert.getAverage();                
        assertEquals(erwartet,erhalten,delta);
    }
    
    @Test
    public void test_ArrayFunctionsBerechneMittelwert_erwarted_15_mit_Testwerten2_gesamt75() {        
        double [] messwerte = mittelwertTestWerte2;
        Mittelwert derMittelWert = ArrayFunctions.berechneMittelwert(messwerte);
        double erwartet = 15;
        double erhalten = derMittelWert.getAverage();                
        assertEquals(erwartet,erhalten,delta);
    }
    
    @Test

    public void test_ArrayFunctionsCalculateFurthestValueFromArray_erwartet_1_mit_Werten_1bis9_TestWert8
    () {        
        double [] messwerte = new double[] {1,2,3,4,5,6,7,8,9};
        double TestWert = 8;
        double erwartet = 1;
        double erhalten = ArrayFunctions.calculateFurthestValue(messwerte,TestWert);                
        assertEquals(erwartet,erhalten,delta);
    }
    @Test
    public void test_ArrayFunctionsCalculateFurthestValueFromArray_erwartet_9_mit_Werten_1bis9_TestWert2
    () {        
        double [] messwerte = new double[] {1,2,3,4,5,6,7,8,9};
        double TestWert = 2;
        double erwartet = 9;
        double erhalten = ArrayFunctions.calculateFurthestValue(messwerte,TestWert);                
        assertEquals(erwartet,erhalten,delta);
    }

    @Test
     public void test_ArrayFunctionsCalculateClosestValueFromArray_erwartet_9_mit_Werten_1bis9_TestWert_8Komma7(){
        double [] messwerte = new double[] {1,2,3,4,5,6,7,8,9};
        double TestWert = 8.7;
        double erwartet = 9;
        double erhalten = ArrayFunctions.calculateClosestValueFromArray(messwerte,TestWert);                
        assertEquals(erwartet,erhalten,delta);

    }
    
    @Test
     public void test_ArrayFunctionsCalculateClosestValueFromArray_erwartet_1_mit_Werten_1bis9_TestWert_1Komma3(){
        double [] messwerte = new double[] {1,2,3,4,5,6,7,8,9};
        double TestWert = 1.3;
        double erwartet = 1;
        double erhalten = ArrayFunctions.calculateClosestValueFromArray(messwerte,TestWert);                
        assertEquals(erwartet,erhalten,delta);

    }
    
  
    
    @Test
    public void test_Mittelwert_Konstruktor_mit_avg_closest_furthest(){
        double avg = 1;
        double closest = 2;
        double furthest = 3;
        Mittelwert derMittelWert = new Mittelwert(avg,closest,furthest);
        
        assertEquals(avg,derMittelWert.getAverage(),delta);
        assertEquals(closest,derMittelWert.getClosest(),delta);
        assertEquals(furthest,derMittelWert.getFurthest(),delta);
        
    }

        @Test
    public void test_StringAuswerten_mit_3_UpperCaseStrings__erwartet_3(){
        String[] strings = new String[] {"MY","NAME","JEFF"};
        int erhalten=ArrayFunctions.stringsAuswerten(strings);
        int erwartet=3;
        assertEquals(erhalten,erwartet);
    }
        @Test
    public void test_StringAuswerten_mit_3_LoweerCaseStrings_erwartet_3(){
        String[] strings = new String[] {"my","name","jeff"};
        int erhalten=ArrayFunctions.stringsAuswerten(strings);
        int erwartet=3;
        assertEquals(erhalten,erwartet);
        
    }
    @Test
    public void test_StringAuswerten_mit_mixedCaseStrings_erwartet_0(){
        String[] strings = new String[] {"My","Name","Jeff"};
        int erhalten=ArrayFunctions.stringsAuswerten(strings);
        int erwartet=0;
        assertEquals(erhalten,erwartet);
        
    }
    
    @Test
    public void test_StringAuswerten_mit_1Lower_1Upper_1mixed_CaseStrings_erwartet2(){
         String[] strings = new String[] {"my","Name","JEFF"};
        int erhalten=ArrayFunctions.stringsAuswerten(strings);
        int erwartet=2;
        assertEquals(erhalten,erwartet);
        
    }
}


