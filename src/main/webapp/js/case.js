saleOrders = {};
saleOrderToSave = {};
casePriority = {
    1: "Low",
    2: "Normal",
    3: "High",
    4: "Critical"
};
transactionType = {
    1: "Processing",
    2: "Closed"
};
customerServiceRep = {
    0: "Auto Rotation",
    1: "Customer Service Rep 1",
    2: "Customer Service Rep 2",
    3: "Customer Service Rep 3",
    4: "Customer Service Rep 4"
};

caseTypes = {};
editCaseType = {};
caseTypeValue = {};
newCaseType = {};
caseToUpdate = {};
caseType = {};
selectedSO = {};
serviceMasterID = {};
withOutSaveOrder = false;
productList = {
    1: [
        {
            productID: 1,
            quantity: 1,
            soldPrice: 2095.02,
            amount: 2095.20,
            totalWeight: 900,
            isWarranty: 1,
            serialNumber: "Product 02"
        },
        {
            productID: 2,
            quantity: 1,
            soldPrice: 2095.02,
            amount: 2095.20,
            totalWeight: 900,
            isWarranty: 1,
            serialNumber: "Product 01"
        }
    ]
};

rmaProductList = {
    1: [
        {
            productID: 1,
            quantity: 1,
            returnPrice: 2095.02,
            price: 2095.20,
            warehouse: 1,
            receiver: 1
        },
        {
            productID: 2,
            quantity: 2,
            returnPrice: 2095.02,
            price: 2095.20,
            warehouse: 1,
            receiver: 1
        }
    ]
};

soList = [
    {"name": "S0001", "code": "1"}
];

serviceType = {
    1: "buyPartCount",
    2: "serviceCount",
    3: "returnCount"
};

$(document).ready(function () {
    loadCase();
    $("#addNew").on("click", function () {
        $('#addContactDialog').dialog({
            title: 'New Contact',
            width: 400,
            height: 400,
            closed: false,
            cache: false,
            modal: true,
            href: 'user.html',
            onLoad: function () {
                $(".easyui-checkbox").checkbox({
                    onChange: function (value) {
                        caseType[$(this).val()] = value;
                    }
                });
            }
        });
    });

    $("#editSaleOrder").on("click", function () {
        registerCaseTransactionDialog('editSaleOrderDialog', 'Service', $(this).attr("path"));
    });

    $("#editReturn").on("click", function () {
        registerCaseTransactionDialog('editSaleOrderDialog', 'Return', $(this).attr("path"), {
            withOutSaveOrder: false
        });
    });

    $("#editContact").on("click", function() {
        $('#editContactDialog').dialog({
            title: 'Edit Contact',
            width: 400,
            height: 400,
            closed: false,
            cache: false,
            modal: true,
            href: 'edit_user.html',
            onLoad: function () {
                $("#editMobilePhone").textbox({value: window.defaultUser.mobile_phone, required: true, validateOnCreate: false, width: 337});
                $("#editBusinessPhone").textbox({value: window.defaultUser.business_phone, required: true, validateOnCreate: false, width: 337});
                $("#editFirstName").textbox({value: window.defaultUser.first_name, required: true, validateOnCreate: false, width: 337});
                $("#editLastName").textbox({value: window.defaultUser.last_name, required: true, validateOnCreate: false, width: 337});
            }
        });
        $('#editContactDialog').dialog("move", {top: 100});
    });

    $("#editCase").on("click", function () {
        $('#editCaseDialog').dialog({
            title: 'Edit Case Information',
            width: 400,
            height: 400,
            closed: false,
            cache: false,
            modal: true,
            inline: true,
            href: 'case_model.html',
            onLoad: function () {
               getCaseType();
            }
        });
        $('#editCaseDialog').dialog("move", {top: 100});
    });
});

function submitCaseForm() {
    editCase($.urlParam("case_id")); 
}

function setEditCaseData(updateCase) {
    $('#customerService').combo({
        onChange: function(value) {
            caseToUpdate.customerServiceRep = value;
        }
    }).combo('setText', customerServiceRep[updateCase.customerServiceRep] || customerServiceRep);
   
    $('#editCasePriority').combo({
        onChange: function(value) {
            caseToUpdate.casePriority = value;
        }
    }).combo('setText', casePriority[updateCase.casePriority] || updateCase.casePriority);
}

