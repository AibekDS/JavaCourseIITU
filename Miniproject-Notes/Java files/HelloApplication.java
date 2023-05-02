package com.example.game3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

import static javafx.application.Application.launch;

public class HelloApplication{
    public static void changeLabel(ActionEvent event, String fxmlFile, int id, String str){
        Parent root = null;
        if(id != 0){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
                root = loader.load();
                com.example.game3.HelloController hc = loader.getController();
                hc.setUserInformation(str);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(HelloApplication.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("IITU");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void changeScene(ActionEvent event,String fxml, int id){
        Parent root = null;
        if(id != 0){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));
                root = loader.load();
                SecondController sc = loader.getController();
                sc.InSecPage(id);


                //Parent homepage = FXMLLoader.load(HelloApplication.class.getResource("second.fxml"));

                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene homepageScene = new Scene(root);
                appStage.setScene(homepageScene);
                appStage.setTitle("IITU");
                appStage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void changeSceneThree(ActionEvent event,String fxml, int id) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            root = loader.load();
            ViewController vc = loader.getController();
            vc.IdSet(id);
            vc.TableView();

            //Parent homepage = FXMLLoader.load(HelloApplication.class.getResource("second.fxml"));

            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene homepageScene = new Scene(root);
            appStage.setScene(homepageScene);
            appStage.setTitle("IITU");
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void changeSceneFour(ActionEvent event,String fxml) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            root = loader.load();

            //Parent homepage = FXMLLoader.load(HelloApplication.class.getResource("second.fxml"));

            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene homepageScene = new Scene(root);
            appStage.setScene(homepageScene);
            appStage.setTitle("IITU");
            appStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void changeSceneTwo(ActionEvent event,String fxml, int id){
        Parent root = null;
        if(id != 0){
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));
                root = loader.load();
                KeeperController kc = loader.getController();
                kc.SetId(id);


                //Parent homepage = FXMLLoader.load(HelloApplication.class.getResource("second.fxml"));

                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene homepageScene = new Scene(root);
                appStage.setScene(homepageScene);
                appStage.setTitle("IITU");
                appStage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void signUpUser(ActionEvent event, String username, int user_id, String password, String group){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxfp", "root", "Tumar12345");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            psCheckUserExists.setInt(1, user_id);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this id.");
                alert.show();
            } else{
                psInsert = connection.prepareStatement("INSERT INTO user (user_id, username, password, `group`) VALUES (?, ?, ?, ?)");
                psInsert.setInt(1, user_id);
                psInsert.setString(2, username);
                psInsert.setString(3, password);
                psInsert.setString(4, group);
                psInsert.executeUpdate();

//                changeScene(event, user_id);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists != null){
                try {
                    psCheckUserExists.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }


        }
    }

    public static void logInUser(ActionEvent event, String username, int user_id, String password, String group){
        Connection connection = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxfp", "root", "Tumar12345");
            psInsert = connection.prepareStatement("SELECT username, password, 'group' FROM user WHERE user_id = ?");
            psInsert.setInt(1, user_id);
            resultSet = psInsert.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorect");
                alert.show();
            } else{
                while(resultSet.next()){
                    String retpassword = resultSet.getString("password");
                    String retname = resultSet.getString("username");
                    String retgroup = resultSet.getString("group");
                    if(retpassword.equals(password) || retname.equals(username) || retgroup.equals(group)){
//                        changeScene(event, user_id);
                    } else{
                        System.out.println("Password didn't match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }


        }
    }



}