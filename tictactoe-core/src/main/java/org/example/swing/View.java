package org.example.swing;

import org.example.SpielController;
import org.example.Spielfeld;
import org.example.Zeichen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class View implements ActionListener {
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
        button.setText(aktuellesZeichen.getValue());
        button.setEnabled(false);
        int row = button.getRow();
        int column = button.getColumn();
        spielfeld.setzeZeichen(aktuellesZeichen, row, column);
        boolean gewonnen = controller.gewonnen(spielfeld);
        boolean zuEnde = controller.spielZuEnde(spielfeld);
        if (zuEnde && !gewonnen) {
            JOptionPane.showMessageDialog(frame, "Tie game!");
            resetGame();
        } // noch nicht zu Ende
        else if (!gewonnen) {
            switchAktuellesZeichen();
            frame.setTitle(getTitle());
        } else {
            JOptionPane.showMessageDialog(frame, aktuellesZeichen.getValue() + " wins!");
            resetGame();
        }
    }

    private Zeichen randomZeichen() {
        int i = new Random().nextInt(Zeichen.values().length - 1);
        return Zeichen.values()[i];
    }

    private void switchAktuellesZeichen() {
        if (aktuellesZeichen == Zeichen.KREUZ) {
            aktuellesZeichen = Zeichen.KREIS;
        } else {
            aktuellesZeichen = Zeichen.KREUZ;
        }
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
