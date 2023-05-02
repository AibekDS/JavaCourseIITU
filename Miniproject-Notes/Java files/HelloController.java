package com.example.game3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label Error;

    @FXML
    private Button Find;

    @FXML
    private Button signup;

    @FXML
    private TextField userGroup;

    @FXML
    private TextField userId;

    @FXML
    private TextField userName;

    @FXML
    private TextField userPassword;

//    @FXML
//    private AnchorPane scene1;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        Find.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelloApplication.logInUser(event, userName.getText(), Integer.parseInt(userId.getText()), userPassword.getText(), userGroup.getText());
                /*
                try {
                    Parent homepage = FXMLLoader.load(getClass().getResource("second.fxml"));
                    Scene homepageScene = new Scene(homepage);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(homepageScene);
                    appStage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                */
                HelloApplication.changeScene(event, "second.fxml",Integer.parseInt(userId.getText()));

            }
        });

        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelloApplication.signUpUser(event, userName.getText(), Integer.parseInt(userId.getText()), userPassword.getText(), userGroup.getText());
//                HelloApplication.changeScene(event, Integer.parseInt(userId.getText()));
                HelloApplication.changeScene(event, "second.fxml",Integer.parseInt(userId.getText()));
            }
        });
    }

    public void setUserInformation(String str){
        Error.setText(str);
    }

}
