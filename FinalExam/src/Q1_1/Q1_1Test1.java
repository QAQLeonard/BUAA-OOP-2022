//public class Q1_1Test1
//{
//    public static void main(String[] args)
//    {
//        Feeder feeder = new Feeder();
//        Animal cat = new Cat();
//        Animal dog = new Dog();
//        Food fish = new Fish();
//        Food bone = new Bone();
//        feeder.feed(cat, fish);
//        feeder.feed(cat, bone);
//        feeder.feed(dog, fish);
//        feeder.feed(dog, bone);
//    }
//}
//
//class Animal
//{
//    public void eat(Food food)
//    {
//        System.out.println("Animal is eating");
//    }
//
//    public String name;
//};
//
//class Cat extends Animal
//{
//    public Cat()
//    {
//        name = "Cat";
//    }
//
//    public void eat(Food food)
//    {
//        if (food instanceof Fish)
//        {
//            System.out.println("Cat is eating fish");
//        }
//        else
//        {
//            System.out.println("Cat don't like bone");
//        }
//    }
//}
//
//class Dog extends Animal
//{
//    public Dog()
//    {
//        name = "Dog";
//    }
//
//    public void eat(Food food)
//    {
//        if (food instanceof Bone)
//        {
//            System.out.println("Dog is eating bone");
//        }
//        else
//        {
//            System.out.println("Dog don't like fish");
//        }
//    }
//};
//
//class Feeder
//{
//    public void feed(Animal animal, Food food)
//    {
//        System.out.println("Feeding " + animal.name + " with " + food.name);
//        animal.eat(food);
//    }
//}
//
//class Food
//{
//    public String name;
//};
//
//class Fish extends Food
//{
//    public Fish()
//    {
//        name = "Fish";
//    }
//}
//
//class Bone extends Food
//{
//    public Bone()
//    {
//        name = "Bone";
//    }
//}
//
//
