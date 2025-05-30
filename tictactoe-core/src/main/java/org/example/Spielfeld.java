package org.example;

import java.util.Arrays;
import java.util.Random;

public class Spielfeld {
    private Zeichen[][] SPIELFELD = new Zeichen[3][3];
    public Zeichen[][] getSpielFeld() {
        return this.SPIELFELD;
    }
    private Zeichen aktuellesZeichen = randomZeichen();
    private SpielfeldObserver spielfeldObserver;

    public Zeichen getAktuellesZeichen() {
        return aktuellesZeichen;
    }

    public void register(SpielfeldObserver spielfeldObserver) {
        this.spielfeldObserver = spielfeldObserver;
    }

    public Spielfeld () {
        this.SPIELFELD = leeresSpielFeld();
        this.register(new SpielfeldZeichner());
    }

    public Zeichen getGewinner() {
        if (aktuellesZeichen == Zeichen.KREIS) {
            return Zeichen.KREUZ;
        } else if (aktuellesZeichen == Zeichen.KREUZ) {
            return Zeichen.KREIS;
        } else {
            return null;
        }
    }

    public Spielfeld (Zeichen[][] spielFeld) {
        this.SPIELFELD = spielFeld;
    }

    private Zeichen[][] leeresSpielFeld() {
        Zeichen[] zeile1 = new Zeichen[] { Zeichen.LEER, Zeichen.LEER, Zeichen.LEER };
        Zeichen[] zeile2 = new Zeichen[] { Zeichen.LEER, Zeichen.LEER, Zeichen.LEER };
        Zeichen[] zeile3 = new Zeichen[] { Zeichen.LEER, Zeichen.LEER, Zeichen.LEER };
        return new Zeichen[][] { zeile1, zeile2, zeile3 };
    }

    public boolean setzeZeichen(int zeile, int spalte) {
        if (this.SPIELFELD[zeile][spalte] == Zeichen.LEER) {
            this.SPIELFELD[zeile][spalte] = aktuellesZeichen;
            wechsleSpieler();
            this.spielfeldObserver.aktualisiere(this.SPIELFELD);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Zeichen[] zeile: SPIELFELD) {
            result.append(Arrays.toString(zeile)).append(System.lineSeparator());
        }
        return result.toString();
    }

    private void wechsleSpieler() {
        if (aktuellesZeichen == Zeichen.KREUZ) {
            aktuellesZeichen = Zeichen.KREIS;
        } else {
            aktuellesZeichen = Zeichen.KREUZ;
        }
    }

    private Zeichen randomZeichen() {
        int i = new Random().nextInt(Zeichen.values().length - 1);
        return Zeichen.values()[i];
    }
}
