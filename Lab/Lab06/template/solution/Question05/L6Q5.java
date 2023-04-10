import java.util.*;


public class L6Q5
{
    public static void main(String[] args)
    {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        ShapeFactory factory = new ShapeFactory();

        for (Shape shape : shapes)
        {
            System.out.println(shape.calcArea());
        }
    }
}
