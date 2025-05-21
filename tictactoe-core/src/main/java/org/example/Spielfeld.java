package org.example;

import java.util.Arrays;

public class Spielfeld {
    private Zeichen[][] SPIELFELD = new Zeichen[3][3];
    public Zeichen[][] getSpielFeld() {
        return this.SPIELFELD;
    }

    public Spielfeld () {
        this.SPIELFELD = leeresSpielFeld();
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

    public boolean setzeZeichen(Zeichen zeichen, int zeile, int spalte) {
        if (this.SPIELFELD[zeile][spalte] == Zeichen.LEER) {
            this.SPIELFELD[zeile][spalte] = zeichen;
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
}
