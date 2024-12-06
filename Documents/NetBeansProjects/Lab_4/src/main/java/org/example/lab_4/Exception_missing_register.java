package org.example.lab_4;

public class Exception_missing_register extends My_Exception{
    String registr;
    Exception_missing_register(String registr_)
    {
        super("Eror: missing register: ");
        registr = registr_;
    }
    @Override
    public String getMessage()
    {
        return super.getMessage() + registr + ".";
    }
}
