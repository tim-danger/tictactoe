package org.example.swing;

import org.example.SpielController;
import org.example.Zeichen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ActionListener {
    // Controller
    private SpielController controller = new SpielController();
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
        for (int i = 0; i < controller.getSpielFeld().length; i++) {
            Zeichen[] zeile = controller.getSpielFeld()[i];
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
        return TITLE + " " + this.controller.getAktuellesZeichen().getValue() + "'s turn";
    }

    public void actionPerformed(ActionEvent e) {
        SpielButton button = (SpielButton) e.getSource();
        // wir setzen ein "X" oder ein "O" (abhängig vom aktuellen Spieler)
        button.setText(this.controller.getAktuellesZeichen().getValue());
        // Button wird ausgegraut (damit er nicht mehr geklickt werden kann)
        button.setEnabled(false);

        // Update des Modells
        setzeWertInSpielfeld(button);

        pruefeAufGewinn();
    }

    private void pruefeAufGewinn() {
        // Spielstatus bestimmen und weitermachen oder eben nicht
        SpielStatus status = controller.getStatus();
        fahreFortMitSpielFuerStatus(status);
    }

    private void setzeWertInSpielfeld(SpielButton button) {
        int row = button.getRow();
        int column = button.getColumn();
        controller.setzeZeichen(row, column);
    }

    private void fahreFortMitSpielFuerStatus(SpielStatus status) {
        switch(status) {
            case GEWONNEN: {
                zeigeMeldungUndSetzeSpielZurueck(this.controller.getGewinner().getValue() + " gewinnt!", frame);
                break;
            }
            case UNENTSCHIEDEN: {
                zeigeMeldungUndSetzeSpielZurueck("Unentschieden!", frame);
                break;
            }
            default: {
                frame.setTitle(getTitle());
            }
        }
    }

    private void zeigeMeldungUndSetzeSpielZurueck(String meldung, JFrame frame) {
        JOptionPane.showMessageDialog(frame, meldung);
        resetGame();
    }

    public void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        controller = new SpielController();
        frame.setTitle(getTitle());
    }

    public static void main(String[] args) {
        new View();
    }
}
