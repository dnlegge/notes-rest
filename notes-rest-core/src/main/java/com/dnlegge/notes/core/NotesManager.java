package com.dnlegge.notes.core;

import com.dnlegge.notes.domain.Note;

import java.util.Collection;

public interface NotesManager {

    Collection<Note> getAll();

    Note add(Note note);

}
