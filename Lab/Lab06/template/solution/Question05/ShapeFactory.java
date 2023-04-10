import java.util.Random;

public class ShapeFactory
{
    public enum ShapeType
    {
        ELLIPSE, RECTANGLE, RHOMBUS;
    }
    public Shape makeShape(ShapeType type, double a, double b)
    {
        Shape shape = null;
        switch(type)
        {
            case ELLIPSE:
                shape = new Ellipse(a, b);
                break;
            case RECTANGLE:
                shape = new Rectangle(a, b);
                break;
            case RHOMBUS:
                shape = new Rhombus(a, b);
                break;
        }
        return shape;
    }
    public Shape randomNextShape()
    {
        Shape shape = null;
        Random random = new Random();
        int type = random.nextInt(3);
        double a = random.nextDouble() * 10;
        double b = random.nextDouble() * 10;
        switch(type)
        {
            case 0:
                shape = new Ellipse(a, b);
                break;
            case 1:
                shape = new Rectangle(a, b);
                break;
            case 2:
                shape = new Rhombus(a, b);
                break;
            default:
                break;
        }
        return shape;
    }
}
