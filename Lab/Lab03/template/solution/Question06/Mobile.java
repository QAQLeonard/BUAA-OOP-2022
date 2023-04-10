package Lab.Lab03.template.solution.Question06;

public class Mobile implements IMessage
{
    String model;

    String brand;

    public Mobile(String model,String brand)
    {
        this.model= model;
        this.brand= brand;
    }

    @Override
    public void print()
    {
        System.out.println("Mobile Model: "+model);
        System.out.println("Mobile Brand: "+brand);
    }
}
