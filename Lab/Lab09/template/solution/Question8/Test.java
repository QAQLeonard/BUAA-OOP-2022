import java.util.ArrayList;
import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        ArrayList<Triangle> list = new ArrayList<Triangle>();
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 4; i++)
        {
            System.out.println("Please input the length of the " + (i + 1) + "th triangle:");
            double x,y,z;
            try
            {
                x = input.nextDouble();
                y = input.nextDouble();
                z = input.nextDouble();
            }
            catch (Exception e)
            {
                System.out.println("Please input a number!");
                i--;
                continue;
            }
            try
            {
                Triangle t = new Triangle(x, y, z);
                list.add(t);
            }
            catch (Triangle.NotTriangleException notTriangleException)
            {
                System.out.println("your input is not a triangle");
                i--;
            }
        }

        for (Triangle t : list)
        {
            t.showInfo();
            System.out.println("Area = " + t.getArea()+"\n");
        }
    }
}
