package org.example.swing;

import javax.swing.*;

public class SpielButton extends JButton {
    public SpielButton(int row, int column) {
        this.row = row;
        this.column = column;
    }
    private final int row;
    private final int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
