package com.dnlegge.notes.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Note {

    private UUID uuid;

    private LocalDateTime creationDateTime;

    private String noteTextContent;

    //default constructor required by JSON libraries
    Note() {
    }

    public Note(String noteTextContent) {
        this.uuid = UUID.randomUUID();
        this.creationDateTime = LocalDateTime.now();
        this.noteTextContent = noteTextContent;
    }

    public Note(UUID uuid, LocalDateTime creationDateTime, String noteTextContent) {
        this.uuid = uuid;
        this.creationDateTime = creationDateTime;
        this.noteTextContent = noteTextContent;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public String getNoteTextContent() {
        return noteTextContent;
    }

}
