package org.example;

import java.util.Arrays;

public class SpielController {
    public boolean gewonnen(Spielfeld spielfeld) {
        return dreiInEinerReihe(spielfeld) || dreiInEinerSpalte(spielfeld) || dreiInDerDiagonale(spielfeld);
    }

    private boolean dreiInEinerReihe(Spielfeld spielfeld) {
        Zeichen[][] spielFeld = spielfeld.getSpielFeld();
        boolean ergebnis = false;
        for (Zeichen[] zeile : spielFeld) {
            int verschiedeneElemente = Arrays.stream(zeile).distinct().toList().size();
            ergebnis = ergebnis || verschiedeneElemente == 1;
        }
        return ergebnis;
    }

    private boolean dreiInEinerSpalte(Spielfeld spielfeld) {
        Zeichen[][] spielFeld = spielfeld.getSpielFeld();
        boolean ergebnis = false;
        for (int i = 0; i < spielFeld.length; i++) {
            Zeichen[] spalte = new Zeichen[] { spielFeld[0][i], spielFeld[1][i], spielFeld[2][i] };
            int verschiedeneElemente = Arrays.stream(spalte).distinct().toList().size();
            ergebnis = ergebnis || verschiedeneElemente == 1;
        }
        return ergebnis;
    }

    private boolean dreiInDerDiagonale(Spielfeld spielfeld) {
        Zeichen[][] spielFeld = spielfeld.getSpielFeld();
        // TODO : 2. Diagonale
        Zeichen[] diagonale1 = new Zeichen[] { spielFeld[0][0], spielFeld[1][1], spielFeld[2][2] };
        return alleElementeGleich(diagonale1);
    }

    private boolean alleElementeGleich(Zeichen[] zeileSpalteDiagonale) {
        int verschiedeneElemente = Arrays.stream(zeileSpalteDiagonale).distinct().toList().size();
        return verschiedeneElemente == 1;
    }
}
