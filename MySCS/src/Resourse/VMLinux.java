package Resourse;

import User.users;

import java.io.Serializable;

public class VMLinux extends VisualMachine implements Serializable
{
    public VMLinux(users owner)
    {
        super(owner);
    }
}