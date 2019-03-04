package utils;

import shapes.Canvas;
import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

import java.util.Scanner;

public final class ReadShapeVisitor implements ShapeFactory.ShapeVisitor {

    private static final int NO_LINES_TRIANGLE = 3;

    private ReadShapeVisitor() {
    }

    private static ReadShapeVisitor instance = new ReadShapeVisitor();

    static ReadShapeVisitor getInstance() {
        return instance;
    }

    public void read(final Canvas canvas, final Scanner scanner) {
        canvas.setHeight(scanner.nextInt());
        canvas.setWidth(scanner.nextInt());
        canvas.getARGB().read(scanner);
    }

    public void read(final Circle circle, final Scanner scanner) {
        circle.getMiddlePoint().read(scanner);
        circle.setRadius(scanner.nextInt());
        circle.getOutlineColor().read(scanner);
        circle.getFillColor().read(scanner);
    }

    public void read(final Diamond diamond, final Scanner scanner) {
        diamond.getMiddlePoint().read(scanner);
        diamond.setHorizontalDiagonal(scanner.nextInt());
        diamond.setVerticalDiagonal(scanner.nextInt());
        diamond.getOutlineColor().read(scanner);
        diamond.getFillColor().read(scanner);
    }

    public void read(final Line line, final Scanner scanner) {
        line.getStartPoint().read(scanner);
        line.getFinalPoint().read(scanner);
        line.getColor().read(scanner);
    }

    public void read(final Polygon polygon, final Scanner scanner) {
//        Read number of points
        polygon.setNumberOfPoints(scanner.nextInt());
        polygon.setPoints();

//        Read all points
        for (int count = 0; count < polygon.getNumberOfPoints(); count++) {
            (polygon.getPoints())[count] = new Point();
            (polygon.getPoints())[count].read(scanner);
        }

//        Read colors
        polygon.getOutlineColor().read(scanner);
        polygon.getFillColor().read(scanner);
    }

    public void read(final Rectangle rectangle, final Scanner scanner) {
        rectangle.getTopLeftPoint().read(scanner);
        rectangle.setHeight(scanner.nextInt());
        rectangle.setWidth(scanner.nextInt());
        rectangle.getOutlineColor().read(scanner);
        rectangle.getFillColor().read(scanner);
    }

    public void read(final Square square, final Scanner scanner) {
        square.getTopLeftPoint().read(scanner);
        square.setSide(scanner.nextInt());
        square.getOutlineColor().read(scanner);
        square.getFillColor().read(scanner);
    }

    public void read(final Triangle triangle, final Scanner scanner) {
        for (int i = 0; i < NO_LINES_TRIANGLE; i++) {
            (triangle.getPoints())[i] = new Point();
            (triangle.getPoints())[i].read(scanner);
        }

        triangle.getOutlineColor().read(scanner);
        triangle.getFillColor().read(scanner);
    }
}
