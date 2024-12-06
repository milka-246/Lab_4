package org.example.lab_4;

import java.util.*;
import java.util.stream.Collectors;

public class Program implements Iterable<Command> {
    ArrayList<Command> prog;
    ArrayList<IObserver> allObserver = new ArrayList<>();
    int i = 0;

    @Override
            public Iterator<Command> iterator()
    {
        return new Iterator<Command>() {
            int IP = 0;
            @Override
            public boolean hasNext() {
                return IP < prog.size();
            }

            @Override
            public Command next() {
                return prog.get(IP++);
            }
        };
    }

    void reset()
    {
        prog.clear();
    }

    void up()
    {
        if(i>0)
        {
            Command h = prog.get(i);
            prog.set(i, prog.get(i-1));
            prog.set(i-1,h);
            eventCall();
        }
    }

    void plus()
    {
        if(i<prog.size()-1)
        {
            i++;
        }
    }

    void down()
    {
        if(i<prog.size()-1)
        {
            Command h = prog.get(i);
            prog.set(i, prog.get(i+1));
            prog.set(i+1,h);
            eventCall();
        }
    }

    void del_com(Command p)
    {
        prog.remove(p);
        eventCall();
    }

    void eventCall()
    {
        allObserver.forEach(action->action.event(this));
    }

    public void addObserver(IObserver e)
    {
        allObserver.add(e);
        eventCall();
    }



    Program(Command [] prog_) throws Exception_unidentified_command
    {
        prog = new ArrayList<>();

        for(Command a: prog_)
        {
            try
            {
                Commands.valueOf(a.info[0]);
                prog.add(a);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception_unidentified_command(a.info[0]);
            }
        }
        eventCall();
    }

    Program() {
        prog = new ArrayList<>();
    }

    public void add(Command com) throws Exception_unidentified_command
    {
        try
        {
            Commands.valueOf(com.info[0]);
            prog.add(com);
        }
        catch (IllegalArgumentException e)
        {
            throw new Exception_unidentified_command(com.info[0]);
        }
        eventCall();
    }

    ArrayList<String> getInstruction()
    {
        ArrayList<String> statistic = new ArrayList<>();
         prog
                .stream()
                .collect(Collectors.groupingBy(Command::getCom, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((o1,o2) -> Long.compare(o2.getValue(), o1.getValue()))
                .forEach(o->statistic.add(o.getKey().toString()+" "+o.getValue().toString()));
        System.out.println();
        return statistic;
    }

   public void printInstruction()
   {
       System.out.println("All instruction:");

            prog
               .stream()
               .collect(Collectors.groupingBy(Command::getCom, Collectors.counting()))
               .entrySet()
               .stream()
               .sorted((o1,o2) -> Long.compare(o2.getValue(), o1.getValue()))
               .forEach(System.out::println);
       System.out.println();
   }

    public void  mostPopularInstruction()
    {
        Map.Entry<Commands, Long> statistic =
                prog
                        .stream()
                        .collect(Collectors.groupingBy(Command::getCom, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue()).orElse(null);
        if(statistic != null) {
            System.out.println("Most popular instruction:");
            prog
                    .stream()
                    .collect(Collectors.groupingBy(Command::getCom, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(i -> i.getValue() == statistic.getValue())
                    .forEach(System.out::println);
            System.out.println();
        }
    }

    public void addreses ()
    {
        Set<Integer> ad = new HashSet<>();
        for(Command tyt: prog)
        {
            Commands T = Commands.valueOf(tyt.info[0]);
            switch (T)
            {
                case ld:
                        ad.add(Integer.valueOf(tyt.info[2]));
                    break;
                case st:
                        ad.add(Integer.valueOf(tyt.info[2]));
                    break;
                case init:
                        ad.add(Integer.valueOf(tyt.info[1]));
                    break;
            }
        }
        System.out.println("Address range:");
        Integer max =
                ad
                .stream()
                .max((val1, val2) -> Integer.compare(val1, val2))
                        .orElse(null);
        Integer min =
                ad
                        .stream()
                        .min((val1, val2) -> Integer.compare(val1, val2))
                        .orElse(null);
        if (max != null)
            System.out.println(min + "..."  + max);
        System.out.println();

    }

}
