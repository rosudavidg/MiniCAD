package shapes;

import utils.ARGB;
import utils.DrawShapeVisitor;
import utils.Point;
import utils.ReadShapeVisitor;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Polygon implements utils.ShapeFactory.Shape {
    private int numberOfPoints;             // Numarul de puncte
    private Point[] points;                 // Vector cu punctele

    private ARGB outlineColor = new ARGB(); // Culoarea de contur
    private ARGB fillColor = new ARGB();    // Culoarea de umplere

    public final int getNumberOfPoints() {
        return numberOfPoints;
    }

    public final Point[] getPoints() {
        return points;
    }

    public final ARGB getOutlineColor() {
        return outlineColor;
    }

    public final ARGB getFillColor() {
        return fillColor;
    }

    public final void setNumberOfPoints(final int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public final void setPoints() {
        this.points = new Point[numberOfPoints];
    }

    public final void accept(final ReadShapeVisitor readShapeVisitor, final Scanner scanner) {
        readShapeVisitor.read(this, scanner);
    }

    public final void accept(final DrawShapeVisitor drawShapeVisitor, final BufferedImage image) {
        drawShapeVisitor.draw(this, image);
    }
}
