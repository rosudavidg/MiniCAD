package shapes;

import utils.ARGB;
import utils.DrawShapeVisitor;
import utils.Point;
import utils.ReadShapeVisitor;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Circle implements utils.ShapeFactory.Shape {
    private Point middlePoint = new Point(); // Centrul cercului
    private int radius;                      // Raza cercului

    private ARGB outlineColor = new ARGB();  // Culoarea de contur
    private ARGB fillColor    = new ARGB();  // Culoarea de umplere

    public final Point getMiddlePoint() {
        return middlePoint;
    }

    public final int getRadius() {
        return radius;
    }

    public final ARGB getOutlineColor() {
        return outlineColor;
    }

    public final ARGB getFillColor() {
        return fillColor;
    }

    public final void setRadius(final int radius) {
        this.radius = radius;
    }

    public final void accept(final ReadShapeVisitor readShapeVisitor, final Scanner scanner) {
        readShapeVisitor.read(this, scanner);
    }

    public final void accept(final DrawShapeVisitor drawShapeVisitor, final BufferedImage image) {
        drawShapeVisitor.draw(this, image);
    }
}
