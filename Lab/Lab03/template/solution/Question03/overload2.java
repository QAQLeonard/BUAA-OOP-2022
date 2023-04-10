package Lab.Lab03.template.solution.Question03;

class Dog
{
    int age;
    String name;
    public Dog(int age)
    {
        this.age=age;
        System.out.println("this dog is "+age+" years old");
    }

    public Dog(int age,String name)
    {
        this(age);
        this.name=name;
        System.out.println("this dog's name is "+name);
    }

    public void bark(String str)
    {
        System.out.println(name+"你在狗吠什么"+str+"?");
    }
    public void bark(int a)
    {
        System.out.println(name+"你在狗哮什么"+a+"?");
    }
    public void bark(int a,String str)
    {
        System.out.println(name+"你不要再狗吠"+a+str+"了");
    }
    public void bark(String str,int a)
    {
        System.out.println(name+"你不要再狗哮"+str+a+"了");
    }
}

public class overload2
{
    public static void main(String[] args)
    {
        Dog gougou=new Dog(3);
        Dog lff=new Dog(40,"梁非凡");
        lff.bark("吔屎啦");
        lff.bark(123);
        lff.bark(123,"吔屎啦");
        lff.bark("吔屎啦",123);
    }
}
