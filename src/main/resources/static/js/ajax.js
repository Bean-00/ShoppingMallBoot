
function uploadAjax(formData) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: '/api/files/upload',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success(res) {
               resolve(res);
            },
            error(xhr, status, error) {
                reject(error);
            }
        })
    })
}

function sendGetAjax (url, successCallback, errorCallback) {
    sendAjax(url, 'GET', null, successCallback, errorCallback)
}

function sendPostAjax (url, body, successCallback, errorCallback) {
    sendAjax(url, 'POST', body, successCallback, errorCallback)
}

function sendPutAjax (url, body, successCallback, errorCallback) {
    sendAjax(url, 'PUT', body, successCallback, errorCallback)
}

function sendDeleteAjax (url, successCallback, errorCallback) {
    sendAjax(url, 'DELETE', null, successCallback, errorCallback)
}

function sendAjax (url, method, body, successCallback, errorCallback) {
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
            if (errorCallback) {
                errorCallback(error);
            }
        }
    };

    if (body) {
        param.data =  JSON.stringify(body);
        param.contentType =  'application/json';
    }
    $.ajax(param)
}

