package org.example;

public class SpielfeldZeichner {

    public String zeichneSpielFeld(Spielfeld spielfeld) {
        return zeichneSpielFeld(spielfeld.getSpielFeld());
    }

    public String zeichneSpielFeld(Zeichen[][] spielfeld) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < spielfeld.length; i++) {
            result.append(zeichneLinieFuerZeichenInZeile(spielfeld[i], i == spielfeld.length - 1));
        }
        return result.toString();
    }

    private StringBuilder zeichneLinieFuerZeichenInZeile(Zeichen[] zeile, boolean lastLine) {
        StringBuilder result = new StringBuilder(" " + zeile[0].getValue() + " | " + zeile[1].getValue() + " | " + zeile[2].getValue() + " " + System.lineSeparator());
        if (!lastLine) {
            result.append(zeichneHorizontaleLinie());
        }
        return result;
    }

    private StringBuilder zeichneHorizontaleLinie() {
        return new StringBuilder("---+---+---" + System.lineSeparator());
    }
}
