package com.dnlegge.notes.domain;

import java.time.LocalDateTime;

public class Note {

    private LocalDateTime creationDateTime;

    private String noteTextContent;

    //default constructor required by JSON libraries
    Note() {
    }

    public Note(LocalDateTime creationDateTime, String noteTextContent) {
        this.creationDateTime = creationDateTime;
        this.noteTextContent = noteTextContent;
    }

    public Note(String noteTextContent) {
        this.creationDateTime = LocalDateTime.now();
        this.noteTextContent = noteTextContent;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public String getNoteTextContent() {
        return noteTextContent;
    }

}
