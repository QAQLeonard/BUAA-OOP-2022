
public class Rectangle extends Shape
{
    public Rectangle(double a, double b)
    {
        super(a, b);
        shapeType= "Rectangle";
    }
    @Override
    public double calcArea()
    {
        return a * b;
    }

}

