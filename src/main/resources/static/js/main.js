$(document).ready(function () {

    $("#search-form").submit(function (event) {

        event.preventDefault();

        fire_ajax_show_by_id();

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

function fire_ajax_show_by_id() {

    $("#btn-search-by-id").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/v1/employee/" + $("#employee_id").val(),
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
            $("#btn-search-by-id").prop("disabled", false);

        }
    });

}

function fire_ajax_show_all() {

    $("#btn-show-all").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/v1/employee",
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
            url: "/api/v1/employee/" + $("#employee_id").val(),
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
    create["employeeId"] = $("#employee_id").val();
    create["firstName"] = $("#employee_first_name").val();
    create["gender"] = $("#employee_gender").val().toUpperCase();

    $("#btn-create").prop("disabled", true);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/v1/employee",
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
    update["gender"] = $("#employee_gender").val().toUpperCase();

    $("#btn-update").prop("disabled", true);

        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/v1/employee/" + $("#employee_id").val(),
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