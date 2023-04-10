import java.util.ArrayList;

public class Q1_2Test
{
    public static void main(String[] args)
    {
        Employee employee1 = new Employee();
        employee1.SetName("张三");
        employee1.SetAge(20);
        employee1.SetSex("Female");
        employee1.SetSalary(10000);

        Employee employee2 = new Employee("Jon", 30, "Others");
        employee2.SetSalary(20000);
        Employee employee3 = new Employee("Tom", 30, "Male");
        employee3.SetSalary(30000);
        Employee employee4 = new Employee("Keel", 30, "Male");
        employee3.SetSalary(30000);
        Employee employee5 = new Employee("lll", 30, "Male");
        employee3.SetSalary(30000);
        Manager manager1 = new Manager("m4", 30, "Male");
        Manager manager2 = new Manager("m3", 30, "Male");
        Manager manager3 = new Manager("m2", 30, "Male");
        Manager manager4 = new Manager("m1", 30, "Male");
        Manager manager5 = new Manager("m5", 30, "Male");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(employee1);
        roles.add(employee2);
        roles.add(employee3);
        roles.add(manager1);
        roles.add(manager2);
        for (Role role : roles)
        {
            role.work();
            role.play();
            ((Employee)role).sing();
        }
    }
}


interface Vehicle
{
    void start();

    void stop();
}

class Car implements Vehicle
{
    public void start()
    {
        System.out.println("Car is starting");
    }

    public void stop()
    {
        System.out.println("Car is stopping");
    }
}

class Bus implements Vehicle
{
    public void start()
    {
        System.out.println("Bus is starting");
    }

    public void stop()
    {
        System.out.println("Bus is stopping");
    }
}

abstract class Role
{
    public Role()
    {
        System.out.println("Role is created by default constructor");
    }

    public Role(String name, int age, String sex)
    {
        this.name = name;
        this.age = age;
        this.sex = sex;
        System.out.println("Role is created by constructor with parameters");
    }

    protected String name;
    protected int age;
    protected String sex;

    public void SetName(String name)
    {
        this.name = name;
    }

    public String GetName()
    {
        return name;
    }

    public void SetAge(int age)
    {
        this.age = age;
    }

    public int GetAge()
    {
        return age;
    }

    public void SetSex(String sex)
    {
        this.sex = sex;
    }

    public String getSex()
    {
        return sex;
    }

    abstract void play();

    abstract void work();
}

class Employee extends Role
{
    public Employee()
    {
        super();
        System.out.println("Employee is created by default constructor");
    }

    public Employee(String name, int age, String sex)
    {
        super(name, age, sex);
        System.out.println("Employee is created by constructor with parameters");
    }

    protected int salary;

    public void SetSalary(int salary)
    {
        this.salary = salary;
    }

    public int GetSalary()
    {
        return salary;
    }

    public void play()
    {
        System.out.println("Employee is playing");
    }

    final static String ID = "21373339";

    final void sing()
    {
        System.out.println("Employee is singing");
    }

    @Override
    void work()
    {
        System.out.println("Employee" + name + " is working, age is" + age + ", sex is" + sex);
        System.out.println("Employee ID is " + ID + " and salary is " + salary);
    }
}

class Manager extends Employee
{
    public Manager()
    {
        super();
        System.out.println("Manager is created by default constructor");
    }

    public Manager(String name, int age, String sex)
    {
        super(name, age, sex);
        System.out.println("Manager is created by constructor with parameters");
    }

    final Vehicle vehicle = new Car();
    protected String department;

    public void SetDepartment(String department)
    {
        this.department = department;
    }

    public String GetDepartment()
    {
        return department;
    }

    @Override
    void work()
    {
        System.out.println(department);
        vehicle.start();
        vehicle.stop();
        System.out.println("Manager" + name + " is working, age is" + age + ", sex is" + sex);
        System.out.println("Manager ID is " + ID + " and salary is " + salary);

    }
}