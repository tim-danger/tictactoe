package org.example;

import org.example.swing.SpielStatus;

import java.util.Arrays;

public class SpielController {
    private final Spielfeld spielfeld;

    public SpielController() {
        this.spielfeld = new Spielfeld();
    }

    // für Tests
    public SpielController(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    public Zeichen getGewinner() {
        return this.spielfeld.getGewinner();
    }

    public SpielStatus getStatus() {
        boolean gewonnen = gewonnen();
        boolean zuEnde = gewonnen || spielZuEnde();
        if (gewonnen) {
            return SpielStatus.GEWONNEN;
        } else if (zuEnde) {
            return SpielStatus.UNENTSCHIEDEN;
        } else {
            return SpielStatus.WEITER;
        }
    }

    public boolean spielZuEnde() {
        return gewonnen() || !leeresElementEnthalten(this.spielfeld);
    }

    private boolean leeresElementEnthalten(Spielfeld spielfeld) {
        Zeichen[][] spielFeld = spielfeld.getSpielFeld();
        boolean ergebnis = false;
        for (Zeichen[] zeile : spielFeld) {
            boolean leeresElement = Arrays.asList(zeile).contains(Zeichen.LEER);
            ergebnis = ergebnis || leeresElement;
        }
        return ergebnis;
    }

    public boolean gewonnen() {
        return dreiInEinerReihe(this.spielfeld) || dreiInEinerSpalte(this.spielfeld) || dreiInDerDiagonale(this.spielfeld);
    }

    private boolean dreiInEinerReihe(Spielfeld spielfeld) {
        Zeichen[][] spielFeld = spielfeld.getSpielFeld();
        boolean ergebnis = false;
        for (Zeichen[] zeile : spielFeld) {
            ergebnis = ergebnis || alleElementeGleichUndVerschiedenVonLeer(zeile);
        }
        return ergebnis;
    }

    private boolean dreiInEinerSpalte(Spielfeld spielfeld) {
        Zeichen[][] spielFeld = spielfeld.getSpielFeld();
        boolean ergebnis = false;
        for (int i = 0; i < spielFeld.length; i++) {
            Zeichen[] spalte = new Zeichen[] { spielFeld[0][i], spielFeld[1][i], spielFeld[2][i] };
            ergebnis = ergebnis || alleElementeGleichUndVerschiedenVonLeer(spalte);
        }
        return ergebnis;
    }

    private boolean dreiInDerDiagonale(Spielfeld spielfeld) {
        Zeichen[][] spielFeld = spielfeld.getSpielFeld();
        Zeichen[] diagonale1 = new Zeichen[] { spielFeld[0][0], spielFeld[1][1], spielFeld[2][2] };
        Zeichen[] diagonale2 = new Zeichen[] { spielFeld[2][0], spielFeld[1][1], spielFeld[0][2] };
        return alleElementeGleichUndVerschiedenVonLeer(diagonale1) || alleElementeGleichUndVerschiedenVonLeer(diagonale2);
    }

    private boolean alleElementeGleichUndVerschiedenVonLeer(Zeichen[] zeileSpalteDiagonale) {
        int verschiedeneElemente = Arrays.stream(zeileSpalteDiagonale).distinct().toList().size();
        return verschiedeneElemente == 1 && zeileSpalteDiagonale[0] != Zeichen.LEER;
    }

    public Zeichen getAktuellesZeichen() {
        return this.spielfeld.getAktuellesZeichen();
    }

    public boolean setzeZeichen(int zeile, int spalte) {
        return this.spielfeld.setzeZeichen(zeile, spalte);
    }

    public Zeichen[][] getSpielFeld() {
        return this.spielfeld.getSpielFeld();
    }
}
