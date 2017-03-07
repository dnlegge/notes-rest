# notes-rest

Getting started:

run 'mvn clean install' from the top level

The project requires Java 8.

It uses Spring Boot, Jackson (for Json)

There are three modules:
- an api
- a core mmodule, encaspulating the notes operations
- a rest module, handling the networking issues

To run:
Either use a development environment, such as intellij and get that to it run for you.
Or type from the root directory of the check ed out code:
'java -jar notes-rest-web/target/notes.jar'


TODO:
More unit tests, some for editing and deleting
Investigate extra characters - a trailing equal signs gets appended to note text
Save button should be hidden until Edit is clicked.
