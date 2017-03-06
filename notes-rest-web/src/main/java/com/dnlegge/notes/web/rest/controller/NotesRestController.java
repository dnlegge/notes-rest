package com.dnlegge.notes.web.rest.controller;


import com.dnlegge.notes.core.NotesManager;
import com.dnlegge.notes.domain.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;


@RestController
@RequestMapping(value = "rest/",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class NotesRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesRestController.class);

    @Autowired
    private NotesManager notesManager;

    @RequestMapping(value = "note",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Note ok() {

        return new Note("OK");

    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<Note> run() {

        return notesManager.getAll();

    }

    @RequestMapping(value = "addDefaultNote",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addDefaultNote() {

        Note note = new Note("Note added on application start/load at " + LocalDateTime.now());
        notesManager.add(note);

        return note.getNoteTextContent();
    }

    @RequestMapping(value = "add",
            method = RequestMethod.POST//,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE

    )

    @ResponseBody
    public UUID addNote(@RequestBody String content) {

        Note note = new Note(content);
        notesManager.add(note);

        return note.getUuid();
    }

    @RequestMapping(value = "add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public UUID editNote(@RequestBody Note note) {

        return notesManager.update(note);

    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public boolean deleteNote(@RequestBody Note note) {

        return notesManager.delete(note.getUuid());

    }

    @RequestMapping(value = "id/{uuid}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public boolean deleteNote(@PathVariable UUID uuid) {

        return notesManager.delete(uuid);

    }

}
