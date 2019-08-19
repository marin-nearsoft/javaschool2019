function alert(exception){
    let message = "Something happened. Please call your administrator.";
    if(exception.responseText){
        message = exception.responseText;
    }

    let html = '<div class="alert alert-danger alert-dismissible" role="alert">'+
        '<strong>' + message + '</strong>'+
        '<button class="close" type="button" data-dismiss="alert" aria-label="Close">'+
        '<span aria-hidden="true">×</span>'+
        '</button>'+
        '</div>';

    $('#alertDiv').append(html);
};

function rest(method, path, success, payload){
    let settings = {
        method: method,
        url: config.host +":"+ config.port + path,
        error: alert,
        success: success,
        data: JSON.stringify(payload),
        contentType: "application/json"
    };

    $.ajax(settings);
};

function createOption(object){
    return $('<option>', { 
        value: object.name,
        text : object.name 
    });
}