package Lab.Lab03.template.solution.Question06;

public class Music implements IMessage
{
    String name;

    String type;

    public Music(String name,String type)
    {
        this.name= name;
        this.type= type;
    }

    @Override
    public void print()
    {
        System.out.println("Mobile Model: "+name);
        System.out.println("Mobile type: "+type);
    }
}
