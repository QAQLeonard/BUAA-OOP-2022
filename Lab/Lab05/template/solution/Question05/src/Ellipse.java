
public class Ellipse extends Shape
{
    public Ellipse(double a, double b)
    {
        super(a, b);
        shapeType= "Ellipse";
    }
    @Override
    public double calcArea()
    {
        return Math.PI * a * b;
    }

}
