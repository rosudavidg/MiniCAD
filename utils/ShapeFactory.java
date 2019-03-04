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
import java.util.HashMap;
import java.util.Scanner;

public final class ShapeFactory {
    private HashMap<String, Class<?>> shapesHashMap = new HashMap<>();
    private static ShapeFactory instance = new ShapeFactory();

    private ShapeFactory() {
        createShapesHashMap();
    }

    public static ShapeFactory getInstance() {
        return instance;
    }

    /**
     * Crearea unui HashMap:
     * String - numele figurii
     * Class  - clasa figurii.
     */
    private void createShapesHashMap() {
        shapesHashMap.put("LINE", Line.class);
        shapesHashMap.put("CIRCLE", Circle.class);
        shapesHashMap.put("DIAMOND", Diamond.class);
        shapesHashMap.put("POLYGON", Polygon.class);
        shapesHashMap.put("RECTANGLE", Rectangle.class);
        shapesHashMap.put("SQUARE", Square.class);
        shapesHashMap.put("TRIANGLE", Triangle.class);
    }

    /**
     * Intoarce o instanta a unei figuri noi in functie de tip.
     * @param type Tipul figurii
     * @return Noua figura
     * @throws Exception Exceptie pentru lipsa fisierului I/O.
     */
    public Shape getFigure(final String type) throws Exception {

        if (type.equals("CANVAS")) {
            return Canvas.getInstance();
        }

        return ((Shape) (shapesHashMap.get(type)).newInstance());
    }

    /**
     * Intoarce o noua instanta a unui Visitor pentru desenare.
     * @return Instanta DrawShapeVisitor
     */
    public DrawShapeVisitor getDrawShapeVisitor() {
        return DrawShapeVisitor.getInstance();
    }

    /**
     * Intoarce o noua instanta a unui Visitor pentru citire.
     * @return Instanta ReadShapeVisitor
     */
    public ReadShapeVisitor getReadShapeVisitor() {
        return ReadShapeVisitor.getInstance();
    }

    /**
     * Interfata pentru fiecare tip de figura.
     */
    public interface Shape {
        void accept(DrawShapeVisitor drawShapeVisitor, BufferedImage image);
        void accept(ReadShapeVisitor readShapeVisitor, Scanner scanner);
    }

    interface ShapeVisitor {

    }
}
