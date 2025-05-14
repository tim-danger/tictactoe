package org.example;

public enum Zeichen {
    KREIS("O"), KREUZ("X"), LEER(" ");
    private String value;
    Zeichen(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
