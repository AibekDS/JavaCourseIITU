package com.example.game3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondController implements Initializable {
    @FXML
    private Button add;

    @FXML
    private Label idsec;

    @FXML
    private Button view;
    @FXML
    private Button backMain;

    public void InSecPage(int id){
        idsec.setText(String.valueOf(id));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelloApplication.changeSceneTwo(event, "Keeper.fxml", Integer.parseInt(idsec.getText()));
            }
        });

        view.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelloApplication.changeSceneThree(event, "ViewTable.fxml", Integer.parseInt(idsec.getText()));
            }
        });

        backMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelloApplication.changeSceneFour(event, "hello-view.fxml");
            }
        });
    }
}
