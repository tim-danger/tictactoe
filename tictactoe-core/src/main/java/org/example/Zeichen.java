package org.example;

public enum Zeichen {
    KREIS("O"), KREUZ("X"), LEER(" ");
    private final String value;
    Zeichen(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static Zeichen fromValue(String value) {
        for (Zeichen z : Zeichen.values()) {
            if (z.getValue().equals(value)) {
                return z;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}
