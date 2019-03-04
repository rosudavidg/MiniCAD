package shapes;

import utils.ARGB;
import utils.DrawShapeVisitor;
import utils.Point;
import utils.ReadShapeVisitor;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Rectangle implements utils.ShapeFactory.Shape {
    private Point topLeftPoint = new Point(); // Punctul din coltul stanga-sus
    private int height;                       // Valoarea lungimii
    private int width;                        // Valoarea latimii

    private ARGB outlineColor = new ARGB();   // Culoarea de contur
    private ARGB fillColor    = new ARGB();   // Culoarea de umplere

    public final Point getTopLeftPoint() {
        return topLeftPoint;
    }

    public final int getHeight() {
        return height;
    }

    public final int getWidth() {
        return width;
    }

    public final ARGB getOutlineColor() {
        return outlineColor;
    }

    public final ARGB getFillColor() {
        return fillColor;
    }

    public final void setHeight(final int height) {
        this.height = height;
    }

    public final void setWidth(final int width) {
        this.width = width;
    }

    public final void accept(final ReadShapeVisitor readShapeVisitor, final Scanner scanner) {
        readShapeVisitor.read(this, scanner);
    }

    public final void accept(final DrawShapeVisitor drawShapeVisitor, final BufferedImage image) {
        drawShapeVisitor.draw(this, image);
    }
}
