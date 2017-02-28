package com.dnlegge.notes.core;

import com.dnlegge.notes.domain.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Service
@Component
public class NotesManagerBean implements NotesManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesManagerBean.class);

    //inelegant but saves notes in memory as per spec
    private static Map<LocalDateTime, Note> notes = new ConcurrentHashMap<>();

    @Override
    public Collection<Note> getAll() {
        return notes.values();
    }

    @Override
    public Note add(Note note) {
        notes.put(note.getCreationDateTime(), note);
        return note;
    }
}
