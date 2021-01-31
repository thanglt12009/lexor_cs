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
                var log = [];
                data.forEach(function (data) {
                    hitory.val(hitory.val() + data.logMessage + " " + data.createdDate + '\r\n');
                });
            }
        },
        contentType: 'application/json'
    });
}