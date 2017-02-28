package com.dnlegge.notes.core;

import com.dnlegge.notes.domain.Note;

import java.time.LocalDateTime;
import java.util.Collection;

public interface NotesManager {

    Collection<Note> getAll();

    Note add(Note note);

    boolean containsNoteCreatedAt(LocalDateTime note);

    Note getNoteCreatedAt(LocalDateTime creationDateTime);
}

