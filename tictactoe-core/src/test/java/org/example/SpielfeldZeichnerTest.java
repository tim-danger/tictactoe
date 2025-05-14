package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpielfeldZeichnerTest {

    private SpielfeldZeichner zeichner = new SpielfeldZeichner();

    @Test
    public void testZeichneSpielfeld() {
        Zeichen[] zeile1 = new Zeichen[] { Zeichen.KREUZ, Zeichen.KREIS, Zeichen.KREUZ };
        Zeichen[] zeile2 = new Zeichen[] { Zeichen.LEER, Zeichen.KREUZ, Zeichen.KREUZ };
        Zeichen[] zeile3 = new Zeichen[] { Zeichen.KREUZ, Zeichen.KREIS, Zeichen.KREIS };
        Zeichen[][] spielfeld = new Zeichen[][] { zeile1, zeile2, zeile3 };
        System.out.println(zeichner.zeichneSpielFeld(spielfeld));
    }

    @Test
    public void testSpielGewonnen3GleicheInZeile() {
        Zeichen[] zeile1 = new Zeichen[] { Zeichen.KREUZ, Zeichen.KREIS, Zeichen.KREUZ };
        Zeichen[] zeile2 = new Zeichen[] { Zeichen.LEER, Zeichen.KREUZ, Zeichen.KREUZ };
        Zeichen[] zeile3 = new Zeichen[] { Zeichen.KREIS, Zeichen.KREIS, Zeichen.KREIS };
        Zeichen[][] spielfeld = new Zeichen[][] { zeile1, zeile2, zeile3 };
        Spielfeld feld = new Spielfeld();
        feld.setSpielFeld(spielfeld);
        SpielController controller = new SpielController();
        Assertions.assertTrue(controller.gewonnen(feld), "Hey, ich hab gewonnen, hier der Beweis: " + System.lineSeparator() + zeichner.zeichneSpielFeld(spielfeld));
    }

    @Test
    public void testSpielGewonnen3GleicheInSpalte() {
        Zeichen[] zeile1 = new Zeichen[] { Zeichen.KREIS, Zeichen.KREIS, Zeichen.KREUZ };
        Zeichen[] zeile2 = new Zeichen[] { Zeichen.KREIS, Zeichen.KREUZ, Zeichen.KREIS };
        Zeichen[] zeile3 = new Zeichen[] { Zeichen.KREIS, Zeichen.KREIS, Zeichen.KREUZ };
        Zeichen[][] spielfeld = new Zeichen[][] { zeile1, zeile2, zeile3 };
        Spielfeld feld = new Spielfeld();
        feld.setSpielFeld(spielfeld);
        SpielController controller = new SpielController();
        Assertions.assertTrue(controller.gewonnen(feld), "Hey, ich hab gewonnen, hier der Beweis: " + System.lineSeparator() + zeichner.zeichneSpielFeld(spielfeld));
    }

    @Test
    public void testSpielGewonnen3GleicheInDiagonale1() {
        Zeichen[] zeile1 = new Zeichen[] { Zeichen.KREUZ, Zeichen.KREIS, Zeichen.KREUZ };
        Zeichen[] zeile2 = new Zeichen[] { Zeichen.KREIS, Zeichen.KREUZ, Zeichen.KREIS };
        Zeichen[] zeile3 = new Zeichen[] { Zeichen.KREIS, Zeichen.KREIS, Zeichen.KREUZ };
        Zeichen[][] spielfeld = new Zeichen[][] { zeile1, zeile2, zeile3 };
        Spielfeld feld = new Spielfeld();
        feld.setSpielFeld(spielfeld);
        SpielController controller = new SpielController();
        Assertions.assertTrue(controller.gewonnen(feld), "Hey, ich hab gewonnen, hier der Beweis: " + System.lineSeparator() + zeichner.zeichneSpielFeld(spielfeld));
    }

    @Test
    public void testSpielNichtGewonnen() {
        Zeichen[] zeile1 = new Zeichen[] { Zeichen.KREUZ, Zeichen.KREIS, Zeichen.KREUZ };
        Zeichen[] zeile2 = new Zeichen[] { Zeichen.LEER, Zeichen.KREUZ, Zeichen.KREUZ };
        Zeichen[] zeile3 = new Zeichen[] { Zeichen.KREIS, Zeichen.KREIS, Zeichen.LEER };
        Zeichen[][] spielfeld = new Zeichen[][] { zeile1, zeile2, zeile3 };
        Spielfeld feld = new Spielfeld();
        feld.setSpielFeld(spielfeld);
        SpielController controller = new SpielController();
        Assertions.assertFalse(controller.gewonnen(feld), "Hey, ich hab nicht gewonnen, hier der Beweis: " + System.lineSeparator() + zeichner.zeichneSpielFeld(spielfeld));
    }

}
