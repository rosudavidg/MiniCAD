import shapes.Canvas;
import utils.DrawShapeVisitor;
import utils.ReadShapeVisitor;
import utils.ShapeFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

final class Main {

    private Main() {
    }

    public static void main(final String[] args) throws Exception {
//        Input and Output files
        final Scanner scanner = new Scanner(new File(args[0]));
        final File outputFile = new File("drawing.png");

//        Factory and Visitors for shapes (Singleton)
        ShapeFactory shapeFactory = ShapeFactory.getInstance();
        ReadShapeVisitor readShapeVisitor = shapeFactory.getReadShapeVisitor();
        DrawShapeVisitor drawShapeVisitor = shapeFactory.getDrawShapeVisitor();

//        Read number of shapes
        final int numberOfShapes;
        numberOfShapes = scanner.nextInt();

//        Array list of all shapes
        ArrayList<ShapeFactory.Shape> shapeList = new ArrayList<>();

//        Read every shape
        for (int count = 0; count < numberOfShapes; count++) {
            ShapeFactory.Shape newShape = shapeFactory.getFigure(scanner.next());
            newShape.accept(readShapeVisitor, scanner);
            shapeList.add(newShape);
        }

//        Create image by CANVAS
        BufferedImage image = ((Canvas) shapeList.get(0)).createImageByCanvas();

//        Draw every figure (CANVAS included)
        for (ShapeFactory.Shape shape : shapeList) {
            shape.accept(drawShapeVisitor, image);
        }

//        Export image as PNG
        ImageIO.write(image, "png", outputFile);
    }
}
