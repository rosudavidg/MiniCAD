package shapes;

import utils.ARGB;
import utils.DrawShapeVisitor;
import utils.Point;
import utils.ReadShapeVisitor;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Triangle implements utils.ShapeFactory.Shape {
    private static final int NO_LINES_TRIANGLE = 3;

    private Point[] points = new Point[NO_LINES_TRIANGLE]; // Cele trei puncte ale triunghiului

    private ARGB outlineColor = new ARGB();                // Culoarea de contur
    private ARGB fillColor    = new ARGB();                // Culoarea de umplere

    public final Point[] getPoints() {
        return points;
    }

    public final ARGB getOutlineColor() {
        return outlineColor;
    }

    public final ARGB getFillColor() {
        return fillColor;
    }

    public final void accept(final ReadShapeVisitor readShapeVisitor, final Scanner scanner) {
        readShapeVisitor.read(this, scanner);
    }

    public final void accept(final DrawShapeVisitor drawShapeVisitor, final BufferedImage image) {
        drawShapeVisitor.draw(this, image);
    }
}
