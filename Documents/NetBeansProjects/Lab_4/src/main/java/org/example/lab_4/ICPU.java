package org.example.lab_4;


public interface ICPU  {
     void work (Command com) throws My_Exception;
     String getA();
     String getB();
     String getC();
     String getD();
     String getM(int i);
     void reset();
}
