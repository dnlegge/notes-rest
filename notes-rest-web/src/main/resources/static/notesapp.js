$(document).ready(function () {
    addDefaultNote();
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
            // clearModelPanels();
            document.getElementById('notesListContent').innerHTML = "Failed to retrieve notes";
        })
    ;
}

function handleNoteList(data) {


    // if (data.httpStatus == '200 - OK' && data.result != null ) {
    var table = '<table ><tr>';
    table += '<td>UUID</td>';
    table += '<td>Creation Date Time</td>';
    table += '<td>Text</td>';
    table += '</tr>';
    for (var key in data) {

        table += '<tr>';

        table += '<td>' + data[key].uuid + '</td>';
        table += '<td>' + data[key].creationDateTime + '</td>';
        table += '<td>' + data[key].noteTextContent + '</td>';
        table += '</tr>';

    }

    table += '</tr>';

    document.getElementById('notesListContent').innerHTML = table;
    // }
    // else {
    //     document.getElementById('chart').innerHTML = "Status returned: " + jsonObj.httpStatus
    //         + ". With message: " + jsonObj.error;
    // }
}
