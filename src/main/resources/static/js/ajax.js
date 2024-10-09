
function sendGetAjax (url, successCallback) {
    sendAjax(url, 'GET', null, successCallback)
}

function sendPostAjax (url, body, successCallback) {
    sendAjax(url, 'POST', body, successCallback)
}

function sendPutAjax (url, body, successCallback) {
    sendAjax(url, 'PUT', body, successCallback)
}

function sendDeleteAjax (url, successCallback) {
    sendAjax(url, 'DELETE', null, successCallback)
}

function sendAjax (url, method, body, successCallback) {
    const param = {
        url,
        type: method,
        dataType: 'text',
        success(res) {
            try {
                successCallback(JSON.parse(res));
            } catch(err) {
                successCallback(res);
            }
        },
        error(xhr, status, error) {
            console.error("ERROR !", error)
        }
    };

    if (body) {
        param.data =  JSON.stringify(body);
        param.contentType =  'application/json';
    }
    $.ajax(param)
}

