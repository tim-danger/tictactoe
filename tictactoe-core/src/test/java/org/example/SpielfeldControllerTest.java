package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SpielfeldControllerTest {

    private final SpielfeldZeichner zeichner = new SpielfeldZeichner();

    @ParameterizedTest
    @MethodSource("spielfeldGewinnUnentschieden")
    public void testGewinnUnentschieden(Spielfeld spielfeld, boolean gewonnen) {
        SpielController controller = new SpielController();
        if (gewonnen) {
            Assertions.assertTrue(controller.gewonnen(spielfeld), "Hey, ich hab gewonnen: " + System.lineSeparator() + zeichner.zeichneSpielFeld(spielfeld));
        } else {
            Assertions.assertFalse(controller.gewonnen(spielfeld), "Hey, ich hab verloren: " + System.lineSeparator() + zeichner.zeichneSpielFeld(spielfeld));
        }
        Assertions.assertTrue(controller.spielZuEnde(spielfeld), "Spiel ist noch nicht zu Ende: " + System.lineSeparator() + zeichner.zeichneSpielFeld(spielfeld));
    }

    @ParameterizedTest
    @MethodSource("spielfeldNichtBeendet")
    public void testSpielNochNichtFertig(Spielfeld spielfeld) {
        SpielController controller = new SpielController();
        Assertions.assertFalse(controller.spielZuEnde(spielfeld), "Sieht das wie ein beendetes Spiel aus? ->" + System.lineSeparator() + zeichner.zeichneSpielFeld(spielfeld));
    }

    private static Stream<Arguments> spielfeldNichtBeendet() {
        return Stream.of(
                // individuelle Tests
                Arguments.of(new Spielfeld(new TestUtils().readSpielfeldFromFile("nicht_fertig.txt"))),
                Arguments.of(new Spielfeld(TestUtils.leeresSpielfeld()))
        );
    }

    private static Stream<Arguments> spielfeldGewinnUnentschieden() {
        return Stream.of(
                Arguments.of(new Spielfeld(TestUtils.spielFeldSpalteXMitZeichen(1, Zeichen.KREUZ)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldSpalteXMitZeichen(2, Zeichen.KREUZ)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldSpalteXMitZeichen(3, Zeichen.KREUZ)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldSpalteXMitZeichen(1, Zeichen.KREIS)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldSpalteXMitZeichen(2, Zeichen.KREIS)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldSpalteXMitZeichen(3, Zeichen.KREIS)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldZeileXMitZeichen(1, Zeichen.KREUZ)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldZeileXMitZeichen(2, Zeichen.KREUZ)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldZeileXMitZeichen(3, Zeichen.KREUZ)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldZeileXMitZeichen(1, Zeichen.KREIS)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldZeileXMitZeichen(2, Zeichen.KREIS)), true),
                Arguments.of(new Spielfeld(TestUtils.spielFeldZeileXMitZeichen(3, Zeichen.KREIS)), true),
                // individuelle Tests
                Arguments.of(new Spielfeld(new TestUtils().readSpielfeldFromFile("gewinn.txt")), true),
                Arguments.of(new Spielfeld(new TestUtils().readSpielfeldFromFile("gewinn2.txt")), true),
                Arguments.of(new Spielfeld(new TestUtils().readSpielfeldFromFile("unentschieden.txt")), false)
        );
    }
}
