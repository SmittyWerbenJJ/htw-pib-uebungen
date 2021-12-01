package tests;

import src.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions.*;

/**
 * Die Test-Klasse
 *
 * @author Rapahel Kimbula
 * @version 0.1
 */
public class testArtikelMethoden {
    Artikel einArtikel;

    double TESTartikelPreis;
    int TestArtikelNr;
    int TestArtikelBestand;
    String TestArtikelArt;

    @Before
    public void erstelleArtikel() {

        TESTartikelPreis = 100;
        TestArtikelNr = 1234;
        TestArtikelBestand = 25;
        TestArtikelArt = "Banane";
        einArtikel = new Artikel(TestArtikelNr, TestArtikelArt, TestArtikelBestand, TESTartikelPreis);
    }

    @Test
    public void getArt_korrekte_Art() {
        assertEquals(einArtikel.getArt(), TestArtikelArt);
    }

    @Test
    public void getArtikelNr_korrekte_ArtikelNr() {
        assertEquals(einArtikel.getArtikelNr(), TestArtikelNr);
    }

    @Test
    public void setArtikelNr_korrekte_artNr() {
        einArtikel.setArtikelNr(TestArtikelNr);
        assertEquals(einArtikel.getArtikelNr(), TestArtikelNr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setArtikelNr_falsche_NR_nichtVierStellig_3stellig() {
        einArtikel.setArtikelNr(123);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setArtikelNr_falsche_NR_nichtVierStellig_5stellig() {
        einArtikel.setArtikelNr(12345);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setArtikelNr_falsche_NR_negativ() {
        einArtikel.setArtikelNr(-1234);
    }

    @Test
    public void setArt_korrekt() {
        einArtikel.setArt(TestArtikelArt);
        String erhalteneArt = einArtikel.getArt();
        assertEquals(erhalteneArt, TestArtikelArt);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setArt_falsche_leerstring() {
        einArtikel.setArt("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setArt_falsche_art_leerzeichen() {
        einArtikel.setArt(" ");
    }

    @Test
    public void getBestand_korrekter_Bestand() {
        assertEquals(einArtikel.getBestand(), TESTartikelPreis);
    }

    @Test
    public void buche_abgang_korrekt_wenigerAlsImBestand() {
        int erwarteterBestand = 1;
        einArtikel.bucheAbgang(TestArtikelBestand - 1);
        assertEquals(einArtikel.getBestand(), TestArtikelBestand-1);
    }

    @Test
    public void buche_abgang_korrekt_kompletterImBestand() {
        einArtikel.bucheAbgang(TestArtikelBestand);
        assertEquals(einArtikel.getBestand(), 0);
    }

    @Test
    public void buche_abgang_falsch_mehrAlsVorhanden() {
       qArtikel.bucheAbgang(TestArtikelBestand + 1);
        assertEquals(einArtikel.getBestand(), TestArtikelBestand);
    }

    @Test
    public void setPreis_korrekt_100() {
        Double erwartet = 100d;
        einArtikel.setPreis(erwartet);
        assertEquals(erwartet, einArtikel.getPreis());
    }

    @Test
    public void setPreis_korrekt_cent() {
        Double erwartet = 0.01;
        einArtikel.setPreis(erwartet);
        assertEquals(erwartet, einArtikel.getPreis());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPreis_falsch_negativ() {
        einArtikel.setPreis(-100d);
    }

    @Test
    public void test_Artikel_() {
        einArtikel.getArt();
        einArtikel.getPreis();

    }

    // @Test
    // public void test_Lager_zugangsbuchung_mit_1234_und_eins() {
    // lager= new Lager();
    // Artikel artikel1 = new Artikel(1234, "banane");
    // lager1.bucheZugang(artikelNr, zugang);
    // assertNotNull(lager1);
    // }

    // @Test
    // public void test_Lager_zugangsbuchung_mit_123_und_eins() {
    // lager = new Lager();
    // Artikel artikel1 = new Artikel(1234, "banane");
    // assertNotNull(lager1);
    // }

    // public void test_Lager_zugangsbuchung_mit_-1234_und_eins(){
    // lager= new Lager();
    // Artikel artikel1 = new Artikel(1234, "banane");
    // assertNotNull(lager1);
    // }

    // public void test_Lager_zugangsbuchung_mit_1234_und_nullzahl() {
    // lager = new Lager();
    // Artikel artikel1 = new Artikel(1234, "banane");
    // assertNotNull(lager1);
    // }

}
