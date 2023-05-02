package com.example.game3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewController {
    @FXML
    private TableView<Notes> table = new TableView<>();

    @FXML
    private TableColumn<Notes, Integer> ColId = new TableColumn<>("Student id");

    @FXML
    private TableColumn<Notes, String> ColCont = new TableColumn<>("Content");
    @FXML
    private Button BackSec;

    private String idView;

    public void IdSet(int iduser){
        idView = String.valueOf(iduser);
    }

    public void TableView(){

//        ColId.setText("student id");
//        ColCont.setText("Content");
        try {
            // Connect to database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/javafxfp", "root", "Tumar12345");
            Statement statement = conn.createStatement();
            String tab = "SELECT *\n" +
                    "FROM notes\n" +
                    "WHERE notes.idstudent = " + idView +";\n";
            ResultSet set = statement.executeQuery(tab);

//            while(set.next()){
//                int idp = set.getInt("id");
//                String nam = set.getString("name");
//                String surnam = set.getString("surname");
//                String pername = set.getString("film.name");
//                String fil = set.getString("film");
//                String rol = set.getString("role");
//
//                System.out.println(idp +" "+ nam + " " + surnam + " " + film + " " + pername + " " + rol);
//            }

            // Execute SQL query
//            String query = "SELECT * FROM mytable";
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
            ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
            ColCont.setCellValueFactory(new PropertyValueFactory<>("content"));

            // Populate table
            ObservableList<Notes> data = FXCollections.observableArrayList();
            while (set.next()) {
                String id = set.getString("idstudent");
                String con = set.getString("content");
                data.add(new Notes(id, con));
            }
            table.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        BackSec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelloApplication.changeScene(event, "second.fxml", Integer.parseInt(idView));
            }
        });
    }

    public class Notes {

        private String id;
        private String content;

        public Notes(String id, String content) {
            this.id = id;
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
