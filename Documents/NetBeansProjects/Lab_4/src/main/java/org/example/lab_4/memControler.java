package org.example.lab_4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class  memControler
{
    @FXML
    Label index;
    @FXML
    Label value;



    void setMem(int ind, String val, int v)
    {
        if(v == 1)
        {
            index.setStyle("-fx-text-fill: red;");
            value.setStyle("-fx-text-fill: red;");
        }
        index.setText(Integer.toString(ind) + " ");
        value.setText(": " + val);
    }
}