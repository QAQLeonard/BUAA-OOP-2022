package Question03;
public class Rectangle extends Shape
{
    private Rectangle(double a, double b)
    {
        super(a, b);
        shapeType= "Rectangle";
    }
//    @Override
//    public double calcArea()
//    {
//        return a * b;
//    }
    public static IShapeFactory factory = new IShapeFactory()
    {
        @Override
        public Shape makeShape(double a, double b)
        {
            return new Rectangle(a, b);
        }
    };
}

