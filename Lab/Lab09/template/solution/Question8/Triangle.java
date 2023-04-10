public class Triangle
{
    public double x, y, z;

    public Triangle(double x, double y, double z) throws NotTriangleException
    {
        if (!(x > 0 && y > 0 && z > 0 && x + y > z && x + z > y && y + z > x))
        {
            throw new NotTriangleException();
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public class NotTriangleException extends Exception
    {
        public NotTriangleException()
        {
        }
    }

    public double getArea()
    {
        double p = (x + y + z) / 2;
        return Math.sqrt(p * (p - x) * (p - y) * (p - z));
    }

    public void showInfo()
    {

        System.out.println("x = " + x + "\n" + "y = " + y + "\n" + "z = " + z);

    }

}

