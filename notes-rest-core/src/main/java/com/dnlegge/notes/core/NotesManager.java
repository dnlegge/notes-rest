package com.dnlegge.notes.core;

import com.dnlegge.notes.domain.Note;

import java.util.Collection;
import java.util.UUID;

public interface NotesManager {

    Collection<Note> getAll();

    Note add(Note note);

    UUID update(Note note);

    UUID update(UUID uuid, String newText);

    boolean delete(UUID uuid);

}

