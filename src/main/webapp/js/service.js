var removedProductList = [];
var serviceMasterID = 0;
var caseServiceID = 0;
$(document).ready(function () {
    loadServices();
    loadPaymentMethod();
    registerUpdatePaymentMethod();
    
     $("#editReturn").on("click", function () {
        registerCaseTransactionDialog('editSaleOrderDialog', 'Return', $(this).attr("path"), {
            withOutSaveOrder: true
        });
    });
    
    $("#editProducts").on("click", function(){
       reloadList();
    });
    
    $(".editServiceStatus").on("click", function(){
        updateServiceStatus($(this).attr('data-id'), $(this).attr('data-id'));
    });
    
    $("#saveProducts").on("click", function(){
        reloadShippingAmount(function(){
            reloadList(false);
        });
    });
    
    $("#addSaleOrder").on("click", function(){
       $('#editServiceDialog').dialog({
            title: 'Add Service',
            width: 700,
            height: 400,
            closed: false,
            cache: false,
            modal: true,
            inline: true,
            href: 'sale_order.html',
            onLoad: function() {
                getSOProductList().then(function(template){
                    $("#addProductDialogTab").html(template.join(""));
                    $('#addProductDialogTab').tabs({
                        border:false
                    });
                    for ( let key in productSaleOrder ) {
                        $('#' + key).datagrid({
                            pagination:true,
                            pageSize:20,
                            showFooter: true,
                            data: productSaleOrder[key],
                            columns: [[
                                {field:'product_name',title:'Product Name',width:200},
                                {field:'under_warranty',title:'Under Warranty',width:100},
                                {field:'warranty_issue',title:'Warranty Issued',width:100},
                                {field:'warranty_expire',title:'Warranty Expired',width:100},
                                {field:'action',title:'Code',width:100}
                            ]],
                            onLoadSuccess: function() {
                                $(".easyui-linkbutton").linkbutton();
                            }
                        }).datagrid('clientPaging');
                    }
                });

            }
        });
        $('#editServiceDialog').dialog("move", {top: 100});
        $('#editServiceDialog').show();

    });
    $("#editSalon").on("click", function () {
        $('#editSalonDialog').dialog({
            title: 'Salon Information',
            width: 400,
            height: 400,
            closed: false,
            cache: false,
            modal: true,
            inline: true,
            href: 'salon_information.html'
        });
        $('#editSalonDialog').dialog("move", {top: 100});
    });
    $("#editProducts").on("click", function () {
        $("#addSaleOrder").show();
        $("#saveProducts").show();
        $(this).hide();
    });
    $("#saveProducts").on("click", function () {
        $("#addSaleOrder").hide();
        $("#editProducts").show();
        
        const promises = saveProduct().concat(editProduct()).concat(removeListProduct()).concat(saveShippingFee()).concat(createServiceActivity($.urlParam('service_id'), "Edit product"));
        Promise.all([
            saveProduct(),
            editProduct(),
            removeListProduct(),
            saveShippingFee(),
            createServiceActivity($.urlParam('service_id'), "Edit product")
        ]).then(function() {
            getProducts();
            loadServiceActivity($.urlParam('service_id'));
        }).catch(function() {
            getProducts();
            loadServiceActivity($.urlParam('service_id'));
        });       
        $(this).hide();
    });
});

function saveProduct() {
    var products = prepareProductToSave();
    
    const promise = [];
    if ( products ) {
        for ( let i = 0 ; i < products.length; i++ ) {
            promise.push(
                $.post({
                    type: "POST",
                    url: '/lexor_cs/api/serviceDetail',
                    data: JSON.stringify(products[i]),
                    contentType: 'application/json'
                })
            ); 
        }
    }
    
    return promise;
}

function editProduct() {
    var products = prepareProductToEdit();
    
    const promise = [];
    if ( products ) {
        for ( let i = 0 ; i < products.length; i++ ) {
            promise.push(
                $.ajax({
                    contentType: 'application/json',
                    url: '/lexor_cs/api/serviceDetail/' + products[i].serviceDetailID,
                    data: JSON.stringify(products[i]),
                    type: 'PUT',
                    sussess : function() {
                        resolve(true);
                    }
                })
            );    
        }
    }
    
    return promise;
}

function removeListProduct() {
    const promise = [];
    if ( removedProductList ) {
        for ( var product in removedProductList) {
            if (id = removedProductList[product].serviceDetailID) {
                promise.push(
                    $.ajax({
                        contentType: 'application/json',
                        url: '/lexor_cs/api/serviceDetail/' + id,
                        type: 'DELETE',
                        sussess : function() {
                            resolve(true);
                        }
                    })
                );
            }
        }
    }
    
    return promise;
}

