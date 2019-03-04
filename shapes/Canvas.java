package shapes;

import utils.ARGB;
import utils.DrawShapeVisitor;
import utils.ReadShapeVisitor;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public final class Canvas implements utils.ShapeFactory.Shape {

    private int height;                  // Inaltimea
    private int width;                   // Latimea
    private ARGB fillColor = new ARGB(); // Culoarea de umplere

    private static Canvas instance = new Canvas();

    private Canvas() {

    }

    public static Canvas getInstance() {
        return instance;
    }

    /**
     * Instantiez o noua imagine cu dimensiunile din Canvas.
     * @return O noua imagine
     */
    public BufferedImage createImageByCanvas() {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public ARGB getARGB() {
        return this.fillColor;
    }

    public void accept(final ReadShapeVisitor readShapeVisitor, final Scanner scanner) {
        readShapeVisitor.read(this, scanner);
    }

    public void accept(final DrawShapeVisitor drawShapeVisitor, final BufferedImage image) {
        drawShapeVisitor.draw(this, image);
    }
}
