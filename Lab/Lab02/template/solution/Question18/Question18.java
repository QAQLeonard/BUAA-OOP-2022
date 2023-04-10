package Lab.Lab02.template.solution.Question18;

class Question18_people
{
    int age;
    String name;
    public Question18_people(int age,String name)
    {
        this.age=age;
        this.name=name;
    }
}

class Question18_buyer extends Question18_people
{
    String[] ingredients;
    public Question18_buyer(int age, String name,String... args)
    {
        super(age, name);
        ingredients=args;
    }
    public String[] show()
    {
        return ingredients;
    }
}

class Question18_cook extends Question18_people
{
    String[] dish;
    public Question18_cook(int age, String name,String... args)
    {
        super(age, name);
        dish=args;
    }
    public String[] show()
    {
        return dish;
    }
}

public class Question18
{
    public  static void main(String[] args)
    {
        Question18_buyer gugu=new Question18_buyer(40,"姑姑","鱼","鸡肉");
        Question18_buyer mama=new Question18_buyer(40,"妈妈","羊肉","五花肉");
        Question18_buyer wo=new Question18_buyer(40,"卢本伟","牛肉","蔬菜");

    }
}
