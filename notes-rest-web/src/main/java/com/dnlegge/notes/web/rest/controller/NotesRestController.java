package com.dnlegge.notes.web.rest.controller;


import com.dnlegge.notes.domain.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "rest/",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class NotesRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesRestController.class);

    @RequestMapping(value = "note",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Note run() {

        return new Note("OK");

    }
}