function prepareProductToSave() {
    var products = productDatas.rows;
    var productToSave = [];
    for ( let i = 0; i < products.length; i++ ) {
        if (products[i].serviceMasterID === false) {
            products[i].amount = parseFloat(products[i].amount.replace("$", '')).toFixed(2);
            products[i].soldPrice = parseFloat(products[i].soldPrice.replace("$", '')).toFixed(2);
            products[i].serviceMasterID = serviceMasterID;
            products[i].wareHouse = wareHouseExchange[products[i].wareHouse];
            productToSave.push(products[i]);
        }
    }
    
    return productToSave;
}

function prepareProductToEdit() {    
    var products = productDatas.rows;
    var productToEdit = [];
    for ( let i = 0; i < products.length; i++ ) {
        if (products[i].serviceDetailID) {
            products[i].wareHouse = wareHouseExchange[products[i].wareHouse];
            
            if (typeof products[i].amount == "string"){
                products[i].amount = parseFloat(products[i].amount.replace("$", ""));
            }
            
            if (typeof  products[i].soldPrice == "string"){ 
                products[i].soldPrice = parseFloat(products[i].soldPrice.replace("$", ""));
            }
            
            productToEdit.push(products[i]);
        } 
    };
    
    return productToEdit;
}

function getProducts() {
     $.get({
        url: "/lexor_cs/api/serviceDetail/" + serviceMasterID + '/' + serviceMasterID,
        success: function(data) {
            productDatas.rows = [];
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    dateBoxProduct[data[i]['productID']] = data[i]['shipingDay'] || new Date().toISOString().slice(0, 10);
                    comboBoxedProduct[data[i]['productID']] = variableOptions[data[i]['wareHouse']];
                    serviceMasterID = data[i]['serviceMasterID'];
                    data[i]['product_name'] = data[i]['serialnumber'];
                    data[i]['shipingDay'] = dateBoxProduct[data[i]['productID']];
                    data[i]['no'] = data[i]['productID'];
                    data[i]['isWarranty'] = data[i]['isWarranty'] ? isWarrantyOptions[data[i]['isWarranty']] : isWarrantyOptions[1];
                    data[i]['wareHouse'] = variableOptions[data[i]['wareHouse']] || variableOptions[1];
                    data[i]['shippingDate'] = dateBoxProduct[data[i]['productID']];                  
                    data[i]['warehouseName'] = data[i]['wareHouse'];
                    data[i]['image'] = "<img width='60px' height='60px' src='"+ getProductImage(data[i]['productImage'] || null) + "' />",
                    data[i]['amount'] = "$" + (parseFloat(data[i]['quantity']) * parseFloat(data[i]['soldPrice'])).toString();
                    data[i]['soldPrice'] = "$" + data[i]['soldPrice'];
                    productDatas.rows.push(data[i]); 
                }
            }

            loadProducts();
        },
        contentType: 'application/json'
    });
}

function submitSalonForm() {
    $('#editSalonDialog').window('close');
}

function loadShipping(shippingDetail = [{}]) {

    $('#shippingGrid').datagrid({
        pagination:true,
        pageSize:20,
        height: 400,
        showFooter: true,
        data: shippingDetail,
        onLoadSuccess: function() {
            $('.shippingOrder').combobox({
                data:[
                    {id : 0, text: "R&L"},
                    {id : 1, text: "Old Dominion"},
                    {id : 2, text: "UPS"},
                    {id : 3, text: "USPS"},
                    {id : 4, text: "Lexor Truck"},
                ],
                valueField:'id',
                textField:'text',
                width: 140
            });
        }
    }).datagrid('clientPaging');
}

function loadService(data = 'all') {
    var services = [];

    $.get({
        url: "/lexor_cs/api/case_service/find/" + data,
        success: function(data) {
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    services.push({
                        service_number: data[i].caseServiceID,
                        original_case: data[i].caseID,
                        customer_name: data[i].customerSOID,
                        action: '<a href="javascript:void(0)" onClick="openService( '+ data[i].caseServiceID +' , '+ data[i].customerSOID +')" class="easyui-linkbutton">Select</a>'
                    }); 
                }

                $("#serviceGrid").datagrid({
                    pagination:true,
                    pageSize:20,
                    showFooter: true,
                    data: services,
                    onLoadSuccess: function() {
                        $(".easyui-linkbutton").linkbutton();
                    }
                }).datagrid('clientPaging');
            }
        },
        contentType: 'application/json'
    });
}

