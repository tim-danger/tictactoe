package org.example;

public class SpielfeldZeichner {

    public String zeichneSpielFeld(Zeichen[][] spielfeld) {
        boolean lastLine;
        int lineCounter = 1;
        StringBuilder result = new StringBuilder();
        for (Zeichen[] zeile : spielfeld) {
            lastLine = lineCounter == spielfeld.length;
            result.append(zeichneLinieFuerZeichenInZeile(zeile));
            result.append(System.lineSeparator());
            if (!lastLine) {
                result.append(zeichneHorizontaleLinie());
                result.append(System.lineSeparator());
            }
            lineCounter++;
        }
        return result.toString();
    }

    private StringBuilder zeichneLinieFuerZeichenInZeile(Zeichen[] zeile) {
        return new StringBuilder(" " + zeile[0].getValue() + " | " + zeile[1].getValue() + " | " + zeile[2].getValue() + " ");
    }

    private StringBuilder zeichneHorizontaleLinie() {
        return new StringBuilder("---+---+---");
    }
}
