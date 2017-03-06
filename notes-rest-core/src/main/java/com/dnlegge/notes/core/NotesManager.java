package com.dnlegge.notes.core;

import com.dnlegge.notes.domain.Note;

import java.util.Collection;
import java.util.UUID;

public interface NotesManager {

    Collection<Note> getAll();

    Note add(Note note);

    UUID update(Note note);

    boolean delete(UUID uuid);

}