function openService(serviceId, userId) {
    window.location.href = "/lexor_cs/pages/cs/purchaseorder/service.html?user_id="+userId+"&service_id=" + serviceId;
}

function refreshProduct() {
    $('#productGrid').datagrid('data', productDatas).datagrid('clientPaging');
}

function loadProducts() {
    let productShippingDetail = {};
    productDatas.total = productDatas.rows.length;
    for(i = 0 ; i < productDatas.rows.length; i++) {
        var wareHouse = productDatas.rows[i].warehouseName;
        var shipDate = productDatas.rows[i]. shippingDate;        
        var key = wareHouse + shipDate;
        var weight = 0;
        var id = i + 1;
        if (productShippingDetail[key]) {
            weight = productShippingDetail[key]['shipping_weight'] + parseInt(productDatas.rows[i].totalWeight);
            id = productShippingDetail[key]['no'];
        } else {
            weight = parseInt(productDatas.rows[i].totalWeight);
        }

        productShippingDetail[key] = {
            no : id,
            wareHouse : wareHouse,
            shipping_type: '<input class="shippingOrder" name="shippingOrder" value="">',
            shipping_cost: "$" + shippingPrice[wareHouseExchange[wareHouse]],
            shipping_weight: weight,
            shipping_day: shipDate
        };
    }
    
    var datas = shippingDatas;
    var totalShippingCost = 0;
    if (productShippingDetail) {
        datas.rows = [];
        for (const shipping in productShippingDetail) {
            datas.rows.push(productShippingDetail[shipping]);
            totalShippingCost += parseInt(productShippingDetail[shipping].shipping_cost.replace("$", ''));
        }
    }

    shippingFee = (totalShippingCost - discountShipping);
    totalShippingAmount = totalShippingCost;
    datas.footer[0].shipping_cost = '<strong> $'+totalShippingCost+'.00</strong>';
    datas.footer[2].shipping_cost = '<strong> $'+ (shippingFee) +'.00</strong>';
    recalculateAmount();
    $('#productGrid').datagrid({
        pagination:true,
        pageSize:20,
        showFooter: true,
        data: productWithCalculatedAmount(),
        onLoadSuccess: function() {
            loadShipping(datas);
            initRemove();
            registerComboboxAction();
            $("#newAmount").val(shippingFee);
            $('.date-box').each(function(){
                 $(this).datebox({
                    required: true,
                    onSelect: function(date){
                        dateBoxProduct[$(this).attr('data-id')] = date.getDate() + "/" + (date.getMonth()+ 1) + "/" + date.getFullYear();
                    }
                 }).datebox('calendar').calendar({
                    validator: function(date){
                        var now = new Date();
                        var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                        return d1 <= date;
                    }
                });
            });
        }
    }).datagrid('clientPaging');
}

function addProduct(SOID, position) {
    setTimeout(function(){
        let productIndex = productSaleOrder[SOID].findIndex(function(product, index) {
                if(product.productID == position) {
                    return true;
                }
        });
        if ( productDatas.rows.find(
                function(element) {
                    return element.productID === productSaleOrder[SOID][productIndex].productID;
                }
            ) === undefined) {
            
            let newProduct = productSaleOrder[SOID][productIndex];
            let price = "$" + newProduct.price;
            newProduct.price = price;
            productDatas.rows.push(newProduct);
            comboBoxedProduct[newProduct.productID] = "CA";
            removeTagProduct[newProduct.productID] = newProduct.productID;
            reloadShippingAmount(function(){
                reloadList(true, false);
            });
        }
    }, 1000);
}

function removeProduct(key) {
    var remove = productDatas.rows.find((item) => {return item.productID === key});
    productDatas.rows = productDatas.rows.filter((item) => {return item.productID !== key});

    if ( remove ) {
        removedProductList.push(remove);
    }
    loadProducts();
}

function initRemove() {
    $('.removeUser').linkbutton({
        iconCls: 'icon-remove',
        text: ''
    }); 
}

function getComboboxTemplate(id) {
    var options = '<select data-id="' + id + '" class="easyui-combobox" name="dept" style="width:50px;">:selections</select>';
    var selections = "";
    for ( var key in variableOptions ) {
        var value = variableOptions[key];
        if (comboBoxedProduct[id] === value) {
            selections += '<option selected="selected" value="'+ value +'">' + value + '</option>';
        } else {
            selections += '<option value="'+ value +'">' + value + '</option>';
        }
    }

    return options.replace(':selections', selections);
}

