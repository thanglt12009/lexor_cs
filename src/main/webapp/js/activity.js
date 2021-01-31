function createActivity(caseId, message) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/case_log',
        data: JSON.stringify({
            "caseID": caseId,
            "logMessage": message,
            "createdDate": getCurrentTime(),
            "updatedDate": getCurrentTime()
        }),
        contentType: 'application/json'
    });
}

function loadActivity(caseId) {
    $.get({
        url: "/lexor_cs/api/case_log/" + caseId + "/" + caseId,
        success: function (data) {
            let tableData = [];
            if (data) {
                var hitory = $("#logHistory");
                hitory.val("");
                var log = [];
                data.forEach(function (data) {
                    hitory.val(hitory.val() + data.logMessage + " " + data.createdDate + '\r\n');
                });
            }
        },
        contentType: 'application/json'
    });
}

function createRMAActivity(caseId, message) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/rma_log',
        data: JSON.stringify({
            "RMAID": caseId,
            "logMessage": message,
            "createdDate": getCurrentTime(),
            "updatedDate": getCurrentTime()
        }),
        contentType: 'application/json'
    });
}

function loadRMAActivity(caseId) {
    $.get({
        url: "/lexor_cs/api/rma_log/" + caseId + "/" + caseId,
        success: function (data) {
            let tableData = [];
            if (data) {
                var hitory = $("#logHistory");
                hitory.val("");
                data.forEach(function (data) {
                    hitory.val(hitory.val() + data.logMessage + " " + data.createdDate + '\r\n');
                });
            }
        },
        contentType: 'application/json'
    });
}


function getCurrentTime() {
    var dt = new Date;

    return dt.getFullYear() + "/" + (dt.getMonth() + 1) + "/" + dt.getDate();
}