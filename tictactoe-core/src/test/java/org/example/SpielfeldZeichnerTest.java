package org.example;

import org.junit.jupiter.api.Test;

public class SpielfeldZeichnerTest {

    private final SpielfeldZeichner zeichner = new SpielfeldZeichner();

    @Test
    public void testZeichneSpielfeld() {
        Zeichen[] zeile1 = new Zeichen[] { Zeichen.KREUZ, Zeichen.KREIS, Zeichen.KREUZ };
        Zeichen[] zeile2 = new Zeichen[] { Zeichen.LEER, Zeichen.KREUZ, Zeichen.KREUZ };
        Zeichen[] zeile3 = new Zeichen[] { Zeichen.KREUZ, Zeichen.KREIS, Zeichen.KREIS };
        Zeichen[][] spielfeld = new Zeichen[][] { zeile1, zeile2, zeile3 };
        System.out.println(zeichner.zeichneSpielFeld(spielfeld));
    }
}
