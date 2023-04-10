package Lab.Lab03.template.solution.Question07;

class Person
{
    String name;
    String age;
    String sex;

    public Person(String name,String age,String sex)
    {
        this.name=name;
        this.age=age;
        this.sex=sex;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getAge()
    {
        return age;
    }

    public void work()
    {
        System.out.println("working");
    }
    public void showAge()
    {
        System.out.println("age: "+age);
    }
}

public class TestPerson
{
    public static void main(String[] args)
    {
        Person p = new Person("cxk", "2.5", "其他");
        p.setAge("2.5");
        System.out.println(p.getAge());

        Person p2 = new Person("jige", "2.5", "其他");
        p2.setAge("12.5");
        System.out.println(p2.getAge());
    }
}
