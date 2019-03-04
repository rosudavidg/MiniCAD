package utils;

import shapes.Canvas;
import shapes.Circle;
import shapes.Diamond;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Square;
import shapes.Triangle;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public final class DrawShapeVisitor implements ShapeFactory.ShapeVisitor {

    private DrawShapeVisitor() {
    }

    private static DrawShapeVisitor instance = new DrawShapeVisitor();

    static DrawShapeVisitor getInstance() {
        return instance;
    }

    private static final int NO_LINES_SQUARE   = 4;
    private static final int NO_LINES_TRIANGLE = 3;

    /**
     * Verific daca un punct este sau nu in Canvas.
     * @param x Valoarea x a punctului
     * @param y Valoarea y a punctului
     * @param image Imaginea
     * @return True/ false daca punctul este sau nu in Canvas
     */
    private boolean isInCanvas(final int x, final int y, final BufferedImage image) {
        return ((x >= 0)
                && (y >= 0)
                && (x < image.getWidth())
                && (y < image.getHeight()));
    }

    /**
     * Incerc colorarea unui pixel in imagine.
     * @param x Valoarea x a punctului
     * @param y Valoarea y a punctului
     * @param color Culoarea
     * @param image Imaginea
     */
    private void tryDrawPixel(final int x, final int y, final ARGB color,
                              final BufferedImage image) {
        if (isInCanvas(x, y, image)) {
            image.setRGB(x, y, color.getValue());
        }
    }

    /**
     * Colorarea mai multor linii.
     * @param lines Liniile
     * @param image Imaginea
     */
    private void drawLines(final Line[] lines, final BufferedImage image) {
        for (Line line: lines) {
            draw(line, image);
        }
    }

    /**
     * Desenarea a 8 puncte conform algoritmului de desenare.
     * @param xc XC
     * @param yc YC
     * @param x X
     * @param y Y
     * @param color Culoare
     * @param image Imaginea
     */
    private void drawCirclePixels(final int xc, final int yc, final int x, final int y,
                                  final ARGB color, final BufferedImage image) {
        tryDrawPixel(xc + x, yc + y, color, image);
        tryDrawPixel(xc - x, yc + y, color, image);
        tryDrawPixel(xc + x, yc - y, color, image);
        tryDrawPixel(xc - x, yc - y, color, image);
        tryDrawPixel(xc + y, yc + x, color, image);
        tryDrawPixel(xc - y, yc + x, color, image);
        tryDrawPixel(xc + y, yc - x, color, image);
        tryDrawPixel(xc - y, yc - x, color, image);

    }

    /**
     * Fill shape.
     * @param outlineColor Outline color
     * @param fillColor Fill color
     * @param image Image
     * @param startPoint Start point
     */
    private void floodFill(final ARGB outlineColor, final ARGB fillColor,
                           final BufferedImage image, final Point startPoint) {
        Queue<Point> pointQueue = new LinkedList<>();
        pointQueue.add(startPoint);

        while (!pointQueue.isEmpty()) {
            Point point = pointQueue.remove();
            if (isInCanvas(point.getX(), point.getY(), image)) {
                if (image.getRGB(point.getX(), point.getY()) != outlineColor.getValue()
                        && image.getRGB(point.getX(), point.getY()) != fillColor.getValue()) {
                    image.setRGB(point.getX(), point.getY(), fillColor.getValue());

                    pointQueue.add(new Point(point.getX() + 1, point.getY()));
                    pointQueue.add(new Point(point.getX() - 1, point.getY()));
                    pointQueue.add(new Point(point.getX(), point.getY() + 1));
                    pointQueue.add(new Point(point.getX(), point.getY() - 1));
                }
            }
        }
    }

    /**
     * Crearea liniilor dupa un vector de puncte.
     * @param points Vectorul de puncte
     * @param color Culoarea liniilor
     * @return Vector de linii
     */
    private Line[] createLines(final Point[] points, final ARGB color) {
//        I have N points and N lines
        int numberOfLines = points.length;
        Line[] lines = new Line[numberOfLines];

//        Create N - 1 lines
        for (int i = 0; i < numberOfLines - 1; i++) {
            lines[i] = new Line(points[i], points[i + 1], color);
        }

//        Create line (N - 1, 0)
        lines[numberOfLines - 1] = new Line(points[numberOfLines - 1], points[0], color);

        return lines;
    }

    /**
     * Gasirea centrului de greutate dupa un numar de puncte.
     * @param points Punctele
     * @param numberOfPoints Numarul de puncte
     * @return Un punct care reprezinta centrul de greutate al figurii
     */
    private Point getCentroid(final Point[] points, final int numberOfPoints) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < numberOfPoints; i++) {
            x += points[i].getX();
            y += points[i].getY();
        }

        x /= numberOfPoints;
        y /= numberOfPoints;

        return new Point(x, y);
    }

    /**
     * Gasirea punctelor unui romb.
     * @param diamond Rombul
     * @return Vector cu punctele
     */
    private Point[] getDiamondPoints(final Diamond diamond) {
        Point[] points = new Point[NO_LINES_SQUARE];

        points[0] = new Point(diamond.getMiddlePoint().getX() - diamond.getHorizontalDiagonal() / 2,
                diamond.getMiddlePoint().getY());
        points[1] = new Point(diamond.getMiddlePoint().getX(),
                diamond.getMiddlePoint().getY() + diamond.getVerticalDiagonal() / 2);
        points[2] = new Point(diamond.getMiddlePoint().getX() + diamond.getHorizontalDiagonal() / 2,
                diamond.getMiddlePoint().getY());
        points[3] = new Point(diamond.getMiddlePoint().getX(),
                diamond.getMiddlePoint().getY() - diamond.getVerticalDiagonal() / 2);

        return points;
    }

    /**
     * Gasirea punctelor unui patrat.
     * @param square Patratul
     * @return Vector cu punctele patratului
     */
    private Point[] getSquarePoints(final Square square) {
        Point[] points = new Point[NO_LINES_SQUARE];

        points[0] = new Point(square.getTopLeftPoint());
        points[1] = points[0].add(new Point(0, square.getSide() - 1));
        points[2] = points[0].add(new Point(square.getSide() - 1, square.getSide() - 1));
        points[3] = points[0].add(new Point(square.getSide() - 1, 0));

        return points;
    }

    /**
     * Gasirea punctelor unui dreptunghi.
     * @param rectangle Dreptunghiul
     * @return Vector cu punctele dreptunghiului
     */
    private Point[] getRectanglePoints(final Rectangle rectangle) {
        Point[] points = new Point[NO_LINES_SQUARE];
        points[0] = new Point(rectangle.getTopLeftPoint());
        points[1] = points[0].add(new Point(0, rectangle.getHeight() - 1));
        points[2] = points[0].add(new Point(rectangle.getWidth() - 1, rectangle.getHeight() - 1));
        points[3] = points[0].add(new Point(rectangle.getWidth() - 1, 0));

        return points;
    }

    public void draw(final Canvas canvas, final BufferedImage image) {
//        Fill figure
        floodFill(new ARGB(), canvas.getARGB(), image, new Point(0, 0));
    }

    public void draw(final Circle circle, final BufferedImage image) {
        int xc = circle.getMiddlePoint().getX();
        int yc = circle.getMiddlePoint().getY();
        int r = circle.getRadius();

        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        while (y >= x) {
            drawCirclePixels(xc, yc, x, y, circle.getOutlineColor(), image);
            x++;

            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }

            drawCirclePixels(xc, yc, x, y, circle.getOutlineColor(), image);
        }

        floodFill(circle.getOutlineColor(), circle.getFillColor(), image, circle.getMiddlePoint());
    }

    public void draw(final Diamond diamond, final BufferedImage image) {
//        Create points and lines
        Point[] points = getDiamondPoints(diamond);
        Line[] lines = createLines(points, diamond.getOutlineColor());

//        Draw lines
        drawLines(lines, image);

//        Fill figure
        Point centroid = getCentroid(points, NO_LINES_SQUARE);
        floodFill(diamond.getOutlineColor(), diamond.getFillColor(), image, centroid);
    }

    public void draw(final Line line, final BufferedImage image) {
        int x1 = line.getStartPoint().getX();
        int y1 = line.getStartPoint().getY();

        int x2 = line.getFinalPoint().getX();
        int y2 = line.getFinalPoint().getY();

        int x = x1;
        int y = y1;

        int deltaX = Math.abs(x2 - x1);
        int deltaY = Math.abs(y2 - y1);

        int s1 = (int) Math.signum(x2 - x1);
        int s2 = (int) Math.signum(y2 - y1);

        boolean interchanged;

        if (deltaY > deltaX) {
            final int temp = deltaX;
            deltaX = deltaY;
            deltaY = temp;
            interchanged = true;
        } else {
            interchanged = false;
        }

        int error = 2 * deltaY - deltaX;

        for (int i = 0; i <= deltaX; i++) {
            tryDrawPixel(x, y, line.getColor(), image);

            while (error > 0) {
                if (interchanged) {
                    x = x + s1;
                } else {
                    y = y + s2;
                }

                error = error - 2 * deltaX;
            }

            if (interchanged) {
                y = y + s2;
            } else {
                x = x + s1;
            }

            error = error + 2 * deltaY;
        }
    }

    public void draw(final Polygon polygon, final BufferedImage image) {
//        Create lines
        Line[] lines = createLines(polygon.getPoints(), polygon.getOutlineColor());

//        Draw lines
        drawLines(lines, image);

//        Fill figure
        Point centroid = getCentroid(polygon.getPoints(), polygon.getNumberOfPoints());
        floodFill(polygon.getOutlineColor(), polygon.getFillColor(), image, centroid);
    }

    public void draw(final Rectangle rectangle, final BufferedImage image) {
//        Create points and lines
        Point[] points = getRectanglePoints(rectangle);
        Line[] lines = createLines(points, rectangle.getOutlineColor());

//        Draw lines
        drawLines(lines, image);

//        Fill figure
        Point startPoint = rectangle.getTopLeftPoint().add(new Point(1, 1));
        floodFill(rectangle.getOutlineColor(), rectangle.getFillColor(), image, startPoint);
    }

    public void draw(final Triangle triangle, final BufferedImage image) {
//        Create lines
        Line[] lines = createLines(triangle.getPoints(), triangle.getOutlineColor());

//        Draw lines
        drawLines(lines, image);

//        Fill figure
        Point centroid = getCentroid(triangle.getPoints(), NO_LINES_TRIANGLE);
        floodFill(triangle.getOutlineColor(), triangle.getFillColor(), image, centroid);
    }

    public void draw(final Square square, final BufferedImage image) {
//        Create points and lines
        Point[] points = getSquarePoints(square);
        Line[] lines = createLines(points, square.getOutlineColor());

//        Draw lines
        drawLines(lines, image);

//        Fill figure
        Point startPoint = square.getTopLeftPoint().add(new Point(1, 1));
        floodFill(square.getOutlineColor(), square.getFillColor(), image, startPoint);
    }
}
