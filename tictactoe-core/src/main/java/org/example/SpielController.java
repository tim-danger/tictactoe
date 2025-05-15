package org.example;

import java.util.Arrays;

public class SpielController {

    public boolean spielZuEnde(Spielfeld spielfeld) {
        return gewonnen(spielfeld) || !leeresElementEnthalten(spielfeld);
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

    public boolean gewonnen(Spielfeld spielfeld) {
        return dreiInEinerReihe(spielfeld) || dreiInEinerSpalte(spielfeld) || dreiInDerDiagonale(spielfeld);
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
}
