productDatas = {
    "total" : 2,
    "footer":[
	{"price":'<strong>Subtotal</strong>', "warehouse":"$3000.00"},
	{"price":'<strong>Restocking Fee</strong>',"warehouse":"$0.00"},
        {"price":'<strong>Total</strong>',"warehouse":'<strong> $0.00</strong>'}
    ],
    "rows":[]
    
};

servicesData = [{
    product_name: "Product 003 </br> Serial Number: 123-456-789",
    under_warranty: "Y",
    warranty_issue: "25/01/2021",
    warranty_expire: "25/01/2021",
    action: '<a href="javascript:void(0)" onClick="addProduct()" class="easyui-linkbutton">Add</a>'
}];

shippingDatas = {
    "total" : 2,
    "rows":[
        {
            no: 1,
            warehouse: "CA",
            shipping_day: "15/01/2021",
            shipping_weight: "800",
            shipping_type: '<input id="shippingOrder" name="shippingOrder" value="">',
            shipping_cost: "$100.00"    
        }
    ],
    "footer":[
	{"shipping_type":'<strong>Total Shipping Cost</strong>',"shipping_cost":"$10.00"},
	{"shipping_type":'<strong>Shipping Discount</strong>',"shipping_cost":"-$<input style='width:100px' value='5.00' />"},
        {"shipping_type":'<strong>Shipping Fee</strong>',"shipping_cost":'<strong>$5.00</strong>'}
    ]
};

isSumFee = {};
productList = {};
paymentMethod = 1;
comboBoxedProduct = {};
dateBoxProduct = {};
removeTagProduct = {};
shippingDetail = {};
comboBoxedProduct[1] = "Y";
removeTagProduct[1] = 1;
comboBoxedProduct[2] = "N";
removeTagProduct[2] = 2;
dateBoxProduct[1] = "25/01/2021";
dateBoxProduct[2] = "25/01/2021";

