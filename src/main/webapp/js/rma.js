productDatas = {
    "total" : 2,
    "footer":[
	{"sold_price":'<strong>Subtotal</strong>',"amout":"$3000.00"},
        {"sold_price":'<strong>Restocking Fee</strong>',"amout":'<strong>-$1000.00</strong>'},
        {"sold_price":'<strong>Total</strong>',"amout":'<strong>$2000.00</strong>'},
    ],
    "rows":[
        {
            id : 1,
            no: '<a class="removeUser" onClick="removeProduct(1)" href="javascript:void(0)">1</a>',
            image: "<img width='60px' height='60px' src='../../../images/product01.jpg' />", 
            product: "Product 01",
            quantity: "1",
            sold_price: "$2,095.00",
            amout: "$2,095.00",
            stock_avaiable: "20",
            total_weight: "YES/NO",
        },
        {
            id : 2,
            no: '<a class="removeUser" onClick="removeProduct(2)" href="javascript:void(0)">2</a>',
            image: "<img width='60px' height='60px' src='../../../images/product02.jpg' />", 
            product: "Product 02",
            quantity: "2",
            sold_price: "$1,000.00",
            amout: "$2,000.00",
            stock_avaiable: "20",
            total_weight: "YES/NO",
        }
    ]
    
};

servicesData = [{
    product_name: "Product 003 </br> Serial Number: 123-456-789",
    under_warranty: "Y",
    warranty_issue: "25/01/2021",
    warranty_expire: "25/01/2021",
    action: '<a href="javascript::void(0)" onClick="addProduct()" class="easyui-linkbutton">Add</a>'
}];

shippingDatas = {
    "total" : 1,
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
	{"shipping_type":'<strong>Shipping Discount</strong>',"shipping_cost":"$<input style='width:100px' value='5.00' />"},
        {"shipping_type":'<strong>Shipping Fee</strong>',"shipping_cost":'<strong>$5.00</strong>'},
    ]
};

$(document).ready(function () {
    loadService();
    loadProducts();
    loadShipping();
    $("#editProducts").on("click", function(){
       initRemove();
    });
    
    $("#saveProducts").on("click", function(){
       loadProducts();
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

function submitSalonForm() {
    $('#editSalonDialog').window('close');
}

function loadShipping() {
    $('#shippingGrid').datagrid({
        data: shippingDatas,
        showFooter: true,
        onLoadSuccess: function() {
            $('#shippingOrder').combobox({
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
    services[0] = {
        service_number: 1,
        original_case: "Case 1", 
        customer_name: "Customer 01",
        mobile_phone: "0999-999-999",
        business_phone: "083-999-9999",
        address: "Customer Address 01",
        action: '<a href="javascript::void(0)" onClick="openService()" class="easyui-linkbutton">Select</a>'
    };
    
    $('#serviceGrid').datagrid({
        data: services,
        onLoadSuccess: function() {
            $(".easyui-linkbutton").linkbutton();
        }
    });
}
function openService() {
    window.location.href = "/lexor_cs/pages/cs/purchaseorder/rma.html"
}

function loadProducts() {
    var products = productDatas;
    
    $('#productGrid').datagrid({
        showFooter: true,
        data: products,
        onLoadSuccess: function() {
        }
    });
}

function addProduct() {
    var id = productDatas.rows.length + 1;
    productDatas.rows.push({
        id: id,
        no: '<a class="removeUser" onClick="removeProduct('+ id +')" href="javascript:void(0)">'+ id +'</a>',
        image: "<img width='60px' height='60px' src='../../../images/product02.jpg' />", 
        product: "Product 003 </br> Serial Number: 123-456-789",
        quantity: "1",
        sold_price: "$1,000.00",
        amout: "$2,000.00",
        stock_avaiable: "20",
        total_weight: "YES/NO"
    });
    loadProducts();
    initRemove();
}

function removeProduct(key) {;
    productDatas.rows = productDatas.rows.filter((item) => {return item.id != key});
    loadProducts();
    initRemove();
}

function initRemove() {
    $('.removeUser').linkbutton({
        iconCls: 'icon-remove',
        text: ''
    }); 
}