function registerComboboxAction() {
    $(".easyui-combobox").on('change', function(){
        comboBoxedProduct[$(this).attr('data-id')] = $(this).val();
    });
    
}

function getEditAmountTemplate(amount) {
    return '$<input type="text" style="width:60px" id="newAmount" value="'+ parseFloat(amount.replace("$", '')) +'" />';
}

function getRemoveTemplate(id) {
    return '<a class="removeUser" onClick="removeProduct('+ id +')" href="javascript:void(0);">'+ id +'</a>';
}

function getDateBoxTemplate(id, value) {
    return '<input class="date-box" style="width: 100px" data-id="'+ id +'" type="text" value="'+ value +'">';
}

function reloadList(isCombobox = true, isReSetup = true) {
    productDatas.rows = productDatas.rows.map( function(product) {
       if ( isCombobox ) {
            product.wareHouse = getComboboxTemplate(product.productID);
            product.no = getRemoveTemplate(product.productID);
            product.shipingDay = getDateBoxTemplate(product.productID, product.shipingDay);

       } else {
            product.wareHouse = comboBoxedProduct[product.productID];
            product.no = removeTagProduct[product.productID] || product.productID;
            product.shipingDay = dateBoxProduct[product.productID];
       }
       return product;
    });
    
    if ( isReSetup ) {
        if (isCombobox) {
            productDatas.footer[1].originalSo = getEditAmountTemplate(productDatas.footer[1].originalSo);
        } else {
            productDatas.footer[1].originalSo = '<strong> $'+ (totalShippingAmount) +'.00</strong>';
        }
    }
    loadProducts();
}

function recalculateAmount(isCombobox) {
    if (isCombobox) {
        productDatas.footer[1].originalSo = getEditAmountTemplate(productDatas.footer[1].originalSo);
    } else {
        productDatas.footer[1].originalSo = '<strong> $'+ (totalShippingAmount) +'.00</strong>';
    }
}

function reloadShippingAmount(callback) {  
    var sum = "$";
    sum+= parseFloat($("#newAmount").val()).toFixed(2);
    productDatas.footer[1].original_so = sum;
    shippingAmount = $("#newAmount").val();
 
    callback();
}

function updateServiceStatus(status, isSubmit = 0) {
    $.ajax({
        contentType: 'application/json',
        url: '/lexor_cs/api/serviceMaster/' + caseServiceID,
        data: JSON.stringify({
            status: parseInt(status),
            isSubmittedProduction: isSubmit
        }),
        success: function () {
            getServiceInformation($.urlParam('service_id'));
        },
        type: 'PUT'
    });
}

function loadServices() {
    var serviceId = null;
    if (serviceId = $.urlParam('service_id')) {
        getServiceInformation(serviceId);
        getUserInfo();
        loadServiceMaster();
        getServiceSO();
        loadServiceActivity(serviceId);
    } else {
        registerServiceSearch();
        loadService();
    }
}

function getServiceInformation(serviceId) {
    $.get({
        url: '/lexor_cs/api/case_service/detail/' + serviceId,
        success: function(data) {
            var text = "Service {name}-{status}";
            var serviceInformation = $(".service-info__status");
            caseServiceID = data.caseServiceID;
            
            if (data.serviceMasterID) {
                serviceMasterID = data.serviceMasterID;
            } else {
                createMasterProduct( data.caseServiceID );
            }
            
            getProducts();
            serviceInformation.html(text.replace('{name}', data.caseServiceID).replace('{status}', serviceStatus[data.status]));
        },
        contentType: 'application/json'
    });
}

function productWithCalculatedAmount() {
    let amount = 0;
    var pattern = /[^0-9.-]+/g;

    for( i = 0; i < productDatas.rows.length; i++ ) {
        
        if (typeof productDatas.rows[i].amount == "string"){
            amount += parseFloat(productDatas.rows[i].amount.replace(pattern, ''));
        } else {
            amount += parseFloat(productDatas.rows[i].amount);
        }
    }
    totalAmount = amount;
    total = totalAmount - discountShipping + parseFloat(totalShippingAmount);

    productDatas.footer[0].originalSo = "<strong>$" + totalAmount.toFixed(2) + " </strong>";
    productDatas.footer[2].originalSo = "<strong>-$" + discountShipping.toFixed(2) + " </strong>";
    productDatas.footer[3].originalSo = "<strong>$" + total.toFixed(2) + " </strong>";
 
    return productDatas;
}

function updatePaymentMethod() {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/' + $.urlParam("service_id"),
        data: JSON.stringify({
            "paymentMethod": paymentMethod, 
        }),
        contentType: 'application/json'
    });
}

