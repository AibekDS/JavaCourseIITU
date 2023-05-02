package com.example.game3;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class NoteStore {
    private static final String NOTES_FILE = "C:\\Users\\asus\\Desktop\\notes.txt";

    public List<Note> loadNotes() {
        List<Note> notes = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(NOTES_FILE));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    int myId = Integer.parseInt(parts[1]);
                    String text = parts[2];
                    Note note = new Note(id, myId, text);
                    notes.add(note);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public void saveNotes(List<Note> notes) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(NOTES_FILE));
            for (Note note : notes) {
                String line = String.format("%d|%d|%s", note.getId(), note.getMyId(), note.getText());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNextNoteId(List<Note> notes) {
        int maxId = -1;
        for (Note note : notes) {
            if (note.getId() > maxId) {
                maxId = note.getId();
            }
        }
        return maxId + 1;
    }

    public void addNote(List<Note> notes, int myId, String text, int idstudent) {
        int nextId = getNextNoteId(notes);
        Note note = new Note(nextId, myId, text);
        notes.add(note);

        Connection connection = null;
        PreparedStatement psInsert = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxfp", "root", "Tumar12345");
            psInsert = connection.prepareStatement("INSERT INTO notes VALUES (?, ?, ?)");
            psInsert.setInt(1, nextId+1);
            psInsert.setInt(2, idstudent);
            psInsert.setString(3, text);
            psInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void deleteNoteById(List<Note> notes, int id) {
        notes.removeIf(note -> note.getId() == id);
        Connection connection = null;
        PreparedStatement psInsert = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxfp", "root", "Tumar12345");
            String del = "DELETE FROM `javafxfp`.`notes` WHERE `idnotes`=?";
            psInsert = connection.prepareStatement(del);
            psInsert.setInt(1, id + 1);
            psInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

