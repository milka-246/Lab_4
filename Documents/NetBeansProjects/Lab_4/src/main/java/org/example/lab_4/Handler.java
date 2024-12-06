package org.example.lab_4;

import java.util.HashMap;

public class Handler implements ICPU
{
    public HashMap <String, Integer> registr = new HashMap<>();
    public int size = 1024;
    public int[] memory = new int [size];
    Handler()
    {
        for (int i = 0; i < size; i++)
        {
            memory[i] = 0;
        }
        registr.put("a",0);
        registr.put("b",0);
        registr.put("c",0);
        registr.put("d",0);
    }

    @Override
    public String getM(int i)
    {
        return Integer.toString(memory[i]);
    }

    @Override
    public String getA()
    {
        return Integer.toString(registr.get("a"));
    }
    @Override
    public String getB()
    {
        return Integer.toString(registr.get("b"));
    }
    @Override
    public String getC()
    {
        return Integer.toString(registr.get("c"));
    }
    @Override
    public String getD()
    {
        return Integer.toString(registr.get("d"));
    }
    @Override
    public void reset()
    {
        for (int i = 0; i < size; i++)
        {
            memory[i] = 0;
        }
        registr.put("a",0);
        registr.put("b",0);
        registr.put("c",0);
        registr.put("d",0);
    }

    @Override
    public void work (Command com) throws My_Exception
    {
        try
        {
            Commands T = Commands.valueOf(com.info[0]);
            switch (T)
            {
                case ld: ld(com.info[1], Integer.parseInt(com.info[2])); break;
                case print: print(); break;
                case st: st(com.info[1], Integer.parseInt(com.info[2])); break;
                case mv: mv(com.info[1], com.info[2]); break;
                case init: init(Integer.parseInt(com.info[1]), Integer.parseInt(com.info[2])); break;
                case add: add(); break;
                case sub: sub(); break;
                case mult: mult(); break;
                case div: div(); break;
            }
        }
        catch (IllegalArgumentException e)
        {
            throw new Exception_unidentified_command(com.info[0]);
        }
    }
    private void ld (String in, int index) throws Exception_exceeded_index, Exception_missing_register
    {
        if((0<=index) && (index < size))
        {
            if(registr.containsKey(in)) {
                registr.remove(in);
                registr.put(in, memory[index]);
            }
            else
                throw new Exception_missing_register(in);
        }
        else
            throw new Exception_exceeded_index(index, "ld");
    }

    private void print()
    {
        System.out.println(registr.get("a")+ " "+ registr.get("b") + " "+ registr.get("c") + " "+ registr.get("d") );
    }

    private void st (String in, int index) throws Exception_exceeded_index, Exception_missing_register
    {
        if((0<=index) && (index < size))
            if(registr.containsKey(in)) {
                memory[index] = registr.get(in);
            }
            else
                throw new Exception_missing_register(in);
        else
            throw new Exception_exceeded_index(index, "st");
       /* if(in.equals("a")) memory[index] = a;
        if(in.equals("b")) memory[index] = b;
        if(in.equals("c")) memory[index] = c;
        if(in.equals("d")) memory[index] = d;
        */

    }

    private void mv (String in1, String in2) throws Exception_missing_register
    {
        if(registr.containsKey(in1)) {
            if(registr.containsKey(in2)) {
                registr.remove(in1);
                registr.put(in1,registr.get(in2));
            }
            else
                throw new Exception_missing_register(in2);
        }
        else
            throw new Exception_missing_register(in1);


       /* if(in1.equals("a")&&in2.equals("b")) a = b;
        if(in1.equals("a")&&in2.equals("c")) a = c;
        if(in1.equals("a")&&in2.equals("d")) a = d;
        if(in1.equals("b")&&in2.equals("a")) b = a;
        if(in1.equals("b")&&in2.equals("c")) b = c;
        if(in1.equals("b")&&in2.equals("d")) b = d;
        if(in1.equals("c")&&in2.equals("a")) c = a;
        if(in1.equals("c")&&in2.equals("b")) c = b;
        if(in1.equals("c")&&in2.equals("d")) c = d;
        if(in1.equals("d")&&in2.equals("a")) d = a;
        if(in1.equals("d")&&in2.equals("b")) d = b;
        if(in1.equals("d")&&in2.equals("c")) d = c;
        */
    }

    private void init (int index, int value) throws Exception_exceeded_index
    {
        if((0<=index) && (index < size))
            memory[index] = value;
        else
            throw new Exception_exceeded_index(index, "init");
    }

    private void add()
    {
        registr.remove("d");
        registr.put("d", registr.get("a") + registr.get("b"));
       // d = a + b;
    }

    private void sub()
    {
        registr.remove("d");
        registr.put("d", registr.get("a") - registr.get("b"));
        //d = a - b;
    }

    private void mult()
    {
        registr.remove("d");
        registr.put("d", registr.get("a") * registr.get("b"));
        //d = a*b;
    }

    private void div() throws Exception_div_zero
    {
        if(registr.get("b") != 0)
        {
            registr.remove("d");
            registr.put("d",registr.get("a") / registr.get("b"));
        }
        else
        {
            throw new Exception_div_zero();
        }
        /*if(b != 0)
            d = a/b; //небезопасный код
        else
            return;
         */
    }
}
