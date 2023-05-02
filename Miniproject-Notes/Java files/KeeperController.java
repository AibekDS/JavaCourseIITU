package com.example.game3;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class KeeperController {
    @FXML
    private Button back;
    @FXML
    private ListView<Note> noteList;

    @FXML
    private TextField noteField;

    private List<Note> notes;
    private NoteStore noteStore;

    @FXML
    private Label idstudent;

    public KeeperController() {
        noteStore = new NoteStore();
        notes = noteStore.loadNotes();
    }

    public void initialize() {
        ObservableList<Note> items = noteList.getItems();
        items.addAll(notes);
        noteList.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println(newSelection.getText());
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelloApplication.changeScene(event, "second.fxml", Integer.parseInt(idstudent.getText()));
            }
        });
    }

    public void SetId(int id){
        idstudent.setText(String.valueOf(id));
    }

    public void addNote() {
        String text = noteField.getText().trim();
        if (text.isEmpty()) {
            return;
        }
        noteStore.addNote(notes, 1, text, Integer.parseInt(idstudent.getText()));
        noteStore.saveNotes(notes);
        noteField.clear();
        noteList.getItems().add(notes.get(notes.size() - 1));
    }

    public void deleteNote() {
        Note selectedNote = noteList.getSelectionModel().getSelectedItem();
        if (selectedNote == null) {
            return;
        }
        noteStore.deleteNoteById(notes, selectedNote.getId());
        noteStore.saveNotes(notes);
        noteList.getItems().remove(selectedNote);
    }
}

