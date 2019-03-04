package shapes;

import utils.ARGB;
import utils.DrawShapeVisitor;
import utils.Point;
import utils.ReadShapeVisitor;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Diamond implements utils.ShapeFactory.Shape {
    private Point middlePoint = new Point(); // Centrul rombului

    private int horizontalDiagonal;          // Lungimea diagonalei orizontale
    private int verticalDiagonal;            // Lungimea diagonalei verticale

    private ARGB outlineColor = new ARGB();  // Culoarea de contur
    private ARGB fillColor = new ARGB();     // Culoarea de umplere

    public final Point getMiddlePoint() {
        return middlePoint;
    }

    public final int getHorizontalDiagonal() {
        return horizontalDiagonal;
    }

    public final int getVerticalDiagonal() {
        return verticalDiagonal;
    }

    public final ARGB getOutlineColor() {
        return outlineColor;
    }

    public final ARGB getFillColor() {
        return fillColor;
    }

    public final void setHorizontalDiagonal(final int horizontalDiagonal) {
        this.horizontalDiagonal = horizontalDiagonal;
    }

    public final void setVerticalDiagonal(final int verticalDiagonal) {
        this.verticalDiagonal = verticalDiagonal;
    }

    public final void accept(final ReadShapeVisitor readShapeVisitor, final Scanner scanner) {
        readShapeVisitor.read(this, scanner);
    }

    public final void accept(final DrawShapeVisitor drawShapeVisitor, final BufferedImage image) {
        drawShapeVisitor.draw(this, image);
    }
}
