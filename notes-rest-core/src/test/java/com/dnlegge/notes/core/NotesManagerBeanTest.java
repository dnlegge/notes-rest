package com.dnlegge.notes.core;


import com.dnlegge.notes.domain.Note;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collection;

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

    private LocalDateTime addNote(String noteText) {

        Note hello = new Note(noteText);

        int size = beingTested.getNumberOfNotes();

        beingTested.add(hello);

//        assertEquals(size + 1, beingTested.getNumberOfNotes());

        assertTrue(beingTested.containsNoteCreatedAt(hello.getCreationDateTime()));

        assertEquals(noteText, beingTested.getNoteCreatedAt(hello.getCreationDateTime()).getNoteTextContent());

        return hello.getCreationDateTime();
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

        LocalDateTime hello = addNote("hello");

        assertEquals(1, beingTested.getNumberOfNotes());

        LocalDateTime helloAgain = addNote("hello again");

        assertEquals(2, beingTested.getNumberOfNotes());

        assertEquals(hello, beingTested.getNoteCreatedAt(hello).getCreationDateTime());
        assertEquals("hello", beingTested.getNoteCreatedAt(hello).getNoteTextContent());
        assertEquals(helloAgain, beingTested.getNoteCreatedAt(helloAgain).getCreationDateTime());
        assertEquals("hello again", beingTested.getNoteCreatedAt(helloAgain).getNoteTextContent());

        beingTested.clear();

    }

}