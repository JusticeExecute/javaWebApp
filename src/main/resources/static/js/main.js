$(document).ready(function () {

    $("#search-form").submit(function (event) {

        event.preventDefault();

        fire_ajax_search();

    });

    $("#show-all-form").submit(function (event) {

        event.preventDefault();

        fire_ajax_show_all();

    });

    $("#delete-form").submit(function (event) {

        event.preventDefault();

        fire_ajax_delete_by_id();

    });

    $("#create-form").submit(function (event) {

        event.preventDefault();

        fire_ajax_create();

    });

    $("#update-form").submit(function (event) {

        event.preventDefault();

        fire_ajax_update();

    });

});

function fire_ajax_search() {

    $("#btn-search").prop("disabled", true);
    var search = {}
    search["firstName"] = $("#employee_first_name").val();
    search["lastName"] = $("#employee_last_name").val();

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/v1/employees/" + "?firstName="
                                  + $("#employee_first_name").val()
                                  + "&lastName="
                                  + $("#employee_last_name").val(),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4>"
                + JSON.stringify(data, null, 4);
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search-by-id").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}

function fire_ajax_show_all() {

    $("#btn-show-all").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/v1/employees",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4>" + JSON.stringify(data, null, 4);
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-show-all").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4>" + e.responseText;
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-show-all").prop("disabled", false);

        }
    });

}

function fire_ajax_delete_by_id() {

    $("#btn-delete-by-id").prop("disabled", true);

        $.ajax({
            type: "DELETE",
            contentType: "application/json",
            url: "/api/v1/employees/" + $("#employee_id").val(),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4>"
                    + JSON.stringify(data, null, 4);
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-delete-by-id").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                    + e.responseText + "&lt;/pre&gt;";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-delete-by-id").prop("disabled", false);

            }
        });

}

function fire_ajax_create() {

    var create = {}
    create["firstName"] = $("#employee_first_name").val();
    create["lastName"] = $("#employee_last_name").val();
    create["gender"] = $("#employee_gender").val().toUpperCase();
    create["age"] = $("#employee_age").val();

    $("#btn-create").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/v1/employees",
            data: JSON.stringify(create),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4>"
                    + JSON.stringify(data, null, 4);
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-create").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                    + e.responseText + "&lt;/pre&gt;";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-create").prop("disabled", false);

            }
        });

}

function fire_ajax_update() {

    var update = {}
    update["firstName"] = $("#employee_first_name").val();
    update["lastName"] = $("#employee_last_name").val();
    update["gender"] = $("#employee_gender").val().toUpperCase();
    update["age"] = $("#employee_age").val();

    $("#btn-update").prop("disabled", true);

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/employees/" + $("#employee_id").val(),
            data: JSON.stringify(update),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = "<h4>Ajax Response</h4>"
                    + JSON.stringify(data, null, 4);
                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-update").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                    + e.responseText + "&lt;/pre&gt;";
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-update").prop("disabled", false);

            }
        });

}