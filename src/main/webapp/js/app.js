saleOrders = {};
saleOrderToSave = {};
defaultUser = {};
casePriority = {     
    1: "Low",
    2: "Normal",
    3: "High",
    4: "Critical"
};
$(document).ready(function () {
    reInitUser();
    loadCase();
    $('#editContactDialog').window('close');
    $("#addNew").on("click", function () {
        $('#addContactDialog').dialog({
            title: 'New Contact',
            width: 400,
            height: 400,
            closed: false,
            cache: false,
            modal: true,
            href: 'user.html'
        });
    });
    
    $("#editSaleOrder").on("click", function () {
        registerCaseTransactionDialog('editSaleOrderDialog', 'Service');
    });
    
    $("#editReturn").on("click", function () {
        registerCaseTransactionDialog('editReturnDialog', 'Return');
    });
    
    $("#editCase").on("click", function () {
        console.log(defaultUser);
        $("#editMobilePhone").textbox({value : defaultUser.mobile_phone, required:true, validateOnCreate:false, width:337});
        $("#editBusinessPhone").textbox({value : defaultUser.business_phone, required:true, validateOnCreate:false, width:337});
        $('#editContactDialog').window('open');
    });
    
});

function reInitUser() {
    var users = [];
    users[0] = {
        doc_number: 1,
        case_status: 1, 
        name: "Customer 01",
        mobile_phone: "0999-999-999",
        business_phone: "083-999-9999",
        service_rep_name: "Customer Service Rep 1",
        action: '<a href="javascript::void(0)" onClick="createCase(1)" class="easyui-linkbutton">Select</a>'
    };
    
    if (localStorage.getItem("users") === null) {
        localStorage.setItem("users", JSON.stringify(users));
    }
}

function registerCaseTransactionDialog(dialogElement, title) {
    var element = $('#' + dialogElement);
    element.dialog({
        title: title,
        width: 400,
        height: 320,
        closed: false,
        cache: false,
        modal: true,
        href: 'case_service.html',
        onLoad: function() {
            registerAddSaleOrder();
            registerCaseSeviceAutoComplete();
            registerSaveCaseService(element.attr("path"), dialogElement, title);
        }
    });
}

function registerSaveCaseService(path, dialogElement, title) {
    $("#saveCaseService").on("click", function () {
        if ($.urlParam('case_id')) {
            createCaseService($.urlParam('case_id'), path , dialogElement, title);
        }
    });
}

function registerCaseSeviceAutoComplete() {
    var options = {
        getValue: "name",
        data: [{"name": "001", "code": "001"}, {"name": "002", "code": "002"}, {"name": "003", "code": "003"}],
        list: {
          match: {
            enabled: true
          }
        },

        theme: "square"
    };
    $("#saleOrderSearch").easyAutocomplete(options);
}

function registerAddSaleOrder() {
    saleOrderToSave = {};
    saleOrders = {};
    $("#addSaleOrder").on("click", function () {
        var name = $("#saleOrderSearch").val();
        if ( saleOrders[name] || name.trim() === '') {
            return;
        }

        var template = '<div class="checkbox-type"><input id=":name" class="easyui-checkbox saleOrder" labelPosition="after" name=":name" value=":name" label="BuyPart"></div>';
        $('#selectionSaleOrder').append(template.replace(':name', name).replace(':name', name).replace(':name', name));
        $('#' + name).checkbox({
            label: name,
            value: name,
            checked: false,
            onChange: function(value) {
                if ( value ) {
                    saleOrderToSave[name] = true;
                } else {
                    saleOrderToSave[name] = false;
                }
            }
        });
        saleOrders[name] = true;
        $("#saleOrderSearch").val(null);
    });
}

function createCase(customerId, priority = 1) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/case',
        data: JSON.stringify({
            "customerID": customerId, 
            "status": 0, 
            "caseType": 0,
            "casePriority": parseInt(priority)
        }),
        success: function(caseId) {
            window.location.href = '/lexor_cs/pages/cs/purchaseorder/case.html?case_id=' + caseId + "&user_uid" + customerId
        },
        contentType: 'application/json'
   });
}

function getUserInfo() {
    var users = JSON.parse(localStorage.getItem("users"));
    
    var user = users.find(x => x.doc_number == $.urlParam('user_id'));
    if (user) {
        defaultUser = user;
        $("#userInformation").html("<li>First Name: " + user.name + " </li><li>Last Name : " + user.name + " </li><li>Mobile Phone : " + user.mobile_phone + "</li><li>Business Phone : " + user.business_phone + "</li><li>Email : </li> ");
    }
}

