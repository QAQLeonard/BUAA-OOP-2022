import java.util.*;

public class SpiralAbyss extends Room
{
    ArrayList<Person> partner;
    public SpiralAbyss(int MaxPeople)
    {
        this.setMaxPeople(MaxPeople);
        partner = new ArrayList<Person>();
    }

    public void addPartner(Person p)
    {
        if(partner.size() < MaxPeople)
        {
            partner.add(p);
        }
        else
        {
            System.out.println("The room is full");
        }
    }
    public void removePartner(Person p)
    {
        if(partner.contains(p))
        {
            partner.remove(p);
        }
        else
        {
            System.out.println("The person is not in the room");
        }
    }
    public void printPartner()
    {
        for(Person p : partner)
        {
            System.out.println(p.name);
        }
    }
}

