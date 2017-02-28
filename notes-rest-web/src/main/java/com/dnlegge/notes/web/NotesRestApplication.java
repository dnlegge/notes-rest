package com.dnlegge.notes.web;

import com.dnlegge.notes.web.rest.converter.LocalDateTimeJsonSerialiser;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@SpringBootApplication
@ComponentScan(
        "com.dnlegge.notes.web, " +
                "com.dnlegge.notes.core"
)
public class NotesRestApplication extends SpringBootServletInitializer {

    private static Class<NotesRestApplication> applicationClass = NotesRestApplication.class;

//    @Value("${notesDir}")
//    private String notesDir = "";

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

//        final String systemnotesDir = System.getenv("notesDir");
//        final String pathname = systemnotesDir + "/notes/notes.properties";
//        File file = new File(pathname);
        final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer
                = new PropertySourcesPlaceholderConfigurer();

        propertySourcesPlaceholderConfigurer.setLocations(
                new ClassPathResource("application.properties")
//                ,
//                new FileSystemResource(file)
        );
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        final String systemnotesDir = System.getenv("notesDir");
//        final String pathname = systemnotesDir + "/notes/notes.properties";
//        File file = new File(pathname);

        final PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocations(
                new ClassPathResource("application.properties")
//                ,
//                new FileSystemResource(file)
        );
        return propertyPlaceholderConfigurer;
    }

    @Autowired
    public void configJackson(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        jackson2ObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        jackson2ObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeJsonSerialiser());

    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);

        return builder;
    }

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        final ArrayList<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapperBuilder().build());

        jsonMessageConverter.setSupportedMediaTypes(
                Arrays.asList(MediaType.APPLICATION_JSON, APPLICATION_JSON_UTF8));
        messageConverters.add(jsonMessageConverter);

        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

    @Bean
    public LoggerContext loggerContext() {
        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
//        File file = new File(notesDir + "/notes/log4j2.xml");

        // this will force a reconfiguration
//        context.setConfigLocation(file.toURI());
        return context;
    }
}