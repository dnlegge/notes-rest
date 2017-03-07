$(document).ready(function () {
    // addDefaultNote();
    getNotes();

});

function getNotes() {
    document.getElementById('notesListContent').innerHTML = ' ... fetching notes, please wait.... ';
    jqxhr = $.ajax({
        url: "/notes/rest/all"
    })
        .done(function (data) {
            handleNoteList(data);
            document.getElementById('notesListStatus').innerHTML = "Notes retrieved at " + new Date();

        })
        .fail(function () {
            // clearModelPanels();
            document.getElementById('notesListStatus').innerHTML = "Failed to retrieve notes";
        })
    ;
}

function addNote() {
    // document.getElementById('metadata').innerHTML = ' ... fetching notes, please wait.... ';
    var newNoteText = document.getElementById('newNoteText').value;
    // var newNoteText = "['" + document.getElementById('newNoteText').value + "']";
    jqxhr = $.ajax({
        url: "/notes/rest/add",
        type: "POST",
        data: newNoteText

    })
        .done(function (data) {
            document.getElementById('newNoteText').value = '';
            document.getElementById('newNoteStatus').innerHTML = "Note added successfully: " + newNoteText;
            getNotes();

        })
        .fail(function () {
            // clearModelPanels();
            document.getElementById('newNoteText').value = '';
            document.getElementById('newNoteStatus').innerHTML = "Failed to add note";
            getNotes();
        })
    ;
}

function addDefaultNote() {
    // document.getElementById('metadata').innerHTML = ' ... fetching notes, please wait.... ';
    jqxhr = $.ajax({
        url: "/notes/rest/addDefaultNote"
    })
        .done(function (data) {
            document.getElementById('notesListContent').innerHTML = "Note added successfully: " + content;
            getNotes();

        })
        .fail(function () {
            document.getElementById('notesListContent').innerHTML = "Failed to retrieve notes";
        })
    ;
}

function deleteNote(uuid) {
    document.getElementById('newNoteStatus').innerHTML = "";
    document.getElementById('notesListContent').innerHTML = "Deleting note: " + uuid;

    jqxhr = $.ajax({
        url: "/notes/rest/id/" + uuid,
        type: "DELETE"
    })
        .done(function (data) {
            document.getElementById('notesListContent').innerHTML = "Note successfully deleted: " + uuid;
            document.getElementById('newNoteStatus').innerHTML = "Note successfully deleted: " + uuid;
            getNotes();

        })
        .fail(function () {
            document.getElementById('notesListContent').innerHTML = "Failed to delete note " + uuid;
        })
    ;
    // document.getElementById('notesListContent').innerHTML = "Note deleted: " + uuid;

    getNotes();
}

function handleNoteList(data) {

    // if (data.httpStatus == '200 - OK' && data.result != null ) {
    var table = '<table ><tr>';
    table += '<td>Delete</td>';
    table += '<td>UUID</td>';
    table += '<td>Creation Date Time</td>';
    table += '<td>Text</td>';
    table += '<td>Edit</td>';
    table += '<td></td>';
    table += '</tr>';
    for (var key in data) {

        table += '<tr>';

        table += '<td><button id="deleteButton' + key + '" onclick="deleteNote(\'' + data[key].uuid + '\')">Delete</button></td>';
        table += '<td>' + data[key].uuid + '</td>';
        table += '<td>' + data[key].creationDateTime + '</td>';
        table += '<td>';
        // table += '<div id="textValue' + key + '" >' + data[key].noteTextContent + '</div>';
        // table += '<div id="textValueEdit' + key + '" enabled="false">';
        table += '<input type="text" id="editNoteText' + data[key].uuid + '" disabled="true" value=' + data[key].noteTextContent + '/>';
        // table += '</div>';
        table += '</td>';
        table += '<td><button id="editButton' + key + '" onclick="editNote(\'' + data[key].uuid + '\')">Edit</button></td>';

        table += '<div id="saveButtonDiv' + data[key].uuid + '" hidden="true">';
        table += '<td><button id="saveButton' + data[key].uuid + '" onclick="saveNote(\'' + data[key].uuid + '\')" >Save</button></td>';
        table += '</div>';

        table += '</tr>';

    }

    table += '</table>';

    document.getElementById('notesListContent').innerHTML = table;
}

function editNote(key) {
    document.getElementById('editNoteText' + key).disabled = false;
    document.getElementById('saveButtonDiv' + key).hidden = false;
}

function saveNote(uuid) {
    var noteTextContent = document.getElementById('editNoteText' + uuid).value;
    // note.noteTextContent = document.getElementById('editNoteText' + note.uuid);
    // var uuid = note.uuid;

    jqxhr = $.ajax({
        url: "/notes/rest/edit/id/" + uuid,
        type: "POST",
        data: noteTextContent
    })
        .done(function (data) {
            document.getElementById('notesListContent').innerHTML = "Note successfully edited: " + uuid;
            document.getElementById('newNoteStatus').innerHTML = "Note successfully edited: " + uuid;
            getNotes();

        })
        .fail(function () {
            document.getElementById('notesListContent').innerHTML = "Failed to edit note " + uuid;
        })
    ;

    getNotes();

}
