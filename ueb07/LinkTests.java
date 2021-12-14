import static org.junit.Assert.*;

import org.junit.*;

public class LinkTests {

    String multiLineHTML;
    String SingleLineHTML;
    String SingleLineHTMLLowerCase;
    String SingleLineHTMLUpperCase;

    @Before
    public void init_html_strings() {
        multiLineHTML = String.format("%s\n%s\n%s\n%s\n%s",
                "<p>",
                "<li><a href=\"http://www.htwsaar.de/\">htwsaar</a></li>",
                "<li><a href=\"http://www.google.com\">Google</a></li>",
                "<a href=\"http://www.heise.de\">HeiseOnline</a>",
                "</p>");
        SingleLineHTML = String.format("%s%s%s%s%s",
                "<p>",
                "<li><a href=\"http://www.htwsaar.de/\">htwsaar</a></li>",
                "<li><a href=\"http://www.google.com\">Google</a></li>",
                "<a href=\"http://www.heise.de\">HeiseOnline</a>",
                "</p>");
        SingleLineHTMLLowerCase = String.format("%s%s%s%s%s",
                "<p>",
                "<li><a href=\"http://www.htwsaar.de/\">htwsaar</a></li>",
                "<li><a href=\"http://www.google.com\">Google</a></li>",
                "<a href=\"http://www.heise.de\">HeiseOnline</a>",
                "</p>");
        SingleLineHTMLUpperCase = String.format("%s%s%s%s%s",
                "<p>",
                "<li><a Href=\"http://www.htwsaar.de/\">htwsaar</a></li>",
                "<li><a Href=\"http://www.google.com\">Google</a></li>",
                "<a href=\"http://www.heise.de\">HeiseOnline</a>",
                "</p>");

    }

    @Test
    public void test_assert_line_length_should_is_1() {
        int erhalten = LinkFilter.anzahlZeilen(SingleLineHTML);
        assertEquals(1, erhalten);
    }

    @Test
    public void test_assert_line_length_should_1_is_5s() {
        int erhalten = LinkFilter.anzahlZeilen(multiLineHTML);
        assertNotEquals(1, erhalten);

    }

    @Test
    public void test_assert_text_in_tags_are_lowercase_with_LowerCaseInput() {
        boolean lowercaseErgebnis = true;
        String[] alleTags = LinkFilter.leseAlleHTMLTagsAusString(SingleLineHTMLLowerCase);

        for (String tag : alleTags) {
            if (LinkFilter.istTagKleingeschrieben(tag) == false) {
                lowercaseErgebnis = false;
                break;
            }
        }
        assertTrue(lowercaseErgebnis);
    }

    @Test
    public void test_assert_text_in_tags_are_lowercase_with_UpperCaseInput() {
        boolean lowercaseErgebnis = true;
        String[] alleTags = LinkFilter.leseAlleHTMLTagsAusString(SingleLineHTMLUpperCase);

        for (String tag : alleTags) {
            if (LinkFilter.istTagKleingeschrieben(tag) == false) {
                lowercaseErgebnis = false;
                break;
            }
        }
        assertFalse(lowercaseErgebnis);

    }

    @Test
    public void test_auslesenHyperTextLinks_mit_SingleLineHTML_erwarte_array_of_hrefTags() {
        String[] htmlLinks = LinkFilter.auslesenHyperTextLinks(SingleLineHTML);
        assertNotEquals(0, htmlLinks.length);
    }

    @Test
    public void test_isHyperTextLink_1_Zeile_Lang_MultiLineHTML() {
        boolean ergebnis = LinkFilter.istHyperTextLink_1_Zeile_Lang(multiLineHTML);
        assertFalse(ergebnis);
    }

    @Test
    public void test_isHyperTextLink_1_Zeile_Lang_mit_SingleLineHTML() {
        boolean ergebnis = LinkFilter.istHyperTextLink_1_Zeile_Lang(SingleLineHTML);
        assertTrue(ergebnis);
    }

}
