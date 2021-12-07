import static org.junit.Assert.*;
import org.junit.*;

/**
 * Tests der artikel klasse
 *
 * @author Raphael Kimbula & Siyamend Bozkurt
 * @version 0.2
 */
public class testArtikel {

    Artikel einArtikel;

    /*
     * 
     * korrekte Konstruktoren:
     * 
     */

    @Test
    public void erstelleArtikel_korrekte_NR_Art() {
        einArtikel = new Artikel(1234, "banane");
        assertNotNull(einArtikel);
    }

    @Test
    public void erstelleArtikel_korrekte_NR_Art_Bestand() {
        einArtikel = new Artikel(1234, "banane", 10);
        assertNotNull(einArtikel);
    }

    public void erstelleArtikel_korrekte_NR_Art_Bestand0() {
        einArtikel = new Artikel(1234, "banane", 0);
        assertNotNull(einArtikel);
    }

    @Test
    public void erstelleArtikel_korrekte_NR_Art_Bestand_Preis() {
        einArtikel = new Artikel(1234, "banane", 10, 69.69);
        assertNotNull(einArtikel);
    }

    /*
     * 
     * Fehlerhafte Kosntruktoren. Sollten Fehler Werfen:
     * 
     */

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsch_ArtikelNR_5stellig() {
        einArtikel = new Artikel(13234, "banane");

    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsch_ArtikelNR_4stelligNegativ() {
        einArtikel = new Artikel(-1234, "banane");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsch_ArtikelNR_3stellig() {
        einArtikel = new Artikel(123, "banane");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsch_ArtikelNR_4stellig0() {

        einArtikel = new Artikel(0100, "banane");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsch_Art1_leerstring() {
        einArtikel = new Artikel(1000, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsch_Art2_leerzeichen() {
        einArtikel = new Artikel(1000, " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsch_Bestand_negativ() {
        einArtikel = new Artikel(1000, "banane", -99999);
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsch_Preis_negativ() {
        einArtikel = new Artikel(1000, "banane", 1, -1.0);
    }

    /*
     * 
     * AbgangsBuchungen
     * 
     */

    @Test
    public void buche_abgang_korrekt_wenigerAlsImBestand() {
        int bestand = 10;
        Artikel einArtikel = new Artikel(1234, "banane", bestand);
        einArtikel.bucheAbgang(9);
        int sollBestand = 1;
        int istBestand = einArtikel.getBestand();
        assertEquals(sollBestand, istBestand);
    }

    @Test
    public void buche_abgang_korrekt_kompletterBestand() {
        int bestand = 10;
        Artikel einArtikel = new Artikel(1234, "banane", bestand);
        einArtikel.bucheAbgang(10);
        int sollBestand = 0;
        int istBestand = einArtikel.getBestand();
        assertEquals(sollBestand, istBestand);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buche_abgang_Falsch_mehrAlsVorhanden() {
        int bestand = 10;
        Artikel einArtikel = new Artikel(1234, "banane", bestand);
        einArtikel.bucheAbgang(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buche_abgang_Falsch_Negativer_Zugang() {
        int bestand = 10;
        Artikel einArtikel = new Artikel(1234, "banane", bestand);
        einArtikel.bucheAbgang(-1);
    }

    /*
     * 
     * ZugangsBuchungen
     * 
     */

    @Test
    public void test_Artikel_zugangsbuchung_korrekt_mit_ArtikelNR_1234_und_Zugang_positiv() {
        Artikel artikel1 = new Artikel(1234, "banane", 10.0d);
        artikel1.bucheZugang(10);
    }

    @Test
    public void test_Artikel_zugangsbuchung_korrekt_mit_ArtikelNR_1234_und_Zugang_0() {
        Artikel artikel1 = new Artikel(1234, "banane", 10);
        artikel1.bucheZugang(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Artikel_zugangsbuchung_Falsch_mit_ArtikelNR_1234_und_Zugang_negativ() {
        Artikel artikel1 = new Artikel(1234, "banane", 10.0d);
        artikel1.bucheZugang(-10);
    }

}