function getCaseType() {
     $.get({
        url: '/lexor_cs/api/casetype/find/' + $.urlParam('case_id'),
        success: function (data) {
            editCaseType = {};
            if ( data ) {
                registerEditCaseType("editBuyPart", false);
                registerEditCaseType("editService", false);
                registerEditCaseType("editReturnCK", false);
                for ( i = 0 ; i < data.length; i ++ ) {
                    editCaseType[data[i].caseTypeValue] = data[i];
                    switch (data[i].caseTypeValue) {
                        case "1":
                            registerEditCaseType("editBuyPart");
                            break;
                        case "2":
                            registerEditCaseType("editService");
                            break;
                        case "3":
                            registerEditCaseType("editReturnCK");
                            break;
                    }
                } 
            }
        },
        contentType: 'application/json'
    });
}

function registerEditCaseType(id, isCheck = true) {
    $('#' + id).checkbox({
        checked: isCheck,
        onChange: function(value) {
            var id = $(this).attr('data-id');
            newCaseType[id] = value;
        } 
    });
}

function editCaseTypeValue() {
    if ( newCaseType ) {
        for ( key in newCaseType ) {
            if ( !editCaseType[key] ) {
                createCaseTypeValue(key);
            } else {
                if ( newCaseType[key] === false ) {
                    deleteCaseType(editCaseType[key].caseTypeID);
                }
            }
        }
    }
}

function createCaseTypeValue(typeValue) {
     $.post({
        type: "POST",
        url: '/lexor_cs/api/casetype',
        data: JSON.stringify({
            "caseID": $.urlParam("case_id"),
            "caseTypeValue" : typeValue
        }),
        contentType: 'application/json'
    });
}

function deleteCaseType(caseTypeId) {
    $.ajax({
        contentType: 'application/json',
        url: '/lexor_cs/api/casetype/' + caseTypeId,
        type: 'DELETE'
    });
}

function registerCaseTransactionDialog(dialogElement, title, path, defaultSaleOrder = {}) {

    var element = $('#' + dialogElement);
    element.dialog({
        title: title,
        width: 400,
        height: 320,
        closed: false,
        cache: false,
        modal: true,
        inline: true,
        href: 'case_service.html',
        onLoad: function () {
            registerAddSaleOrder(defaultSaleOrder);
            registerCaseSeviceAutoComplete();
            registerSaveCaseService(path, dialogElement, title);
        }
    });
    $(element).dialog("move", {top: 100});
}

function registerSaveCaseService(path, dialogElement, title) {
    $("#saveCaseService").on("click", function () {
        if ($.urlParam('case_id')) {
            createServiceOrReturn($.urlParam('case_id'), path, dialogElement, title);
        }
    });
}

function registerCaseSeviceAutoComplete() {
    var options = {
        url: function(orderId) {
            return "/lexor_cs/api/apiSO/find/" + orderId;
	},
        getValue: "code",
        ajaxSettings: {
            dataType: "json",
            method: "GET"
        },
        theme: "square"
    };
    $("#saleOrderSearch").easyAutocomplete(options);
}

function registerAddSaleOrder(saleOrder = {}) {
    saleOrderToSave = {};
    saleOrders = saleOrder;

    if (saleOrder && saleOrder.withOutSaveOrder === false) {
        registerSOCheckbox("withOutSaveOrder", "Without Sale Order");
    }

    $("#addSaleOrder").on("click", function () {
        var name = $("#saleOrderSearch").val();
        if (saleOrders[name] || name.trim() === '') {
            return;
        }

        registerSOCheckbox(name, name);
        saleOrders[name] = true;
        $("#saleOrderSearch").val(null);
    });
}

function registerSOCheckbox(id, name) {
    var template = '<div class="checkbox-type"><input id=":id" class="easyui-checkbox saleOrder" labelPosition="after" name=":name" value=":name"></div>';

    $('#selectionSaleOrder').append(template.replace(':id', id).replace(':name', id).replace(':name', id));
    $('#' + id).checkbox({
        label: name,
        value: id,
        checked: false,
        onChange: function (value) {
            if (($(this)[0].defaultValue === "withOutSaveOrder")) {
                withOutSaveOrder = value;
            } else {
                if (value) {
                    saleOrderToSave[id] = true;
                } else {
                    saleOrderToSave[id] = false;
                }
            }       
        }
    });
}

function createCase(customerId, priority = 1) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/case',
        data: JSON.stringify({
            "caseType": 1,
            "customerID": customerId,
            "status": 1,
            "casePriority": parseInt(priority),
            "customerServiceRep": $("[name='customerService']").val()
        }),
        success: function (caseId) {
            createCasesType(caseType, caseId);
            window.location.href = '/lexor_cs/pages/cs/purchaseorder/case.html?case_id=' + caseId + "&user_id=" + customerId
        },
        contentType: 'application/json'
    });
}

