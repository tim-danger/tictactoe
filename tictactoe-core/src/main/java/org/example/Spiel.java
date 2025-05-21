package org.example;

import org.example.swing.SpielStatus;

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
        SpielStatus status = controller.getStatus(spielfeld);

        // Spielschleife
        while (status == SpielStatus.WEITER) {
            System.out.println(aktuellesZeichen + " ist am Zug");
            Integer row = getInput("Gib bitte Zeilennummer ein:");
            Integer col = getInput("Gib bitte Spaltennummer ein:");

            // Update des Modells
            boolean unerlaubterZug = !setzeWertInSpielfeld(row, col);

            status = pruefeAufGewinn(unerlaubterZug, "Das Zeichen an der Stelle " + row + " / " + col + " ist bereits gesetzt! Gib bitte ein Anderes ein!");
        }
    }

    private SpielStatus pruefeAufGewinn(boolean unerlaubterZug, String message) {
        // Spielstatus bestimmen und weitermachen oder eben nicht
        SpielStatus status = controller.getStatus(spielfeld);
        System.out.println(zeichner.zeichneSpielFeld(spielfeld));
        fahreFortMitSpielFuerStatus(status, unerlaubterZug, message);
        return status;
    }

    private boolean setzeWertInSpielfeld(int row, int column) {
        return spielfeld.setzeZeichen(aktuellesZeichen, row - 1, column - 1);
    }

    private void fahreFortMitSpielFuerStatus(SpielStatus status, boolean unerlaubterZug, String errorMessage) {
        switch(status) {
            case GEWONNEN: {
                System.out.println("Spiel zu Ende! " + aktuellesZeichen + " hat gewonnen!");
                break;
            }
            case UNENTSCHIEDEN: {
                System.out.println("Spiel zu Ende! " + "Unentschieden");
                break;
            }
            default: {
                if (unerlaubterZug) {
                    System.err.println(errorMessage);
                } else {
                    wechsleSpieler();
                }
            }
        }
    }

    private Zeichen randomZeichen() {
        int i = new Random().nextInt(Zeichen.values().length - 1);
        return Zeichen.values()[i];
    }

    private void wechsleSpieler() {
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
