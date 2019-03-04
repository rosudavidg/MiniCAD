package shapes;

import utils.Point;
import utils.ARGB;
import utils.DrawShapeVisitor;
import utils.ReadShapeVisitor;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Line implements utils.ShapeFactory.Shape {
    private Point startPoint = new Point(); // Punctul de inceput
    private Point finalPoint = new Point(); // Punctul de final

    private ARGB color = new ARGB();        // Culoarea liniei

    public Line() {

    }

    /**
     * Constructor pentru crearea unei noi linii.
     * @param startPoint Punctul de inceput
     * @param finalPoint Punctul de sfarsit
     * @param color Culoarea liniei
     */
    public Line(final Point startPoint, final Point finalPoint, final ARGB color) {
        this.startPoint = new Point(startPoint);
        this.finalPoint = new Point(finalPoint);
        setColor(color);
    }

    public final Point getStartPoint() {
        return startPoint;
    }

    public final Point getFinalPoint() {
        return finalPoint;
    }

    public final ARGB getColor() {
        return color;
    }

    private void setColor(final ARGB clone) {
        this.color.clone(clone);
    }

    public final void accept(final ReadShapeVisitor readShapeVisitor, final Scanner scanner) {
        readShapeVisitor.read(this, scanner);
    }

    public final void accept(final DrawShapeVisitor drawShapeVisitor, final BufferedImage image) {
        drawShapeVisitor.draw(this, image);
    }
}
