
public class Rhombus extends Shape
{
    public Rhombus(double a, double b)
    {
        super(a, b);
        shapeType= "Rhombus";
    }
    @Override
    public double calcArea()
    {
        return a * b;
    }

}
