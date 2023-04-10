
public class Rectangle extends Shape
{
    public Rectangle(double a, double b)
    {
        super(a, b);
//        shapeType= ShapeType.RECTANGLE;
    }
    @Override
    public double calcArea()
    {
        return a * b;
    }
}

