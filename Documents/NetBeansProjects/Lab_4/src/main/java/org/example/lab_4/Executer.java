package org.example.lab_4;

public class Executer {
    ICPU cpu;
    Executer(ICPU cpu) {this.cpu = cpu;}

    public void run(Program prog)
    {
    for(Command p:prog) {
        try {
            cpu.work(p);
        } catch (My_Exception e) {
            System.out.println(e.getMessage());
        }
    }
    }


}
