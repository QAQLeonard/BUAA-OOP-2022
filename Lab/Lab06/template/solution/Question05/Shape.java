
public abstract class Shape
{
    protected double a;
    protected double b;
//    public ShapeType shapeType;
    public Shape()
    {
        this(0.0, 0.0);
    }

    public Shape(double a, double b)
    {
        if(a<=0)
        {
            this.a = 0.0;
            System.out.println("a is not positive");
        }
        else
        {
            this.a = a;
        }
        if(b<=0)
        {
            this.b = 0.0;
            System.out.println("b is not positive");
        }
        else
        {
            this.b = b;
        }
    }

    /** calcArea
     * 计算形状的面积
     * @return 面积
     */
    abstract public double calcArea();

    public double getA()
    {
        return a;
    }
    public double getB()
    {
        return b;
    }
    public void setA(double a)
    {
        this.a = a;
    }
    public void setB(double b)
    {
        this.b = b;
    }
}
