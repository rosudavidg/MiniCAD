package utils;

import java.util.Scanner;

public class Point {
    private int x; // Coordonata orizontala a punctului
    private int y; // Coordonata verticala a punctului

    public Point() {

    }

    /**
     * Constructor pentru crearea unui punct dupa coordonate.
     * @param x Coordonata x
     * @param y Coordonata y
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor pentru copierea unui punct.
     * @param toClone Punctul care se copiaza
     */
    public Point(final Point toClone) {
        this(toClone.x, toClone.y);
    }

    /**
     * Citirea unui punct din fisier.
     * @param scanner Scanner pentru fisier
     */
    final void read(final Scanner scanner) {
        x = scanner.nextInt();
        y = scanner.nextInt();
    }

    /**
     * Suma a doua puncte.
     * @param add Punctul cu care se aduna
     * @return Intoarce un nou punct
     */
    final Point add(final Point add) {
        return new Point(x + add.x, y + add.y);
    }

    final int getX() {
        return this.x;
    }

    final int getY() {
        return this.y;
    }
}
