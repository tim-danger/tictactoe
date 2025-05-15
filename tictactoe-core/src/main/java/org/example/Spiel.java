package org.example;

import java.util.Random;
import java.util.Scanner;

public class Spiel {
    // Controller
    private final SpielController controller = new SpielController();
    // Model
    private final Spielfeld spielfeld = new Spielfeld();
    // Teil der View (zusammen mit der Spiel-Klasse)
    private final SpielfeldZeichner zeichner = new SpielfeldZeichner();
    private Zeichen aktuellesZeichen = randomZeichen();

    public void spiel() {

        // prüfen, ob es einen Gewinn / Unentschieden gibt
        boolean gewonnen = false;

        // Spielschleife
        while (!controller.spielZuEnde(spielfeld)) {
            System.out.println(aktuellesZeichen + " ist am Zug");
            Integer row = getInput("Gib bitte Zeilennummer ein:");
            Integer col = getInput("Gib bitte Spaltennummer ein:");

            gewonnen = setzeZeichenUndZeichneSpielfeld(row, col);
        }
        System.out.println("Spiel zu Ende! " + (gewonnen ? aktuellesZeichen + " hat gewonnen!" : "Unentschieden"));
    }

    private Zeichen randomZeichen() {
        int i = new Random().nextInt(Zeichen.values().length - 1);
        return Zeichen.values()[i];
    }

    private boolean setzeZeichenUndZeichneSpielfeld(Integer row, Integer col) {
        if (spielfeld.setzeZeichen(aktuellesZeichen, row - 1, col - 1)) {
            boolean gewonnen = controller.gewonnen(spielfeld);
            if (!gewonnen) {
                switchAktuellesZeichen();
            }
            System.out.println(zeichner.zeichneSpielFeld(spielfeld));
            return gewonnen;
        } else {
            System.err.println("Das Zeichen an der Stelle " + row + " / " + col + " ist bereits gesetzt! Gib bitte ein Anderes ein!");
        }
        return false;
    }

    private void switchAktuellesZeichen() {
        if (aktuellesZeichen == Zeichen.KREUZ) {
            aktuellesZeichen = Zeichen.KREIS;
        } else {
            aktuellesZeichen = Zeichen.KREUZ;
        }
    }

    private Integer getInput(String request) {
        Integer row;
        do {
            row = readInputFromCommandLine(request);
        } while (row == null);
        return row;
    }

    private Integer readInputFromCommandLine(String request) {
        System.out.println(request);
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        if (isNumberInRange(input)) {
            return Integer.parseInt(input);
        }
        return null;
    }

    private boolean isNumberInRange(String numberAsString) {
        try {
            int number = Integer.parseInt(numberAsString);
            return switch (number) {
                case 1, 2, 3 -> true;
                default -> false;
            };
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
