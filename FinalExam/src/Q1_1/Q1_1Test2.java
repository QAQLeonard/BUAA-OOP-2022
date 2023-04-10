public class Q1_1Test2
{
    public static void main(String[] args)
    {
        Feeder feeder = new Feeder();
        Animal cat = new Cat();
        Animal dog = new Dog();
        Food fish = new Fish();
        Food bone = new Bone();
        feeder.feed(cat, fish);
        feeder.feed(cat, bone);
        feeder.feed(dog, fish);
        feeder.feed(dog, bone);
    }
}

class Animal
{
    public void eat(Food food)
    {
        System.out.println("Animal is eating");
    }

    public String name;
};

class Cat extends Animal
{
    public Cat()
    {
        name = "Cat";
    }

    public void eat(Food food)
    {
        if (food instanceof Fish)
        {
            System.out.println("Cat is eating fish");
        }
        else
        {
            System.out.println("Cat don't like bone");
        }
    }
}

class Dog extends Animal
{
    public Dog()
    {
        name = "Dog";
    }

    public void eat(Food food)
    {
        if (food instanceof Bone)
        {
            System.out.println("Dog is eating bone");
        }
        else
        {
            System.out.println("Dog don't like fish");
        }
    }
};

class Feeder
{
    public void feed(Animal animal, Food food)
    {
        System.out.println("Feeding " + animal.name + " with " + food.getname());
        animal.eat(food);
    }
}

interface Food
{
    String getname();
};

class Fish extends Animal implements Food
{
    @Override
    public String getname()
    {
        return "fish";
    }
}

class Bone implements Food
{
    @Override
    public String getname()
    {
        return "Bone";
    }

}

//如果父类对象不是子类对象的实例，就会发生编译器错误。
