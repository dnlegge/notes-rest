package com.dnlegge.notes.web.rest.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeRestController.class);

    public String index() {
        return "index";
    }

    public String home() {
        return "home";
    }

    @RequestMapping(value = "rest",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String run() {

        return "OK";

    }
}
