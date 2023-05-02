package com.example.game3;

public class Note {
    private int id;
    private int myId;
    private String text;

    public Note() {
        this.id = -1; // uninitialized
        this.myId = -1; // uninitialized
        this.text = "";
    }

    public Note(int id, int myId, String text) {
        this.id = id;
        this.myId = myId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", id, text);
    }
}