rmaSO = {};
discount = 0;
total = 0;
totalAmount = 0;
shippingAmount = 0;
rmaStatus = 1;
rmaTempStatus = 1;
isWarrantyOptions = {1 : "Y", 0: "N"};
variableOptions = {};
wareHouseExchange = {'CA' : 1, 'CB': 2 };
productImages = {
    1: "<img width='60px' height='60px' src='../../../images/product01.jpg' />",
    2: "<img width='60px' height='60px' src='../../../images/product02.jpg' />",
    3: "<img width='60px' height='60px' src='../../../images/product03.jpg' />"
};
paymentMethod = 1;
rmaPayment = {};
var removedProductList = [];
$(document).ready(function () {
    loadServices();
    registerUpdatePaymentMethod();
    loadPaymentMethod();
    
    $(".editRMAStatus").on("click", function(){
         $('#editRMADialog').dialog({
            title: 'Change RMA Status',
            width: 400,
            height: 250,
            closed: false,
            cache: false,
            modal: true,
            href: 'rma_status.html'
        });
        $('#editRMADialog').dialog("move", {top: 100});
       rmaTempStatus = $(this).attr('data-id');
    });
    
    $("#editReturn").on("click", function () {
        registerCaseTransactionDialog('editSaleOrderDialog', 'Return', $(this).attr("path"), {
            withOutSaveOrder: true
        });
    });
    
    $("#editProducts").on("click", function(){
       reloadList();
    });
    
    $("#saveProducts").on("click", function(){
        reloadShippingAmount(function(){
            reloadList(false);
        });
    });
    
    $("#addSaleOrder").on("click", function(){
       $('#editServiceDialog').dialog({
            title: 'Add Product',
            width: 700,
            height: 400,
            closed: false,
            cache: false,
            modal: true,
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
    });
    $("#editSalon").on("click", function () {
        $('#editSalonDialog').dialog({
            title: 'Salon Information',
            width: 400,
            height: 400,
            closed: false,
            cache: false,
            modal: true,
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
        
        Promise.all([
            saveProduct(),
            editProduct(),
            removeListProduct(),
            updateTotal(),
            createRMAActivity($.urlParam('rma_id'), "Edit product")
        ]).then(function() {
            getProducts();
            loadRMAActivity($.urlParam('rma_id'));
        }); 
        
        $(this).hide();
    });
});

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

function registerSaveCaseService(path, dialogElement, title) {
    $("#saveCaseService").on("click", function () {
        if ($.urlParam('rma_id')) {
            createServiceOrReturn($.urlParam('rma_id'), dialogElement);
        }
    });
}

function createServiceOrReturn(caseServiceId, dialog) {console.log(saleOrderToSave);
    const promises = [];
    const productList = getProductsBySaleOrder(saleOrderToSave).then(function(soList) {
        for (const key in saleOrderToSave) {
            if (soList[key]) {
                promises.push(createRMASaleOrder(caseServiceId, key, soList[key]));
            }

            Promise.all(promises).then(function() {
                $('#' + dialog).window('close');
            });
        };
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
                if (soList) {
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

function createRMAProducts(soId, rmaID, products) {
    products['RMAID'] = parseInt(rmaID);
    products['RMASOID'] = parseInt(soId);
    $.post({
        type: "POST",
        url: '/lexor_cs/api/rma_soDetail',
        data: JSON.stringify(products),
        contentType: 'application/json',
        success: function() {
           getProducts();
        }
    });
}

function registerAddSaleOrder(saleOrder = {}) {
    saleOrderToSave = {};
    saleOrders = saleOrder;

    if (saleOrder && saleOrder.withOutSaveOrder === false) {
        //registerSOCheckbox("withOutSaveOrder", "Without Sale Order");
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

function submitRMAForm() {
    var quality = $("input[name='quality']").val();
    var status = $("input[name='status']").val();
    
    updateRMAStatus(rmaTempStatus);
    $('#editRMADialog').window('close');
}

function loadService() {
    var services = [];

    $.get({
        url: "/lexor_cs/api/rma/find/1",
        success: function(data) {
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    services.push({
                        service_number: data[i].RMAID,
                        original_case: data[i].caseID,
                        customer_name: data[i].customerSOID,
                        action: '<a href="javascript:void(0)" onClick="openService('+data[i].RMAID+', '+ data[i].customerSOID +')" class="easyui-linkbutton">Select</a>'
                    }); 
                }

                $("#serviceGrid").datagrid({
                    data: services,
                    onLoadSuccess: function() {
                        $(".easyui-linkbutton").linkbutton();
                    }
                });
            }
        },
        contentType: 'application/json'
    });
}

function openService(RMAID, userId) {
    window.location.href = "/lexor_cs/pages/cs/purchaseorder/rma.html?rma_id="+RMAID+"&user_id=" + userId;
}

function refreshProduct() {
    $('#productGrid').datagrid('data', productDatas);
}

function getProducts() {
     $.get({
        url: "/lexor_cs/api/rma_soDetail/" + $.urlParam('rma_id') + "/" + $.urlParam('rma_id'),
        success: function(data) {
            productDatas.rows = [];
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    rmaSO[data[i]['SOID']] = data[i]['RMASOID'];
                    data[i]['no'] = data[i]['productID'];
                    data[i]['quantity'] = data[i]['quantity'];
                    data[i]['reveiver'] = data[i]['reveiver'] ? isWarrantyOptions[data[i]['reveiver']] : isWarrantyOptions[1];
                    data[i]['warehouse'] = data[i]['warehouse'] || variableOptions[1];
                    data[i]['image'] = productImages[data[i].productID] || productImages[3]; 
                    data[i]['amount'] = "$" + parseFloat(parseFloat(data[i]['price']) * parseFloat(data[i]['quantity'])).toString();
                    data[i]['price'] = "$" + parseFloat(parseFloat(data[i]['price'])).toString();
                    productDatas.rows.push(data[i]); 
                }
            }

            loadProducts();
        },
        contentType: 'application/json'
    });
}

productSaleOrder = {};
function loadProducts() {    
    reCalculateAmount();
    $('#productGrid').datagrid({
        showFooter: true,
        data: productDatas,
        onLoadSuccess: function() {
            resgisterSoList();
            getProductsBySaleOrder(rmaSO).then(function(result) {
                productSaleOrder = result;
            });
            initRemove();
            registerComboboxAction();
            $('.date-box').each(function(){
                 $(this).datebox({
                    required: true,
                    onSelect: function(date){
                        dateBoxProduct[$(this).attr('data-id')] = date.getDate() +"/"+ date.getMonth()+ 1+ "/"+ date.getFullYear();
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
    });
   
}

function resgisterSoList() {
    let data = [];
    let position = 1;
    for (let key in rmaSO) {
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

function addProduct(SOID, position) {
    setTimeout(function(){
        if ( productDatas.rows.find(
                function(element) {
                    return element.productID === productSaleOrder[SOID][position].productID;
                }
            ) === undefined) {
            let newProduct = productSaleOrder[SOID][position];
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

function removeProduct(key) {;
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
    var vaiableOptions = ['CA', 'CB'];
    var options = '<select data-id="' + id + '" class="easyui-combobox" name="dept" style="width:50px;">:selections</select>';
    
    var selections = vaiableOptions.map(function(value) {
        isSumFee[value] = 1;
        if (comboBoxedProduct[id] === value) {
            return '<option selected="selected" value="'+ value +'">' + value + '</option>';
        } else {
            return '<option value="'+ value +'">' + value + '</option>';
        }
    });
    
    return options.replace(':selections', selections);
}


function registerComboboxAction() {
    $(".easyui-combobox").on('change', function(){
        comboBoxedProduct[$(this).attr('data-id')] = $(this).val();
        isSumFee[$(this).val()] = 1;
        
        discount = 20 * Object.keys(isSumFee).length;
        console.log(discount, Object.keys(isSumFee).length, isSumFee);
    });
}

function getEditAmountTemplate(amount) {
    return '$<input type="text" style="width:70px" id="newAmount" value="'+ parseFloat(amount.replace("$", '')) +'" />';
}

function getRemoveTemplate(id) {
    return '<a class="removeUser" onClick="removeProduct('+ id +')" href="javascript:void(0)">'+ id +'</a>';
}

function getDateBoxTemplate(id, value) {
    return '<input class="date-box" style="width: 110px" data-id="'+ id +'" type="text" value="'+ value +'">';
}

function reloadList(isCombobox = true, isReSetup = true) {
    productDatas.rows = productDatas.rows.map( function(product) { 
       if ( isCombobox ) {
            product.warehouse = getComboboxTemplate(product.productID);
            product.no = getRemoveTemplate(product.productID);

       } else {
            product.warehouse = comboBoxedProduct[product.productID];
            product.no = removeTagProduct[product.productID];
       }
       return product;
    });
    
    if ( isReSetup ) {
        if (isCombobox) {
            productDatas.footer[1].warehouse = getEditAmountTemplate(productDatas.footer[1].warehouse);
        }
    }
    
    loadProducts();
}

function reloadShippingAmount(callback) {  
    var sum = "$";
    sum+= parseFloat($("#newAmount").val()).toFixed(2);
    productDatas.footer[1].original_so = sum;
    shippingAmount = $("#newAmount").val();
    callback();
}


function loadServices() {
    var serviceId = null;
    if (serviceId = $.urlParam('rma_id')) {
        loadRMAPayment();
        loadRMAActivity(serviceId);
        getServiceInformation(serviceId);
        getUserInfo();
        getProducts();
    } else {
        loadService();
    }
}

function getServiceInformation(serviceId) {
    var status = {4: 'Refund', 3: 'Partial Received', 2: 'Product Received', 1: 'Product Receiving'};
    $.get({
        url: '/lexor_cs/api/rma/detail/' + serviceId,
        success: function(data) {
            var serviceInformation = $(".case-info__status");
            var text = "RMA {name} - {status}";
            serviceInformation.html(text.replace('{name}', data.RMAID).replace('{status}', status[data.status]));
        },
        contentType: 'application/json'
   });
}

function reCalculateAmount() {
    let amount = 0;
    var pattern = /[^0-9.-]+/g;

    for( i = 0; i < productDatas.rows.length; i++ ) {
        amount += parseFloat(productDatas.rows[i].amount.replace(pattern, ''));
    }
    

    totalAmount = amount;
    total = totalAmount + discount;
    productDatas.footer[0].warehouse = "<strong>$" + totalAmount.toFixed(2) + " </strong>";
    productDatas.footer[1].warehouse = "<strong>$" + discount.toFixed(2) + " </strong>";
    productDatas.footer[2].warehouse = "<strong>$" + total.toFixed(2) + " </strong>";
}

function loadPaymentMethod() {
    $("#paymentMethod").combobox({
        onChange: function(value) {
            paymentMethod = value;
        }
    });
}

function prepareProductToSave() {
    var products = productDatas.rows;

    var productToSave = [];
    for ( let i = 0; i < products.length; i++ ) {
        if (products[i].RMAID === false) {
            products[i].price = parseFloat(products[i].price.replace("$", '')).toFixed(2);
            products[i].RMAID = $.urlParam("rma_id");
            products[i].RMASOID = rmaSO[products[i].originalSo];
            productToSave.push(products[i]);
        }
    }
    
    return productToSave;
}

function prepareProductToEdit() {    
    var products = productDatas.rows;
    var productToSave = [];
    for ( let i = 0; i < products.length; i++ ) {
        if (products[i].serviceDetailID) {
            products[i].amount = parseFloat(products[i].amount.replace("$", '')).toFixed(2);
            products[i].soldPrice = parseFloat(products[i].soldPrice.replace("$", '')).toFixed(2);
            
            productToSave.push(products[i]);
        } 
    };
    
    return productToSave;
}

function removeListProduct() {
    if ( removedProductList ) {
        for ( var product in removedProductList) {
            if (id = removedProductList[product].SODetail_ID) {
                $.ajax({
                    contentType: 'application/json',
                    url: '/lexor_cs/api/rma_soDetail/' + id,
                    type: 'DELETE'
                });
            }
        }
    }
}

function saveProduct() {
    var products = prepareProductToSave();
    
    if ( products ) {
        for ( let i = 0 ; i < products.length; i++ ) {
             $.post({
                type: "POST",
                url: '/lexor_cs/api/rma_soDetail',
                data: JSON.stringify(products[i]),
                contentType: 'application/json'
            });
        }
    }
}

function editProduct() {
    var products = prepareProductToEdit();
    
    if ( products ) {
        for ( let i = 0 ; i < products.length; i++ ) {
            $.ajax({
                contentType: 'application/json',
                url: '/lexor_cs/api/rma_soDetail/' + products[i].SODetail_ID,
                data: JSON.stringify(products[i]),
                type: 'PUT'
            });
        }
    }
}

function createRMASO(soID) {
    $.post({
        type: "POST",
        url: '/lexor_cs/api/rma_so',
        data: JSON.stringify({
            "RMAID": $.urlParam("rma_id"), 
            "SOID" : soID
        }),
        contentType: 'application/json'
   });
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

function updateRMAStatus(status) {
     $.ajax({
        contentType: 'application/json',
        url: '/lexor_cs/api/rma/' + $.urlParam('rma_id'),
        data: JSON.stringify({
            "RMAID": $.urlParam('rma_id'),
            "status" : status,
            "customerSOID": 1,
        }),
        success: function() {
            getServiceInformation($.urlParam('rma_id'));
        },
        type: 'PUT'
    });
}

function registerUpdatePaymentMethod() {
    $("#updatePaymentMethod").click(function(){
        $.ajax({
            type: "PUT",
            url: '/lexor_cs/api/rma_payment/' + $.urlParam('rma_id'),
            data: JSON.stringify({
                paymentType: $("#paymentMethod").val()
            }),
            contentType: 'application/json'
        });
    });
}

function loadRMAPayment() {
    $.ajax({
        type: "GET",
        url: '/lexor_cs/api/rma_payment/detail/' + $.urlParam('rma_id'),
        success: function(serviceMasterRES) {
            rmaPayment = serviceMasterRES;
            $('#paymentMethod').combobox('setValue', rmaPayment.paymentType);
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

function updateTotal() {
    $.ajax({
        type: "PUT",
        url: '/lexor_cs/api/rma_so/' + $.urlParam('rma_id'),
        data: JSON.stringify({
            total: total
        }),
        contentType: 'application/json'
    });
}