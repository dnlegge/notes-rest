package com.dnlegge.notes.core;

import com.dnlegge.notes.domain.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//@Service
@Component
public class NotesManagerBean implements NotesManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesManagerBean.class);

    //inelegant but saves notes in memory as per spec
    private static Map<UUID, Note> notes = new ConcurrentHashMap<>();

    @Override
    public Collection<Note> getAll() {
        return notes.values();
    }

    @Override
    public Note add(Note note) {
        notes.put(note.getUuid(), note);
        return note;
    }

    @Override
    public UUID update(Note note) {
        if (notes.containsKey(note.getUuid())) {
            notes.replace(note.getUuid(), note);
        } else {
            notes.put(note.getUuid(), note);
        }
        return note.getUuid();
    }

    @Override
    public UUID update(UUID uuid, String newText) {
        if (notes.containsKey(uuid)) {
            Note note = notes.get(uuid);
            Note newNote = new Note(note.getUuid(), note.getCreationDateTime(), newText);
            notes.replace(note.getUuid(), newNote);
            return uuid;
        }
        //errror
        return null;
    }

    @Override
    public boolean delete(UUID uuid) {
        if (notes.containsKey(uuid)) {
            notes.remove(uuid);
            return true;
        }
        return false;
    }

    // I am useing this for testing, so perhaps it should just be a lower scoped method (not on interface)
    boolean containsNote(UUID uuid) {
        return notes.containsKey(uuid);
    }

    //    @Override
    Note getNote(UUID uuid) {
        return notes.get(uuid);
    }

    int getNumberOfNotes() {
        return notes.size();
    }

    void clear() {
        notes.clear();
    }

}
