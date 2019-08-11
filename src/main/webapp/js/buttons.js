$("#checkPriceButton").click(_checkPrice);
$("#sendButton").click(_sendShipping);

function _checkPrice(response){
    let checkPricePayload = {
        // TODO build check price payload
    }
    rest("POST", config.rest.checkPrice, _setPrice, checkPricePayload);
}

function _sendShipping(response){
    let shippingPayload = {
        // TODO build shipping payload
    }
    rest("POST", config.rest.sendShipping, _fillShippingTable, shippingPayload);
}

function _fillShippingTable(folioInformation){
    // TODO which information we need
}