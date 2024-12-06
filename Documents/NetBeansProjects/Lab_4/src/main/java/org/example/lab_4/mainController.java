package org.example.lab_4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class mainController implements IObserver
{
    Program prog = BProgram.build();
    Program statistic = new Program();
    ICPU cpu = BCPU.build();
    Executer exec = new Executer(cpu);

    @FXML
    Label A;
    @FXML
    Label B;
    @FXML
    Label C;
    @FXML
    Label D;
    @FXML
    GridPane allProg;
    @FXML
    GridPane Memory;
    @FXML
    GridPane Statistic;

    @FXML
    void initialize()
    {
        prog.addObserver(this);
        try { prog.add(new Command("init", "11", "25"));
            prog.add(new Command("init 10 20"));
            prog.add(new Command("init", "11", "25"));
            prog.add(new Command("ld", "a", "10"));
            prog.add(new Command("ld", "b", "11"));
            prog.add(new Command("ld", "c", "11"));
            prog.add(new Command("add"));
            prog.add(new Command("mv", "a", "d"));
            prog.add(new Command("add"));
            prog.add(new Command("print"));}catch(Exception_unidentified_command e) {System.out.println(e.getMessage());}
        doing_visout_work();
    }

    @FXML
    void reset()
    {
        prog.i = 0;
        statistic.reset();
        cpu.reset();
        doing_visout_work();
        doing_statistic();
        event(prog);
    }


    @FXML
    void add()
    {
        prog.add(new Command("init", "11", "25"));
    }

    void doing_visout_work()
    {
        A.setText(cpu.getA());
        B.setText(cpu.getB());
        C.setText(cpu.getC());
        D.setText(cpu.getD());

        Memory.getChildren().clear();
        int k = 0;
        for (int i = 0; i < 1024; i++) {
            memControler mc = new memControler();
            FXMLLoader fxmlLoader = new FXMLLoader(
                    appMain.class.getResource("mem.fxml"));
            fxmlLoader.setController(mc);
            try
            {
                Pane pane = fxmlLoader.load();
                if(cpu.getM(i).equals(Integer.toString(0)))
                    mc.setMem(i, cpu.getM(i), 0);
                else
                    mc.setMem(i, cpu.getM(i), 1);
                switch (k)
                {
                    case 0: Memory.addColumn(0, pane); k++; break;
                    case 1: Memory.addColumn(1, pane); k++; break;
                    case 2: Memory.addColumn(2, pane); k++; break;
                    case 3: Memory.addColumn(3, pane); k++; break;
                    case 4: Memory.addColumn(4, pane); k = 0; break;
                }
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    void doing_statistic() {

        ArrayList<String> stat= prog.getInstruction();//заменить prog на statistic
        Statistic.getChildren().clear();
        for (String i : stat) {
            String [] in;
            in = i.split(" ");
            if(!in[1].equals(Long.toString(0)))
            {
                statControler sc = new statControler();
                FXMLLoader fxmlLoader = new FXMLLoader(
                        appMain.class.getResource("stat.fxml"));
                fxmlLoader.setController(sc);
                try {
                    Pane pane = fxmlLoader.load();
                    sc.setStat(in[0], in[1]);
                    Statistic.addColumn(0, pane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @FXML
    void doing ()
    {
        //statistic.add(prog.prog.get(prog.i));
        cpu.work(prog.prog.get(prog.i));
        doing_visout_work();
        doing_statistic();
        prog.plus();
        event(prog);
    }



    @Override
    public void event(Program prog) {
        int ii = 0;
        allProg.getChildren().clear();
        for (Command p: prog) {
            comControler cc = new comControler();
            FXMLLoader fxmlLoader = new FXMLLoader(appMain.class.getResource("com_view.fxml"));
            fxmlLoader.setController(cc);
            try {
                Pane pane = fxmlLoader.load();
                cc.setCom(p);
                if(ii == prog.i)
                {
                    //Border border = BorderFactory.createLineBorder(Color.BLACK);
                    pane.setStyle("-fx-border-color: red; -fx-border-width: 5");
                }//pane.borderProperty("black");
                allProg.addColumn(0, pane);
                ii++;
                doing_statistic();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}