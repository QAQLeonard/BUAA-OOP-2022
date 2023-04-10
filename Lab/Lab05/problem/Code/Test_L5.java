class Parent_T
{
    int num = 4;

    protected void foo()
    {
        System.out.println("foo() of Parent");
    }

    static protected void bar()
    {
        System.out.println("bar() of Parent");
    }
}

class Child_T extends Parent_T
{
    int num = 5;

    protected void foo()
    {
        System.out.println("foo() of Child");
    }

    static protected void bar()
    {
        System.out.println("bar() of Child");
    }
}

public class Test_L5
{
    public static void main(String[] args)
    {
        Parent_T f1 = new Parent_T();
        System.out.println(f1.num);

        Parent_T f2 = new Child_T();
        System.out.println(f2.num);

        Child_T c = new Child_T();
        System.out.println(c.num);

        f1.foo();
        f2.foo();
        c.foo();

        f1.bar();
        f2.bar();
        c.bar();
    }
}
