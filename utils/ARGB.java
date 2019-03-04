package utils;

import java.util.Scanner;

public class ARGB {
    private static final int DEFAULT_COLOR = 0xFFFFFF;
    private static final int DEFAULT_ALPHA = 255;
    private static final int SHIFT_ALPHA   = 3;
    private static final int BYTE_SIZE     = 8;
    private static final int BASE_HEX      = 16;

    private String stringValue; // Textul RGB
    private int alpha;          // Valoarea alpha de opacitate

    private int intValue;       // Valoarea efectiva a culorii

    public ARGB() {
        stringValue = "";
        alpha = DEFAULT_ALPHA;
        intValue = DEFAULT_COLOR;
    }

    /**
     * Citirea din fisier a unei culori.
     * @param scanner Scanner pentru citire
     */
    final void read(final Scanner scanner) {
        stringValue = scanner.next();
        alpha = scanner.nextInt();

        convertStringToInt();
    }

    /**
     * Copierea unei culori.
     * @param clone Culoarea care trebuie copiata
     */
    public final void clone(final ARGB clone) {
        this.stringValue = clone.stringValue;
        this.alpha = clone.alpha;
        this.intValue = clone.intValue;
    }

    /**
     * Actualizarea campului intValue in functie de stringValue si alpha.
     */
    private void convertStringToInt() {
        String stringValueModified = stringValue.substring(1, stringValue.length());

        this.intValue = alpha * (int) Math.pow(2, BYTE_SIZE * SHIFT_ALPHA)
                    + Integer.parseInt(stringValueModified, BASE_HEX);
    }

    final int getValue() {
        return this.intValue;
    }
}
