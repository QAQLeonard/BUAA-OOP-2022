package Lab.Lab02.template.solution.Question17;

class Question17_people
{
    int weight;
    String name;
    public Question17_people(int weight,String name)
    {
        this.weight=weight;
        this.name=name;
    }
    public void lose_weight(int num)
    {
        System.out.println("减了"+num+"kg\n");
        weight-=num;
    }
    public int getweight()
    {
        return weight;
    }
    public void show()
    {
        System.out.println("姓名："+name+"\n"+"体重："+weight);
    }
}

public class Question17
{
    public static void main(String[] args)
    {
        Question17_people p=new Question17_people(70,"小王");
        p.show();
        p.lose_weight(25);
        p.show();
    }

}
