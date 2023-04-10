package Question03;
public class Rhombus extends Shape
{
    private Rhombus(double a, double b)
    {
        super(a, b);
        shapeType= "Rhombus";
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
            return new Rhombus(a, b);
        }
    };
    public static void runnm()
    {
        System.out.println("Rhombus");
    }
}