function createCaseType() {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/case',
        data: JSON.stringify({
            "customerID": customerId,
            "status": 2,
            "casePriority": parseInt(priority)
        }),
        success: function (caseId) {
            window.location.href = '/lexor_cs/pages/cs/purchaseorder/case.html?case_id=' + caseId + "&user_id=" + customerId
        },
        contentType: 'application/json'
    });
}

function createCasesType(caseTypes, caseId) {
    for (var type in caseTypes) {
        if (caseTypes[type] && caseTypes[type] === true) {
            $.post({
                type: "POST",
                url: '/lexor_cs/api/casetype',
                data: JSON.stringify({
                    "caseID": caseId,
                    "caseTypeValue": type
                }),     
                contentType: 'application/json'
            });
        }
    }
}

function createServiceDetails(caseServiceID, customerSOID) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/case_service_detail',
        data: JSON.stringify({
            "caseServiceID": caseServiceID,
            "customerSOID": customerSOID
        }),
        contentType: 'application/json'
    });
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
        case_status: 2,
        name: "Customer " + docNumber,
        first_name: $("[name='firstName']").val(),
        last_name: $("[name='lastName']").val(),
        mobile_phone: $("[name='mobilePhone']").val(),
        business_phone: $("[name='businessPhone']").val(),
        service_rep_name: $("[name='customerService']").val(),
        email: $("[name='email']").val(),
        action: '<a href="javascript:void(0)" onClick="createCase(' + docNumber + ')" class="easyui-linkbutton">Select</a>'
    });

    localStorage.setItem("users", JSON.stringify(users));
}


function getCaseInformation(caseId) {
    var status = {2: 'Closed', 1: "Open"};
    $.get({
        url: '/lexor_cs/api/case/' + caseId,
        success: function (data) {
            caseToUpdate = data;
            $("#customerServiceRep").textbox({value: customerServiceRep[data.customerServiceRep]});
            var caseInformation = $(".case-info__status");
            $("#casePriorityText").textbox({value: casePriority[data.casePriority]});
            caseInformation.html(caseInformation.text().replace('{name}', data.caseID).replace('{status}', status[data.status]));
            
            setEditCaseData(caseToUpdate);
            getCaseType();
        },
        contentType: 'application/json'
    });
}

