public class Main
{
    public static void main(String[] args)
    {
        SpiralAbyss abyss = new SpiralAbyss(2);
        Person person = new Person("Klee", 1);
        abyss.addPartner(person);
        Person person2 = new Person("Yae Miko", 1);
        abyss.addPartner(person2);
        Person person3 = new Person("Ganyu", 1);
        abyss.addPartner(person3);
        abyss.printPartner();
    }
}