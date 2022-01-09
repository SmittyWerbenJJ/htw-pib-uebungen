package tests;

import src.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Die Test-Klasse
 *
 * @author Rapahel Kimbula
 * @version 0.1
 */
public class testArtikelErstellung {
    Artikel einArtikel;

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

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsche_NR_5stellig() {
        einArtikel = new Artikel(13234, "banane");

    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsche_NR_4stelligNegativ() {
        einArtikel = new Artikel(-1234, "banane");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsche_NR_3stellig() {
        einArtikel = new Artikel(123, "banane");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsche_NR_4stellig0() {

        einArtikel = new Artikel(0100, "banane");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsche_Art1_leerstring() {
        einArtikel = new Artikel(1000, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsche_Art2_leerzeichen() {
        einArtikel = new Artikel(1000, " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsche_Bestand_negativ() {
        einArtikel = new Artikel(1000, "banane", -99999);
    }

    @Test(expected = IllegalArgumentException.class)
    public void erstelleArtikel_Falsche_Preis_negativ() {
        einArtikel = new Artikel(1000, "banane", 1, -1.0);
    }
}