function getCount(service, assignElement) {
    $.get({
        url: '/lexor_cs/api/' + service + '/count/' + $.urlParam("case_id"),
        success: function (count) {
            if (parseInt(count) > 0) {
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
function createServiceOrReturn(caseId, path, dialog, title) {
    $.post({
        type: "POST",
        url: path,
        data: JSON.stringify({
            "caseID": caseId,
            "customerSOID": $.urlParam('user_id'),
            "logMessage": title,
            "status": 1
        }),
        success: function(caseServiceId) {
            if ( caseServiceId && saleOrderToSave ) {
                var promises = [];

                
                const productList = getProductsBySaleOrder(saleOrderToSave).then(function(soList) {
                    if ( path === "/lexor_cs/api/case_service" ) {
                        createMasterProduct(caseServiceId, {}).then(function(masterId) {
                            for (const key in saleOrderToSave) {
                                if (saleOrderToSave[key]) {
                                    promises.push(createServiceDetails(caseServiceId, key));
                                }

                                if (soList[key]) {
                                    for (const soKey in soList[key]) {
                                        promises.push(createProducts(masterId, soList[key][soKey]));
                                    }
                                }
                            }
                        });
                    } else {
                        for (const key in saleOrderToSave) {
                            if (soList[key]) {
                                promises.push(createRMASaleOrder(caseServiceId, key, soList[key]));
                            }
                        }
                    }
                    
                    Promise.all(promises).then(function() {
                        $('#' + dialog).window('close');
                    });
                    
                });
                
                if (path !== "/lexor_cs/api/case_service") {
                    promises.push(createRMAPayment(caseServiceId))
                    Promise.all(promises).then(function() {
                         $('#' + dialog).window('close');
                    });
                }
            }
            createTransaction(caseId, title, "Address");
            createActivity(caseId, "Create " + title);
            getCount('case_service', 'serviceCount');
            getCount('rma', 'returnCount');
            loadTransaction(caseId);
            loadActivity(caseId);
            
        },
        contentType: 'application/json'
   });
}

function loadCase() {
    var caseId = null;
    if (caseId = $.urlParam('case_id')) {
        getCaseInformation(caseId);
        loadCaseType(caseId);
        getCount('case_service', 'serviceCount');
        getCount('rma', 'returnCount');
        loadTransaction(caseId);
        loadActivity(caseId);
        getUserInfo();
    } else {
        const users = JSON.parse(localStorage.getItem('users')).map(function(user) {
           user = user.contactInfo;
           user['name'] = user['firstName'] + " " + user['lastName'];   
           user['action'] = '<a href="javascript:void(0)" onClick="createCase('+user.id+')" class="easyui-linkbutton">Select</a>';

           return user;
        });
        $('#userGrid').datagrid({
            data: users,
            onLoadSuccess: function () {
                $(".easyui-linkbutton").linkbutton();
            }
        });
    }
}

function loadTransaction(caseId) {
    $.get({
        url: "/lexor_cs/api/case_info/" + caseId + "/" + caseId,
        success: function (data) {
            let tableData = [];
            if (data) {
                for (i = 0; i < data.length; i++) {
                    tableData.push({
                        doc_number: data[i].docCode,
                        case_status: transactionType[data[i].status],
                        type: data[i].status,
                        address: data[i].address,
                    });
                }

                $("#transactionDataGrid").datagrid({
                    data: tableData
                });
            }
        },
        contentType: 'application/json'
    });
}

function createRMASaleOrder(rmaId, soId, soList) {
    return new Promise(function (resolve) {
        $.post({
            type: "POST",
            url: '/lexor_cs/api/rma_so',
            data: JSON.stringify({
                "RMAID": rmaId,
                "SOID" : soId
            }),
            success: function (response) {
                if (soList && withOutSaveOrder === false) {
                    let productList = soList;
                    for (const soKey in productList) {
                        createRMAProducts(response, rmaId, productList[soKey]);
                    }
                }
                resolve();
            },
            contentType: 'application/json'
        });
    });
}

function createRMAPayment(rmaId) {
    return new Promise(function (resolve) {
        $.post({
            type: "POST",
            url: '/lexor_cs/api/rma_payment',
            data: JSON.stringify({
                "RMAID": rmaId,
                "paymentType" : 1,
                "paymentStatus": 1
            }),
            success: function (response) {
                resolve();
            },
            contentType: 'application/json'
        });
    });
}

function createProducts(caseServiceID, products ) {
    products['serviceMasterID'] = parseInt(caseServiceID);
    products['productID'] = Math.floor(Math.random() * 1000) + 1;
    products['soldPrice'] = products['price'];
    products['totalWeight'] = 1000;
    products['amount'] =  products['price'] *  products['quantity'];
    $.post({
        type: "POST",
        url: '/lexor_cs/api/serviceDetail',
        data: JSON.stringify(products),
        contentType: 'application/json'
    });
}

function createRMAProducts(soId, rmaID, products) {
    products['RMAID'] = parseInt(rmaID);
    products['RMASOID'] = parseInt(soId);
    $.post({
        type: "POST",
        url: '/lexor_cs/api/rma_soDetail',
        data: JSON.stringify(products),
        contentType: 'application/json'
    });
}

function createMasterProduct(caseServiceID) {
    return new Promise(function(resolve) {
        $.post({
            type: "POST",
            url: '/lexor_cs/api/serviceMaster',
            data: JSON.stringify({
                status: 0,
                caseServiceID: caseServiceID
            }),
            success: function (serviceMasterID) {
                resolve(serviceMasterID);
            },
            contentType: 'application/json'
        })
    });
}


function createTransaction(caseId, documentCode, address) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/case_info',
        data: JSON.stringify({
            "caseID": caseId,
            "docCode": documentCode,
            "address": address,
            "status": 1,
            "createdDate": getCurrentTime()
        }),
        contentType: 'application/json'
    });
}

function loadCaseType(caseId) {
    
    for ( key in serviceType) {
        $("#" + serviceType[key]).checkbox({
            disabled: true,
            checked: false
        });
    }
    
    $.get({
        url: "/lexor_cs/api/casetype/find/" + caseId,
        success: function (data) {
            if (data) {
                data.forEach(function (data) {
                    $("#" + serviceType[data.caseTypeValue]).checkbox({
                        disabled: true,
                        checked: true
                    });
                });
            }
        },
        contentType: 'application/json'
    });
}

function editCase(caseId) {
    $.ajax({
        contentType: 'application/json',
        url: '/lexor_cs/api/case/' + caseId,
        data: JSON.stringify(caseToUpdate),
        type: 'PUT',
        success: function() {
            $('#editCaseDialog').window('close');
            editCaseTypeValue();
            loadCase();
        }
    });
}