function loadPaymentMethod() {
    $("#paymentMethod").combobox({
        onChange: function(value) {
            paymentMethod = value;
        }
    });
}

function createMasterProduct(caseServiceID) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/serviceMaster',
        data: JSON.stringify({
            status: 0,
            caseServiceID: caseServiceID
        }),
        success: function (masterID) {
           serviceMasterID = masterID;
        },
        contentType: 'application/json'
    });
}

function getCaseServiceDetail() {
    $.post({
        type: "GET",
        url: '/lexor_cs/api/case_service_detail',
        data: JSON.stringify({
            status: 0,
            caseServiceID: caseServiceID
        }),
        success: function (caseServiceDetail) {
           caseServiceDetail = caseServiceDetail;
        },
        contentType: 'application/json'
    });
}

productSaleOrder = {};
function getServiceSO() {
    const serviceId = $.urlParam('service_id');
    $.get({
        type: "GET",
        url: '/lexor_cs/api/case_service_detail/' + serviceId + "/" + serviceId,
        success: function (serviceSO) {
           if ( serviceSO ) {
                const listSO = {};
                for ( let i = 0 ; i < serviceSO.length; i ++) {
                   listSO[serviceSO[i].customerSOID] = serviceSO[i].customerSOID;
                }
                serviceSO = listSO;
                resgisterSoList(listSO);
                getProductsBySaleOrder(listSO).then(function(result) {console.log();
                    productSaleOrder = result;
                });
                
           }
        },
        contentType: 'application/json'
    });
}

function getSOProductList() {
    let promise = [];
    const template = '<div title=":title" style="padding:20px;">' +
        '<table id=":id" style="width: 100%" pagination="true">' +
        '</table>' +
        '</div>';
    
    for ( let key in productSaleOrder ) {
        promise.push(new Promise(function(resolve) {
            let layout = template;
            resolve(layout.replace(":title", key).replace(":id", key));
        }));
    }
    
    return Promise.all(promise);
}

function registerUpdatePaymentMethod() {
    $("#updatePaymentMethod").click(function(){
        $.ajax({
            type: "PUT",
            url: '/lexor_cs/api/serviceMaster/' + $.urlParam('service_id'),
            data: JSON.stringify({
                paymentType: $("#paymentMethod").val()
            }),
            contentType: 'application/json'
        });
    });
}

function loadServiceMaster() {
    $.ajax({
        type: "GET",
        url: '/lexor_cs/api/serviceMaster/detail/' + $.urlParam('service_id'),
        success: function(serviceMasterRES) {
            serviceMaster = serviceMasterRES;
            $('#paymentMethod').combobox('setValue', serviceMaster.paymentType);
        },
        contentType: 'application/json'
    });
}

function saveShippingFee() {
    return [
        $.ajax({
            type: "PUT",
            url: '/lexor_cs/api/serviceMaster/' + $.urlParam('service_id'),
            data: JSON.stringify({
                shippingFee: shippingFee,
                total: total,
                subTotal: totalAmount
            }),
            contentType: 'application/json',
            success: function() {
            }
        })
    ];
}

function registerServiceSearch() {
    $('#serviceSearch').textbox({
        inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents, {
            keyup: function(event){
                if(event.keyCode === 13){
                    event.preventDefault();
                    let keyword = $(event.data.target).textbox('getText');
                    
                    searchService(keyword.trim() == "" ? "all" : keyword);
                }
            }
        })
    });
}

function searchService(keyword) {
   loadService(keyword);
}

function resgisterSoList(listSO) {console.log(listSO)
    let data = [];
    let position = 1;
    for (let key in listSO) {
        data.push({
           no:  position,
           so: key
        });
        position++;
    }
    $('#soGrid').datagrid({
        data: data
    });
}

function registerAddSaleOrder(saleOrder = {}) {
    saleOrderToSave = {};
    saleOrders = saleOrder;

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

function createServiceOrReturn(caseServiceId, dialog) {
    const promises = [];
    const productList = getProductsBySaleOrder(saleOrderToSave).then(function(soList) {
        for (const key in saleOrderToSave) {
            if (saleOrderToSave[key] && saleOrderToSave[key] === true) {
                promises.push(createServiceDetails(caseServiceId, key));
            }

            if (soList[key]) {
                for (const soKey in soList[key]) {
                    promises.push(createProducts(masterId, soList[key][soKey]));
                }
            }

            Promise.all(promises).then(function() {
                $('#' + dialog).window('close');
            });
        };
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
        contentType: 'application/json'
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