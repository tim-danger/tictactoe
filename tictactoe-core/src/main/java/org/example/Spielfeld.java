package org.example;

public class Spielfeld {
    private Zeichen[][] SPIELFELD = new Zeichen[3][3];
    public Zeichen[][] getSpielFeld() {
        return this.SPIELFELD;
    }
    // Methode ist nur für Tests gedacht
    public void setSpielFeld(Zeichen[][] spielFeld) {
        this.SPIELFELD = spielFeld;
    }

    public Spielfeld () {
        this.SPIELFELD = leeresSpielFeld();
    }

    private Zeichen[][] leeresSpielFeld() {
        Zeichen[] zeile1 = new Zeichen[] { Zeichen.LEER, Zeichen.LEER, Zeichen.LEER };
        Zeichen[] zeile2 = new Zeichen[] { Zeichen.LEER, Zeichen.LEER, Zeichen.LEER };
        Zeichen[] zeile3 = new Zeichen[] { Zeichen.LEER, Zeichen.LEER, Zeichen.LEER };
        return new Zeichen[][] { zeile1, zeile2, zeile3 };
    }
}
