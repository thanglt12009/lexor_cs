productDatas = {
    "total" : 2,
    "footer":[
	{"shipingDay":'<strong>Subtotal</strong>', "originalSo":"$3000.00"},
	{"shipingDay":'<strong>Shipping Fee</strong>',"originalSo":"$0.00"},
        {"shipingDay":'<strong>Manager Discount</strong>',"originalSo":'<strong>-$1000.00</strong>'},
        {"shipingDay":'<strong>Total</strong>',"originalSo":'<strong> $0.00</strong>'}
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

serviceStatus = { 1: 'Closed', 0: "Processing", 2: "Submitted To Production" };
variableOptions = {1 :'CA', 2: 'CB'};
isWarrantyOptions = {1 : "Y", 0: "N"};
productImages = {
    1: "<img width='60px' height='60px' src='../../../images/product01.jpg' />",
    2: "<img width='60px' height='60px' src='../../../images/product02.jpg' />",
    3: "<img width='60px' height='60px' src='../../../images/product03.jpg' />"
};
paymentMethod = 1;
comboBoxedProduct = {};
dateBoxProduct = {};
removeTagProduct = {};
shippingDetail = {};
comboBoxedProduct[1] = "CA";
removeTagProduct[1] = 1;
comboBoxedProduct[2] = "CB";
removeTagProduct[2] = 2;
dateBoxProduct[1] = "25/01/2021";
dateBoxProduct[2] = "25/01/2021";

discount = 1000;
total = 0;
totalAmount = 0;
shippingAmount = 0;
oldProduct = {};
var removedProductList = [];
var serviceMasterID = 0;
var caseServiceID = 0;
$(document).ready(function () {
    loadServices();
    loadPaymentMethod();
    getServiceSO();
    $("#editProducts").on("click", function(){
       reloadList();
    });
    
    $(".editServiceStatus").on("click", function(){
        updateServiceStatus($(this).attr('data-id'), 1);
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
        
        Promise.all([ 
            saveProduct(),
            editProduct(),
            removeListProduct()
        ]).then(function() {
            getProducts();
        });       
        $(this).hide();
    });
});

function saveProduct() {
    var products = prepareProductToSave();
    
    if ( products ) {
        for ( let i = 0 ; i < products.length; i++ ) {
             $.post({
                type: "POST",
                url: '/lexor_cs/api/serviceDetail',
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
                url: '/lexor_cs/api/serviceDetail/' + products[i].serviceDetailID,
                data: JSON.stringify(products[i]),
                type: 'PUT'
            });
        }
    }
}

function removeListProduct() {
    if ( removedProductList ) {
        for ( var product in removedProductList) {
            if (id = removedProductList[product].serviceDetailID) {
                $.ajax({
                    contentType: 'application/json',
                    url: '/lexor_cs/api/serviceDetail/' + id,
                    type: 'DELETE'
                });
            }
        }
    }
}

function prepareProductToSave() {
    var products = productDatas.rows;

    var productToSave = [];
    for ( let i = 0; i < products.length; i++ ) {
        if (products[i].serviceMasterID === false) {
            products[i].amount = parseFloat(products[i].amount.replace("$", '')).toFixed(2);
            products[i].soldPrice = parseFloat(products[i].soldPrice.replace("$", '')).toFixed(2);
            products[i].serviceMasterID = serviceMasterID;
            
            delete products[i].warehouse; 
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
            
            delete products[i].warehouse; 
            productToSave.push(products[i]);
        } 
    };
    
    return productToSave;
}

function getProducts() {
     $.get({
        url: "/lexor_cs/api/serviceDetail/" + serviceMasterID + '/' + serviceMasterID,
        success: function(data) {
            productDatas.rows = [];
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    serviceMasterID = data[i]['serviceMasterID'];
                    data[i]['no'] = data[i]['productID'];
                    data[i]['isWarranty'] = data[i]['isWarranty'] ? isWarrantyOptions[data[i]['isWarranty']] : isWarrantyOptions[1];
                    data[i]['warehouse'] = data[i]['warehouse'] || variableOptions[1];
                    data[i]['image'] = productImages[data[i].productID] || productImages[3];
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

function loadShipping(shippingDetail = {}) {
    var datas = shippingDatas;
    if (shippingDetail) {
        datas.rows = [];
        for (const shipping in shippingDetail) {
            datas.rows.push(shippingDetail[shipping]);
        }
    }
    $('#shippingGrid').datagrid({
        pagination:true,
        pageSize:20,
        showFooter: true,
        data: datas,
        showFooter: true,
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

function loadService() {
    var services = [];

    $.get({
        url: "/lexor_cs/api/case_service/find/1",
        success: function(data) {
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    services.push({
                        service_number: data[i].caseServiceID,
                        original_case: data[i].caseID,
                        customer_name: data[i].customerSOID,
                        action: '<a href="javascript:void(0)" onClick="openService( '+ data[i].caseServiceID +')" class="easyui-linkbutton">Select</a>'
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

function openService(serviceId) {
    window.location.href = "/lexor_cs/pages/cs/purchaseorder/service.html?user_id=1&service_id=" + serviceId;
}

function refreshProduct() {
    $('#productGrid').datagrid('data', productDatas).datagrid('clientPaging');
}

function loadProducts() {
    shippingDetail = [];
    productDatas.total = productDatas.rows.length;
    for(i = 0 ; i < productDatas.rows.length; i++) {
        var wareHouse = productDatas.rows[i].warehouse;
        var shipDate = productDatas.rows[i].shipingDay;        
        var key = wareHouse + shipDate;
        var weight = 0;
        var id = i + 1;
        if (shippingDetail[key]) {
            weight = shippingDetail[key]['shipping_weight'] + parseInt(productDatas.rows[i].totalWeight);
            id = shippingDetail[key]['no'];
        } else {
            weight = parseInt(productDatas.rows[i].totalWeight);
        }

        shippingDetail[key] = {
            no : id,
            warehouse : wareHouse,
            shipping_type: '<input class="shippingOrder" name="shippingOrder" value="">',
            shipping_cost: "$0",
            shipping_weight: weight,
            shipping_day: shipDate
        };
    }
    
    reCalculateAmount();
    $('#productGrid').datagrid({
        pagination:true,
        pageSize:20,
        showFooter: true,
        data: productDatas,
        onLoadSuccess: function() {
            loadShipping(shippingDetail);
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
    }).datagrid('clientPaging');
}

function addProduct() {
    var id = Math.floor(Math.random() * 1000) + 1;
    var newProduct = {
        productID: id,
        no: id,
        image: "<img width='60px' height='60px' src='../../../images/product02.jpg' />", 
        serialNumber: "Product 003 </br> Serial Number: 123-456-789",
        quantity: "1",
        soldPrice: "$1000.00",
        amount: "$2000.00",
        stockAvaiable: "20",
        totalWeight: "1000",
        wareHouse: "CA",
        shipingDay: "25/01/2021",
        originalSo: "001",
        serviceForProduct: "Service for Product 003",
        isWarranty: isWarrantyOptions[1],
        serviceMasterID: false
    };

    if ( productDatas.rows.find(
            function(element) {
                return element.productId === id;
            }
        ) === undefined) {
        productDatas.rows.push(newProduct);
    }
    
    comboBoxedProduct[id] = "CA";
    removeTagProduct[id] = id;
    reloadShippingAmount(function(){
        reloadList(true, false);
    });
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
    return '$<input type="text" style="width:70px" id="newAmount" value="'+ parseFloat(amount.replace("$", '')) +'" />';
}

function getRemoveTemplate(id) {
    return '<a class="removeUser" onClick="removeProduct('+ id +')" href="javascript:void(0);">'+ id +'</a>';
}

function getDateBoxTemplate(id, value) {
    return '<input class="date-box" style="width: 110px" data-id="'+ id +'" type="text" value="'+ value +'">';
}

function reloadList(isCombobox = true, isReSetup = true) {
    productDatas.rows = productDatas.rows.map( function(product) { 
       if ( isCombobox ) {
            product.warehouse = getComboboxTemplate(product.productID);
            product.no = getRemoveTemplate(product.productID);
            product.shipingDay = getDateBoxTemplate(product.productID, product.shipingDay);

       } else {
            product.warehouse = comboBoxedProduct[product.productID];
            product.no = removeTagProduct[product.productID];
            product.shipingDay = dateBoxProduct[product.productID];
       }
       
       return product;
    });
    
    if ( isReSetup ) {
        if (isCombobox) {
            productDatas.footer[1].originalSo = getEditAmountTemplate(productDatas.footer[1].originalSo);
        } else {
            productDatas.footer[1].originalSo = shippingAmount;
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

function updateServiceStatus(status, isSubmit = 1) {
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
    } else {
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
                createMasterProduct( data.caseServiceID);
            }
            
            getProducts();
            serviceInformation.html(text.replace('{name}', data.caseServiceID).replace('{status}', serviceStatus[data.status]));
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
    total = totalAmount - discount + parseFloat(shippingAmount);
    productDatas.footer[0].originalSo = "<strong>$" + totalAmount.toFixed(2) + " </strong>";
    productDatas.footer[3].originalSo = "<strong>$" + total.toFixed(2) + " </strong>";
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
        success: function (serviceSO) {console.log(serviceSO)
           if ( serviceSO ) {
                const listSO = {};
                for ( let i = 0 ; i < serviceSO.length; i ++) {
                   listSO[serviceSO[i].customerSOID] = serviceSO[i].customerSOID;
                }
                getProductsBySaleOrder(listSO).then(function(result) {
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