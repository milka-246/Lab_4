package org.example.lab_4;

public class Exception_unidentified_command extends My_Exception
{
    String com;
    Exception_unidentified_command(String com_)
    {
        super("Error: unidentified command: ");
        com = com_;
    }
    @Override
    public String getMessage()
    {
        return super.getMessage() + com + ".";
    }
}
