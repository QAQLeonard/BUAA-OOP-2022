//AnimalTest1.java

import java.util.List;
import java.util.ArrayList;

public class AnimalTest0
{
    public void testDemo(List<?> s)
    {
        for (Object obj : s)
        {
            System.out.println("My type is " + obj.getClass().getName());
        }
    }

    public static void main(String[] args)
    {
        AnimalTest0 test = new AnimalTest0();
        Dog0 dog = new Dog0();
        Animal0 animal = new Animal0();
        List<Animal0> s = new ArrayList<Animal0>();
        s.add(dog);
        s.add(animal);
        test.testDemo(s);
    }
}
