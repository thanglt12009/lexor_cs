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

    $("#editCase").on("click", function () {
        getCaseType();
        $("#editMobilePhone").textbox({value: window.defaultUser.mobile_phone, required: true, validateOnCreate: false, width: 337});
        $("#editBusinessPhone").textbox({value: window.defaultUser.business_phone, required: true, validateOnCreate: false, width: 337});
        $('#editContactDialog').window('open');
    });
    
    $("#editUser").on("click", function () {
        editCase($.urlParam("case_id")); 
    });

});

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

function reInitUser() {
    var users = [];
    users[0] = {
        doc_number: 1,
        case_status: 1,
        name: "Customer 01",
        mobile_phone: "0999-999-999",
        business_phone: "083-999-9999",
        service_rep_name: "Customer Service Rep 1",
        action: '<a href="javascript:void(0)" onClick="createCase(1)" class="easyui-linkbutton">Select</a>'
    };

    if (localStorage.getItem("users") === null) {
        localStorage.setItem("users", JSON.stringify(users));
    }
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
        href: 'case_service.html',
        onLoad: function () {
            registerAddSaleOrder(defaultSaleOrder);
            registerCaseSeviceAutoComplete();
            registerSaveCaseService(path, dialogElement, title);
        }
    });
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
        getValue: "name",
        data: soList,
        list: {
            match: {
                enabled: true
            },
            onClickEvent: function () {
                selectedSO['value'] = $("#saleOrderSearch").getSelectedItemData().code;
            }
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

        registerSOCheckbox(selectedSO['value'], name);
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

function createSaleOrder(caseServiceID, customerSOID) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/case_service_detail',
        data: JSON.stringify({
            "caseServiceID": caseServiceID,
            "customerSOID": customerSOID
        }),
        success: function () {
            resolve(true);
        },
        contentType: 'application/json'
    });
}

function createServiceDetails(caseServiceID, customerSOID) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/case_service_detail',
        data: JSON.stringify({
            "caseServiceID": caseServiceID,
            "customerSOID": customerSOID
        }),
        success: function () {
            resolve(true);
        },
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
        mobile_phone: $("[name='mobilePhone']").val(),
        business_phone: $("[name='businessPhone']").val(),
        service_rep_name: $("[name='customerService']").val(),
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
        url: '/lexor_cs/api/' + service + '/count',
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
            "customerSOID": 1,
            "logMessage": title
        }),
        success: function(caseServiceId) {
            if ( caseServiceId && saleOrderToSave ) {
                var promises = [];

                if ( Object.keys(saleOrderToSave).length === 0 ) {
                    createMasterProduct(caseServiceId, {});
                }

                for (const key in saleOrderToSave) {
                    if (path === "/lexor_cs/api/case_service") {
                        if (saleOrderToSave[key]) {
                            promises.push(createSaleOrder(caseServiceId, key));
                        }

                        if (productList[key]) {
                            createMasterProduct(caseServiceId, productList[key]);
                        }
                    } else {
                        if (saleOrderToSave[key]) {
                            promises.push(createRMASaleOrder(caseServiceId, key));
                        }
                    }
                }
                
                Promise.all(promises).then(function() {
                    $('#' + dialog).window('close');
                });
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
        $('#userGrid').datagrid({
            data: JSON.parse(localStorage.getItem('users')),
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

function createSaleOrder(customerSOID, caseServiceID) {
    return new Promise(function (resolve) {
        $.post({
            type: "POST",
            url: '/lexor_cs/api/case_service_detail',
            data: JSON.stringify({
                "caseServiceID": caseServiceID,
                "customerSOID": customerSOID,
            }),
            success: function () {
                resolve(true);
            },
            contentType: 'application/json'
        });
    });
}

function createRMASaleOrder(rmaId, soId) {
    return new Promise(function (resolve) {
        $.post({
            type: "POST",
            url: '/lexor_cs/api/rma_so',
            data: JSON.stringify({
                "RMAID": rmaId
            }),
            success: function (response) {
                if (rmaProductList[soId] && withOutSaveOrder === false) {
                    let productList = rmaProductList[soId];
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

function createProducts(caseServiceID, products ) {
    products['serviceMasterID'] = parseInt(caseServiceID);
    $.post({
        type: "POST",
        url: '/lexor_cs/api/serviceDetail',
        data: JSON.stringify(products),
        contentType: 'application/json'
    });
}

function createRMAProducts(soId, rmaID, products) {
    products['RMAID'] = parseInt(rmaID);
    products['SOID'] = parseInt(soId);
    $.post({
        type: "POST",
        url: '/lexor_cs/api/rma_soDetail',
        data: JSON.stringify(products),
        contentType: 'application/json'
    });
}

function createMasterProduct(caseServiceID, productList) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/serviceMaster',
        data: JSON.stringify({
            status: 0,
            caseServiceID: caseServiceID
        }),
        success: function (serviceMasterID) {
            for (const soKey in productList) {
                createProducts(serviceMasterID, productList[soKey]);
            }
        },
        contentType: 'application/json'
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
            $('#editContactDialog').window('close');
            editCaseTypeValue();
            getCaseType();
        }
    });
}


$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results === null) {
        return null;
    }
    return decodeURI(results[1]) || 0;
}