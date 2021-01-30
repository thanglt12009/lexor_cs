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

variableOptions = {1 :'CA', 2: 'CB'};
isWarrantyOptions = {1 : "Y", 0: "N"}
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
$(document).ready(function () {
    loadServices();
    getProducts();
    loadPaymentMethod();
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
            title: 'Add Service',
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
    });
    $("#editProducts").on("click", function () {
        $("#addSaleOrder").show();
        $("#saveProducts").show();
        $(this).hide();
    });
    $("#saveProducts").on("click", function () {
        $("#addSaleOrder").hide();
        $("#editProducts").show();
        $(this).hide();
    });
});

function getProducts() {
     $.get({
        url: "/lexor_cs/api/serviceDetail/1/100",
        success: function(data) {
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    data[i]['no'] = data[i]['productID'];
                    data[i]['isWarranty'] = data[i]['isWarranty'] ? isWarrantyOptions[data[i]['isWarranty']] : isWarrantyOptions[1];
                    data[i]['warehouse'] = data[i]['warehouse'] || variableOptions[1];
                    data[i]['image'] = productImages[data[i].productID];
                    data[i]['amount'] = "$" + (parseFloat(data[i]['quantity']) * parseFloat(data[i]['soldPrice'])).toString();
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
    });
}

function loadService() {
    var services = [];

    $.get({
        url: "/lexor_cs/api/case_service/",
        success: function(data) {
            let tableData = [];
            if ( data ) {
                for ( i = 0; i < data.length; i++ ) {
                    services.push({
                        service_number: data[i].docCode,
                        original_case: data[i].status,
                        customer_name: data[i].status,
                        mobile_phone: data[i].address,
                        business_phone : data[i].address,
                        address : data[i].address,
                        action: '<a href="javascript:void(0)" onClick="openService()" class="easyui-linkbutton">Select</a>'
                    }); 
                }

                var serviceGrid = $("#serviceGrid").datagrid({
                    data: tableData,
                    
                    onLoadSuccess: function() {
                        $(".easyui-linkbutton").linkbutton();
                    }
                });
                serviceGrid.datagrid('enableCellEditing');
            }
        },
        contentType: 'application/json'
    });
}

function openService() {
    window.location.href = "/lexor_cs/pages/cs/purchaseorder/service.html"
}

function refreshProduct() {
    $('#productGrid').datagrid('data', productDatas);
}

function loadProducts() {
    shippingDetail = [];
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
    });
}

function addProduct() {
    var id = productDatas.rows.length + 1;
    productDatas.rows.push({
        productId: id,
        no: id,
        image: "<img width='60px' height='60px' src='../../../images/product02.jpg' />", 
        product: "Product 003 </br> Serial Number: 123-456-789",
        quantity: "1",
        sold_price: "$1,000.00",
        amount: "$2,000.00",
        stock_avaiable: "20",
        total_weight: "1000",
        ware_house: "CA",
        shiping_day: "25/01/2021",
        original_so: "001",
        service_for_product: "Service for Product 003",
        under_warranty: "Y"
    });
    comboBoxedProduct[id] = "CA";
    removeTagProduct[id] = id;
    loadProducts();
    initRemove();
}

function removeProduct(key) {;
    productDatas.rows = productDatas.rows.filter((item) => {return item.productID != key});
    loadProducts();
    initRemove();
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
    return '<a class="removeUser" onClick="removeProduct('+ id +')" href="javascript:void(0)">'+ id +'</a>';
}

function getDateBoxTemplate(id, value) {
    return '<input class="date-box" style="width: 110px" data-id="'+ id +'" type="text" value="'+ value +'">';
}

function reloadList(isCombobox = true) {
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
    
    if (isCombobox) {
        productDatas.footer[1].originalSo = getEditAmountTemplate(productDatas.footer[1].originalSo);
    } else {
        productDatas.footer[1].originalSo = shippingAmount;
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
    if (serviceId = $.urlParam('service_id')) {
        getServiceInformation(serviceId);
        getUserInfo();
    } else {
        loadService();
    }
}

function getServiceInformation(serviceId) {
    var status = { 2: 'Closed', 1: "Processing"};
    $.get({
        url: '/lexor_cs/api/case_service/' + serviceId,
        success: function(data) {
            var serviceInformation = $(".service-info__status");
            serviceInformation.html(serviceInformation.text().replace('{name}', data.serviceID).replace('{status}', status[1]));
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

$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results === null) {
       return null;
    }
    return decodeURI(results[1]) || 0;
};