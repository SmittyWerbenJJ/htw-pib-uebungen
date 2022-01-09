import src.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Die Test-Klasse
 *
 * @author Rapahel Kimbula
 * @version 0.1
 */
public class testLager {

    Lager lager;
    Artikel einArtikel;

    @Before
    public void erstelleArtikel() {
        lager = new Lager();
        einArtikel = new Artikel(1234, "banane", 100, 2.0d);
    }

    @Test
    public void test_Lager_korrekt_groesse_100() {
        lager = new Lager(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Lager_falsch_Falsche_groesse_Minus_10() {
        lager = new Lager(-10);
    }

    @Test
    public void test_Lager_korrekt_standartkonstruktor_soll_10() {
        lager = new Lager();
        int istgroesse = lager.getLagerGroesse();
        int sollgroesse = 10;
        assertEquals(sollgroesse, istgroesse);
    }

    @Test
    public void test_lager_korrekt_legeArtikelAn_Artikel_NeuerArtikel() {
        lager = new Lager();
        int artikelnr = 1234;
        int andereArtikelNR = 4321;
        einArtikel = new Artikel(artikelnr, "banane");
        lager.legeAnArtikel(einArtikel);
        einArtikel = new Artikel(andereArtikelNR, "banane");
        lager.legeAnArtikel(einArtikel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_lager_falsch_legeArtikelAn_Artikel_Existiert_Bereits() {
        lager = new Lager();
        int artikelnr = 1234;
        einArtikel = new Artikel(artikelnr, "banane");
        lager.legeAnArtikel(einArtikel);
        einArtikel = new Artikel(artikelnr, "banane");
        lager.legeAnArtikel(einArtikel);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_lager_falsch_legeArtikelAn_Wenn_Lager_Schon_Voll_ist() {
        lager = new Lager();
        int artikelnr = 1234;
        for (int i = 0; i < 10; i++) {
            einArtikel = new Artikel(artikelnr + i, "banane");
            lager.legeAnArtikel(einArtikel);

        }
        einArtikel = new Artikel(artikelnr + 100, "banane");
        lager.legeAnArtikel(einArtikel);
    }

    @Test
    public void test_lager_korrekt_entferneArtikel_Aus_Lager_mit_existierendemArtikel() {
        lager = new Lager();
        int artikelnr = 1234;
        einArtikel = new Artikel(artikelnr, "banane");
        lager.legeAnArtikel(einArtikel);
        lager.entferneArtikel(artikelnr);
    }

    @Test
    public void test_lager_korrekt_bucheZugang_gueltigen_Artikel_Buchen() {
        lager = new Lager();
        int artikelNr = 1234;
        einArtikel = new Artikel(artikelNr, "banane");
        lager.legeAnArtikel(einArtikel);
        lager.bucheZugang(artikelNr, 1);
    }

    @Test
    public void test_lager_korrekt_erhohePreis_EinesArtikels() {
        lager = new Lager();
        int artikelNr = 1234;
        einArtikel = new Artikel(artikelNr, "banane", 10d);
        lager.legeAnArtikel(einArtikel);
        lager.aenderePreisEinesArtikels(artikelNr, 0.2d);

        double sollpreis = 12; // 10* (1+0,2)
        double istPreis = lager.getArtikel(0).getPreis();
        Assert.assertEquals(sollpreis, istPreis, 1E-9);
    }

    @Test
    public void test_lager_korrekt_verringerePreis_EinesArtikels() {
        lager = new Lager();
        int artikelNr = 1234;
        einArtikel = new Artikel(artikelNr, "banane", 10d);
        lager.legeAnArtikel(einArtikel);
        lager.aenderePreisEinesArtikels(artikelNr, -0.2d);

        double sollpreis = 8; // 10* (1+0,2)
        double istPreis = lager.getArtikel(0).getPreis();
        Assert.assertEquals(sollpreis, istPreis, 1E-9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_lager_falsch_verringerePreis_EinesArtikels_Preis_Wird_Negativ() {
        lager = new Lager();
        int artikelNr = 1234;
        einArtikel = new Artikel(artikelNr, "banane", 10d);
        lager.legeAnArtikel(einArtikel);
        lager.aenderePreisEinesArtikels(artikelNr, -1.2d);

    }

}
