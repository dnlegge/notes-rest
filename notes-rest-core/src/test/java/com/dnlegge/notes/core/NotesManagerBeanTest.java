package com.dnlegge.notes.core;


import com.dnlegge.notes.domain.Note;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class NotesManagerBeanTest {

    private NotesManager beingTested;

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
    public void add() throws Exception {
        Note hello = new Note("hello");

        beingTested.add(hello);

        assertTrue(beingTested.containsNoteCreatedAt(hello.getCreationDateTime()));

        assertEquals("hello", beingTested.getNoteCreatedAt(hello.getCreationDateTime()).getNoteTextContent());

    }

}