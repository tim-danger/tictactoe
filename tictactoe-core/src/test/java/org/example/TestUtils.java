package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class TestUtils {

    private final static int SPIELFELD_GROESSE = 3;

    public static Zeichen[][] leeresSpielfeld() {
        return new Zeichen[][] { zeileMit(Zeichen.LEER), zeileMit(Zeichen.LEER), zeileMit(Zeichen.LEER) };
    }

    public static Zeichen[] leereZeile() {
        return zeileMit(Zeichen.LEER);
    }

    public static Zeichen[][] spielFeldSpalteXMitZeichen(int x, Zeichen z) {
        Zeichen[][] spielfeld = new Zeichen[SPIELFELD_GROESSE][SPIELFELD_GROESSE];
        for (int i = 0; i < SPIELFELD_GROESSE; i++) {
            // erst mit leeren Zeilen initialisieren
            spielfeld[i] = leereZeile();
        }

        for (int j = 0; j < SPIELFELD_GROESSE; j++) {
            spielfeld[j][x - 1] = z;
        }
        return spielfeld;
    }

    public static Zeichen[][] spielFeldZeileXMitZeichen(int x, Zeichen z) {
        Zeichen[][] spielfeld = new Zeichen[SPIELFELD_GROESSE][SPIELFELD_GROESSE];
        for (int i = 0; i < SPIELFELD_GROESSE; i++) {
            if (i + 1 == x) {
                spielfeld[i] = zeileMit(z);
            } else {
                spielfeld[i] = leereZeile();
            }
        }
        return spielfeld;
    }

    public static Zeichen[] zeileMit(Zeichen z) {
        return new Zeichen[] { z, z, z };
    }

    public Zeichen[][] readSpielfeldFromFile(String resourceName) {
        String fromFile = readFileAsString(resourceName);
        String[] lines = fromFile.split(System.lineSeparator());
        Zeichen[][] spielfeld = new Zeichen[SPIELFELD_GROESSE][];
        for (int i = 0; i < lines.length; i++) {
            if (i % 2 == 0) {
                String[] elements = lines[i].split("\\|");
                if (elements.length < SPIELFELD_GROESSE) {
                    elements = append(elements, " ");
                }
                Zeichen[] zeile = Arrays.stream(elements).map(this::trimOrEmpty).map(Zeichen::fromValue).toArray(Zeichen[]::new);
                spielfeld[i / 2] = zeile;
            }
        }
        return spielfeld;
    }

    static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    private String trimOrEmpty(String s) {
        String sOrEmtpy = s.trim();
        return sOrEmtpy.isEmpty() ? " " : sOrEmtpy;
    }

    private String readFileAsString(String fileName) {
        URL url = getClass().getResource(fileName);
        if (url == null) {
            url = getClass().getClassLoader().getResource(fileName);
        }
        if (url != null) {
            try {
                Path path = Path.of(url.toURI());
                return Files.readString(path, StandardCharsets.UTF_8);
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Could not read file " + fileName);
    }
}
