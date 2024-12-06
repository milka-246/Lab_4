package org.example.lab_4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class comControler
{
    @FXML
    Label lb1;
    @FXML
    Label lb2;
    @FXML
    Label lb3;
    Command p;
    Program m = BProgram.build();

    void setCom(Command p)
    {
        this.p = p;
        if(p.info[0] == "print" || p.info[0] == "add" || p.info[0] == "sub" || p.info[0] == "mult")
        {
            lb1.setText(p.info[0]);
            lb2.setText(" ");
            lb3.setText(" ");
            return;
        }
        lb1.setText(p.info[0]);
        lb2.setText(p.info[1]);
        lb3.setText(p.info[2]);
    }

    @FXML
    void del_com()
    {
        m.del_com(p);
        m.up();
    }
    @FXML
    void up()
    {
        m.up();
    }
    @FXML
    void down()
    {
        m.down();
    }
}
