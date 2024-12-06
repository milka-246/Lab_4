package org.example.lab_4;

public class Exception_exceeded_index extends My_Exception {
    int index;
    String operation;
    Exception_exceeded_index(int index_, String operation_)
    {
        super("Error: exceeded index: ");
        index = index_;
        operation = operation_;
    }
    @Override
    public String getMessage()
    {
        return super.getMessage() + index + ", operation: " + operation + ".";
    }
}
