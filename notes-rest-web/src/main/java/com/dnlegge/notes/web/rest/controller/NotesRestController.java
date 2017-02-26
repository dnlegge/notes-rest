package com.dnlegge.notes.web.rest.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "rest/",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class NotesRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesRestController.class);


}
