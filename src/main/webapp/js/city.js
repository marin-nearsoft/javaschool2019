$(document).ready(function(){
    function _getCities(cities){
        $(cities).each(function(index, city){
            $('#originSelect').append(_createOption(city));
            $('#destinationSelect').append(_createOption(city));
        });
    }

    function _createOption(city){
        return $('<option>', { 
            value: city.name,
            text : city.name 
        });
    }

    function _changeCity(event){
        let originSelected = $("#originSelect option:selected").val();
        let destinationSelected = $("#destinationSelect option:selected").val();
        let isOriginValid = originSelected !== "empty"
        let isDestinationValid = destinationSelected !== "empty"
        var areOptionsValid = isOriginValid && isDestinationValid;
        if(areOptionsValid){
            rest("GET", config.rest.cityPath, _cityPath);
        }
    }

    function _cityPath(path){
        // TODO ??
    }

    rest("GET", config.rest.city, _getCities);

    $(".city-select").change(_changeCity);
});