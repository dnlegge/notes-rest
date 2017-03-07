package com.dnlegge.notes.core;


import com.dnlegge.notes.domain.Note;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.*;

public class NotesManagerBeanTest {

    private NotesManagerBean beingTested;

    @Before
    public void setUp() throws Exception {
        beingTested = new NotesManagerBean();
    }

    @Test
    public void getAll() throws Exception {
        Collection<Note> all = beingTested.getAll();
        assertNotNull(all);
        assertEquals(0, all.size());
    }

    @Test
    public void addSingleAndClear() throws Exception {

        addNote("hello");

        assertEquals(1, beingTested.getNumberOfNotes());

        beingTested.clear();

        assertEquals(0, beingTested.getNumberOfNotes());

    }

    private UUID addNote(String noteText) {

        Note hello = new Note(noteText);

        int size = beingTested.getNumberOfNotes();

        beingTested.add(hello);

//        assertEquals(size + 1, beingTested.getNumberOfNotes());

        assertTrue(beingTested.containsNote(hello.getUuid()));

        assertEquals(noteText, beingTested.getNote(hello.getUuid()).getNoteTextContent());

        return hello.getUuid();
    }

    @Test
    public void addMultipleAndClear() throws Exception {

        addNote("hello");

        addNote("hello again");

        assertEquals(2, beingTested.getNumberOfNotes());

        beingTested.clear();

    }

    @Test
    public void addMultipleCheckVeracityAndClear() throws Exception {

        UUID helloUuid = addNote("hello");

        assertEquals(1, beingTested.getNumberOfNotes());

        UUID helloAgainUuid = addNote("hello again");

        assertEquals(2, beingTested.getNumberOfNotes());

        assertEquals(helloUuid, beingTested.getNote(helloUuid).getUuid());
        assertEquals("hello", beingTested.getNote(helloUuid).getNoteTextContent());
        assertEquals(helloAgainUuid, beingTested.getNote(helloAgainUuid).getUuid());
        assertEquals("hello again", beingTested.getNote(helloAgainUuid).getNoteTextContent());

        beingTested.clear();

    }

    @Test
    public void addAndEdit() throws Exception {

        UUID uuid = addNote("hello");

        UUID update = beingTested.update(uuid, "hello there");

        assertEquals(uuid, update);

        assertEquals(1, beingTested.getNumberOfNotes());

        beingTested.clear();

    }

    @Test
    public void addAndDelete() throws Exception {

        UUID uuid = addNote("hello");

        assertEquals(1, beingTested.getNumberOfNotes());

        beingTested.delete(uuid);

        assertEquals(0, beingTested.getNumberOfNotes());

        beingTested.clear();

    }

    @Test
    public void addAndDeleteNonExistent() throws Exception {

        UUID uuid = addNote("hello");

        assertEquals(1, beingTested.getNumberOfNotes());

        assertFalse(beingTested.delete(UUID.randomUUID()));

        assertEquals(1, beingTested.getNumberOfNotes());

        beingTested.clear();

    }

    @Test
    public void addAndUpdateNonExistent() throws Exception {

        UUID uuid = addNote("hello");

        assertEquals(1, beingTested.getNumberOfNotes());

        UUID update = UUID.randomUUID();

        try {

            update = beingTested.update(update, "bye bye");

            fail();

        } catch (Exception e) {
            assertEquals("No such note as " + update, e.getMessage());
        }

        assertEquals(1, beingTested.getNumberOfNotes());

        beingTested.clear();

    }

}