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
variableOptions = {1 :'CA', 2: 'CB'};
productImages = {
    1: "<img width='60px' height='60px' src='../../../images/product01.jpg' />",
    2: "<img width='60px' height='60px' src='../../../images/product02.jpg' />",
    3: "<img width='60px' height='60px' src='../../../images/product03.jpg' />"
};
paymentMethod = 1;
var removedProductList = [];
$(document).ready(function () {
    loadServices();
    getProducts();
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
                $('#serviceGrid').datagrid({
                    data: servicesData,
                    onLoadSuccess: function() {
                        $(".easyui-linkbutton").linkbutton();
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
            createRMAActivity($.urlParam('rma_id'), "Edit product")
        ]).then(function() {
            getProducts();
            loadRMAActivity($.urlParam('rma_id'));
        }); 
        
        $(this).hide();
    });
});

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
                        action: '<a href="javascript:void(0)" onClick="openService('+data[i].RMAID+')" class="easyui-linkbutton">Select</a>'
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

function openService(RMAID) {
    window.location.href = "/lexor_cs/pages/cs/purchaseorder/rma.html?rma_id="+RMAID+"&user_id=1";
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
                    data[i]['quantity'] = 1;
                    data[i]['reveiver'] = data[i]['reveiver'] ? isWarrantyOptions[data[i]['reveiver']] : isWarrantyOptions[1];
                    data[i]['warehouse'] = data[i]['warehouse'] || variableOptions[1];
                    data[i]['image'] = productImages[data[i].productID] || productImages[3];
                    data[i]['price'] = "$" + parseFloat(parseFloat(data[i]['price'])).toString();
                    productDatas.rows.push(data[i]); 
                }
            }

            loadProducts();
        },
        contentType: 'application/json'
    });
}

function loadProducts() {    
    reCalculateAmount();
    $('#productGrid').datagrid({
        showFooter: true,
        data: productDatas,
        onLoadSuccess: function() {
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

function addProduct() {
    var id = Math.floor(Math.random() * 1000) + 1;
    var newProduct = {
        productID: id,
        no: id,
        image: "<img width='60px' height='60px' src='../../../images/product02.jpg' />", 
        product: "Product 003 </br> Serial Number: 123-456-789",
        quantity: "1",
        soldPrice: "$1,000.00",
        price: "$2,000.00",
        stockAvaiable: "20",
        totalWeight: "1000",
        warehouse: "CA",
        shiping_day: "25/01/2021",
        original_so: "001",
        service_for_product: "Service for Product 003",
        receiver: "Y",
        RMAID: false
    };
    
    if ( productDatas.rows.find(
            function(element) {
                return element.productId === id;
            }
        ) === undefined) {
        productDatas.rows.push(newProduct);
    }
    
    comboBoxedProduct[id] = "Y";
    removeTagProduct[id] = id;
    reloadShippingAmount(function(){
        reloadList(true, false);
    });
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
    var vaiableOptions = ['Y', 'N'];
    var options = '<select data-id="' + id + '" class="easyui-combobox" name="dept" style="width:50px;">:selections</select>';
    
    var selections = vaiableOptions.map(function(value) {
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
            product.receiver = getComboboxTemplate(product.productID);
            product.no = getRemoveTemplate(product.productID);

       } else {
            product.receiver = comboBoxedProduct[product.productID];
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
        loadRMAActivity(serviceId);
        getServiceInformation(serviceId);
        getUserInfo();
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
        console.log(productDatas.rows[i].price, productDatas.rows[i].price.replace(pattern, ''));
        amount += parseFloat(productDatas.rows[i].price.replace(pattern, ''));
    }
    

    totalAmount = amount;
    total = totalAmount - discount;
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
            products[i].SOID = 1;
            products[i].RMASOID = rmaSO[1];
           
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

$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results === null) {
       return null;
    }
    return decodeURI(results[1]) || 0;
};