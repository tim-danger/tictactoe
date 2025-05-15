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

        for (int i = 0; i < spielfeld.getSpielFeld().length; i++) {
            Zeichen[] zeile = spielfeld.getSpielFeld()[i];
            for (int j = 0; j < zeile.length; j++) {
                int laufenderIndex = i * zeile.length + j;
                buttons[laufenderIndex] = new SpielButton(i, j);
                buttons[laufenderIndex].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[laufenderIndex].addActionListener(this);
                panel.add(buttons[laufenderIndex]);
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

        // Spielstatus bestimmen und weitermachen oder eben nicht
        SpielStatus status = getStatus(controller, spielfeld);
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

    private SpielStatus getStatus(SpielController controller, Spielfeld spielfeld) {
        boolean gewonnen = controller.gewonnen(spielfeld);
        boolean zuEnde = gewonnen || controller.spielZuEnde(spielfeld);
        if (gewonnen) {
            return SpielStatus.GEWONNEN;
        } else if (zuEnde) {
            return SpielStatus.UNENTSCHIEDEN;
        } else {
            return SpielStatus.WEITER;
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
        frame.setTitle(getTitle());
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        spielfeld = new Spielfeld();
        aktuellesZeichen = randomZeichen();
        frame.setTitle(getTitle());
    }

    public static void main(String[] args) {
        new View();
    }
}
