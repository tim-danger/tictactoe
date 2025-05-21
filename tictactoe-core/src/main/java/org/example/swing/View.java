package org.example.swing;

import org.example.SpielController;
import org.example.Spielfeld;
import org.example.SpielfeldZeichner;
import org.example.Zeichen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class View implements ActionListener {
    // Test
    private final SpielfeldZeichner zeichner = new SpielfeldZeichner();
    // Controller
    private final SpielController controller = new SpielController();
    // Model
    private Spielfeld spielfeld = new Spielfeld();
    private Zeichen aktuellesZeichen = randomZeichen();
    private static final String TITLE = "Tic-Tac-Toe";

    private final JFrame frame;
    private final JButton[] buttons = new JButton[9];

    public View() {
        frame = new JFrame(getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int counter = -1;
        for (int i = 0; i < spielfeld.getSpielFeld().length; i++) {
            Zeichen[] zeile = spielfeld.getSpielFeld()[i];
            for (int j = 0; j < zeile.length; j++) {
                counter++;
                buttons[counter] = new SpielButton(i, j);
                buttons[counter].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[counter].addActionListener(this);
                panel.add(buttons[counter]);
            }
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    private String getTitle() {
        return TITLE + " " + aktuellesZeichen.getValue() + "'s turn";
    }

    public void actionPerformed(ActionEvent e) {
        SpielButton button = (SpielButton) e.getSource();
        // wir setzen ein "X" oder ein "O" (abhängig vom aktuellen Spieler)
        button.setText(aktuellesZeichen.getValue());
        // Button wird ausgegraut (damit er nicht mehr geklickt werden kann)
        button.setEnabled(false);

        // Update des Modells
        setzeWertInSpielfeld(button);

        pruefeAufGewinn();
    }

    private void pruefeAufGewinn() {
        // Spielstatus bestimmen und weitermachen oder eben nicht
        SpielStatus status = controller.getStatus(spielfeld);
        System.out.println(zeichner.zeichneSpielFeld(spielfeld));
        fahreFortMitSpielFuerStatus(status);
    }

    private void setzeWertInSpielfeld(SpielButton button) {
        int row = button.getRow();
        int column = button.getColumn();
        spielfeld.setzeZeichen(aktuellesZeichen, row, column);
    }

    private void fahreFortMitSpielFuerStatus(SpielStatus status) {
        switch(status) {
            case GEWONNEN: {
                zeigeMeldungUndSetzeSpielZurueck(aktuellesZeichen.getValue() + " gewinnt!", frame);
                break;
            }
            case UNENTSCHIEDEN: {
                zeigeMeldungUndSetzeSpielZurueck("Unentschieden!", frame);
                break;
            }
            default: {
                wechsleSpieler();
            }
        }
    }

    private void zeigeMeldungUndSetzeSpielZurueck(String meldung, JFrame frame) {
        JOptionPane.showMessageDialog(frame, meldung);
        resetGame();
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
        frame.setTitle(getTitle());
    }

    public void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        spielfeld = new Spielfeld();
        aktuellesZeichen = randomZeichen();
        frame.setTitle(getTitle());
    }

    public static void main(String[] args) {
        new View();
    }
}
