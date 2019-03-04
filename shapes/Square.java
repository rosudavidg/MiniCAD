package shapes;

import utils.ARGB;
import utils.DrawShapeVisitor;
import utils.Point;
import utils.ReadShapeVisitor;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Square implements utils.ShapeFactory.Shape {
    private Point topLeftPoint = new Point(); // Punctul din coltul stanga-sus
    private int side;                         // Lungimea laturii

    private ARGB outlineColor = new ARGB();   // Culoarea de contur
    private ARGB fillColor    = new ARGB();   // Culoarea de umplere

    public final Point getTopLeftPoint() {
        return topLeftPoint;
    }

    public final int getSide() {
        return side;
    }

    public final ARGB getOutlineColor() {
        return outlineColor;
    }

    public final ARGB getFillColor() {
        return fillColor;
    }

    public final void setSide(final int side) {
        this.side = side;
    }

    public final void accept(final ReadShapeVisitor readShapeVisitor, final Scanner scanner) {
        readShapeVisitor.read(this, scanner);
    }

    public final void accept(final DrawShapeVisitor drawShapeVisitor, final BufferedImage image) {
        drawShapeVisitor.draw(this, image);
    }
}
