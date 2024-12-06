package org.example.lab_4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class statControler
{
    @FXML
    Label name;
    @FXML
    Label kol;



    void setStat(String n, String k)
    {
        name.setText(n);
        kol.setText(k);
    }
}
