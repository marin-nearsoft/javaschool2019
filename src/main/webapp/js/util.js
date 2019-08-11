function alert(exception){
    if(exception.responseText){
        var html = '<div class="alert alert-danger alert-dismissible" role="alert">'+
        '<strong>' + exception.responseText + '</strong>'+
        '<button class="close" type="button" data-dismiss="alert" aria-label="Close">'+
        '<span aria-hidden="true">×</span>'+
        '</button>'
        '</div>';

        $('#alertDiv').append(html);
    }
}

function rest(method, path, success, payload){
    let settings = {
        method: method,
        url: config.host +":"+ config.port + path,
        error: alert,
        success: success,
        data: payload
    }

    $.ajax(settings);
}