function createCaseService(caseId, path, dialog, title) {
    $.post({
        type: "POST",
        url: path,
        data: JSON.stringify({
            "caseID": caseId,
            "customerSOID": 1,
            "logMessage": title
        }),
        success: function(caseServiceId) {
            if ( caseServiceId && saleOrderToSave) {
                var promises = [];
                for (const key in saleOrderToSave) {
                    if (saleOrderToSave[key]) {
                        promises.push(createSaleOrder(key, caseServiceId))
                    }
                }
                
                Promise.all(promises).then(function() {
                    $('#' + dialog).window('close');
                });
            }
            getCount('case_service', 'serviceCount');
        },
        contentType: 'application/json'
   });
}

function createSaleOrder(customerSOID, caseServiceID) {
    return new Promise(function (resolve) {
        $.post({
            type: "POST",
            url: '/lexor_cs/api/case_service_detail',
            data: JSON.stringify({
                "caseServiceID": caseServiceID,
                "customerSOID": customerSOID
            }),
            success: function() {
                resolve(true);
            },
            contentType: 'application/json'
        });
    }) ;
}

function submitUserForm() {
    $('#userForm').form();
    var docNumber = Math.floor(Math.random() * 100000) + 1;
    if ($("#userForm").form('validate')) {
        createCase(docNumber, $("[name='casePriority']").val());
        createUser(docNumber);
    }
}

function createUser(docNumber) {
    var users = JSON.parse(localStorage.getItem("users"));
    
    users.push({
        doc_number: docNumber,
        case_status: 1, 
        name: "Customer " + docNumber,
        mobile_phone: $("[name='mobilePhone']").val(),
        business_phone: $("[name='businessPhone']").val(),
        service_rep_name: $("[name='customerService']").val(),
        action: '<a href="javascript::void(0)" onClick="createCase(' +docNumber+ ')" class="easyui-linkbutton">Select</a>'
    });
    
    localStorage.setItem("users", JSON.stringify(users));
}


function getCaseInformation(caseId) {
    var status = { 0: 'Close', 1: "Open"};
    $.get({
        url: '/lexor_cs/api/case/' + caseId,
        success: function(data) {
            var caseInformation = $(".case-info__status");
            $("#casePriorityText").textbox({value: casePriority[data.casePriority]});
            caseInformation.html(caseInformation.text().replace('{name}', data.caseID).replace('{status}', status[data.status]));
        },
        contentType: 'application/json'
   });
}

function getCount(service, assignElement) {
    $.get({
        url: '/lexor_cs/api/' + service + '/count',
        success: function(count) {
            if (  parseInt(count) > 0 ) {
                $("." + assignElement).html("(" + count + ")");
                $("#" + assignElement).checkbox({
                    disabled: true,
                    checked: true
                });
            }
        },
        contentType: 'application/json'
   });
}

function loadCase() {
    var caseId = null;
    if (caseId = $.urlParam('case_id')) {
        getCaseInformation(caseId);
        getCount('case_service', 'serviceCount');
        getCount('case_return', 'returnCount');
        loadTransaction(caseId);
        getUserInfo();
    } else {
        $('#userGrid').datagrid({
            data: JSON.parse(localStorage.getItem('users')),
            onLoadSuccess: function() {
                $(".easyui-linkbutton").linkbutton();
            }
        });
    }
}

function loadTransaction(caseId) {
    $.get({
        url: "/lexor_cs/api/case_info/" + caseId +"/" + caseId,
        success: function(data) {
            let tableData = [];
            if ( data ) {
                data.forEach(function(data) {
                    tableData.push({
                        doc_number: data.docCode,
                        case_status: data.status,
                        name: data.caseID,
                        mobile_phone: data.address,
                    });
                });
                $("#transactionDataGrid").datagrid({
                    data: tableData
                });
            }
        },
        contentType: 'application/json'
    });
}

function createSaleOrder(customerSOID, caseServiceID) {
    return new Promise(function (resolve) {
        $.post({
            type: "POST",
            url: '/lexor_cs/api/case_service_detail',
            data: JSON.stringify({
                "caseServiceID": caseServiceID,
                "customerSOID": customerSOID
            }),
            success: function() {
                resolve(true);
            },
            contentType: 'application/json'
        });
    }) ;
}


$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results === null) {
       return null;
    }
    return decodeURI(results[1]) || 0;
}